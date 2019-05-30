// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.mapkit.MKPinAnnotationView definition

#import "xmlvm.h"
#import <CoreLocation/CoreLocation.h>
#import <MapKit/MapKit.h>
@protocol crossmobile_ios_mapkit_MKAnnotation;
@class java_lang_String;

CM_EXPORT_CLASS
@interface crossmobile_ios_mapkit_MKPinAnnotationView$Ext : MKPinAnnotationView
@end

#define crossmobile_ios_mapkit_MKPinAnnotationView MKPinAnnotationView
@interface MKPinAnnotationView (cm_crossmobile_ios_mapkit_MKPinAnnotationView)
- (instancetype) __init_crossmobile_ios_mapkit_MKPinAnnotationView___crossmobile_ios_mapkit_MKAnnotation_java_lang_String:(id<MKAnnotation>) annotation :(NSString*) reuseIdentifier ;
- (void) setAnimatesDrop___boolean:(BOOL) animatesDrop ;
- (BOOL) animatesDrop__;
- (void) setPinColor___int:(int) pinColor ;
- (int) pinColor__;
@end