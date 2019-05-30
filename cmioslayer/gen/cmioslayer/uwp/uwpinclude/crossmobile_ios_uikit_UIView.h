// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UIView definition

#import "xmlvm.h"
#import <CoreGraphics/CoreGraphics.h>
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@class crossmobile_ios_coregraphics_CGAffineTransform;
@class crossmobile_ios_coregraphics_CGPoint;
@class crossmobile_ios_coregraphics_CGRect;
@class crossmobile_ios_coregraphics_CGSize;
@class crossmobile_ios_foundation_NSDate;
@class crossmobile_ios_quartzcore_CALayer;
@class crossmobile_ios_uikit_NSLayoutConstraint;
@class crossmobile_ios_uikit_NSLayoutDimension;
@class crossmobile_ios_uikit_NSLayoutXAxisAnchor;
@class crossmobile_ios_uikit_NSLayoutYAxisAnchor;
@class crossmobile_ios_uikit_UIColor;
@class crossmobile_ios_uikit_UIEdgeInsets;
@class crossmobile_ios_uikit_UIEvent;
@class crossmobile_ios_uikit_UIGestureRecognizer;
@class crossmobile_ios_uikit_UILayoutGuide;
@class crossmobile_ios_uikit_UIWindow;
@class java_lang_Boolean;
@protocol java_lang_Runnable;
@class java_lang_String;
@protocol java_util_List;
@protocol org_robovm_objc_block_VoidBlock1;

CM_EXPORT_CLASS
@interface crossmobile_ios_uikit_UIView$Ext : UIView
@end

#define crossmobile_ios_uikit_UIView UIView
@interface UIView (cm_crossmobile_ios_uikit_UIView)
+ (void) animateWithDuration___double_double_int_java_lang_Runnable_org_robovm_objc_block_VoidBlock1:(double) duration :(double) delay :(int) options :(id<java_lang_Runnable>) animations :(id<org_robovm_objc_block_VoidBlock1>) completion ;
+ (void) animateWithDuration___double_java_lang_Runnable:(double) duration :(id<java_lang_Runnable>) animations ;
+ (void) animateWithDuration___double_java_lang_Runnable_org_robovm_objc_block_VoidBlock1:(double) duration :(id<java_lang_Runnable>) animations :(id<org_robovm_objc_block_VoidBlock1>) completion ;
+ (BOOL) areAnimationsEnabled__;
+ (BOOL) requiresConstraintBasedLayout__;
+ (void) setAnimationBeginsFromCurrentState___boolean:(BOOL) fromCurrentState ;
+ (void) setAnimationCurve___int:(int) curve ;
+ (void) setAnimationDelay___double:(double) delay ;
+ (void) setAnimationDuration___double:(double) duration ;
+ (void) setAnimationRepeatAutoreverses___boolean:(BOOL) repeatAutoreverses ;
+ (void) setAnimationRepeatCount___float:(float) repeatCount ;
+ (void) setAnimationStartDate___crossmobile_ios_foundation_NSDate:(NSDate*) startDate ;
+ (void) setAnimationTransition___int_crossmobile_ios_uikit_UIView_boolean:(int) transition :(UIView*) view :(BOOL) cache ;
+ (void) setAnimationsEnabled___boolean:(BOOL) enabled ;
- (instancetype) __init_crossmobile_ios_uikit_UIView__;
- (instancetype) __init_crossmobile_ios_uikit_UIView___crossmobile_ios_coregraphics_CGRect:(crossmobile_ios_coregraphics_CGRect*) frame ;
- (void) setAlpha___double:(double) alpha ;
- (double) alpha__;
- (void) setAutoresizesSubviews___boolean:(BOOL) autoresizesSubviews ;
- (BOOL) autoresizesSubviews__;
- (void) setAutoresizingMask___int:(int) autoresizingMask ;
- (int) autoresizingMask__;
- (void) setBackgroundColor___crossmobile_ios_uikit_UIColor:(UIColor*) backgroundColor ;
- (UIColor*) backgroundColor__;
- (NSLayoutYAxisAnchor*) bottomAnchor__;
- (void) setBounds___crossmobile_ios_coregraphics_CGRect:(crossmobile_ios_coregraphics_CGRect*) bounds ;
- (crossmobile_ios_coregraphics_CGRect*) bounds__;
- (void) setCenter___crossmobile_ios_coregraphics_CGPoint:(crossmobile_ios_coregraphics_CGPoint*) center ;
- (crossmobile_ios_coregraphics_CGPoint*) center__;
- (NSLayoutXAxisAnchor*) centerXAnchor__;
- (NSLayoutYAxisAnchor*) centerYAnchor__;
- (void) setClearsContextBeforeDrawing___boolean:(BOOL) clearsContextBeforeDrawing ;
- (void) setClipsToBounds___boolean:(BOOL) clipsToBounds ;
- (BOOL) clipsToBounds__;
- (NSArray*) constraints__;
- (void) setContentMode___int:(int) contentMode ;
- (int) contentMode__;
- (void) setFrame___crossmobile_ios_coregraphics_CGRect:(crossmobile_ios_coregraphics_CGRect*) frame ;
- (crossmobile_ios_coregraphics_CGRect*) frame__;
- (void) setGestureRecognizers___java_util_List:(NSArray*) gestureRecognizers ;
- (NSArray*) gestureRecognizers__;
- (NSLayoutDimension*) heightAnchor__;
- (void) setHidden___boolean:(BOOL) hidden ;
- (BOOL) isHidden__;
- (void) setInsetsLayoutMarginsFromSafeArea___boolean:(BOOL) insetsLayoutMarginsFromSafeArea ;
- (BOOL) insetsLayoutMarginsFromSafeArea__;
- (CALayer*) layer__;
- (void) setLayoutMargins___crossmobile_ios_uikit_UIEdgeInsets:(crossmobile_ios_uikit_UIEdgeInsets*) layoutMargins ;
- (crossmobile_ios_uikit_UIEdgeInsets*) layoutMargins__;
- (UILayoutGuide*) layoutMarginsGuide__;
- (NSLayoutXAxisAnchor*) leadingAnchor__;
- (NSLayoutXAxisAnchor*) leftAnchor__;
- (void) setMultipleTouchEnabled___boolean:(BOOL) multipleTouchEnabled ;
- (BOOL) isMultipleTouchEnabled__;
- (void) setOpaque___boolean:(BOOL) opaque ;
- (BOOL) isOpaque__;
- (void) setPreservesSuperviewLayoutMargins___boolean:(BOOL) preservesSuperviewLayoutMargins ;
- (BOOL) preservesSuperviewLayoutMargins__;
- (void) setRestorationIdentifier___java_lang_String:(NSString*) restorationIdentifier ;
- (NSString*) restorationIdentifier__;
- (NSLayoutXAxisAnchor*) rightAnchor__;
- (crossmobile_ios_uikit_UIEdgeInsets*) safeAreaInsets__;
- (UILayoutGuide*) safeAreaLayoutGuide__;
- (NSArray*) subviews__;
- (UIView*) superview__;
- (void) setTag___int:(int) tag ;
- (int) tag__;
- (void) setTintAdjustmentMode___int:(int) tintAdjustmentMode ;
- (int) tintAdjustmentMode__;
- (void) setTintColor___crossmobile_ios_uikit_UIColor:(UIColor*) tintColor ;
- (UIColor*) tintColor__;
- (NSLayoutYAxisAnchor*) topAnchor__;
- (NSLayoutXAxisAnchor*) trailingAnchor__;
- (void) setTransform___crossmobile_ios_coregraphics_CGAffineTransform:(crossmobile_ios_coregraphics_CGAffineTransform*) transform ;
- (crossmobile_ios_coregraphics_CGAffineTransform*) transform__;
- (void) setTranslatesAutoresizingMaskIntoConstraints___boolean:(BOOL) translatesAutoresizingMaskIntoConstraints ;
- (BOOL) translatesAutoresizingMaskIntoConstraints__;
- (void) setUserInteractionEnabled___boolean:(BOOL) userInteractionEnabled ;
- (BOOL) isUserInteractionEnabled__;
- (int) userInterfaceLayoutDirection__;
- (UIView*) viewForFirstBaselineLayout__;
- (UIView*) viewForLastBaselineLayout__;
- (NSLayoutDimension*) widthAnchor__;
- (UIWindow*) window__;
- (void) addConstraint___crossmobile_ios_uikit_NSLayoutConstraint:(NSLayoutConstraint*) constraint ;
- (void) addConstraints___java_util_List:(NSArray*) constraints ;
- (void) addGestureRecognizer___crossmobile_ios_uikit_UIGestureRecognizer:(UIGestureRecognizer*) gestureRecognizer ;
- (void) addLayoutGuide___crossmobile_ios_uikit_UILayoutGuide:(UILayoutGuide*) layoutGuide ;
- (void) addSubview___crossmobile_ios_uikit_UIView:(UIView*) view ;
- (void) bringSubviewToFront___crossmobile_ios_uikit_UIView:(UIView*) view ;
- (float) contentCompressionResistancePriorityForAxis___int:(int) axis ;
- (float) contentHuggingPriorityForAxis___int:(int) axis ;
- (crossmobile_ios_coregraphics_CGPoint*) convertPointFromView___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGPoint*) point :(UIView*) view ;
- (crossmobile_ios_coregraphics_CGPoint*) convertPointToView___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGPoint*) point :(UIView*) view ;
- (crossmobile_ios_coregraphics_CGRect*) convertRectFromView___crossmobile_ios_coregraphics_CGRect_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGRect*) rect :(UIView*) view ;
- (crossmobile_ios_coregraphics_CGRect*) convertRectToView___crossmobile_ios_coregraphics_CGRect_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGRect*) rect :(UIView*) view ;
- (void) didAddSubview___crossmobile_ios_uikit_UIView:(UIView*) subview ;
- (void) didMoveToSuperview__;
- (void) didMoveToWindow__;
- (void) drawRect___crossmobile_ios_coregraphics_CGRect:(crossmobile_ios_coregraphics_CGRect*) rect ;
- (UIView*) hitTest___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIEvent:(crossmobile_ios_coregraphics_CGPoint*) point :(UIEvent*) event ;
- (void) insertSubview___crossmobile_ios_uikit_UIView_int:(UIView*) view :(int) index ;
- (crossmobile_ios_coregraphics_CGSize*) intrinsicContentSize__;
- (void) invalidateIntrinsicContentSize__;
- (void) layoutIfNeeded__;
- (void) layoutMarginsDidChange__;
- (void) layoutSubviews__;
- (BOOL) needsUpdateConstraints__;
- (BOOL) pointInside___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIEvent:(crossmobile_ios_coregraphics_CGPoint*) point :(UIEvent*) event ;
- (void) removeConstraint___crossmobile_ios_uikit_NSLayoutConstraint:(NSLayoutConstraint*) constraint ;
- (void) removeConstraints___java_util_List:(NSArray*) constraints ;
- (void) removeFromSuperview__;
- (void) removeGestureRecognizer___crossmobile_ios_uikit_UIGestureRecognizer:(UIGestureRecognizer*) gestureRecognizer ;
- (void) removeLayoutGuide___crossmobile_ios_uikit_UILayoutGuide:(UILayoutGuide*) layoutGuide ;
- (void) sendSubviewToBack___crossmobile_ios_uikit_UIView:(UIView*) view ;
- (void) setContentCompressionResistancePriority___float_int:(float) priority :(int) axis ;
- (void) setContentHuggingPriority___float_int:(float) priority :(int) axis ;
- (void) setNeedsDisplay__;
- (void) setNeedsLayout__;
- (void) setNeedsUpdateConstraints__;
- (crossmobile_ios_coregraphics_CGSize*) sizeThatFits___crossmobile_ios_coregraphics_CGSize:(crossmobile_ios_coregraphics_CGSize*) size ;
- (void) sizeToFit__;
- (void) tintColorDidChange__;
- (void) updateConstraints__;
- (void) updateConstraintsIfNeeded__;
- (void) willMoveToSuperview___crossmobile_ios_uikit_UIView:(UIView*) newSuperview ;
- (void) willMoveToWindow___crossmobile_ios_uikit_UIWindow:(UIWindow*) newWindow ;
- (void) willRemoveSubview___crossmobile_ios_uikit_UIView:(UIView*) subview ;
@end