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
package crossmobile.ios.accounts;

import crossmobile.ios.foundation.NSObject;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.List;
import java.util.Map;

/**
 * ACAccountStore class defines an object that encapsulates information related
 * to accounts.
 */
@CMClass
public class ACAccountStore extends NSObject {

    /**
     * Returns a list with the accounts that are stored on the device.
     *
     * @return A list with the accounts that are stored on the device.
     */
    @CMGetter("@property(readonly, weak, nonatomic) NSArray *accounts;")
    public List accounts() {
        Native.lifecycle().notImplemented();
        return null;
    }

    /**
     * Returns the account of the specified id.
     *
     * @param identifier The id of the requested acccount.
     * @return The account related to the specified id.
     */
    @CMSelector("- (ACAccount *)accountWithIdentifier:(NSString *)identifier;")
    public ACAccount accountWithIdentifier(String identifier) {
        Native.lifecycle().notImplemented();
        return null;
    }

    /**
     * Returns the type of account of the specified id.
     *
     * @param typeIdentifier The id for which the type account is requested.
     * @return The type of account for the specified id.
     */
    @CMSelector("- (ACAccountType *)accountTypeWithAccountTypeIdentifier:(NSString *)typeIdentifier;")
    public ACAccountType accountTypeWithAccountTypeIdentifier(String typeIdentifier) {
        Native.lifecycle().notImplemented();
        return null;
    }

    /**
     * Returns a list that contains all accounts of the specified type.
     *
     * @param accountType The type of the accounts.
     * @return A list containing the accounts of the specified type.
     */
    @CMSelector("- (NSArray *)accountsWithAccountType:(ACAccountType *)accountType;\n"
            + "")
    public List accountsWithAccountType(ACAccountType accountType) {
        Native.lifecycle().notImplemented();
        return null;
    }

    /**
     * Saves the specified account to the related database.
     *
     * @param account           The account that is stored.
     * @param completionHandler The related handler that is called after the
     *                          account is stored.
     */
    @CMSelector("- (void)saveAccount:(ACAccount *)account \n"
            + "withCompletionHandler:(ACAccountStoreSaveCompletionHandler)completionHandler;")
    public void saveAccount(ACAccount account, ACAccountStoreSaveCompletionHandler completionHandler) {
        Native.lifecycle().notImplemented();
    }

    /**
     * Requests access to the specified type of accounts.
     *
     * @param accountType The type of accounts for which access is requested.
     * @param handler     The handler that is called after the operation completion.
     */
    @Deprecated
    @CMSelector("- (void)requestAccessToAccountsWithType:(ACAccountType *)accountType withCompletionHandler:(ACAccountStoreRequestAccessCompletionHandler)handler;")
    public void requestAccessToAccountsWithType(ACAccountType accountType, ACAccountStoreRequestAccessCompletionHandler handler) {
        Native.lifecycle().notImplemented();
    }

    /**
     * Requests permission to the specified type of account for the given
     * options.
     *
     * @param accountType The type of account for which permission is requested.
     * @param options     The options of the account.
     * @param completion  The handler that is called after the operation
     *                    completion.
     */
    @CMSelector("- (void)requestAccessToAccountsWithType:(ACAccountType *)accountType \n"
            + "                                options:(NSDictionary *)options \n"
            + "                             completion:(ACAccountStoreRequestAccessCompletionHandler)completion;")
    public void requestAccessToAccountsWithType(ACAccountType accountType, Map options, ACAccountStoreRequestAccessCompletionHandler completion) {
        Native.lifecycle().notImplemented();
    }

    /**
     * Renews the credentials of the specified account when they are no longer
     * valid.
     *
     * @param account           The account whose credentials are renewed.
     * @param completionHandler The handler that is called after the operation
     *                          completion.
     */
    @CMSelector("- (void)renewCredentialsForAccount:(ACAccount *)account \n"
            + "                        completion:(ACAccountStoreCredentialRenewalHandler)completionHandler;")
    public void renewCredentialsForAccount(ACAccount account, ACAccountStoreCredentialRenewalHandler completionHandler) {
        Native.lifecycle().notImplemented();
    }

    /**
     * Removes the specified account from the account store.
     *
     * @param account           The account that is removed from the account store.
     * @param completionHandler The handler that is called after the operation
     *                          completion.
     */
    @CMSelector("- (void)removeAccount:(ACAccount *)account \n"
            + "withCompletionHandler:(ACAccountStoreRemoveCompletionHandler)completionHandler;")
    public void removeAccount(ACAccount account, ACAccountStoreRemoveCompletionHandler completionHandler) {
        Native.lifecycle().notImplemented();
    }
}