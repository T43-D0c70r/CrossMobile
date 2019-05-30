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
package org.crossmobile.backend.swing;

import crossmobile.ios.coregraphics.CGAffineTransform;
import crossmobile.ios.coregraphics.CGLineJoin;
import crossmobile.ios.coregraphics.CGRect;
import crossmobile.ios.coregraphics.CGSize;
import org.crossmobile.bind.graphics.GraphicsContext;
import org.crossmobile.bind.graphics.NativeBitmap;
import org.crossmobile.bind.graphics.NativeFont;
import org.crossmobile.bridge.Native;

import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;

public class SwingGraphicsContext implements GraphicsContext<SwingNativePath, AffineTransform> {

    private static final int UNKNOWN = -1;
    private static final int FILL = 1;
    private static final int DRAW = 2;
    //
    final Graphics2D g2;
    final private boolean isLive;
    private final Stack<GState> states;  // J2D doesn not provide a save state - we have to implement our own
    //
    private Color fill_paint = Color.BLACK;
    private Color draw_paint = Color.BLACK;
    private int last_paint = UNKNOWN;
    private SwingFont last_font = null;
    //
    private double line_width = 1;
    private int line_cap = BasicStroke.CAP_BUTT;
    private int line_join = BasicStroke.JOIN_MITER;

    public SwingGraphicsContext(Graphics2D canvas, boolean isLive) {
        this.g2 = canvas;
        this.isLive = isLive;
        states = new Stack<>();
    }

    @Override
    public void setAlpha(double alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
    }

    @Override
    public void saveState() {
        states.push(new GState());
    }

    @Override
    public void restoreState() {
        states.pop().apply();
    }

    @Override
    public void concatCTM(AffineTransform transform) {
        g2.transform(transform);
    }

    @Override
    public CGAffineTransform getCTM() {
        return Native.graphics().nativeToTarget(g2.getTransform(), null);
    }

    @Override
    public void drawBitmap(NativeBitmap bitmap, int x, int y, int width, int height) {
        if (bitmap != null)
            g2.drawImage(((SwingBitmap) bitmap).img, x, y, width, height, null);
    }

    private void updateColor(int current_paint) {
        if (last_paint == current_paint)
            return;
        last_paint = current_paint;
        g2.setColor(last_paint == FILL ? fill_paint : draw_paint);
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        updateColor(DRAW);
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    @Override
    public void drawPath(SwingNativePath path) {
        updateColor(DRAW);
        g2.draw(path);
    }

    @Override
    public void drawRect(double x, double y, double width, double height) {
        updateColor(DRAW);
        g2.draw(new Rectangle2D.Double(x, y, width - 1, height - 1));
    }

    @Override
    public void drawEllipse(double x, double y, double width, double height) {
        updateColor(DRAW);
        g2.draw(new Ellipse2D.Double(x, y, width - 1, height - 1));
    }

    @Override
    public void drawArc(double x, double y, double width, double height, double from, double extend) {
        updateColor(DRAW);
        g2.draw(new Arc2D.Double(x, y, width - 1, height - 1, from * PI_TO_DEG, extend * PI_TO_DEG, Arc2D.Double.OPEN));
    }

    @Override
    public void fillPath(SwingNativePath path) {
        updateColor(FILL);
        g2.fill(path);
    }

    @Override
    public void fillRect(double x, double y, double width, double height) {
        updateColor(FILL);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
    }

    @Override
    public void fillEllipse(double x, double y, double width, double height) {
        updateColor(FILL);
        g2.fill(new Ellipse2D.Double(x, y, width, height));
    }

    @Override
    public void fillArc(double x, double y, double width, double height, double from, double extend) {
        updateColor(FILL);
        g2.fill(new Arc2D.Double(x, y, width, height, from * PI_TO_DEG, extend * PI_TO_DEG, Arc2D.Double.PIE));
    }

    @Override
    public void drawLinearGradient(int[] RGBcolors, double[] locations, double startPointX, double startPointY, double endPointX, double endPointY, int CGGradientDrawingOptions) {
        float[] locationsF = new float[locations.length];
        for (int i = 0; i < locations.length; i++) locationsF[i] = (float) locations[i];
        g2.setPaint(new LinearGradientPaint((float) startPointX, (float) startPointY, (float) endPointX, (float) endPointY, locationsF, toColors(RGBcolors, false), MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g2.fill(new Rectangle2D.Double(startPointX, startPointY, endPointX, endPointY));
    }

    @Override
    public void drawRadialGradient(int[] RGBcolors, double[] locations, double startCenterX, double startCenterY, double startRadius, double endCenterX, double endCenterY, double endRadius, int CGGradientDrawingOptions) {
        float[] locationsF = new float[locations.length];
        for (int i = 0; i < locations.length; i++) locationsF[i] = (float) locations[i];
        g2.setPaint(new RadialGradientPaint((float) startCenterX, (float) startCenterY, (float) startRadius, inverseGradientLocations(locationsF), toColors(RGBcolors, true), MultipleGradientPaint.CycleMethod.NO_CYCLE));
        g2.fill(new Ellipse2D.Double(startCenterX - startRadius, startCenterY - startRadius, startRadius * 2, startRadius * 2));
    }

    @Override
    public void setFillColorWithColor(int color) {
        fill_paint = new Color(color, true);
        last_paint = UNKNOWN;
    }

    @Override
    public void setDrawColorWithColor(int color) {
        draw_paint = new Color(color, true);
        last_paint = UNKNOWN;
    }

    @Override
    public void clipToRect(CGRect frame) {
        g2.clip(new Rectangle2D.Double(frame.getOrigin().getX(), frame.getOrigin().getY(), frame.getSize().getWidth(), frame.getSize().getHeight()));
    }

    @Override
    public void translate(double tx, double ty) {
        g2.translate(tx, ty);
    }

    @Override
    public void rotate(double theta) {
        g2.rotate(theta);
    }

    @Override
    public void scale(double sx, double sy) {
        g2.scale(sx, sy);
    }

    @Override
    public void setFont(NativeFont font) {
        last_font = (SwingFont) font;
    }

    @Override
    public CGSize stringSizeWithFont(String text, NativeFont font) {
        if (text == null || text.length() < 1 || font == null)
            return new CGSize(0, 0);
        return ((SwingFont) font).stringSize(text);
    }

    @Override
    public void showTextAtPoint(double x, double y, String text) {
        if (last_font == null)
            return;
        updateColor(FILL);
        g2.setFont(last_font.font);
        g2.drawString(text, (float) x, (float) (y + last_font.getAscent()));
    }

    @Override
    public void setLineCap(int CGLineCap) {
        switch (CGLineCap) {
            case crossmobile.ios.coregraphics.CGLineCap.Round:
                line_cap = BasicStroke.CAP_ROUND;
                break;
            case crossmobile.ios.coregraphics.CGLineCap.Square:
                line_cap = BasicStroke.CAP_SQUARE;
                break;
            case crossmobile.ios.coregraphics.CGLineCap.Butt:
            default:
                line_cap = BasicStroke.CAP_BUTT;
                break;
        }
        g2.setStroke(new BasicStroke((float) line_width, line_cap, line_join));
    }

    @Override
    public void setLineJoin(int cglinejoin) {
        switch (cglinejoin) {
            case CGLineJoin.Bevel:
                line_join = BasicStroke.JOIN_BEVEL;
                break;
            case CGLineJoin.Round:
                line_join = BasicStroke.CAP_ROUND;
                break;
            case CGLineJoin.Miter:
            default:
                line_join = BasicStroke.JOIN_MITER;
                break;
        }
        g2.setStroke(new BasicStroke((float) line_width, line_cap, line_join));
    }

    @Override
    public void setLineWidth(double width) {
        line_width = width;
        g2.setStroke(new BasicStroke((float) line_width, line_cap, line_join));
    }

    @Override
    public void setAntialias(boolean shouldAntialias) {
        SwingGraphicsBridge.setHighQuality(g2, shouldAntialias);
    }

    @Override
    public boolean isLiveContext() {
        return isLive;
    }

    private static Color[] toColors(int[] RGB, boolean inverse) {
        int size = RGB.length;
        Color[] res = new Color[size];
        for (int i = 0; i < size; i++)
            if (inverse)
                res[i] = new Color(RGB[size - i - 1], true);
            else
                res[i] = new Color(RGB[i], true);
        return res;
    }

    private static float[] inverseGradientLocations(float[] locations) {
        int size = locations.length;
        float[] result = new float[size];
        for (int i = 0; i < size; i++)
            result[i] = 1 - locations[size - i - 1];
        return result;
    }

    private class GState {

        private final Color _fill_paint;
        private final Color _draw_paint;
        private final SwingFont _last_font;
        private final double _line_width;
        private final int _line_cap;
        private final int _line_join;

        private final AffineTransform _transf;
        private final Shape _shape;

        private GState() {
            _fill_paint = fill_paint;
            _draw_paint = draw_paint;
            _last_font = last_font;
            _line_width = line_width;
            _line_cap = line_cap;
            _line_join = line_join;
            _transf = g2.getTransform();
            _shape = g2.getClip();
        }

        private void apply() {
            fill_paint = _fill_paint;
            draw_paint = _draw_paint;
            last_paint = UNKNOWN;
            last_font = _last_font;
            if (last_font != null)
                g2.setFont(last_font.font);
            line_width = _line_width;
            line_cap = _line_cap;
            line_join = _line_join;
            g2.setStroke(new BasicStroke((float) line_width, line_cap, line_join));
            g2.setTransform(_transf);
            g2.setClip(_shape);
        }
    }
}