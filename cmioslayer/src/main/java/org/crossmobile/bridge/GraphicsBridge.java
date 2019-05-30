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
package org.crossmobile.bridge;

import crossmobile.ios.coregraphics.CGAffineTransform;
import org.crossmobile.bind.graphics.*;

import java.util.List;

public interface GraphicsBridge<CANVAS, NTVP, TRANSF> {

    void refreshDisplay();

    GraphicsContext<NTVP, TRANSF> newGraphicsContext(CANVAS canvas, boolean isLive);

    CANVAS createCanvas(NativeBitmap bitmap);

    void destroyCanvas(CANVAS canvas);

    /**
     * Convert an affine transformation from the platform specific
     * transformation matrix to CGAffineTransformation.
     *
     * @param from the source transformation
     * @param to   the transformation to recycle
     * @return the resulting transformation
     */
    CGAffineTransform nativeToTarget(TRANSF from, CGAffineTransform to);

    /**
     * Convert an affine transformation from CGAffineTransformation to the the
     * platform specific transformation matrix
     *
     * @param from the source transformation
     * @param to   the transformation to recycle
     * @return the resulting transformation
     */
    TRANSF targetToNative(CGAffineTransform from, TRANSF to);

    DrawableMetrics metrics();

    void relayoutMainView();

    /**
     * Convert a color from the HSB colorspace to the RGB
     *
     * @param h hue from 0 to 1
     * @param s saturation from 0 to 1
     * @param b brightness from 0 to 1
     * @param a alpha from 0 to 1
     * @return color in 0xAARRGGBB format
     */
    int colorHSBAtoRGBA(double h, double s, double b, double a);

    /**
     * Convert a color from the RGB colorspace to the HSB
     *
     * @param color color in 0xAARRGGBB format
     * @return 0: hue from 0 to 1, 1: saturation from 0 to 1, 2: brightness from
     * 0 to 1, 3: alpha from 0 to 1
     */
    double[] colorRGBAtoHSVA(int color);

    default int colorRGBA(double r, double g, double b, double a) {
        return (((int) (a * 255)) << 24)
                | (((int) (r * 255)) << 16)
                | (((int) (g * 255)) << 8)
                | ((int) (b * 255));
    }

    default double[] colorUnpack(int rgba, double[] buffer) {
        if (buffer == null || buffer.length < 4)
            buffer = new double[4];
        buffer[0] = ((rgba & 0xFF0000) >>> 16) / 255f;
        buffer[1] = ((rgba & 0xFF00) >> 8) / 255f;
        buffer[2] = (rgba & 0xFF) / 255f;
        buffer[3] = ((rgba & 0xFF000000) >>> 24) / 255f;
        return buffer;
    }

    default int colorWithAlpha(int color, double alpha) {
        return (color & 0xffffff) | (((int) (0xFF * alpha) << 24) & 0xFF000000);
    }

    default String colorToString(int color) {
        double[] cols = colorUnpack(color, null);
        return ((int) (cols[0] * 100.5) / 100) + ", " + ((int) (cols[1] * 100.5) / 100) + ", " + ((int) (cols[2] * 100.5) / 100) + ", " + ((int) (cols[3] * 100.5) / 100);
    }

    default NativeFont getFont(String name, double fontsize) {
        boolean bold = false;
        boolean italic = false;
        name = name == null ? "" : name;
        String lower = name.toLowerCase();

        /*
        fish bold/italic styles
         */
        if (lower.endsWith(" bolditalic") || lower.endsWith(" italicbold")) {
            lower = lower.substring(0, lower.length() - 11).trim();
            bold = true;
            italic = true;
        } else {
            if (lower.endsWith(" bold")) {
                bold = true;
                lower = lower.substring(0, lower.length() - 5).trim();
            }
            if (lower.endsWith(" italic")) {
                italic = true;
                lower = lower.substring(0, lower.length() - 7).trim();
            }
            if (!bold && lower.endsWith(" bold")) { // again, in case italic is first and bold doesn't follow
                bold = true;
                lower = lower.substring(0, lower.length() - 5).trim();
            }
        }
        name = name.substring(0, lower.length()).trim();

        return getFont(name, fontsize, bold, italic);
    }

    NativeFont getFont(String family, double size, boolean bold, boolean italic);

    NativePath newNativePath();

    String getBackChar();

    List<String> listFontFamilies();

    List<String> listFont(String familyName);

    void setOrientation(int iosOrientation);

}