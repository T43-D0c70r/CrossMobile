// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.uikit.UIOffset implementation

#import "crossmobile_ios_uikit_UIOffset.h"

@implementation crossmobile_ios_uikit_UIOffset

// direct binding of: UIOffset UIOffsetMake(CGFloat horizontal, CGFloat vertical);
- (crossmobile_ios_uikit_UIOffset*) __init_crossmobile_ios_uikit_UIOffset___double_double:(double) horizontal :(double) vertical 
{
    return [self initWithUIOffset:UIOffsetMake(horizontal, vertical)];
}

// direct binding of: CGFloat horizontal;
- (void) setHorizontal___double:(double) horizontal 
{
    self->horizontal_double = horizontal;
}

// direct binding of: CGFloat horizontal;
- (double) getHorizontal__
{
    return self->horizontal_double;
}

// direct binding of: CGFloat vertical;
- (void) setVertical___double:(double) vertical 
{
    self->vertical_double = vertical;
}

// direct binding of: CGFloat vertical;
- (double) getVertical__
{
    return self->vertical_double;
}

- (instancetype) initWithUIOffset:(UIOffset) other
{
    self = [super init];
    self->horizontal_double = other.horizontal;
    self->vertical_double = other.vertical;
    return self;
}

- (void) setUIOffset:(UIOffset) other
{
    self->horizontal_double = other.horizontal;
    self->vertical_double = other.vertical;
}

- (UIOffset) getUIOffset
{
    UIOffset result;
    result.horizontal = self->horizontal_double;
    result.vertical = self->vertical_double;
    return result;
}

@end