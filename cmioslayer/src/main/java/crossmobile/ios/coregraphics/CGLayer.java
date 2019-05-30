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
import org.crossmobile.bridge.ann.CMFunction;
import org.crossmobile.bridge.ann.CMReference;

import java.util.Map;

/**
 * CGLayer class defines a drawing optimization object.
 */
@CMReference
public class CGLayer extends CFType {

    private final CGContext context;
    private final CGSize size;

    /**
     * Creates and returns a CGLayer object with the specified parameter values.
     *
     * @param context    The graphics context of this CGLayer object.
     * @param size       The size of this CGLayer object.
     * @param dictionary The dictionary related to this CGLayer object.
     * @return The new CGLayer object.
     */
    @CMFunction(" CGLayerRef CGLayerCreateWithContext ( CGContextRef context, CGSize size, CFDictionaryRef auxiliaryInfo ); ")
    public static CGLayer createWithContext(CGContext context, CGSize size, Map dictionary) {
        return new CGLayer(context, size, dictionary);
    }

    private CGLayer(CGContext context, CGSize size, Map dictionary) {
        this.context = context;
        this.size = new CGSize(size);
    }

    /**
     * Returns the graphics context of this CGLayer object.
     *
     * @return The graphics context of this CGLayer object.
     */
    @CMFunction(" CGContextRef CGLayerGetContext ( CGLayerRef layer ); ")
    public CGContext getContext() {
        return context;
    }

    /**
     * Returns the width and height of this CGLayer object.
     *
     * @return The width and height of this CGLayer object.
     */
    @CMFunction(" CGSize CGLayerGetSize ( CGLayerRef layer ); ")
    public CGSize getSize() {
        return new CGSize(size);
    }
}