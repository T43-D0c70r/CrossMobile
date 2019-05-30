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
package crossmobile.ios.gamekit;

import crossmobile.ios.foundation.NSError;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMParamMod;
import org.crossmobile.bridge.ann.CMSelector;

/**
 * GKSessionDelegate interface is the delegate that cooperates with the
 * GKSession objects.
 */
@CMClass
public interface GKSessionDelegate {

    /**
     * It is used when GKSession in case of an error.
     *
     * @param p1 The GKSession that corresponds to this delegate.
     * @param p2 The error that occurred.
     */
    @CMSelector("- (void)session:(GKSession *)session \n"
            + "didFailWithError:(NSError *)error;")
    public void didFailWithError(GKSession p1, NSError p2);

    /**
     * It is used when a peer changes state.
     *
     * @param p1 The GKSession that corresponds to this delegate.
     * @param p2 The peer that changed state.
     * @param p3 The new state of the peer.
     */
    @CMSelector("- (void)session:(GKSession *)session\n"
            + "           peer:(NSString *)peerID\n"
            + " didChangeState:(GKPeerConnectionState)state")
    public void peerDidChangeState(GKSession p1, String p2, @CMParamMod(concatName = true) int p3);

    /**
     * It is used when a remote peer request connection to the GKSession.
     *
     * @param p1 The GKSession that corresponds to this delegate.
     * @param p2 The remote peer that requests connection.
     */
    @CMSelector("- (void)session:(GKSession *)session \n"
            + "didReceiveConnectionRequestFromPeer:(NSString *)peerID;")
    public void didReceiveConnectionRequestFromPeer(GKSession p1, String p2);

    /**
     * It is after a failure to connect to another peer. failed.
     *
     * @param p1 The GKSession that corresponds to this delegate.
     * @param p2 The peer to which connection attempt failed.
     * @param p3 The error that occurred.
     */
    @CMSelector("- (void)session:(GKSession *)session \n"
            + "connectionWithPeerFailed:(NSString *)peerID \n"
            + "      withError:(NSError *)error;")
    public void connectionWithPeerFailed(GKSession p1, String p2, NSError p3);
}