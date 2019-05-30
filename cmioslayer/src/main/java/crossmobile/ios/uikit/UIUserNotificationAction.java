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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * UIUserNotificationAction defines an object that represents the custom action
 * of the application in response to a notification.
 */
@CMClass
public class UIUserNotificationAction extends NSObject implements NSSecureCoding {

    String identifier;
    String title;
    int activationMode;
    boolean authenticationRequired;
    boolean destructive;
    int behavior;
    Map parameters;

    public static final String ResponseTypedTextKey = "UIUserNotificationActionResponseTypedTextKey";

    /**
     * Returns the identifier of this action.
     *
     * @return The identifier of this action.
     */
    @CMGetter("@property(nonatomic, copy, readonly) NSString *identifier;")
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the text displayed on the button of the action.
     *
     * @return The text displayed on the button of the action.
     */
    @CMGetter("@property(nonatomic, copy, readonly) NSString *title;")
    public String title() {
        return title;
    }

    /**
     * Returns a Boolean value that shows whether unlocking the device is needed
     * before the action.
     *
     * @return TRUE then the user must unlock the device before the action.
     */
    @CMGetter("@property(nonatomic, assign, readonly, getter=isAuthenticationRequired) BOOL authenticationRequired;")
    public boolean isAuthenticationRequired() {
        return activationMode == UIUserNotificationActivationMode.Foreground || authenticationRequired;
    }

    /**
     * Returns a Boolean that shows whether is action is destructive.
     *
     * @return TRUE then the action is destructive.
     */
    @CMGetter("@property(nonatomic, assign, readonly, getter=isDestructive) BOOL destructive;")
    public boolean isDestructive() {
        return destructive;
    }

    /**
     * Returns the supported custom behavior of the action.
     *
     * @return The supported custom behavior of the action.
     * @see crossmobile.ios.uikit.UIUserNotificationActionBehavior
     */
    @CMGetter("@property(nonatomic, assign, readonly) UIUserNotificationActionBehavior behavior;")
    public int behavior() {
        return behavior;
    }

    /**
     * Returns the mode of the application when action is performed.
     *
     * @return The mode of the application when action is performed.
     * @see crossmobile.ios.uikit.UIUserNotificationActivationMode
     */
    @CMGetter("@property(nonatomic, assign, readonly) UIUserNotificationActivationMode activationMode;")
    public int activationMode() {
        return activationMode;
    }

    // TODO

    /**
     * Returns a dictionary with additional parameters for the action.
     *
     * @return A a dictionary with additional parameters for the action.
     */
    @CMGetter("@property(nonatomic, copy, readonly) NSDictionary *parameters;")
    public Map parameters() {
        return new HashMap(parameters == null ? Collections.EMPTY_MAP : parameters);
    }

}