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
import org.crossmobile.bridge.ann.CMLib;

/**
 * UIImagePickerControllerCameraCaptureMode class defines different types media
 * capturing for the camera.
 */
@CMEnum
@CMLib(name = "cmimage")
public final class UIImagePickerControllerCameraCaptureMode {

    /**
     * The camera captures photos.
     */
    public static final int Photo = 0;

    /**
     * The camera captures videos.
     */
    public static final int Video = 1;

    private UIImagePickerControllerCameraCaptureMode() {
    }
}