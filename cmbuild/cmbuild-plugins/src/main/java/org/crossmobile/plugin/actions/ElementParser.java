/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.crossmobile.plugin.actions;

import crossmobile.rt.StrongReference;
import org.crossmobile.bridge.ann.*;
import org.crossmobile.plugin.model.*;
import org.crossmobile.plugin.parser.AnnotationParser;
import org.crossmobile.plugin.utils.Collectors;
import org.crossmobile.plugin.utils.Factories;
import org.crossmobile.utils.Log;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.crossmobile.plugin.actions.FormerTests.testConstructorStruct;
import static org.crossmobile.plugin.actions.FormerTests.testMissingJoin;
import static org.crossmobile.plugin.model.MethodType.*;
import static org.crossmobile.plugin.reg.TypeRegistry.*;
import static org.crossmobile.plugin.utils.Collectors.getListOfTypes;
import static org.crossmobile.plugin.utils.Texters.annName;
import static org.crossmobile.plugin.utils.Texters.typesafeClassName;
import static org.crossmobile.utils.GenericsUtils.getTypeArgument;
import static org.crossmobile.utils.NamingUtils.*;
import static org.crossmobile.utils.ReflectionUtils.*;
import static org.crossmobile.utils.TextUtils.*;

public class ElementParser {

    public static boolean parseConstructor(NObject nobj, Constructor cs, CMConstructor constr) {
        NSelector sel;
        String value = constr.value().trim();
        sel = value.startsWith("+") || value.startsWith("-")
                ? AnnotationParser.parseSelector(constr.value())
                : AnnotationParser.parseFunction(constr.value());
        if (sel == null) {
            Log.error("Unable to parse constructor " + execSignature(cs) + " using  native code: " + constr.value());
            return false;
        }
        sel.setConstructor(true);
        if (isStruct(nobj.getType()) && !testConstructorStruct(cs))
            return false;
        return parseSelector(nobj, sel, cs, nobj.getType());
    }

    public static boolean parseMethod(NObject nobj, Method m, boolean forceStatic, String nativeCode, String sizeResolver, String swiftVarArgMethod) {
        NSelector sel = AnnotationParser.parseSelector(nativeCode);
        if (sel == null) {
            Log.error("Unable to parse selector for method " + execSignature(m) + " using native code: " + nativeCode);
            return false;
        } else {
            sel.getReturnType().setSizeResolver(sizeResolver);
            sel.setSwiftMethod(swiftVarArgMethod);
            return parseSelector(nobj, sel, m, m.getReturnType(), forceStatic ? StaticMappingType.JAVA : StaticMappingType.NONE);
        }
    }

    public static boolean parseGetter(NObject nobj, Method m, String nativeCode, String sizeResolver) {
        NProperty get = AnnotationParser.parseProperty(nativeCode);
        if (get == null) {
            Log.error("Unable to parse getter for method " + execSignature(m) + " using native code: " + nativeCode);
            return false;
        }
        NSelector sel = Factories.getGetterSelector(get);
        sel.getReturnType().setSizeResolver(sizeResolver);
        return parseSelector(nobj, sel, m, m.getReturnType());
    }

    public static boolean parseSetter(NObject nobj, Method m, String nativeCode) {
        NProperty set = AnnotationParser.parseProperty(nativeCode);
        if (set == null) {
            Log.error("Unable to parse setter for method " + execSignature(m) + " using native code: " + nativeCode);
            return false;
        }
        if (set.isReadonly()) {
            Log.error("Property " + set.getName() + " is a readonly method and not compatible with `" + execSignature(m) + "` ; native code provided: " + nativeCode);
            return false;
        }
        return parseSelector(nobj, Factories.getSetterSelector(set), m, m.getReturnType());
    }

    public static boolean parseFunc(NObject nobj, Method m, String nativeCode, String sizeResolver, String swiftVarArgMethod) {
        NSelector func = AnnotationParser.parseFunction(nativeCode);
        StaticMappingType staticMapping = StaticMappingType.NONE;
        if (func == null) {
            Log.error("Unable to parse function for method " + execSignature(m) + " using native code: " + nativeCode);
            return false;
        } else if (!func.getParams().isEmpty()) {
            func.getReturnType().setSizeResolver(sizeResolver);
            func.setSwiftMethod(swiftVarArgMethod);
            Class firstParam = getReferencedJavaClass(func.getParams().get(0).getNType().getType());
            if (isAssignableFrom(firstParam, nobj.getType(), null))
                staticMapping = StaticMappingType.NATIVE;
        }
        return parseSelector(nobj, func, m, m.getReturnType(), staticMapping);
    }

    public static boolean parseBlock(NObject nobj, Method m, String nativeCode) {
        NSelector block = AnnotationParser.parseBlock(nativeCode);
        if (!nobj.isTarget()) {
            Log.error("Block method " + execSignature(m) + " can only be defined inside target objects, please consider annotating " + getClassNameFull(nobj.getType()) + " with annotation " + annName(CMTarget.class) + " ; native code provided: " + nativeCode);
            return false;
        }
        if (!nobj.getType().isInterface()) {
            Log.error("Block method " + execSignature(m) + " can only be defined in interfaces, please consider changing type of " + getClassNameFull(nobj.getType()) + " to interface ; native code provided: " + nativeCode);
            return false;
        }
        if (getLambdaMethod(nobj.getType()) == null) {
            Log.error("Block method " + execSignature(m) + " should be the only method of the interface ; native code provided: " + nativeCode);
            return false;
        }
        if (!block.getName().isEmpty() && !block.getName().equals(getClassNameSimple(m.getDeclaringClass()))) {
            Log.error("Target class " + getClassNameFull(m.getDeclaringClass()) + " should be named " + block.getName() + " according to the block declaration: " + nativeCode);
            return false;
        }
        block.setName(m.getName()); // replace block name with java method name, to avoid conficts
        return parseSelector(nobj, block, m, m.getReturnType());
    }

    private static boolean parseSelector(NObject container, NSelector selector, Executable exec, Class returnType) {
        return parseSelector(container, selector, exec, returnType, StaticMappingType.NONE);
    }

    private static boolean parseSelector(NObject container, NSelector selector, Executable exec, Class returnType, StaticMappingType staticMapping) {

        if (Modifier.isStatic(exec.getModifiers()) != selector.isStatic()) {
            if (selector.isConstructor()) {
                // Check that the return type of a static "constructor" is correct
                Class baseType = selector.getReturnType().getType();
                Class javaType = getReferencedJavaClass(exec.getDeclaringClass());
                if (!isAssignableFrom(baseType, javaType, "Static native constructor `" + selector.getOriginalCode() + "` for object " + getClassNameFull(exec.getDeclaringClass()) + " is not compatible with return type " + getClassNameFull(baseType) + " of constructor " + execSignature(exec)))
                    return false;
            } else if (staticMapping == StaticMappingType.NATIVE) {
                // Native static mapping already checked
            } else if (!Modifier.isStatic(exec.getModifiers()) && staticMapping != StaticMappingType.NONE) {
                Log.error(capitalize(selector.getFamily()) + " `" + execSignature(exec) + "` is not static, while annotation defined a static mapping ; native code provided: " + selector.getOriginalCode());
                return false;
            } else if (selector.isStatic() || staticMapping != StaticMappingType.JAVA) {
                Log.error(capitalize(selector.getFamily()) + " `" + execSignature(exec) + "` is " + (Modifier.isStatic(exec.getModifiers()) ? "" : "not ") + "static, while native code `" + selector.getOriginalCode() + "` is " + (selector.isStatic() ? "" : "not ") + "static.");
                return false;
            }
        } else if (!Modifier.isStatic(exec.getModifiers()) && staticMapping == StaticMappingType.JAVA) {
            Log.error(capitalize(selector.getFamily()) + " `" + execSignature(exec) + "` is not static, while annotation defined a static mapping ; native code provided: " + selector.getOriginalCode());
            return false;
        }
        if ((selector = Factories.joinParams(selector, exec)) == null)
            return false;

        // Calculate class types, also based on converters
        List<NParam> sParams = Collectors.getJoinedParams(selector);
        List<Class> mParams = new ArrayList<>();
        List<ParamMod> mMods = new ArrayList<>();
        int pidx = 0;
        for (Parameter p : exec.getParameters()) {
            CMParamMod pmod = p.getAnnotation(CMParamMod.class);
            if (pmod != null && pmod.byRef() && !isStruct(p.getType())) {
                Log.error("Parameter at position #" + pidx + " in " + selector.getFamily() + " `" + execSignature(exec) + "` is marked as reference with " + annName(CMParamMod.class) + ", but it is not a struct.");
                return false;
            }
            ParamMod mod = new ParamMod(pmod, p);
            mParams.add(mod.type);
            mMods.add(mod);
            pidx++;
        }

        if (staticMapping == StaticMappingType.JAVA && selector.getReturnType().getNativeType().equals("instancetype")
                && !selector.isStatic() && Modifier.isStatic(exec.getModifiers()))
            selector.setFakeConstructor(true);

        if (staticMapping != StaticMappingType.NONE && mParams.size() != sParams.size() && !selector.isFakeConstructor()) {
            List<Class> wparam = new ArrayList<>(staticMapping == StaticMappingType.JAVA ? mParams : getListOfTypes(sParams));
            if (!wparam.isEmpty()) {
                Class baseType = wparam.remove(0);
                Class javaType = getReferencedJavaClass(exec.getDeclaringClass());
                if (!isAssignableFrom(baseType, javaType, "Static mapped parameter at position #1 in " + selector.getFamily() + " `" + execSignature(exec) + "` does not match native code `" + selector.getOriginalCode() + "`, " + typesafeClassName(baseType) + " should be a superclass of " + typesafeClassName(javaType)))
                    return false;
                if (staticMapping == StaticMappingType.JAVA) {
                    ParamMod mod = mMods.get(0);
                    NParam param = new NParam();
                    param.setContainer(selector);
                    param.setJavaParameter(mod.param);
                    param.setTransferName(mod.transferName);
                    param.setNType(new NType("id", mod.type));
                    param.setVarname("this");
                    selector.getParams().add(0, param);
                    mParams = wparam;
                    mMods.remove(0);
                } else {
                    selector.getParams().get(0).setJavaParameter(createParam(null, Modifier.PRIVATE, createMethod(exec.getDeclaringClass(), selector.getName() + "_gen", new Class[]{exec.getDeclaringClass()}, void.class), 0));
                    sParams.remove(0);
                }
                selector.getParams().get(0).setStaticMapping(staticMapping);
            }
        }

        // Fix silent varargs, where last option is Object[]
        if (mParams.size() == (sParams.size() + 1) && !sParams.isEmpty() &&
                sParams.get(sParams.size() - 1).getNType().getVarargType() != null &&
                !mParams.get(mParams.size() - 2).isArray() && mParams.get(mParams.size() - 1).isArray() &&
                (selector.getMethodType() == FUNCTION || selector.getMethodType() == SELECTOR)) {

            if (selector.getSwiftVarArgMethod().trim().isEmpty()) {
                Log.error("A vararg argument has been suspected for " + selector.getFamily() + " `" + execSignature(exec) + "` but no swiftVarArgMethod parameter has been defined on the " + annName(CMFunction.class) + "/" + annName(CMSelector.class) + " annotation.");
                return false;
            }
            if (!trimAll(selector.getSwiftVarArgMethod().toLowerCase()).endsWith("va_array)")) {
                Log.error("A vararg argument has been defined for " + selector.getFamily() + " `" + execSignature(exec) + "`, but no va_list Swift method provided, with the va_list argument named `va_array`");
                return false;
            }

            NParam old = sParams.get(sParams.size() - 1);
            NType base = new NType(old.getNType().getNativeType(), old.getNType().getType().getComponentType());
            NType prod = new NType("void *", Object[].class);   // Change it into Object.class if you want to support any kind of arrays
            prod.setVarArgType(selector.getMethodType() == FUNCTION ? VarargType.CVIRTUAL : VarargType.OBJCVIRTUAL);

            NParam baseParam = new NParam();
            baseParam.setName(old.getName());
            baseParam.setVarname(old.getVarname());
            baseParam.setNType(base);
            sParams.set(sParams.size() - 1, baseParam);

            NParam prodParam = new NParam();
            prodParam.setVarname("va_array");
            prodParam.setNType(prod);
            sParams.add(prodParam);

            selector.removeLastParam();
            selector.addParam(baseParam);
            selector.addParam(prodParam);
            selector.setMethodType(selector.getMethodType() == SELECTOR ? VA_SELECTOR : VA_FUNCTION);
        } else if (!selector.getSwiftVarArgMethod().isEmpty()) {
            Log.error("A vararg Swift method has been defined for method that does not require it: " + execSignature(exec));
            return false;
        }

        if (mParams.size() != sParams.size())   // after parameter fix
            if (selector.isGetter() || selector.isSetter()) {
                Log.error(capitalize(selector.getFamily()) + " `" + execSignature(exec) + "` should have " + sParams.size() + " parameter" + plural(sParams.size()) + ", " + mParams.size() + " found for native code " + selector.getOriginalCode());
                return false;
            } else if (testMissingJoin(selector, exec)) {
                Log.error("Probably a missing " + annName(CMJoinSEL.class) + ": " + selector.getFamily() + " `" + execSignature(exec) + "` should have " + sParams.size() + " parameter" + plural(sParams.size()) + ", " + mParams.size() + " found for native code " + selector.getOriginalCode());
                return false;
            } else {
                Log.error(capitalize(selector.getFamily()) + " `" + execSignature(exec) + "` should have " + sParams.size() + " parameter" + plural(sParams.size()) + ", " + mParams.size() + " found for native code " + selector.getOriginalCode());
                return false;
            }

        for (int i = 0; i < mParams.size(); i++) {
            NParam sParam = sParams.get(i);
            NType sType = sParam.getNType();
            Class sClass = sType.getType();
            Class mClass = mParams.get(i);
            ParamMod mMod = mMods.get(i);

            if (sType.countReferences() > 1 && !mMod.reference) {
                Log.error("Parameter at position #" + i + " in " + selector.getFamily() + " `" + execSignature(exec) + "` should be a reference, as declared in `" + sType.getNativeType() + "`");
            } else if (mMod.reference && sType.countReferences() < (1 + (isStruct(mClass) ? 0 : 1))) {
                Log.error("Parameter at position #" + i + " in " + selector.getFamily() + " `" + execSignature(exec) + "` is declared a reference, but natively is declared as `" + sType.getNativeType() + "'");
            }

            if (mMod.reference)
                if (sClass.isArray())
                    sClass = sClass.getComponentType();
                else {
                    mClass = getTypeArgument(mMod.param);
                    if (mClass == null) {
                        Log.error("Needs generic annotation for parameter " + getClassNameFull(StrongReference.class) + " at position #" + (i + 1) + " in " + selector.getFamily() + " `" + execSignature(exec) + "`");
                        return false;
                    }
                }

            if (mMod.transferName && i == 0)
                Log.error("Parameter at position #0 in " + selector.getFamily() + " `" + execSignature(exec) + "` is marked to transfer name through annotation " + annName(CMParamMod.class) + ", but it is the first parameter of the selector.");

            if (!isAssignableFrom(sClass, mClass, "Parameter at position #" + (i + 1) + " in " + selector.getFamily() + " `" + execSignature(exec) + "` does not match native `" + selector.getOriginalCode() + "`, " + typesafeClassName(sClass) + " should be a superclass of " + typesafeClassName(mClass)))
                return false;

            if (mMod.association.needsAccosiation() && sClass.isPrimitive()) {
                Log.error("Parameter at position #" + (i + 1) + " in " + selector.getFamily() + " `" + execSignature(exec) + "` should be an object reference in order to be associatable, " + getClassNameFull(selector.getReturnType().getType()) + " found instead.");
                return false;
            }
            if (mMod.association == AssociationType.DEFAULT && selector.isSetter() && selector.getProperty().isWeak() && !sClass.isPrimitive() && !isStruct(sClass))
                mMod.association = AssociationType.ASSOCIATE;
            sParam.setAssociation(mMod.association);

            if (mMod.shouldNotBeNull && !selector.getReturnType().getType().equals(void.class)) {
                Log.error("Parameter at position #" + (i + 1) + " in " + selector.getFamily() + " `" + execSignature(exec) + "` should be void, " + getClassNameFull(selector.getReturnType().getType()) + " found instead.");
                return false;
            }
            sParam.setShouldNotBeNull(mMod.shouldNotBeNull);
            sParam.setTransferName(mMod.transferName);
            sParam.setJavaParameter(mMod.param);
            sType.setTypeConverter(mMod.code);
        }
        if (returnType.isArray() && selector.getReturnType().getSizeResolver().isEmpty()) {
            Log.error("For methods that return arrays, it is required to define a size resolve hint with the appropriate annotation (" + annName(CMSelector.class) + ", " + annName(CMGetter.class) + ", " + annName(CMFunction.class) + "); affecting method is " + execSignature(exec));
            return false;
        }
        if (container.isStruct() && (selector.isGetter() || selector.isSetter())) {
            // Bind selectors with struct references
            boolean found = false;
            for (NStructField field : container.getStructFields())
                if (field.getter.equals(exec) || field.setter.equals(exec)) {
                    selector.setStructRef(field);
                    found = true;
                    break;
                }
            if (!found) {
                Log.error("Inconsistency error: unable to locate corresponding struct field for selector " + execSignature(exec));
                return false;
            }
        }

        if (!isAssignableFrom(selector.getReturnType().getType(), returnType, "Return type of `" + execSignature(exec) + "` is invalid, " + typesafeClassName(selector.getReturnType().getType()) + " should be a superclass of " + typesafeClassName(returnType) + " ; native code provided: " + selector.getOriginalCode()))
            return false;
        selector.setJavaExecutable(exec, returnType);
        container.addSelector(selector);
        return true;
    }

    private static final class ParamMod {

        private final Class type;
        private final String code;
        private final Parameter param;
        private final boolean reference;
        private AssociationType association;
        private final boolean shouldNotBeNull;
        private final boolean transferName;

        public ParamMod(CMParamMod converter, Parameter p) {
            param = p;
            if (converter == null) {
                reference = p.getType().equals(StrongReference.class);
                type = p.getType();
                code = "";
                association = AssociationType.DEFAULT;
                shouldNotBeNull = false;
                transferName = false;
            } else {
                reference = converter.byRef() || p.getType().equals(StrongReference.class);
                type = converter.type().equals(Object.class) ? p.getType() : converter.type();
                code = converter.code().trim();
                association = converter.association();
                shouldNotBeNull = converter.shouldNotBeNull();
                transferName = converter.concatName();
            }
        }
    }
}