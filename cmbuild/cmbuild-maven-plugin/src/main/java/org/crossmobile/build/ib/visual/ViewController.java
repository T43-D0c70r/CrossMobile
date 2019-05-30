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
package org.crossmobile.build.ib.visual;

import org.crossmobile.build.ib.Elements;
import org.crossmobile.build.ib.Values;
import org.crossmobile.build.ib.helper.LayoutGuides;
import org.crossmobile.build.ib.helper.Objects;
import org.crossmobile.build.ib.helper.RealElement;

public class ViewController extends RealElement {

    @Override
    protected void addSupported() {
        super.addSupported();
        addSupportedAttribute("storyboardIdentifier", Values.String);
        addSupportedAttribute("sceneMemberID", Values.String);
        addSupportedAttribute("restorationIdentifier", Values.String);
        addSupportedChild("view", Elements.View);
        addSupportedAttribute("title", Values.LocalizedString);
        addSupportedChild("navigationItem", Elements.NavigationItem);
        addSupportedChild(Elements.LayoutGuides);

    }

    public String toCodeBare() {
        if (attrName("sceneMemberID") != null && !attrName("sceneMemberID").equals(""))
            Objects.SCENE_VC = variable();
        StringBuilder out = new StringBuilder();
        out.append(toCodeSuper());
        out.append(printConnections(variable() + ".this"));
        for (LayoutGuides guide : parts(Elements.LayoutGuides))
            out.append(guide.toCode());
        return out.toString();
    }

    @Override
    public String toCode() {
        Objects.addVC(attrName("storyboardIdentifier"), variable());
        StringBuilder out = new StringBuilder(toCodeBare());
        NavigationItem navItem;
        if ((navItem = (NavigationItem) item("navigationItem")) != null)
            out.append(navItem.toCode());
        appendAttributeCall(out, "title");
        appendAttributeCall(out, "restorationIdentifier");
        out.append(I4).append("org.crossmobile.build.StoryBoardBinder.bindStoryboardWithViewController(").append(variable()).append(".this, ").append("get_Storyboard());").append(NEWLINE);
        out.append(segueMap());

        return out.toString();
    }

    @Override
    protected String publicConstructor() {
        StringBuilder out = new StringBuilder();
        out.append("private ").append(getClassName()).append(" ").append(Objects.GETTER).append(variable()).append("() {").append(NEWLINE);
        out.append(I4).append("return this;").append(NEWLINE);
        out.append(I3).append("}").append(NEWLINE).append(I3);
        constructorOverrides(out);
        out.append(I3).append(super.publicConstructor());
        return out.toString();
    }

    protected void constructorOverrides(StringBuilder out) {
        String view = (item("view") != null) ? ((View) item("view")).variable() : "";
        if (!view.equals("")) {
            out.append("@Override").append(NEWLINE);
            out.append(I3).append("public void loadView() {").append(NEWLINE);
            out.append(I4).append("setView(").append(Objects.GETTER).append(view).append("());").append(NEWLINE);
            out.append(I4).append(Objects.LATE_INITTER).append(view).append("();").append(NEWLINE);
            out.append(I4).append("view().layoutSubviews();").append(NEWLINE);
            out.append(I3).append("}").append(NEWLINE);
        }
    }

    private String segueMap() {
        View view = (View) item("view");
        return view == null ? "" : view.segueMap(variable());
    }

    protected String toCodeSuper() {
        return "";
    }
}