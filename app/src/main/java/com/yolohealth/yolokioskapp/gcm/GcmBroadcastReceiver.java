package com.yolohealth.yolokioskapp.gcm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.activity.MainActivity;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.user.UserAttributes;
import com.yolohealth.yolokioskapp.utils.JsonUtil;

import org.json.JSONObject;




/**
 * This {@code WakefulBroadcastReceiver} takes care of creating and managing a
 * partial wake lock for your app. It passes off the work of processing the GCM
 * message to an {@code IntentService}, while ensuring that the device does not
 * go back to sleep in the transition. The {@code IntentService} calls
 * {@code GcmBroadcastReceiver.completeWakefulIntent()} when it is ready to
 * release the wake lock.
 */

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    private static final String REPORT = "Kiosk_Report";
    private Context mContext;
    private int consultId=0;
    private NotificationManager mNotificationManager;

    private NotificationCompat.Builder mBuilder;
    private String mReportid="";
    private String mReportType="";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Explicitly specify that GcmIntentService will handle the intent.
        mContext=context;
        Bundle info = intent.getExtras();
        parseData(info);
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmJobIntentService.class.getName());
        // Start the service, keeping the device awake while it is launching.

       // startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }

    private void parseData(Bundle info) {
        String lNotificationType=info.getString(UserAttributes.GCMINFOTYPE);
        String jsonReportStr = info.getString(GCMCONSTANTS.GCMDATASRT);
        Log.i("json data gcm ", "Json=" + jsonReportStr);

        try {
            JSONObject json = new JSONObject(jsonReportStr);
            Log.i("BroadCast", "Json=" + json);

            String lNotificationText= JsonUtil.getString(json,UserAttributes.NOTIFICATION_TEXT);
            consultId=JsonUtil.getInt(json,UserAttributes.CONSULTID);
            if(!lNotificationText.equals(""))
                generateNotification(lNotificationType,lNotificationText);
            else{
//                if(json.has("reporttype")){
//                    mReportid=JsonUtil.getString(json,UserAttributes.REPORTID);
//                    mReportType=JsonUtil.getString(json,UserAttributes.REPORTTYPE);
//                    TokenIntentService(GCMCONSTANTS.REPORT,"Kiosk Report has been generated");
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void generateNotification(String type, String message) {
        mNotificationManager =
                (NotificationManager) Platform.getInstance().getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notify_001",
                    "Channel yolo",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(Platform.getInstance().getContext(), MainActivity.class);;
//        if(type.equals(GCMCONSTANTS.REPORT)){
//            intent = new Intent(Platform.getInstance().getContext(), MainActivity.class);
//            intent.putExtra(KioskReportActivity.REPORTID,mReportid);
//            intent.putExtra(KioskReportActivity.REPORTTYPE,mReportType);
//
//        }
//        else {
//
//            intent = new Intent(Platform.getInstance().getContext(), ConsultDetailsActivity.class);
//            intent.setAction(ConsultDetailsActivity.NOTIFICATION_INTENT);
//            intent.putExtra(BundleConstants.CONSULT_ID,String.valueOf(consultId));
//        }
//            showReport.putExtra(UserAttributes.REPORTTYPE,rporttype);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //show notification here:

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mBuilder =
                    new NotificationCompat.Builder(mContext.getApplicationContext(), "notify_001");
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
//            bigText.setBigContentTitle("Today's Bible Verse");
//            bigText.setSummaryText("Text in detail");
            mBuilder.setSmallIcon(R.drawable.yolo_logo);
            mBuilder.setContentTitle(mContext.getResources().getString(R.string.app_name));
            mBuilder.setContentText(Html.fromHtml(message));
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigText);
            mBuilder.setAutoCancel(true);
        }else {
            mBuilder = new NotificationCompat.Builder(mContext, "notify")
                    .setSmallIcon(R.drawable.yolo_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yolo_logo))
                    .setContentTitle(mContext.getResources().getString(R.string.app_name))
                    //.setContentInfo("Booking ID:" + newBookingID)
                    .setContentText(Html.fromHtml(message))
                    .setAutoCancel(true);

        }

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        mContext,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(consultId, mBuilder.build());
    }
}