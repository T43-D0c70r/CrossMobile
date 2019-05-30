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
package org.crossmobile.plugin.objc.param;

import org.crossmobile.plugin.model.NParam;
import org.crossmobile.plugin.model.NType;

import static org.crossmobile.plugin.reg.TypeRegistry.getJavaBoxed;
import static org.crossmobile.plugin.reg.TypeRegistry.getObjCUnboxed;
import static org.crossmobile.plugin.utils.Texters.toObjC;
import static org.crossmobile.utils.NamingUtils.getClassNameBare;

class EmitterPrimitive extends Emitter {

    private final boolean boxed;
    private final String boxedType;
    private final String boxedName;

    EmitterPrimitive(NParam param, boolean forward) {
        this(param.getName(), param.getVarname(), param.getNType(), !param.getJavaParameter().getType().isPrimitive(), forward);
    }

    EmitterPrimitive(String paramName, String varName, NType type, boolean boxed, boolean forward) {
        super(paramName, varName, type, boxed, forward);
        this.boxed = boxed;
        this.boxedType = toObjC(getClassNameBare(getJavaBoxed(type.getType())));
        this.boxedName = getObjCUnboxed(type.getType());
    }

    @Override
    protected String embedForward() {
        return boxed
                ? "[" + givenVar() + " unbox]"
                : super.embedForward();
    }

    @Override
    protected String initReverse() {
        return boxed
                ? boxedType + "* " + paramVar() + " = [[" + boxedType + " alloc] initWith" + boxedName + ":" + givenVar() + "];\n"
                : super.initReverse();
    }

    @Override
    protected String destroyReverse() {
        return boxed
                ? "[" + paramVar() + " release];\n"
                : super.destroyReverse();
    }

}