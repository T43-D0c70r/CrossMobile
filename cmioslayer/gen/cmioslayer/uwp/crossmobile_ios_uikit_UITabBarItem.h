// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UITabBarItem definition

#import "xmlvm.h"
#import <CoreGraphics/CoreGraphics.h>
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@class crossmobile_ios_uikit_UIImage;
@class java_lang_String;

@interface crossmobile_ios_uikit_UITabBarItem$Ext : UITabBarItem
@end

#define crossmobile_ios_uikit_UITabBarItem UITabBarItem
@interface UITabBarItem (cm_crossmobile_ios_uikit_UITabBarItem)
- (instancetype) __init_crossmobile_ios_uikit_UITabBarItem___java_lang_String_crossmobile_ios_uikit_UIImage_int:(NSString*) title :(UIImage*) image :(int) tag ;
- (void) setBadgeValue___java_lang_String:(NSString*) badgeValue ;
- (NSString*) badgeValue__;
@end