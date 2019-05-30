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
package crossmobile.ios.coregraphics;

import crossmobile.ios.foundation.CFType;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMFunction;
import org.crossmobile.bridge.ann.CMReference;

/**
 * CGColor class defines an object that represents a color used in the graphics
 * context as a structure of components.
 */
@CMReference
public class CGColor extends CFType {

    final int color;

    CGColor(int color) {
        this.color = color;
    }

    /**
     * Returns the alpha value of this color.
     *
     * @return The alpha value of this color.
     */
    @CMFunction(" CGFloat CGColorGetAlpha ( CGColorRef color ); ")
    public double getAlpha() {
        return ((color & 0xFF000000) >>> 24) / 255d;
    }

    /**
     * Returns the number of components that define this color.
     *
     * @return The number of components of this color.
     */
    @CMFunction(" size_t CGColorGetNumberOfComponents ( CGColorRef color ); ")
    public int getNumberOfComponents() {
        return CGColorSpace.RGB.getNumberOfComponents() + 1;  // plus alpha
    }

    /**
     * Returns the components that specify this color.
     *
     * @return The components of this color.
     */
    @CMFunction(value = " const CGFloat * CGColorGetComponents ( CGColorRef color ); ", sizeResolver = "(CGColorGetNumberOfComponents(" + CMReference.REFERENCE_NAME + ")+1)")
    public double[] getComponents() {
        return CGColorSpace.RGB.unpack(color, new double[getNumberOfComponents() + 1]);
    }

    @Override
    public String toString() {
        return "CGColor rgba=" + Native.graphics().colorToString(color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CGColor other = (CGColor) obj;
        return this.color == other.color;
    }

}