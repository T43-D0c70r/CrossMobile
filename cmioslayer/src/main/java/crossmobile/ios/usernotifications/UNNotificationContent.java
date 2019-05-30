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

import crossmobile.ios.foundation.NSObject;
import crossmobile.ios.foundation.NSSecureCoding;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;

import java.util.List;
import java.util.Map;

@CMClass
public class UNNotificationContent extends NSObject implements NSSecureCoding {

    private final String title;
    private final String subtitle;
    private final String body;
    private final Number badge;
    private final UNNotificationSound sound;
    private final String launchImageName;
    private final Map<String, Object> userInfo;
    private final List<UNNotificationAttachment> attachments;
    private final String categoryIdentifier;
    private final String threadIdentifier;

    UNNotificationContent() {
        this.title = null;
        this.subtitle = null;
        this.body = null;
        this.badge = null;
        this.sound = null;
        this.launchImageName = null;
        this.userInfo = null;
        this.attachments = null;
        this.categoryIdentifier = null;
        this.threadIdentifier = null;
    }

    UNNotificationContent(String title, String subtitle, String body, Number badge, UNNotificationSound sound, String launchImageName, Map<String, Object> userInfo, List<UNNotificationAttachment> attachments, String categoryIdentifier, String threadIdentifier) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
        this.badge = badge;
        this.sound = sound;
        this.launchImageName = launchImageName;
        this.userInfo = userInfo;
        this.attachments = attachments;
        this.categoryIdentifier = categoryIdentifier;
        this.threadIdentifier = threadIdentifier;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *title;")
    public String title() {
        return title;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *subtitle;")
    public String subtitle() {
        return subtitle;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *body;")
    public String body() {
        return body;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSNumber *badge;")
    public Number badge() {
        return badge;
    }

    @CMGetter("@property(readonly, copy, nonatomic) UNNotificationSound *sound;")
    public UNNotificationSound sound() {
        return sound;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *launchImageName;")
    public String launchImageName() {
        return launchImageName;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSDictionary *userInfo;")
    public Map<String, Object> userInfo() {
        return userInfo;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSArray<UNNotificationAttachment *> *attachments;")
    public List<UNNotificationAttachment> attachments() {
        return attachments;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *categoryIdentifier;")
    public String categoryIdentifier() {
        return categoryIdentifier;
    }

    @CMGetter("@property(readonly, copy, nonatomic) NSString *threadIdentifier;")
    public String threadIdentifier() {
        return threadIdentifier;
    }

}