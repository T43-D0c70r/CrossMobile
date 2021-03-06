/*
 * (c) 2021 by Panayotis Katsaloulis
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package org.crossmobile.backend.avian;

import org.crossmobile.backend.desktop.DesktopDrawableMetrics;
import org.crossmobile.backend.desktop.DesktopLifecycleBridge;
import org.crossmobile.bridge.Native;

import static org.crossmobile.bind.graphics.GraphicsBridgeConstants.DefaultInitialOrientation;

public class AvianLifecycleBridge extends DesktopLifecycleBridge {

    @Override
    public void init(String[] args) {
        super.init(args);
        AvianGraphicsBridge.window = new SDLWindow("Aroma");
        Native.graphics().setOrientation(DefaultInitialOrientation);
    }

    @Override
    public void splashTerminated() {
    }

    @Override
    public void runOnEventThread(Runnable runnable) {
    }

    @Override
    public void postOnEventThread(Runnable runnable) {
    }

    @Override
    public boolean isEventThread() {
        return false;
    }

    @Override
    public SystemTimerHandler createSystemTimer() {
        return null;
    }

    @Override
    public void hasAnimationFrames(boolean enabled) {

    }
}
