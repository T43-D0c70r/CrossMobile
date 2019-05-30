// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UIPanGestureRecognizer implementation

#import "crossmobile_ios_coregraphics_CGPoint.h"
#import "crossmobile_ios_foundation_NSSelector.h"
#import "crossmobile_ios_uikit_UIEvent.h"
#import "crossmobile_ios_uikit_UIGestureRecognizer.h"
#import "crossmobile_ios_uikit_UIGestureRecognizerDelegate.h"
#import "crossmobile_ios_uikit_UIPanGestureRecognizer.h"
#import "crossmobile_ios_uikit_UIView.h"
#import "java_lang_Object.h"
#import "java_lang_String.h"
#import "java_lang_reflect_Method.h"
#import "java_util_Set.h"

@implementation crossmobile_ios_uikit_UIPanGestureRecognizer$Ext

// (UIGestureRecognizer) @property(nonatomic) BOOL cancelsTouchesInView;
- (void) setCancelsTouchesInView___boolean:(BOOL) cancelsTouchesInView 
{
    [super setCancelsTouchesInView:cancelsTouchesInView];
}

// (UIGestureRecognizer) @property(nonatomic) BOOL cancelsTouchesInView;
- (BOOL) cancelsTouchesInView__
{
    return [super cancelsTouchesInView];
}

// (UIGestureRecognizer) @property(nonatomic) BOOL delaysTouchesBegan;
- (void) setDelaysTouchesBegan___boolean:(BOOL) delaysTouchesBegan 
{
    [super setDelaysTouchesBegan:delaysTouchesBegan];
}

// (UIGestureRecognizer) @property(nonatomic) BOOL delaysTouchesBegan;
- (BOOL) delaysTouchesBegan__
{
    return [super delaysTouchesBegan];
}

// (UIGestureRecognizer) @property(nonatomic) BOOL delaysTouchesEnded;
- (void) setDelaysTouchesEnded___boolean:(BOOL) delaysTouchesEnded 
{
    [super setDelaysTouchesEnded:delaysTouchesEnded];
}

// (UIGestureRecognizer) @property(nonatomic) BOOL delaysTouchesEnded;
- (BOOL) delaysTouchesEnded__
{
    return [super delaysTouchesEnded];
}

// (UIGestureRecognizer) @property(nonatomic, weak) id<UIGestureRecognizerDelegate> delegate;
- (void) setDelegate___crossmobile_ios_uikit_UIGestureRecognizerDelegate:(id<UIGestureRecognizerDelegate>) delegate 
{
    objc_setAssociatedObject(self, @selector(setDelegate:), delegate, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    [super setDelegate:(delegate == JAVA_NULL ? nil : delegate)];
}

// (UIGestureRecognizer) @property(nonatomic, weak) id<UIGestureRecognizerDelegate> delegate;
- (id<UIGestureRecognizerDelegate>) delegate__
{
    id<UIGestureRecognizerDelegate> re$ult = [super delegate];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIGestureRecognizer) @property(nonatomic, getter=isEnabled) BOOL enabled;
- (void) setEnabled___boolean:(BOOL) enabled 
{
    [super setEnabled:enabled];
}

// (UIGestureRecognizer) @property(nonatomic, getter=isEnabled) BOOL enabled;
- (BOOL) isEnabled__
{
    return [super isEnabled];
}

// (UIPanGestureRecognizer) @property(nonatomic) NSUInteger maximumNumberOfTouches;
- (void) setMaximumNumberOfTouches___int:(int) maximumNumberOfTouches 
{
    [super setMaximumNumberOfTouches:maximumNumberOfTouches];
}

// (UIPanGestureRecognizer) @property(nonatomic) NSUInteger maximumNumberOfTouches;
- (int) maximumNumberOfTouches__
{
    return [super maximumNumberOfTouches];
}

// (UIPanGestureRecognizer) @property(nonatomic) NSUInteger minimumNumberOfTouches;
- (void) setMinimumNumberOfTouches___int:(int) minimumNumberOfTouches 
{
    [super setMinimumNumberOfTouches:minimumNumberOfTouches];
}

// (UIPanGestureRecognizer) @property(nonatomic) NSUInteger minimumNumberOfTouches;
- (int) minimumNumberOfTouches__
{
    return [super minimumNumberOfTouches];
}

// (UIGestureRecognizer) @property(nonatomic, readonly) UIGestureRecognizerState state;
- (int) state__
{
    return [super state];
}

// (UIGestureRecognizer) @property(nonatomic, readonly) UIView *view;
- (UIView*) view__
{
    UIView* re$ult = [super view];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIGestureRecognizer) - (void)addTarget:(id)target action:(SEL)action;
- (void) addTarget___crossmobile_ios_foundation_NSSelector:(id<crossmobile_ios_foundation_NSSelector>) target 
{
    objc_setAssociatedObject(self, (void*)target, target, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    [super addTarget:(target == JAVA_NULL ? nil : target) action:@selector(exec___java_lang_Object:)];
}

// (UIGestureRecognizer) - (BOOL)canBePreventedByGestureRecognizer:(UIGestureRecognizer *)preventingGestureRecognizer;
- (BOOL) canBePreventedByGestureRecognizer___crossmobile_ios_uikit_UIGestureRecognizer:(UIGestureRecognizer*) preventingGestureRecognizer 
{
    return [super canBePreventedByGestureRecognizer:(preventingGestureRecognizer == JAVA_NULL ? nil : preventingGestureRecognizer)];
}

// (UIGestureRecognizer) - (BOOL)canPreventGestureRecognizer:(UIGestureRecognizer *)preventedGestureRecognizer;
- (BOOL) canPreventGestureRecognizer___crossmobile_ios_uikit_UIGestureRecognizer:(UIGestureRecognizer*) preventedGestureRecognizer 
{
    return [super canPreventGestureRecognizer:(preventedGestureRecognizer == JAVA_NULL ? nil : preventedGestureRecognizer)];
}

// (UIGestureRecognizer) - (CGPoint)locationInView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) locationInView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[super locationInView:(view == JAVA_NULL ? nil : view)]];
}

// (UIGestureRecognizer) - (CGPoint)locationOfTouch:(NSUInteger)touchIndex inView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) locationOfTouch___int_crossmobile_ios_uikit_UIView:(int) touchIndex :(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[super locationOfTouch:touchIndex inView:(view == JAVA_NULL ? nil : view)]];
}

// (UIGestureRecognizer) - (NSUInteger)numberOfTouches;
- (int) numberOfTouches__
{
    return [super numberOfTouches];
}

// (UIGestureRecognizer) - (void)removeTarget:(id)target action:(SEL)action;
- (void) removeTarget___crossmobile_ios_foundation_NSSelector:(id<crossmobile_ios_foundation_NSSelector>) target 
{
    objc_setAssociatedObject(self, (void*)target, nil, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    [super removeTarget:(target == JAVA_NULL ? nil : target) action:@selector(exec___java_lang_Object:)];
}

// (UIGestureRecognizer) - (void)requireGestureRecognizerToFail:(UIGestureRecognizer *)otherGestureRecognizer;
- (void) requireGestureRecognizerToFail___crossmobile_ios_uikit_UIGestureRecognizer:(UIGestureRecognizer*) otherGestureRecognizer 
{
    [super requireGestureRecognizerToFail:(otherGestureRecognizer == JAVA_NULL ? nil : otherGestureRecognizer)];
}

// (UIGestureRecognizer) - (void)reset;
- (void) reset__
{
    [super reset];
}

// (UIPanGestureRecognizer) - (void)setTranslation:(CGPoint)translation inView:(UIView *)view;
- (void) setTranslation___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGPoint*) translation :(UIView*) view 
{
    [super setTranslation:[translation getCGPoint] inView:(view == JAVA_NULL ? nil : view)];
}

// (NSObject) - (void)setValue:(id)value forKey:(NSString *)key;
- (void) setValue___java_lang_Object_java_lang_String:(id) value :(NSString*) key 
{
    [super setValue:(value == JAVA_NULL ? nil : value) forKey:(key == JAVA_NULL ? nil : key)];
}

// (UIGestureRecognizer) - (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesBegan___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesBegan:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIGestureRecognizer) - (void)touchesCancelled:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesCancelled___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesCancelled:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIGestureRecognizer) - (void)touchesEnded:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesEnded___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesEnded:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIGestureRecognizer) - (void)touchesMoved:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event;
- (void) touchesMoved___java_util_Set_crossmobile_ios_uikit_UIEvent:(NSSet*) touches :(UIEvent*) event 
{
    [super touchesMoved:(touches == JAVA_NULL ? nil : touches) withEvent:(event == JAVA_NULL ? nil : event)];
}

// (UIPanGestureRecognizer) - (CGPoint)translationInView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) translationInView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[super translationInView:(view == JAVA_NULL ? nil : view)]];
}

// (NSObject) - (id)valueForKey:(NSString *)key;
- (id) valueForKey___java_lang_String:(NSString*) key 
{
    id re$ult = [super valueForKey:(key == JAVA_NULL ? nil : key)];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (UIPanGestureRecognizer) - (CGPoint)velocityInView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) velocityInView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[super velocityInView:(view == JAVA_NULL ? nil : view)]];
}

@end

@implementation UIPanGestureRecognizer (cm_crossmobile_ios_uikit_UIPanGestureRecognizer)

// direct binding of: - (instancetype)initWithTarget:(id)target action:(SEL)action;
- (instancetype) __init_crossmobile_ios_uikit_UIPanGestureRecognizer___crossmobile_ios_foundation_NSSelector:(id<crossmobile_ios_foundation_NSSelector>) target 
{
    objc_setAssociatedObject(self, (void*)target, target, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
    return [self initWithTarget:(target == JAVA_NULL ? nil : target) action:@selector(exec___java_lang_Object:)];
}

// direct binding of: @property(nonatomic) NSUInteger maximumNumberOfTouches;
- (void) setMaximumNumberOfTouches___int:(int) maximumNumberOfTouches 
{
    [self setMaximumNumberOfTouches:maximumNumberOfTouches];
}

// direct binding of: @property(nonatomic) NSUInteger maximumNumberOfTouches;
- (int) maximumNumberOfTouches__
{
    return [self maximumNumberOfTouches];
}

// direct binding of: @property(nonatomic) NSUInteger minimumNumberOfTouches;
- (void) setMinimumNumberOfTouches___int:(int) minimumNumberOfTouches 
{
    [self setMinimumNumberOfTouches:minimumNumberOfTouches];
}

// direct binding of: @property(nonatomic) NSUInteger minimumNumberOfTouches;
- (int) minimumNumberOfTouches__
{
    return [self minimumNumberOfTouches];
}

// direct binding of: - (void)setTranslation:(CGPoint)translation inView:(UIView *)view;
- (void) setTranslation___crossmobile_ios_coregraphics_CGPoint_crossmobile_ios_uikit_UIView:(crossmobile_ios_coregraphics_CGPoint*) translation :(UIView*) view 
{
    [self setTranslation:[translation getCGPoint] inView:(view == JAVA_NULL ? nil : view)];
}

// direct binding of: - (CGPoint)translationInView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) translationInView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[self translationInView:(view == JAVA_NULL ? nil : view)]];
}

// direct binding of: - (CGPoint)velocityInView:(UIView *)view;
- (crossmobile_ios_coregraphics_CGPoint*) velocityInView___crossmobile_ios_uikit_UIView:(UIView*) view 
{
    return [[crossmobile_ios_coregraphics_CGPoint alloc] initWithCGPoint:[self velocityInView:(view == JAVA_NULL ? nil : view)]];
}

@end