/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package crossmobile.ios.storekit;

import crossmobile.ios.foundation.NSData;
import crossmobile.ios.foundation.NSDate;
import crossmobile.ios.foundation.NSError;
import crossmobile.ios.foundation.NSObject;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;

/**
 * SKPaymentTransaction class defines an object that represents payment
 * transaction objects of the payment queue.
 */
@CMClass
public class SKPaymentTransaction extends NSObject {

    private final SKPayment payment;
    private final int transactionState;
    private final String transactionIdentifier;
    private final NSData transactionReceipt;
    private final NSDate transactionDate;
    private final SKPaymentTransaction originalTransaction;
    private final NSError error;

    SKPaymentTransaction(SKPayment payment, int transactionState, String transactionIdentifier, NSData transactionReceipt, NSDate transactionDate, SKPaymentTransaction originalTransaction, NSError error) {
        this.payment = payment;
        this.transactionState = transactionState;
        this.transactionIdentifier = transactionIdentifier;
        this.transactionReceipt = transactionReceipt;
        this.transactionDate = transactionDate;
        this.originalTransaction = originalTransaction;
        this.error = error;
    }

    /**
     * Returns an error related to this transaction.
     *
     * @return An error related to this transaction.
     */
    @CMGetter("@property(nonatomic, readonly) NSError *error;\n"
            + "")
    public NSError error() {
        return error;
    }

    /**
     * Returns a restored by App Store transaction.
     *
     * @return A restored by App Store transaction.
     */
    @CMGetter("@property(nonatomic, readonly) SKPaymentTransaction *originalTransaction;")
    public SKPaymentTransaction originalTransaction() {
        return originalTransaction;
    }

    /**
     * Returns the SKPayment object associated to this payment transaction.
     *
     * @return The SKPayment object associated to this payment transaction.
     */
    @CMGetter("@property(nonatomic, readonly) SKPayment *payment;")
    public SKPayment payment() {
        return payment;
    }

    /**
     * Returns the date that the transaction was added to the App Store.
     *
     * @return The date that the transaction was added to the App Store.
     */
    @CMGetter("@property(nonatomic, readonly) NSDate *transactionDate;")
    public NSDate transactionDate() {
        return transactionDate;
    }

    /**
     * Returns the id of this payment transaction.
     *
     * @return The id of this payment transaction.
     */
    @CMGetter("@property(nonatomic, readonly) NSString *transactionIdentifier;\n"
            + "")
    public String transactionIdentifier() {
        return transactionIdentifier;
    }

    /**
     * Returns a receipt that contains all the information of this successful
     * transaction.
     *
     * @return A receipt with all the the information of this successful
     * transaction.
     */
    @Deprecated
    @CMGetter("@property(nonatomic, readonly) NSData *transactionReceipt;")
    public NSData transactionReceipt() {
        return transactionReceipt;
    }

    /**
     * Returns the current state of this transaction.
     *
     * @return The current state of this transaction.
     */
    @CMGetter("@property(nonatomic, readonly) SKPaymentTransactionState transactionState;")
    public int transactionState() {
        return transactionState;
    }
}