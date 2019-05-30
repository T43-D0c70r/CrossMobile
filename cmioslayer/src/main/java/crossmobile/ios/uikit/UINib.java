package crossmobile.ios.uikit;

import crossmobile.ios.foundation.NSBundle;
import crossmobile.ios.foundation.NSData;
import crossmobile.ios.foundation.NSObject;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CMClass
public class UINib extends NSObject {
    //TODO method implmentations
    public static final String ExternalObjects = "UINibExternalObjects";

    @CMSelector("+ (UINib *)nibWithNibName:(NSString *)name bundle:(NSBundle *)bundleOrNil;")
    public static UINib nibWithNibName(String name, NSBundle bundleOrNil) {
        if (bundleOrNil == null)
            bundleOrNil = NSBundle.mainBundle();
        return new UINib();
    }

    @CMSelector("+ (UINib *)nibWithData:(NSData *)data bundle:(NSBundle *)bundleOrNil;")
    public static UINib nibWithData(NSData data, NSBundle bundleOrNil) {
        return new UINib();
    }

    @CMSelector("- (NSArray *)instantiateWithOwner:(id)ownerOrNil options:(NSDictionary *)optionsOrNil;")
    public List instantiateWithOwner(NSObject ownerOrNil, Map optionsOrNil) {
        return new ArrayList();
    }
}