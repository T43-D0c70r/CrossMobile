// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.foundation.Foundation implementation

#import "crossmobile_ios_foundation_Foundation.h"
#import "java_lang_String.h"
#import "java_util_List.h"

@implementation crossmobile_ios_foundation_Foundation

// direct binding of: NSString * NSHomeDirectory ( void );
+ (NSString*) NSHomeDirectory__
{
    NSString* re$ult = NSHomeDirectory();
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: NSArray<NSString *> * NSSearchPathForDirectoriesInDomains ( NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, BOOL expandTilde );
+ (NSArray*) NSSearchPathForDirectoriesInDomains___int_int_boolean:(int) directory :(int) domainMask :(BOOL) expandTilde 
{
    NSArray* re$ult = NSSearchPathForDirectoriesInDomains(directory, domainMask, expandTilde);
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: NSString * NSTemporaryDirectory ( void );
+ (NSString*) NSTemporaryDirectory__
{
    NSString* re$ult = NSTemporaryDirectory();
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

@end