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
 * You should have received attr copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.crossmobile.build.ib.helper;

import org.crossmobile.build.ib.Element;
import org.crossmobile.build.ib.Values;

public class FontDescription extends Element {

    @Override
    protected void addSupported() {
        addSupportedAttribute("key", Values.String);
        addSupportedAttribute("type", Values.String);
        addSupportedAttribute("name", Values.String);
        addSupportedAttribute("family", Values.String);
        addSupportedAttribute("pointSize", Values.Float);
        addSupportedAttribute("weight", Values.String);
    }

    @Override
    public String toCode() {
        StringBuilder out = new StringBuilder("UIFont.");
        if (attr("type", "custom").toLowerCase().endsWith("system\""))
            out.append(attrName("type")).append("FontOfSize(").append(attr("pointSize", "14")).append(")");
        else
            out.append("fontWithName(").append(attr("name")).append(", ").append(attr("pointSize", "14")).append(")");
        return out.toString();
    }

}