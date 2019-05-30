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
import org.crossmobile.bind.graphics.ImageBridgeConstants;
import org.crossmobile.bind.graphics.NativeBitmap;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMFunction;
import org.crossmobile.bridge.ann.CMReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * CGImage class defines an object that represents a bitmap image.
 */
@CMReference
public class CGImage extends CFType {

    private final String filename;
    private NativeBitmap bitmap;
    private int width;
    private int height;

    /**
     * Constructs and returns a CGImage using the data of specified image that
     * are contained within the given rectangle.
     *
     * @param image The image that is used.
     * @param rect  The rectangle that specifies which part of the image to use.
     * @return The new CGImage.
     */
    @CMFunction(" CGImageRef CGImageCreateWithImageInRect ( CGImageRef image, CGRect rect ); ")
    public static CGImage createWithImageInRect(CGImage image, CGRect rect) {
        Native.lifecycle().notImplemented();
        return null;
    }

    /**
     * Constructs and returns a CGImage from the specified PNG image.
     *
     * @param provider               The PNG image that is used for the CGImage.
     * @param decode                 The decode array to use, usually null.
     * @param shouldInterpolate      WHether interpolation is desired.
     * @param CGColorRenderingIntent The way color is handled.
     * @return The new CGImage object.
     */
    @CMFunction(" CGImageRef CGImageCreateWithPNGDataProvider ( CGDataProviderRef source, const CGFloat *decode, bool shouldInterpolate, CGColorRenderingIntent intent ); ")
    public static CGImage createWithPNGDataProvider(CGDataProvider provider, double[] decode, boolean shouldInterpolate, int CGColorRenderingIntent) {
        Native.lifecycle().notImplemented();
        return null;
    }

    @SuppressWarnings("LeakingThisInConstructor")
    CGImage(String filename, NativeBitmap bitmap) {
        if (filename == null && bitmap != null) {
            String tempFile = Native.file().getRandomLocation();
            try {
                Native.image().fillStreamAndClose(bitmap, ImageBridgeConstants.PNG, 1, new FileOutputStream(tempFile));
                filename = tempFile;
            } catch (IOException ex) {
                new File(tempFile).delete();
            }
        }
        Native.image().register(this);
        this.filename = filename;
        this.bitmap = bitmap;
        this.width = bitmap == null ? -1 : bitmap.getWidth();
        this.height = bitmap == null ? -1 : bitmap.getHeight();
    }

    /**
     * Returns the width of this image in pixels.
     *
     * @return The width of this image in pixels.
     */
    @CMFunction(" size_t CGImageGetWidth ( CGImageRef image ); ")
    public int getWidth() {
        if (width < 0)
            bitmap();   // retrieve bitmap and calculate size
        return width;
    }

    /**
     * Returns the height of this image in pixels.
     *
     * @return The height of this image in pixels.
     */
    @CMFunction(" size_t CGImageGetHeight ( CGImageRef image ); ")
    public int getHeight() {
        if (height < 0)
            bitmap();   // retrieve bitmap and calculate size
        return height;
    }

    NativeBitmap bitmap() {
        if (bitmap == null) {
            bitmap = Native.image().retrieve(filename);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
        return bitmap;
    }

    void trash() {
        bitmap = null;
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        super.finalize();
        if (Native.file().isTemporaryLocation(filename))
            Native.system().postOnEventThread(() -> new File(filename).delete());
    }

    String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return "CGImage(width=" + getWidth() + " height=" + getHeight() + " location=" + filename + ")";
    }
}