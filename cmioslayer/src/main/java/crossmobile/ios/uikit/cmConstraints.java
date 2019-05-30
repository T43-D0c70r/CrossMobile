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
package crossmobile.ios.uikit;

import crossmobile.ios.coregraphics.CGRect;
import crossmobile.ios.coregraphics.CGSize;

import static org.crossmobile.bind.graphics.Geometry.isZero;

class cmConstraints {

    private final double left, width, right, top, height, bottom;
    private final boolean flexLeft, flexWidth, flexRight, flexTop, flexHeight, flexBottom;
    private final double floatwidth, floatheight;

    static cmConstraints getConstraints(UIView child, UIView parent) {
        if (parent == null || !parent.autoresizesSubviews() || child.autoresizingMask() == UIViewAutoresizing.None)
            return null;
        int mask = child.autoresizingMask();
        UIViewController rootController = UIApplication.keyWindowIfExists() == null ? null : UIApplication.keyWindowIfExists().rootViewController();
        CGSize size;
        if (rootController != null && rootController.isView(parent))
            size = rootController.getActiveFrame().getSize();
        else
            size = new CGSize(parent.getWidth(), parent.getHeight());
        cmConstraints constr = new cmConstraints(child.frame(), size,
                (mask & UIViewAutoresizing.FlexibleLeftMargin) != 0, (mask & UIViewAutoresizing.FlexibleWidth) != 0, (mask & UIViewAutoresizing.FlexibleRightMargin) != 0,
                (mask & UIViewAutoresizing.FlexibleTopMargin) != 0, (mask & UIViewAutoresizing.FlexibleHeight) != 0, (mask & UIViewAutoresizing.FlexibleBottomMargin) != 0);
        return constr;
    }

    private cmConstraints(CGRect frame, CGSize parentSize,
                          boolean flexLeft, boolean flexWidth, boolean flexRight,
                          boolean flexTop, boolean flexHeight, boolean flexBottom) {

        this.flexLeft = flexLeft;
        this.flexWidth = flexWidth;
        this.flexRight = flexRight;
        this.flexTop = flexTop;
        this.flexHeight = flexHeight;
        this.flexBottom = flexBottom;

        if (isZero(frame) && isZero(parentSize)) {
            left = flexLeft ? 1 : 0;
            width = flexWidth ? 1 : 0;
            right = flexRight ? 1 : 0;
            top = flexTop ? 1 : 0;
            height = flexHeight ? 1 : 0;
            bottom = flexBottom ? 1 : 0;
        } else {
            left = frame.getOrigin().getX();
            width = frame.getSize().getWidth();
            right = parentSize.getWidth() - frame.getOrigin().getX() - frame.getSize().getWidth();
            top = frame.getOrigin().getY();
            height = frame.getSize().getHeight();
            bottom = parentSize.getHeight() - frame.getOrigin().getY() - frame.getSize().getHeight();
        }

        double fw = (flexLeft ? left : 0) + (flexWidth ? width : 0) + (flexRight ? right : 0);
        double fh = (flexTop ? top : 0) + (flexHeight ? height : 0) + (flexBottom ? bottom : 0);
        floatwidth = fw == 0 ? 1 : fw;
        floatheight = fh == 0 ? 1 : fh;
    }

    CGRect getFrame(double vwidth, double vheight) {
        double xfactor = (vwidth - (flexLeft ? 0 : left) - (flexWidth ? 0 : width) - (flexRight ? 0 : right)) / floatwidth;
        double yfactor = (vheight - (flexTop ? 0 : top) - (flexHeight ? 0 : height) - (flexBottom ? 0 : bottom)) / floatheight;
        return new CGRect(
                flexLeft ? left * xfactor : left,
                flexTop ? top * yfactor : top,
                flexWidth ? width * xfactor : width,
                flexHeight ? height * yfactor : height);
    }
}