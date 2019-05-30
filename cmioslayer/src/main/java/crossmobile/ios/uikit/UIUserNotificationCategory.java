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

import crossmobile.ios.foundation.NSObject;
import crossmobile.ios.foundation.NSSecureCoding;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.List;
import java.util.Map;

/**
 * UIUserNotificationCategory class defines an object that holds information
 * related to the custom actions executed in response to local or push
 * notifications.
 */
@CMClass
public class UIUserNotificationCategory extends NSObject implements NSSecureCoding {

    String identifier;
    Map<Integer, List<UIUserNotificationAction>> actions;

    /**
     * Returns the name of the action group.
     *
     * @return The name of the action group.
     */
    @CMGetter("@property(nonatomic, copy, readonly) NSString *identifier;")
    public String identifier() {
        return identifier;
    }

    /**
     * Returns a list of all the actions for the specified notification context.
     *
     * @param UIUserNotificationActionContext The notification context for which
     *                                        the action are requested.
     * @return The list of all the actions for the specified notification
     * context.
     * @see crossmobile.ios.uikit.UIUserNotificationActionContext
     */
    @CMSelector("- (NSArray<UIUserNotificationAction *> *)actionsForContext:(UIUserNotificationActionContext)context;")
    public List<UIUserNotificationAction> actionsForContext(int UIUserNotificationActionContext) {
        return actions == null ? null : actions.get(UIUserNotificationActionContext);
    }
}