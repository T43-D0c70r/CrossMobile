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
package crossmobile.ios.coreimage;

import crossmobile.ios.foundation.NSData;
import crossmobile.ios.foundation.NSObject;
import crossmobile.ios.foundation.NSSecureCoding;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.HashMap;
import java.util.Map;

/**
 * CIFilter class defines an object that uses input values as data(as images)
 * and produces images(data).
 */
@CMClass
public class CIFilter extends NSObject implements NSSecureCoding {

    private final Map<String, Object> attributes;

    /**
     * Constructs and returns a CIFilter object for the specified filter.
     *
     * @param name The name of the filter.
     * @return A CIFilter object.
     */
    @CMSelector("+ (CIFilter *)filterWithName:(NSString *)name;")
    public static CIFilter filterWithName(String name) {
        return null;
    }

    /**
     * Constructs and returns a CIFilter object using the specified data and
     * options.
     *
     * @param data    The image data to initialize the CIFilter object.
     * @param options A options dictionary.
     * @return A CIFilter object.
     */
    @CMSelector("+ (CIFilter *)filterWithImageData:(NSData *)data \n"
            + " options:(NSDictionary *)options;")
    public static CIFilter filterWithImageData(NSData data, Map<String, Object> options) {
        return null;
    }

    private CIFilter(Map<String, Object> options) {
        this.attributes = options == null ? new HashMap<>() : options;
    }

    /**
     * Returns the CIImage object for this filter.
     *
     * @return The CIImage object that is the result of applying this CIFilter.
     */
    @CMGetter("@property(readonly, nonatomic) CIImage *outputImage;")
    public CIImage outputImage() {
        return null;
    }

    /**
     * Returns a dictionary with values that describe the filter.
     *
     * @return The dictionary with values that describe the filter.
     */
    @CMGetter("@property(nonatomic, readonly) NSDictionary<NSString *,id> *attributes;")
    public Map<String, Object> attributes() {
        return attributes;
    }

}