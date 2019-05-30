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

import crossmobile.ios.foundation.NSError;
import crossmobile.ios.foundation.NSObject;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.crossmobile.bind.system.AbstractLifecycleBridge.errorFromInfo;

/**
 * SKPaymentQueue class defines a queue for the App Store's payment transactions
 * of the application.
 */
@CMClass
public class SKPaymentQueue extends NSObject {

    private static final SKPaymentQueue queue = new SKPaymentQueue();
    //
    private Set<SKPaymentTransactionObserver> observers;
    private final List<SKPaymentTransaction> transactions = new ArrayList<>();

    private SKPaymentQueue() {
    }

    /**
     * Returns a Boolean that defines whether payments are allowed or not.
     *
     * @return A Boolean that defines whether payments are allowed or not.
     */
    @CMSelector("+ (BOOL)canMakePayments;")
    public static boolean canMakePayments() {
        return Native.inapp().isSupported();
    }

    /**
     * Returns the singleton instance of the queue.
     *
     * @return The singleton instance of the queue.
     */
    @CMSelector("+ (instancetype)defaultQueue;")
    public static SKPaymentQueue defaultQueue() {
        return queue;
    }

    /**
     * Adds the specified SKPaymentTransactionObserver to the queue.
     *
     * @param observer The specified SKPaymentTransactionObserver to be added to
     *                 the queue.
     */
    @CMSelector("- (void)addTransactionObserver:(id<SKPaymentTransactionObserver>)observer;")
    public void addTransactionObserver(SKPaymentTransactionObserver observer) {
        if (observers == null)
            observers = new HashSet<>();
        observers.add(observer);
    }

    /**
     * Removes the specified SKPaymentTransactionObserver from the queue.
     *
     * @param observer The specified SKPaymentTransactionObserver to be removed
     *                 from the queue.
     */
    @CMSelector("- (void)removeTransactionObserver:(id<SKPaymentTransactionObserver>)observer;")
    public void removeTransactionObserver(SKPaymentTransactionObserver observer) {
        if (observers != null)
            observers.remove(observer);
    }

    /**
     * Returns the list of the transactions.
     *
     * @return The list of the transactions.
     */
    @CMGetter("@property(nonatomic, readonly) NSArray<SKPaymentTransaction *> *transactions;")
    public List<SKPaymentTransaction> transactions() {
        return transactions;
    }

    /**
     * Adds the specified payment request to the queue.
     *
     * @param payment The payment to be added to the queue.
     */
    @CMSelector("- (void)addPayment:(SKPayment *)payment;")
    public void addPayment(SKPayment payment) {
        if (payment == null || payment.quantity <= 0 || payment.productIdentifier == null || payment.productIdentifier.isEmpty()) {
            Native.system().error("Invalid payment: " + (payment == null ? "null" : payment.toString()), null);
            return;
        }
        Native.inapp().requestPayment(payment, this::sendUpdatedTransaction);
    }

    /**
     * Finishes the specified payment transaction.
     *
     * @param transaction The payment transaction to be finished.
     */
    @CMSelector("- (void)finishTransaction:(SKPaymentTransaction *)transaction;")
    public void finishTransaction(SKPaymentTransaction transaction) {
        transactions.remove(transaction);
        if (observers != null && !observers.isEmpty()) {
            List<SKPaymentTransaction> trans = new ArrayList<>();
            trans.add(transaction);
            for (SKPaymentTransactionObserver obs : observers)
                obs.removedTransactions(this, trans);
        }
    }

    /**
     * Requests to restore previously completed purchases.
     */
    @CMSelector("- (void)restoreCompletedTransactions;")
    public void restoreCompletedTransactions() {
        Native.inapp().restoreTransactions(this::sendUpdatedTransaction, () -> {
            if (observers != null && !observers.isEmpty())
                for (SKPaymentTransactionObserver obs : observers)
                    obs.restoreCompletedTransactionsFinished(SKPaymentQueue.this);
        }, (String error) -> {
            NSError nserror = NSError.errorWithDomain(NSError.Domain.SKError, SKError.Unknown, errorFromInfo(error));
            if (observers != null && !observers.isEmpty())
                for (SKPaymentTransactionObserver obs : observers)
                    obs.restoreCompletedTransactionsFailedWithError(SKPaymentQueue.this, nserror);
        });
    }

    private void sendUpdatedTransaction(SKPaymentTransaction transaction) {
        if (observers != null && !observers.isEmpty()) {
            List<SKPaymentTransaction> ctrans = new ArrayList<>();
            ctrans.add(transaction);
            for (SKPaymentTransactionObserver obs : observers)
                obs.updatedTransactions(this, ctrans);
        }
    }
}