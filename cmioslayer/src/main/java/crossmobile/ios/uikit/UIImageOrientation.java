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

import org.crossmobile.bridge.ann.CMEnum;

/**
 * UIImageOrientation class defines different types of orientations for images.
 */
@CMEnum
public final class UIImageOrientation {

    /**
     * The default orientation of an image.
     */
    public static final int Up = 0;

    /**
     * The image is rotated 180 degrees from its original position and it appears upside down.
     */
    public static final int Down = 1;

    /**
     * The image is rotated 90 degrees clockwise from its original position.
     */
    public static final int Left = 2;

    /**
     * The image is rotated 90 degrees anti-clockwise from its original position.
     */
    public static final int Right = 3;

    /**
     * The image is rotated 180 degrees around the y axis from its original position.
     */
    public static final int UpMirrored = 4;

    /**
     * The image is rotated 180 degrees around the x axis from its original position.
     */
    public static final int DownMirrored = 5;

    /**
     * The image is rotated 180 degrees around the y axis and then rotated 90 degrees anticlockwise.
     */
    public static final int LeftMirrored = 6;

    /**
     * The image is rotated 180 degrees around the y axis and then rotated 90 degrees clockwise.
     */
    public static final int RightMirrored = 7;

    private UIImageOrientation() {
    }
}