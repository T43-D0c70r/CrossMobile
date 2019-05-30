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
package org.crossmobile.plugin.reg;

import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMEnum;
import org.crossmobile.bridge.ann.CMReference;
import org.crossmobile.utils.CustomTypeClasses;
import org.crossmobile.utils.Log;

import java.util.*;

import static org.crossmobile.plugin.actions.FormerTests.testEnumType;
import static org.crossmobile.plugin.utils.Texters.toObjC;
import static org.crossmobile.utils.NamingUtils.*;

public class TypeDef {

    private final Map<String, Class> map = new HashMap<>();

    public void add(Class type) {
        define(getClassNameSimple(type), type, false);
    }

    public void alias(Class type, String... aliases) {
        if (aliases != null && aliases.length > 0)
            for (String alias : aliases)
                if (alias != null && !alias.trim().isEmpty())
                    define(alias, type, true);
    }

    private void define(String typeName, Class type, boolean asAlias) {
        if (type == null)
            Log.error("No class has been provided");
        else {
            CMClass cls = (CMClass) type.getAnnotation(CMClass.class);
            CMEnum en = (CMEnum) type.getAnnotation(CMEnum.class);
            CMReference ref = (CMReference) type.getAnnotation(CMReference.class);

            if (en != null)
                // If enumeration, type is the type of it's content
                type = testEnumType(type);

            // lazy addition of items, so that original aliases will have advantage
            Class old = map.get(typeName);
            if (old != null)
                map.put(toObjC(getClassNameBare(type)), old);   // just in case it is not present
            else {
                if (asAlias)
                    type = map.getOrDefault(toObjC(getClassNameFull(type)), type);
                map.put(toObjC(getClassNameBare(type)), type);
                map.put(typeName, type);
            }

            if (!asAlias)
                if (cls != null && cls.alias().length > 0)
                    alias(type, cls.alias());
                else if (ref != null && ref.alias().length > 0)
                    alias(type, ref.alias());
                else if (en != null && en.alias().length > 0)
                    alias(type, en.alias());
        }
    }

    public Class get(String typeName) {
        return map.get(typeName);
    }

    {
        alias(boolean.class, "BOOL");
        alias(boolean.class, "bool");
        alias(int.class, "size_t");
        alias(int.class, "int32_t");
        alias(int.class, "uint32_t");
        alias(long.class, "int64_t");
        alias(long.class, "unt64_t");
        alias(long.class, "long long");
        alias(byte[].class, "uuid_t");

        alias(Object.class, "id");
        alias(CustomTypeClasses.Instance.class, "instancetype");
        alias(Void.TYPE, "IBAction");

        alias(double.class, "CGFloat");
        alias(long.class, "CFIndex");
        alias(int.class, "NSInteger");
        alias(int.class, "NSUInteger");

        alias(String.class, "NSString");
        alias(String.class, "CFString");
        alias(String.class, "CNKeyDescriptor");

        alias(List.class, "NSArray");
        alias(List.class, "CFArray");
        alias(Map.class, "NSDictionary");
        alias(Map.class, "CFDictionary");
        alias(Set.class, "NSSet");
        alias(Set.class, "CFSet");

        alias(Number.class, "NSNumber");
        alias(Number.class, "NSDecimalNumber");

        alias(double.class, "NSTimeInterval");
        alias(double.class, "UIAccelerationValue");

        alias(double.class, "CFTimeInterval");
        alias(double.class, "MKZoomScale");

        alias(double.class, "CLLocationDistance");
        alias(double.class, "CLLocationDirection");
        alias(double.class, "CLLocationSpeed");
        alias(double.class, "CLLocationDegrees");
        alias(double.class, "CLLocationAccuracy");

        alias(int.class, "ABPropertyID");
        alias(int.class, "ABRecordID");
        alias(int.class, "ABRecordType");
        alias(int.class, "ABMultiValueIdentifier");
        alias(double.class, "CLHeadingComponentValue");

        //KeyType/ObjectType να τα κάνουμε Object στο type adviser
        alias(Object.class, "KeyType");
        alias(Object.class, "ObjectType");
        alias(Object.class, "ValueType");
    }

    public static final Collection<String> Numbers = new HashSet<>(Arrays.asList(
            "java_lang_Byte",
            "java_lang_Short",
            "java_lang_Integer",
            "java_lang_Long",
            "java_lang_Float",
            "java_lang_Double"));
}