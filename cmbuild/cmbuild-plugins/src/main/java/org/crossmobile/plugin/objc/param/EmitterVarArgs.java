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
import org.crossmobile.plugin.model.VarargType;

import static org.crossmobile.plugin.reg.TypeRegistry.VARARG_SIZE_SUPPORT;
import static org.crossmobile.utils.TextUtils.TAB;

class EmitterVarArgs extends Emitter {

    private final VarargType type;

    EmitterVarArgs(NParam param, boolean forward) {
        super(param.getName(), param.getVarname(), param.getNType(), false, forward);
        type = param.getNType().getVarargType();
    }

    @Override
    protected String embedForward() {
        if (type.virtual) {
            return "[java_util_Arrays asList___java_lang_Object_ARRAYTYPE:" + super.embedForward() + "]";
        }
        StringBuilder out = new StringBuilder();
        out.append('\n');
        for (int i = 0; i < VARARG_SIZE_SUPPORT; i++)
            out.append(TAB).append("($ != JAVA_NULL && $->length > # ? [$ objectAtIndex:#] : ".replace("$", givenVar()).replace("#", Integer.toString(i))).
                    append(type.function ? "NULL" : "nil").
                    append("),\n");
        out.append(TAB).append(type.function ? "NULL" : "nil");
        return out.toString();
    }
}