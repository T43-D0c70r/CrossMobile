/*
 * (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the CrossMobile Community License as published
 * by the CrossMobile team.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * CrossMobile Community License for more details.
 *
 * You should have received a copy of the CrossMobile Community
 * License along with CrossMobile; if not, please contact the
 * CrossMobile team at https://crossmobile.tech/contact/
 */
package crossmobile.ios.uikit;

import org.crossmobile.bridge.ann.CMEnum;

/**
 * UIApplicationLaunchOptions class defines different options or information
 * related to application launching.
 */
@CMEnum
public final class UIApplicationLaunchOptions {

    /**
     * The application was launched to open a URL.
     */
    public static final String URLKey = "UIApplicationLaunchOptionsURLKey";

    /**
     * Another application requested the launching of the application.
     */
    public static final String SourceApplicationKey = "UIApplicationLaunchOptionsSourceApplicationKey";

    /**
     * The application depends on a remote notification in order to proceed.
     */
    public static final String RemoteNotificationKey = "UIApplicationLaunchOptionsRemoteNotificationKey";

    /**
     * The application that requested to open the URL provided custom data.
     */
    public static final String AnnotationKey = "UIApplicationLaunchOptionsAnnotationKey";

    /**
     * The application depends on a local notification in order to proceed.
     */
    public static final String LocalNotificationKey = "UIApplicationLaunchOptionsLocalNotificationKey";

    /**
     * The application was launched due to a local evenet.
     */
    public static final String LocationKey = "UIApplicationLaunchOptionsLocationKey";

    /**
     * The requested downloaded data required for the application are available.
     */
    public static final String NewsstandDownloadsKey = "UIApplicationLaunchOptionsNewsstandDownloadsKey";

    private UIApplicationLaunchOptions() {
    }
}
