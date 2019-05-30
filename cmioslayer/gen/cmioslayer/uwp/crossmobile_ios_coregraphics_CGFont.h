// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.coregraphics.CGFont definition

#import "xmlvm.h"
#import <CoreGraphics/CoreGraphics.h>
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "crossmobile_ios_foundation_CFType.h"
@class crossmobile_ios_coregraphics_CGDataProvider;
@class java_lang_Object;
@class java_lang_String;

@interface crossmobile_ios_coregraphics_CGFont : crossmobile_ios_foundation_CFType
+ (crossmobile_ios_coregraphics_CGFont*) createWithDataProvider___crossmobile_ios_coregraphics_CGDataProvider:(crossmobile_ios_coregraphics_CGDataProvider*) provider ;
+ (crossmobile_ios_coregraphics_CGFont*) createWithFontName___java_lang_String:(NSString*) name ;
- (int) getAscent__;
- (int) getDescent__;
- (int) getUnitsPerEm__;
- (instancetype) initWithCGFont:(CGFontRef) reference;
@end