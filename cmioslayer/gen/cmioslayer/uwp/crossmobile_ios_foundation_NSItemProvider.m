// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.foundation.NSItemProvider implementation

#import "crossmobile_ios_foundation_NSItemProvider.h"
#import "crossmobile_ios_foundation_NSItemProviderCompletionHandler.h"
#import "crossmobile_ios_foundation_NSSecureCoding.h"
#import "crossmobile_ios_foundation_NSURL.h"
#import "java_lang_Object.h"
#import "java_lang_String.h"
#import "java_util_List.h"
#import "java_util_Map.h"

@implementation crossmobile_ios_foundation_NSItemProvider$Ext

// (NSItemProvider) @property(copy, readonly, nonatomic) NSArray *registeredTypeIdentifiers;
- (NSArray*) registeredTypeIdentifiers__
{
    NSArray* re$ult = [super registeredTypeIdentifiers];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// (NSItemProvider) - (BOOL)hasItemConformingToTypeIdentifier:(NSString *)typeIdentifier;
- (BOOL) hasItemConformingToTypeIdentifier___java_lang_String:(NSString*) typeIdentifier 
{
    return [super hasItemConformingToTypeIdentifier:(typeIdentifier == JAVA_NULL ? nil : typeIdentifier)];
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

@implementation NSItemProvider (cm_crossmobile_ios_foundation_NSItemProvider)

// direct binding of: - (instancetype)initWithItem:(id<NSSecureCoding>)itemtypeIdentifier:(NSString *)typeIdentifier;
- (instancetype) __init_crossmobile_ios_foundation_NSItemProvider___crossmobile_ios_foundation_NSSecureCoding_java_lang_String:(id<NSSecureCoding>) itemtypeIdentifier :(NSString*) typeIdentifier 
{
    return [self initWithItem:(itemtypeIdentifier == JAVA_NULL ? nil : itemtypeIdentifier) :(typeIdentifier == JAVA_NULL ? nil : typeIdentifier)];
}

// direct binding of: - (instancetype)initWithContentsOfURL:(NSURL *)fileURL;
- (instancetype) __init_crossmobile_ios_foundation_NSItemProvider___crossmobile_ios_foundation_NSURL:(NSURL*) fileURL 
{
    return [self initWithContentsOfURL:(fileURL == JAVA_NULL ? nil : fileURL)];
}

// direct binding of: @property(copy, readonly, nonatomic) NSArray *registeredTypeIdentifiers;
- (NSArray*) registeredTypeIdentifiers__
{
    NSArray* re$ult = [self registeredTypeIdentifiers];
    return [(re$ult ? re$ult : JAVA_NULL) retain];
}

// direct binding of: - (BOOL)hasItemConformingToTypeIdentifier:(NSString *)typeIdentifier;
- (BOOL) hasItemConformingToTypeIdentifier___java_lang_String:(NSString*) typeIdentifier 
{
    return [self hasItemConformingToTypeIdentifier:(typeIdentifier == JAVA_NULL ? nil : typeIdentifier)];
}

// direct binding of: - (void)loadItemForTypeIdentifier:(NSString *)typeIdentifier options:(NSDictionary *)options completionHandler:(NSItemProviderCompletionHandler)completionHandler;
- (void) loadItemForTypeIdentifier___java_lang_String_java_util_Map_crossmobile_ios_foundation_NSItemProviderCompletionHandler:(NSString*) typeIdentifier :(NSDictionary*) options :(id<crossmobile_ios_foundation_NSItemProviderCompletionHandler>) completionHandler 
{
    [self loadItemForTypeIdentifier:(typeIdentifier == JAVA_NULL ? nil : typeIdentifier) options:(options == JAVA_NULL ? nil : options) completionHandler:(completionHandler == JAVA_NULL ? nil : ^(id<NSSecureCoding> item, NSError* error) {
        [completionHandler invoke___crossmobile_ios_foundation_NSSecureCoding_crossmobile_ios_foundation_NSError:(item ? item : JAVA_NULL) :(error ? error : JAVA_NULL)];
    })];
}

// direct binding of: - (void)loadPreviewImageWithOptions:(NSDictionary *)options completionHandler:(NSItemProviderCompletionHandler)completionHandler;
- (void) loadPreviewImageWithOptions___java_util_Map_crossmobile_ios_foundation_NSItemProviderCompletionHandler:(NSDictionary*) options :(id<crossmobile_ios_foundation_NSItemProviderCompletionHandler>) completionHandler 
{
    [self loadPreviewImageWithOptions:(options == JAVA_NULL ? nil : options) completionHandler:(completionHandler == JAVA_NULL ? nil : ^(id<NSSecureCoding> item, NSError* error) {
        [completionHandler invoke___crossmobile_ios_foundation_NSSecureCoding_crossmobile_ios_foundation_NSError:(item ? item : JAVA_NULL) :(error ? error : JAVA_NULL)];
    })];
}

@end