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
package org.crossmobile.backend.desktop;

import org.crossmobile.bind.graphics.AbstractGraphicsBridge;
import org.crossmobile.bind.graphics.GraphicsBridgeConstants;
import org.crossmobile.bind.graphics.GraphicsContext;
import org.crossmobile.bind.graphics.Theme;
import org.crossmobile.bind.io.AbstractFileBridge;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.system.ClassWalker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.crossmobile.backend.desktop.DesktopFileBridge.APPRESOURCE;

public abstract class DesktopGraphicsBridge<CANVAS, NTVP, TRANSF> extends AbstractGraphicsBridge<CANVAS, NTVP, TRANSF> {

    private static boolean fontsLoaded = false;

    private int deviceOrientationMask;
    private String backChar;

    @Override
    public int colorHSBAtoRGBA(double h, double s, double b, double a) {
        return (Color.HSBtoRGB((float) h, (float) s, (float) b) & 0xFFFFFF) | ((int) (a * 0xFF) << 24);
    }

    @Override
    public double[] colorRGBAtoHSVA(int color) {
        float[] hsb = new float[3];
        Color.RGBtoHSB((color >>> 16) & 0xFF, (color >>> 8) & 0xFF, color & 0xFF, hsb);
        return new double[]{hsb[0], hsb[1], hsb[2], ((color >>> 24) & 0xFF) / 255d};
    }

    @Override
    public void setOrientation(int orientation) {
        Native.graphics().metrics().setOrientationMetrics(orientation);
        Native.graphics().relayoutMainView();
    }

    public static void rotateDevice(boolean clockwise) {
        int orientation = Native.graphics().metrics().getOrientation();
        for (int i = 0; i < 4; i++) {
            orientation += clockwise ? 1 : -1;
            if (orientation > 4)
                orientation = 1;
            if (orientation < 1)
                orientation = 4;
            if (GraphicsBridgeConstants.shouldAcceptOrientation(orientation)) {
                Native.graphics().setOrientation(orientation);
                return;
            }
        }
    }

    @Override
    public String getBackChar() {
        if (backChar == null)
            if (new Font(Theme.Font.FONTNAME, Font.PLAIN, Theme.Font.LABELSIZE).canDisplay(Theme.Font.BACKCHAR.charAt(0)))
                backChar = Theme.Font.BACKCHAR;
            else
                backChar = "<";
        return backChar;
    }

    public abstract void draw(CDrawable drawable, GraphicsContext cxt, int orientation);

    public static void setHighQuality(Graphics2D g, boolean status) {
        if (status) {
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        } else {
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
//            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    public static void loadFonts() {
        if (fontsLoaded)
            return;
        ClassWalker.getClasspathEntries(null, fontName -> {
            String lowCase = fontName.toLowerCase();
            if (!lowCase.endsWith(".ttf") && !lowCase.endsWith(".otf"))
                return;
            fontName = '/' + fontName;
            if (fontName.startsWith(APPRESOURCE)) {
                fontName = fontName.substring(APPRESOURCE.length());
                if (!fontName.contains("/"))
                    try {
                        GraphicsEnvironment.getLocalGraphicsEnvironment().
                                registerFont(Font.createFont(Font.TRUETYPE_FONT,
                                        ((AbstractFileBridge) Native.file()).getApplicationFileStream(fontName)));
                    } catch (Exception ex) {
                        Native.system().error("Unable to load font " + fontName + ", reason: " + ex.toString(), null);
                    }
            }
        });
        fontsLoaded = true;
    }

    @Override
    public List<String> listFontFamilies() {
        loadFonts();
        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    }

    @Override
    public List<String> listFont(String familyName) {
        loadFonts();
        List<String> fonts = new ArrayList<>();
        for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts())
            if (f.getFamily().equals(familyName))
                fonts.add(f.getFontName());
        return fonts;
    }

}