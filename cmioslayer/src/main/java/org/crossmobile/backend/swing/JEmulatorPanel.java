/*
 * (c) 2021 by Panayotis Katsaloulis
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package org.crossmobile.backend.swing;

import crossmobile.ios.coregraphics.CGPoint;
import crossmobile.ios.uikit.UIApplication;
import crossmobile.ios.uikit.UITouch;
import crossmobile.ios.uikit.UIView;
import crossmobile.ios.uikit.UIWindow;
import org.crossmobile.backend.desktop.*;
import org.crossmobile.backend.cat.MobileApp;
import org.crossmobile.backend.desktop.cgeo.CEvent;
import org.crossmobile.backend.desktop.cgeo.CEventCallback;
import org.crossmobile.backend.swing.SwingGraphicsBridge.SizableComponent;
import org.crossmobile.bind.graphics.DrawableMetrics;
import org.crossmobile.bind.system.AbstractLifecycleBridge;
import org.crossmobile.bridge.Native;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static crossmobile.ios.uikit.UserInterfaceDrill.*;
import static crossmobile.ios.uikit.UITouchPhase.*;

public class JEmulatorPanel extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, SizableComponent, CEventCallback {

    private static Cursor singleTouch;
    private static Cursor doubleTouch;
    private static final float MOUSE_AURA = 20;

    private JComponent nativeFocusTarget = null;
    private CEvent clicked = CEvent.unset();
    private Point2D.Double hardwareMouse = new Point2D.Double(0, 0);
    private boolean multiTouch = false;

    public JEmulatorPanel() {
    }

    @SuppressWarnings({"LeakingThisInConstructor", "OverridableMethodCallInConstructor"})
    public JEmulatorPanel(boolean simulator) {
        setOpaque(true);
        setBackground(Color.black);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setLayout(null);

        if (simulator) {
            addKeyListener(this);
            Toolkit tk = Toolkit.getDefaultToolkit();
            try {
                BufferedImage img = ImageIO.read(JEmulatorPanel.class.getResource(DesktopLocations.ICONS + "single.png"));
                singleTouch = tk.createCustomCursor(img, new Point(13, 1), "singleTouch");
            } catch (IOException | IndexOutOfBoundsException | HeadlessException ignored) {
            }
            try {
                BufferedImage img = ImageIO.read(JEmulatorPanel.class.getResource(DesktopLocations.ICONS + "double.png"));
                doubleTouch = tk.createCustomCursor(img, new Point(19, 1), "doubleTouch");
            } catch (HeadlessException | IOException | IndexOutOfBoundsException ignored) {
            }
            updateMouse();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Native.lifecycle().encapsulateContext(() -> {
            if (clicked.isUnset())
                return;
            if (multiTouch)
                fireTouchEvent(e.getX(), e.getY(), e, Ended, true, true);
            fireTouchEvent(e.getX(), e.getY(), e, Ended, false, false);
            clicked = CEvent.unset();
        });
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Native.lifecycle().encapsulateContext(() -> {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            DesktopDrawableMetrics metrics = (DesktopDrawableMetrics) Native.graphics().metrics();
            clicked = metrics.findArea(e.getX(), e.getY());
            if (clicked.isButton())
                repaint();
            else if (e.getSource() instanceof SwingNativeDispatcher.DesktopNativeWidget)
                widgetTouchCorrection(e, Began);
            else if (clicked.isArea()) {
                fireTouchEvent(e.getX(), e.getY(), e, Began, false, false);
                if (multiTouch)
                    fireTouchEvent(e.getX(), e.getY(), e, Began, true, true);
            }
        });
    }

    private void widgetTouchCorrection(MouseEvent e, int state) {
        UIView view = ((SwingNativeDispatcher.DesktopNativeWidget) e.getSource()).getWrapper().getIOSWidget();
        CGPoint origin;
        origin = UIApplication.sharedApplication().keyWindow().convertPointFromView(Native.graphics().metrics().getVirtualToHardware(0, 0), view);
        e.translatePoint((int) (origin.getX()), (int) (origin.getY()));
        fireTouchEvent(e.getX(), e.getY(), e, state, false, false);
        if (multiTouch)
            fireTouchEvent(e.getX(), e.getY(), e, state, true, true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (clicked.isUnset() || (e.getButton() != MouseEvent.BUTTON1 && e.getButton() != MouseEvent.NOBUTTON))
            return;
        Native.lifecycle().encapsulateContext(() -> {
            DesktopDrawableMetrics metrics = (DesktopDrawableMetrics) Native.graphics().metrics();
            if (e.getSource() instanceof SwingNativeDispatcher.DesktopNativeWidget) {
                widgetTouchCorrection(e, Moved);
                hardwareMouse = new Point2D.Double(e.getX(), e.getY());
            } else if (clicked.isButton()) {
                metrics.updateMouseMoving(e.getX(), e.getY(), clicked);
                repaint();
            } else {
                fireTouchEvent(e.getX(), e.getY(), e, Moved, false, multiTouch);
                hardwareMouse = new Point2D.Double(e.getX(), e.getY());
            }
        });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Native.lifecycle().encapsulateContext(() -> {
            if (e.getButton() != MouseEvent.BUTTON1 || clicked.isUnset())
                return;
            if (e.getSource() instanceof SwingNativeDispatcher.DesktopNativeWidget)
                widgetTouchCorrection(e, Ended);
            else if (clicked.isButton()) {
                clicked.performAction(this);
                repaint();
            } else {
                if (multiTouch)
                    fireTouchEvent(e.getX(), e.getY(), e, Ended, true, true);
                fireTouchEvent(e.getX(), e.getY(), e, Ended, false, false);
            }
            clicked = CEvent.unset();
        });
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hardwareMouse = new Point2D.Double(e.getX(), e.getY());
        if (multiTouch)
            Native.lifecycle().encapsulateContext(this::repaint);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Native.lifecycle().encapsulateContext(() -> {
            int step = e.getWheelRotation() * (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL ? e.getScrollAmount() : 3);
            mouseDidScroll(e.getX(), e.getY(), step);
        });
    }

    @Override
    public void keyPressed(KeyEvent key) {
        Native.lifecycle().encapsulateContext(() -> {
            if (nativeFocusTarget != null)  // might not be needed
                nativeFocusTarget.dispatchEvent(key);
            else
                KeyboardSupport.reactToPressEvent(this, key.getKeyCode(), key.getModifiers());
        });
    }

    @Override
    public void keyReleased(KeyEvent key) {
        Native.lifecycle().encapsulateContext(() -> {
            if (nativeFocusTarget != null)  // might not be needed
                nativeFocusTarget.dispatchEvent(key);
            else
                KeyboardSupport.reactToReleaseEvent(this, key.getKeyCode(), key.getModifiers());
        });
    }

    @Override
    public void keyTyped(KeyEvent key) {
        Native.lifecycle().encapsulateContext(() -> {
            if (nativeFocusTarget != null)  // might not be needed
                nativeFocusTarget.dispatchEvent(key);
        });
    }

    @Override
    public void startMultiTouch() {
        multiTouch = true;
        if (clicked.isArea())
            fireTouchEvent(hardwareMouse.x, hardwareMouse.y, new MouseEvent(this, 1, System.currentTimeMillis(), 0, (int) hardwareMouse.x, (int) hardwareMouse.y, 1, false), Began, true, true);
        else
            repaint();
        updateMouse();
    }

    @Override
    public void endMultiTouch() {
        if (clicked.isArea())
            fireTouchEvent(hardwareMouse.x, hardwareMouse.y, new MouseEvent(this, 1, System.currentTimeMillis(), 0, (int) hardwareMouse.x, (int) hardwareMouse.y, 1, false), Ended, true, true);
        else
            repaint();
        multiTouch = false;
        updateMouse();
    }

    @Override
    public void back() {
        if (!((AbstractLifecycleBridge) Native.lifecycle()).backHandled())
            Native.lifecycle().quit(null, null);
    }

    @Override
    public void home() {
        MobileApp.goToHome();
    }

    @Override
    public void shake() {
        SwingGraphicsBridge.frame.shake();
    }

    @Override
    public void powerOff() {
        Native.lifecycle().quit(null, null);
    }

    @Override
    public void rotateRight() {
        DesktopGraphicsBridge.rotateDevice(true);
    }

    @Override
    public void rotateLeft() {
        DesktopGraphicsBridge.rotateDevice(false);
    }

    private void fireTouchEvent(double x, double y, MouseEvent me, int phase, boolean coreIsStationary, boolean fireMultitouch) {
        UIApplication app = UIApplication.sharedApplication();
        if (app == null)
            return;
        UIWindow window = app.keyWindow();
        if (window == null)
            return;

        UITouch[] touches = new UITouch[fireMultitouch ? 2 : 1];
        touches[0] = newUITouch(x, y, 0, window, coreIsStationary ? Stationary : phase);
        if (fireMultitouch) {
            CGPoint mirror = Native.graphics().metrics().getMirrorVirtualFromHardwarePoint(x, y);
            touches[1] = newUITouch(mirror.getX(), mirror.getY(), 1, window, phase);
        }
        if (phase == Began || phase == Moved) {
            CGPoint[] points = new CGPoint[touches.length];
            for (int i = 0; i < touches.length; i++)
                points[i] = touches[i].locationInView(null);
            Native.graphics().metrics().setActiveTouchLocations(points);
        } else
            Native.graphics().metrics().setActiveTouchLocations(null);
        window.sendEvent(newUIEvent(touches, me, phase));
        if (fireMultitouch)
            repaint();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void paint(Graphics gfx) {
        Native.lifecycle().encapsulateContext(() -> {
            drawWindow(Native.graphics().newGraphicsContext(gfx, true));
            // Draw mouse
            if (multiTouch) {
                Graphics2D g2 = (Graphics2D) gfx;
                gfx.setColor(new Color(clicked.isArea() ? 0.9f : 0.5f, clicked.isArea() ? 0.9f : 0.6f, 0.7f, 0.8f));
                DrawableMetrics metrics = Native.graphics().metrics();
                CGPoint core = metrics.getHardwareToVirtual(hardwareMouse.x, hardwareMouse.y);
                CGPoint shadow = metrics.getMirrorVirtualFromHardwarePoint(hardwareMouse.x, hardwareMouse.y);
                g2.fill(new Ellipse2D.Double(core.getX() - MOUSE_AURA, core.getY() - MOUSE_AURA, MOUSE_AURA * 2, MOUSE_AURA * 2));
                g2.fill(new Ellipse2D.Double(shadow.getX() - MOUSE_AURA, shadow.getY() - MOUSE_AURA, MOUSE_AURA * 2, MOUSE_AURA * 2));
            }
            Toolkit.getDefaultToolkit().sync();
        });
    }

    void registerKeyboardNativeTarget(JComponent focus) {
        nativeFocusTarget = focus;
    }

    void updateMouse() {
        if (!multiTouch && singleTouch != null)
            setCursor(singleTouch);
        else if (doubleTouch != null)
            setCursor(doubleTouch);
    }
}
