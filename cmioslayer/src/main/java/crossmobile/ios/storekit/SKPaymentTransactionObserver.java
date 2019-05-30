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
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.List;

/**
 * SKPaymentTransactionObserver class defines an object that is responsible for
 * handling transactions that are removed or updated within the queue.
 */
@CMClass
public interface SKPaymentTransactionObserver {

    /**
     * It is used when one or more transactions of the queue have been updated.
     *
     * @param queue        The queue of the updated transactions.
     * @param transactions The list of the updated transactions.
     */
    @CMSelector("- (void)paymentQueue:(SKPaymentQueue *)queue \n" +
            " updatedTransactions:(NSArray<SKPaymentTransaction *> *)transactions;")
    public abstract void updatedTransactions(SKPaymentQueue queue, List<SKPaymentTransaction> transactions);

    /**
     * It is used when one or more transactions have been removed from the
     * queue.
     *
     * @param queue        The queue of the transactions that have been removed.
     * @param transactions The list of the removed transactions.
     */
    @CMSelector("- (void)paymentQueue:(SKPaymentQueue *)queue \n" +
            " removedTransactions:(NSArray<SKPaymentTransaction *> *)transactions;\n" +
            "")
    public default void removedTransactions(SKPaymentQueue queue, List<SKPaymentTransaction> transactions) {
    }

    /**
     * It is used when during transaction an error occurs.
     *
     * @param queue The queue that is involved in the transaction.
     * @param error The error that occurred.
     */
    @CMSelector("- (void)paymentQueue:(SKPaymentQueue *)queue \n" +
            "restoreCompletedTransactionsFailedWithError:(NSError *)error;")
    public default void restoreCompletedTransactionsFailedWithError(SKPaymentQueue queue, NSError error) {
    }

    /**
     * It is used when the queue completed the sending restored transactions.
     *
     * @param queue The queue that sent restored transactions.
     */
    @CMSelector("- (void)paymentQueueRestoreCompletedTransactionsFinished:(SKPaymentQueue *)queue;")
    public default void restoreCompletedTransactionsFinished(SKPaymentQueue queue) {
    }
}