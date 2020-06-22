/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crossmobile.backend.desktop;

import crossmobile.ios.foundation.NSString;
import crossmobile.ios.foundation.NSStringEncoding;
import crossmobile.ios.foundation.NSURL;
import crossmobile.ios.social.SLComposeViewControllerResult;
import crossmobile.ios.social.SLServiceType;
import crossmobile.ios.uikit.UIApplication;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMLib;
import org.crossmobile.bridge.ann.CMLibTarget;

@CMLib(name = "cmsocial", target = CMLibTarget.DESKTOP)
public class DesktopSocial {

    public void initialize() {
        Native.social().registerSocial(SLServiceType.Twitter, (text, images, urls, handler) -> {
            if (text != null)
                //noinspection ConstantConditions
                UIApplication.sharedApplication().openURL(NSURL.URLWithString(
                        "https://twitter.com/intent/tweet?text=" + NSString.stringByAddingPercentEscapesUsingEncoding(text, NSStringEncoding.UTF8)
                ));
            if (handler != null)
                handler.invoke(text == null ? SLComposeViewControllerResult.Cancelled : SLComposeViewControllerResult.Done);
        });
    }
}
