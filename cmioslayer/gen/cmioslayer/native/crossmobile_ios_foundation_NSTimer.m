// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.foundation.NSTimer implementation

#import "crossmobile_ios_foundation_NSDate.h"
#import "crossmobile_ios_foundation_NSTimer.h"
#import "crossmobile_ios_foundation_NSTimerDelegate.h"
#import "java_lang_Object.h"
#import "java_lang_String.h"
#import "java_lang_reflect_Method.h"
#import "org_robovm_objc_block_VoidBlock1.h"

@implementation crossmobile_ios_foundation_NSTimer$Ext

// (NSTimer) @property(copy) NSDate *fireDate;
- (void) setFireDate___crossmobile_ios_foundation_NSDate:(NSDate*) fireDate 
{
    [super setFireDate:(fireDate == JAVA_NULL ? nil : fireDate)];
}

// (NSTimer) @property(copy) NSDate *fireDate;
- (NSDate*) fireDate__
{
    NSDate* re$ult = [super fireDate];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (NSTimer) @property(readonly) NSTimeInterval timeInterval;
- (double) timeInterval__
{
    return [super timeInterval];
}

// (NSTimer) @property(readonly, retain) id userInfo;
- (id) userInfo__
{
    id re$ult = [super userInfo];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (NSTimer) @property(readonly, getter=isValid) BOOL valid;
- (BOOL) isValid__
{
    return [super isValid];
}

// (NSTimer) - (void)fire;
- (void) fire__
{
    [super fire];
}

// (NSTimer) - (void)invalidate;
- (void) invalidate__
{
    [super invalidate];
}

// (NSObject) - (void)setValue:(id)value forKey:(NSString *)key;
- (void) setValue___java_lang_Object_java_lang_String:(id) value :(NSString*) key 
{
    [super setValue:(value == JAVA_NULL ? nil : value) forKey:(key == JAVA_NULL ? nil : key)];
}

// (NSObject) - (id)valueForKey:(NSString *)key;
- (id) valueForKey___java_lang_String:(NSString*) key 
{
    id re$ult = [super valueForKey:(key == JAVA_NULL ? nil : key)];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

@end

@implementation NSTimer (cm_crossmobile_ios_foundation_NSTimer)

// direct binding of: + (NSTimer *)scheduledTimerWithTimeInterval:(NSTimeInterval)ti target:(id)target selector:(SEL)aSelector userInfo:(id)userInfo repeats:(BOOL)repeats;
+ (NSTimer*) scheduledTimerWithTimeInterval___double_crossmobile_ios_foundation_NSTimerDelegate_java_lang_Object_boolean:(double) ti :(id<crossmobile_ios_foundation_NSTimerDelegate>) target :(id) userInfo :(BOOL) repeats 
{
    NSTimer* re$ult = [NSTimer scheduledTimerWithTimeInterval:ti target:(target == JAVA_NULL ? nil : target) selector:@selector(fireMethod___crossmobile_ios_foundation_NSTimer:) userInfo:(userInfo == JAVA_NULL ? nil : userInfo) repeats:repeats];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: + (NSTimer *)timerWithTimeInterval:(NSTimeInterval)ti target:(id)target selector:(SEL)aSelector userInfo:(id)userInfo repeats:(BOOL)repeats;
+ (NSTimer*) timerWithTimeInterval___double_crossmobile_ios_foundation_NSTimerDelegate_java_lang_Object_boolean:(double) ti :(id<crossmobile_ios_foundation_NSTimerDelegate>) target :(id) userInfo :(BOOL) repeats 
{
    NSTimer* re$ult = [NSTimer timerWithTimeInterval:ti target:(target == JAVA_NULL ? nil : target) selector:@selector(fireMethod___crossmobile_ios_foundation_NSTimer:) userInfo:(userInfo == JAVA_NULL ? nil : userInfo) repeats:repeats];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: - (instancetype)initWithFireDate:(NSDate *)date interval:(NSTimeInterval)interval repeats:(BOOL)repeats block:(void (^)(NSTimer *timer))block;
- (instancetype) __init_crossmobile_ios_foundation_NSTimer___crossmobile_ios_foundation_NSDate_double_boolean_org_robovm_objc_block_VoidBlock1:(NSDate*) date :(double) interval :(BOOL) repeats :(id<org_robovm_objc_block_VoidBlock1>) block 
{
    return [self initWithFireDate:(date == JAVA_NULL ? nil : date) interval:interval repeats:repeats block:(block == JAVA_NULL ? nil : ^(NSTimer* timer) {
        [block invoke___java_lang_Object:(timer ? timer : JAVA_NULL)];
    })];
}

// direct binding of: - (instancetype)initWithFireDate:(NSDate *)date interval:(NSTimeInterval)ti target:(id)t selector:(SEL)s userInfo:(id)ui repeats:(BOOL)rep;
- (instancetype) __init_crossmobile_ios_foundation_NSTimer___crossmobile_ios_foundation_NSDate_double_crossmobile_ios_foundation_NSTimerDelegate_java_lang_Object_boolean:(NSDate*) date :(double) ti :(id<crossmobile_ios_foundation_NSTimerDelegate>) t :(id) ui :(BOOL) rep 
{
    return [self initWithFireDate:(date == JAVA_NULL ? nil : date) interval:ti target:(t == JAVA_NULL ? nil : t) selector:@selector(fireMethod___crossmobile_ios_foundation_NSTimer:) userInfo:(ui == JAVA_NULL ? nil : ui) repeats:rep];
}

// direct binding of: @property(copy) NSDate *fireDate;
- (void) setFireDate___crossmobile_ios_foundation_NSDate:(NSDate*) fireDate 
{
    [self setFireDate:(fireDate == JAVA_NULL ? nil : fireDate)];
}

// direct binding of: @property(copy) NSDate *fireDate;
- (NSDate*) fireDate__
{
    NSDate* re$ult = [self fireDate];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: @property(readonly) NSTimeInterval timeInterval;
- (double) timeInterval__
{
    return [self timeInterval];
}

// direct binding of: @property(readonly, retain) id userInfo;
- (id) userInfo__
{
    id re$ult = [self userInfo];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: @property(readonly, getter=isValid) BOOL valid;
- (BOOL) isValid__
{
    return [self isValid];
}

// direct binding of: - (void)fire;
- (void) fire__
{
    [self fire];
}

// direct binding of: - (void)invalidate;
- (void) invalidate__
{
    [self invalidate];
}

@end