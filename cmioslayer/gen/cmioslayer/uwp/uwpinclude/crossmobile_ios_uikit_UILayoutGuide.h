// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UILayoutGuide definition

#import "xmlvm.h"
#import <CoreGraphics/CoreGraphics.h>
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@class crossmobile_ios_coregraphics_CGRect;
@class crossmobile_ios_uikit_NSLayoutDimension;
@class crossmobile_ios_uikit_NSLayoutXAxisAnchor;
@class crossmobile_ios_uikit_NSLayoutYAxisAnchor;
@class crossmobile_ios_uikit_UIView;
@class java_lang_String;

CM_EXPORT_CLASS
@interface crossmobile_ios_uikit_UILayoutGuide$Ext : UILayoutGuide
@end

#define crossmobile_ios_uikit_UILayoutGuide UILayoutGuide
@interface UILayoutGuide (cm_crossmobile_ios_uikit_UILayoutGuide)
- (instancetype) __init_crossmobile_ios_uikit_UILayoutGuide__;
- (NSLayoutYAxisAnchor*) bottomAnchor__;
- (NSLayoutXAxisAnchor*) centerXAnchor__;
- (NSLayoutYAxisAnchor*) centerYAnchor__;
- (NSLayoutDimension*) heightAnchor__;
- (void) setIdentifier___java_lang_String:(NSString*) identifier ;
- (NSString*) identifier__;
- (crossmobile_ios_coregraphics_CGRect*) layoutFrame__;
- (NSLayoutXAxisAnchor*) leadingAnchor__;
- (NSLayoutXAxisAnchor*) leftAnchor__;
- (void) setOwningView___crossmobile_ios_uikit_UIView:(UIView*) owningView ;
- (UIView*) owningView__;
- (NSLayoutXAxisAnchor*) rightAnchor__;
- (NSLayoutYAxisAnchor*) topAnchor__;
- (NSLayoutXAxisAnchor*) trailingAnchor__;
- (NSLayoutDimension*) widthAnchor__;
@end