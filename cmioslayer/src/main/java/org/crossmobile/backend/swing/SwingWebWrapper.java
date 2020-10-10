/*
 * (c) 2020 by Panayotis Katsaloulis
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package org.crossmobile.backend.swing;

import crossmobile.ios.foundation.*;
import crossmobile.ios.uikit.UIView;
import crossmobile.ios.uikit.UIWebView;
import crossmobile.ios.uikit.UIWebViewDelegate;
import crossmobile.ios.uikit.UIWebViewNavigationType;
import crossmobile.ios.webkit.WKBackForwardList;
import crossmobile.ios.webkit.WKNavigation;
import crossmobile.ios.webkit.WKNavigationDelegate;
import crossmobile.ios.webkit.WKWebView;
import org.crossmobile.bind.system.SystemUtilities;
import org.crossmobile.bind.wrapper.WebWrapper;
import org.crossmobile.bind.wrapper.WidgetWrapper;
import org.crossmobile.bridge.Native;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.objc.block.VoidBlock2;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class SwingWebWrapper extends WebWrapper<SwingWebWrapper.NativeW, SwingGraphicsContext> implements HyperlinkListener {

    private boolean isLoading = false;

    public SwingWebWrapper(UIWebView wv) {
        super(wv);
    }

    @Override
    public SwingWebWrapper.NativeW newNativeWidget() {
        return new NativeW();
    }

    @Override
    public WKNavigation loadRequest(NSURLRequest req) {
        WKNavigation nav = getNavigation(req.URL().absoluteString());
        new Thread(() -> {
            try {
                isLoading = true;
                if (getNewDelegate() != null)
                    getNewDelegate().didCommitNavigation(getNewWebView(), nav);
                else if (getOldDelegate() != null)
                    getOldDelegate().didStartLoad(getOldWebView());

                if ((req instanceof NSMutableURLRequest) && ((NSMutableURLRequest) req).HTTPBody() != null && ((NSMutableURLRequest) req).HTTPBody().length() > 0)
                    getNativeWidget().getDocument().putProperty("javax.desktop.JEditorPane.postdata", SystemUtilities.bytesToString(((NSMutableURLRequest) req).HTTPBody().bytes()));
                getNativeWidget().setPage(req.URL().absoluteString());
            } catch (IOException ex) {
                isLoading = false;
                if (getNewDelegate() != null)
                    getNewDelegate().didFailNavigation((WKWebView) getIOSWidget(), nav, new NSError(NSError.Domain.NSURL, NSError.ErrorCode.Unknown, null));
                else if (getOldDelegate() != null)
                    getOldDelegate().didFailLoadWithError((UIWebView) getIOSWidget(), new NSError(NSError.Domain.NSURL, NSError.ErrorCode.Unknown, null));
            }
        }).start();
        return nav;
    }

    @Override
    public WKNavigation loadData(String data, String MIMEType, String baseURL) {
        JEditorPane ntv = getNativeWidget();
        ntv.setDocument(ntv.getEditorKit().createDefaultDocument());
        ntv.setContentType(MIMEType);
        ntv.setText(data);
        return null;
    }

    @Override
    public WKNavigation reload() {
        Native.system().notImplemented();
        return null;
    }

    @Override
    public void stopLoading() {
        Native.system().notImplemented();
    }

    @Override
    public String getTitle() {
        Native.system().notImplemented();
        return null;
    }

    @Override
    public String getUrl() {
        Native.system().notImplemented();
        return null;
    }

    @Override
    public double getProgress() {
        Native.system().notImplemented();
        return 0;
    }

    @Override
    public double getMagnification() {
        Native.system().notImplemented();
        return 0;
    }

    @Override
    public WKNavigation goBack() {
        Native.system().notImplemented();
        return null;
    }

    @Override
    public WKNavigation goForward() {
        Native.system().notImplemented();
        return null;
    }

    @Override
    public void evaluateJavaScript(String script, VoidBlock2<Object, NSError> callback) {
        Native.system().notImplemented();
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        Native.system().error("Should ask delegate to update page", null);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public WKBackForwardList getBackForwardList() {
        return null;
    }

    public class NativeW extends JEditorPane implements SwingNativeDispatcher.DesktopNativeWidget {

        @SuppressWarnings({"OverridableMethodCallInConstructor"})
        public NativeW() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    //    TextFieldEventPropagate.consumeKeyEvent(pwidget.get(), widget.getCaretPosition(), e.getKeyChar(), true);    // Always click event
                }
            });
            addPropertyChangeListener("page", (PropertyChangeEvent evt) -> {
                isLoading = false;
                getIOSWidget().setNeedsDisplay();
                if (getNewDelegate() != null)
                    getNewDelegate().didFinishNavigation(getNewWebView(), null);
                else if (getOldDelegate() != null)
                    getOldDelegate().didFinishLoad((UIWebView) getIOSWidget());
            });
            setEditable(false);
            addHyperlinkListener(SwingWebWrapper.this);
        }

        @Override
        public void repaint(long tm, int x, int y, int width, int height) {
            super.repaint(tm, x, y, width, height);
            UIView wv = getIOSWidget();
            if (wv != null)
                wv.setNeedsDisplay();
        }

        @Override
        public void paint(Graphics g) {
            if (useNativeDrawPipeline)
                try {
                    super.paint(g);
                } catch (Throwable th) {
                    Native.system().error("Unable to paint component: " + th.toString(), null);
                }
        }

        @Override
        public void superDraw(SwingGraphicsContext cxt) {
            if (!useNativeDrawPipeline)
                try {
                    super.paint(cxt.g2);
                } catch (Throwable th) {
                    Native.system().error("Unable to paint native component: " + th.toString(), null);
                }
        }

        @Override
        public void setUserInteraction(boolean status) {
            setEnabled(status);
        }

        @Override
        public WidgetWrapper getWrapper() {
            return SwingWebWrapper.this;
        }
    }
}
