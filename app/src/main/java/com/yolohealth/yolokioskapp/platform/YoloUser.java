package com.yolohealth.yolokioskapp.platform;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

import io.fabric.sdk.android.Fabric;


public class YoloUser extends MultiDexApplication {
    // The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-61304927-2"; // My Property id.

    public static int GENERAL_TRACKER = 0;

    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public YoloUser()
    {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //ACRA.init(this);
//        Fabric.with(this, new Crashlytics());
        Log.i("YoloUser","App Start");
        getApplicationContext();
        Platform.getInstance().initialize(this);

        //avoid uri exceptions
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }


   public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            Tracker t = null;
            if(trackerId== TrackerName.APP_TRACKER){
                t= analytics.newTracker(PROPERTY_ID);
            }
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }

}
