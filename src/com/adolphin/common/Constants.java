
package com.adolphin.common;

import android.app.Activity;

@SuppressWarnings("unchecked")
public class Constants {
    public static final String TARGET_PACKAGE_ID = "mobi.mgeek.TunnyBrowser";
    public static final String LAUNCHER_ACTIVITY_CLASSNAME = "mobi.mgeek.TunnyBrowser.BrowserActivity";
    public static Class<Activity> launcherActivityClass;
    static {
        try {
            launcherActivityClass = (Class<Activity>) Class.forName(LAUNCHER_ACTIVITY_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
