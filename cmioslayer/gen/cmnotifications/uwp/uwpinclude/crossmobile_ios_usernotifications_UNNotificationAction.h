// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.usernotifications.UNNotificationAction definition

#import "xmlvm.h"
#import <UserNotifications/UserNotifications.h>
@class java_lang_String;

CM_EXPORT_CLASS
@interface crossmobile_ios_usernotifications_UNNotificationAction$Ext : UNNotificationAction
@end

#define crossmobile_ios_usernotifications_UNNotificationAction UNNotificationAction
@interface UNNotificationAction (cm_crossmobile_ios_usernotifications_UNNotificationAction)
+ (instancetype) actionWithIdentifier___java_lang_String_java_lang_String_long:(NSString*) identifier :(NSString*) title :(JAVA_LONG) options ;
- (NSString*) identifier__;
- (JAVA_LONG) options__;
- (NSString*) title__;
@end