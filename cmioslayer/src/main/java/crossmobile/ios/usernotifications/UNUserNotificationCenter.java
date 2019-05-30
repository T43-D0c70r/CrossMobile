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
package crossmobile.ios.usernotifications;

import crossmobile.ios.foundation.NSError;
import crossmobile.ios.foundation.NSObject;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;
import org.crossmobile.bridge.ann.CMSetter;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.objc.block.VoidBlock2;

import java.util.List;
import java.util.Set;

@CMClass
public class UNUserNotificationCenter extends NSObject {

    private static UNUserNotificationCenter current;
    private final boolean supportsContentExtensions;
    private UNUserNotificationCenterDelegate delegate;
    private UNNotificationSettings settings;

    UNUserNotificationCenter(boolean supportsContentExtensions) {
        this.supportsContentExtensions = supportsContentExtensions;
        current = this;
    }

    @CMSelector("+(UNUserNotificationCenter *)currentNotificationCenter;")
    public static UNUserNotificationCenter currentNotificationCenter() {
        if (current == null)
            current = new UNUserNotificationCenter(false);
        return current;
    }

    @CMGetter("@property(weak, nonatomic) id<UNUserNotificationCenterDelegate> delegate;")
    public UNUserNotificationCenterDelegate delegate() {
        return delegate;
    }

    @CMSetter("@property(weak, nonatomic) id<UNUserNotificationCenterDelegate> delegate;")
    public void setDelegate(UNUserNotificationCenterDelegate delegate) {
        this.delegate = delegate;
    }

    @CMSelector("- (void)requestAuthorizationWithOptions:(UNAuthorizationOptions)options "
            + "completionHandler:(void(^)(BOOL granted, NSError *error))completionHandler;")
    public void requestAuthorizationWithOptions(long options, VoidBlock2<Boolean, NSError> completionHandler) {
        settings = new UNNotificationSettings(UNAuthorizationStatus.Authorized, UNNotificationSetting.Disabled, UNNotificationSetting.Disabled, UNNotificationSetting.Disabled, options & UNAuthorizationOptions.Alert, UNAlertStyle.None, options & UNAuthorizationOptions.Badge, options & UNAuthorizationOptions.Sound);
        Native.notification().requestAuthorizationWithOptions(completionHandler);
    }

    @CMSelector("- (void)getNotificationSettingsWithCompletionHandler:(void (^)(UNNotificationSettings *settings))completionHandler;")
    public void getNotificationSettingsWithCompletionHandler(VoidBlock1<UNNotificationSettings> completionHandler) {
        completionHandler.invoke(settings);
    }

    @CMGetter("@property(readonly, nonatomic) BOOL supportsContentExtensions;")
    public boolean supportsContentExtensions() {
        return supportsContentExtensions;
    }

    @CMSelector("- (void)setNotificationCategories:(NSSet<UNNotificationCategory *> *)categories;")
    public void setNotificationCategories(Set<UNNotificationCategory> categories) {

    }

    //This One
    @CMSelector("- (void)getNotificationCategoriesWithCompletionHandler:(void (^)(NSSet<UNNotificationCategory *> *categories))completionHandler;")
    public void getNotificationCategoriesWithCompletionHandler(VoidBlock1<Set<UNNotificationCategory>> completionHandler) {
        Set<UNNotificationCategory> categories = null;
        completionHandler.invoke(categories);
    }

    @CMSelector("- (void)addNotificationRequest:(UNNotificationRequest *)request "
            + "withCompletionHandler:(void (^)(NSError *error))completionHandler;")
    public void addNotificationRequest(UNNotificationRequest request, VoidBlock1<NSError> completionHandler) {

    }

    //This One
    @CMSelector("- (void)getPendingNotificationRequestsWithCompletionHandler:(void (^)(NSArray<UNNotificationRequest *> *requests))completionHandler;")
    public void getPendingNotificationRequestsWithCompletionHandler(VoidBlock1<List<UNNotificationRequest>> completionHandler) {

    }

    @CMSelector("- (void)removePendingNotificationRequestsWithIdentifiers:(NSArray<NSString *> *)identifiers;")
    public void removePendingNotificationRequestsWithIdentifiers(List<String> identifiers) {

    }

    @CMSelector("- (void)removeAllPendingNotificationRequests;")
    public void removeAllPendingNotificationRequests() {

    }

    //This One
    @CMSelector("- (void)getDeliveredNotificationsWithCompletionHandler:(void (^)(NSArray<UNNotification *> * notifications))completionHandler;")
    public void getDeliveredNotificationsWithCompletionHandler(VoidBlock1<List<UNNotification>> completionHandler) {

    }

    @CMSelector("- (void)removeDeliveredNotificationsWithIdentifiers:(NSArray<NSString *> *)identifiers;")
    public void removeDeliveredNotificationsWithIdentifiers(List<String> identifiers) {

    }

    @CMSelector("- (void)removeAllDeliveredNotifications;")
    public void removeAllDeliveredNotifications() {
        Native.notification().removeAllDeliveredNotifications();
    }

}