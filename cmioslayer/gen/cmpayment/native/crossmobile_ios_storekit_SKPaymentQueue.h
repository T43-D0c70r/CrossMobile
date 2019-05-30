// (c) 2019 under LGPL by CrossMobile plugin tools

// crossmobile.ios.storekit.SKPaymentQueue definition

#import "xmlvm.h"
#import <StoreKit/StoreKit.h>
@class crossmobile_ios_storekit_SKPayment;
@class crossmobile_ios_storekit_SKPaymentTransaction;
@protocol crossmobile_ios_storekit_SKPaymentTransactionObserver;
@protocol java_util_List;

@interface crossmobile_ios_storekit_SKPaymentQueue$Ext : SKPaymentQueue
@end

#define crossmobile_ios_storekit_SKPaymentQueue SKPaymentQueue
@interface SKPaymentQueue (cm_crossmobile_ios_storekit_SKPaymentQueue)
+ (BOOL) canMakePayments__;
+ (instancetype) defaultQueue__;
- (NSArray*) transactions__;
- (void) addPayment___crossmobile_ios_storekit_SKPayment:(SKPayment*) payment ;
- (void) addTransactionObserver___crossmobile_ios_storekit_SKPaymentTransactionObserver:(id<SKPaymentTransactionObserver>) observer ;
- (void) finishTransaction___crossmobile_ios_storekit_SKPaymentTransaction:(SKPaymentTransaction*) transaction ;
- (void) removeTransactionObserver___crossmobile_ios_storekit_SKPaymentTransactionObserver:(id<SKPaymentTransactionObserver>) observer ;
- (void) restoreCompletedTransactions__;
@end