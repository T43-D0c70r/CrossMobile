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
package org.crossmobile.backend.android.notifications;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import crossmobile.ios.foundation.NSData;
import crossmobile.ios.foundation.NSError;
import crossmobile.ios.uikit.UIApplication;
import crossmobile.ios.usernotifications.UNErrorCode;

public class CrossMobileFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "CrossMobileFirebaseIIDService";

    /**
     * The Application's current Instance ID token is no longer valid and thus a
     * new one must be requested.
     */
    @Override
    public void onTokenRefresh() {
        // If you need to handle the generation of a token, initially or after a refresh this is
        // where you should do that.

        String token = FirebaseInstanceId.getInstance().getToken();

        // Once a token is generated, we subscribe to topic.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        if (token != null)
            UIApplication
                    .sharedApplication()
                    .delegate()
                    .didRegisterForRemoteNotificationsWithDeviceToken(UIApplication.sharedApplication(),
                            NSData.dataWithBytes(token.getBytes()));
        else
            UIApplication
                    .sharedApplication()
                    .delegate()
                    .didFailToRegisterForRemoteNotificationsWithError(UIApplication.sharedApplication(),
                            NSError.errorWithDomain("UNNOTIFICATION ERROR",
                                    (int) UNErrorCode.NotificationsNotAllowed, null));
    }
}