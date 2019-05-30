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
package org.crossmobile.gui.parameters.impl;

import org.crossmobile.gui.elements.IdDocumentFilter;
import org.crossmobile.gui.parameters.FreeTextParameter;
import org.crossmobile.utils.ParamList;

import static org.crossmobile.utils.ParamsCommon.ARTIFACT_ID;

public class ArtifactIdParameter extends FreeTextParameter {

    public static final String DEFAULT_ARTIFACT_ID = "crossmobile.projects";

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ArtifactIdParameter(ParamList list) {
        super(list, ARTIFACT_ID.tag());
        setValue(getValue());
        setFilter(new IdDocumentFilter(true));
        setTooltip(IdDocumentFilter.TOOLTIP_SIMPLE);
    }

    @Override
    public String getVisualTag() {
        return "Application name";
    }

    @Override
    public String getValue() {
        String val = super.getValue();
        if (val == null)
            return DEFAULT_ARTIFACT_ID;
        return val.toLowerCase();
    }

}