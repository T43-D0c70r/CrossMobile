/*
 * (c) 2020 by Panayotis Katsaloulis
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package org.crossmobile.bind.graphics.theme;

public class ThemeFactory {
    @SuppressWarnings("unused")
    public static ThemeManager createManager() {
        return new StyledThemeManager();
    }
}
