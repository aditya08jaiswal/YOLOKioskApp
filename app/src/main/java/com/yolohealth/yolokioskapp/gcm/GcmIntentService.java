package com.yolohealth.yolokioskapp.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.utils.Logger;
import com.yolohealth.yolokioskapp.utils.StringUtils;


/**
 * This {@code IntentService} does the actual handling of the GCM message.
 * {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }
    public static final String TAG = "GCM.GcmIntentService";

    @Override
    protected void onHandleIntent(Intent intent) {
        String userId = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
        if(StringUtils.isBlank(userId))
            return;

        Bundle info = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
//        ToastTracker.showToast("Got gcm:" + messageType);
        Log.i(TAG,"GCM===="+messageType);
        if (!info.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                //sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                //sendNotification("Deleted messages on server: " + extras.toString());
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // actual work
                Logger.i(TAG, "Received gcm info: " + info.toString());
//                switch(Integer.parseInt(info.getString(UserAttributes.GCMINFOTYPE))){
                  /*  case GCMCONSTANTS.BOOKINGCONFIRMEDFORUSER:
                        //incoming booking , we get gcmdata,gcminfotype,newbookingid,movername,datetime
                        String bookingJsonStr = info.getString(GCMCONSTANTS.GCMDATASRT);
                        int newBookingID = Integer.parseInt(info.getString(UserAttributes.NEWBOOKINGID));
                        String newBookingMoverName = info.getString(UserAttributes.MOVERNAME);
                        String newBookingDateTime = info.getString(UserAttributes.DATETIME);
                        String dateTimeStr = DateUtils.getDateTimeTextToDisplay(Double.valueOf(newBookingDateTime).longValue());

                        Intent startMainActivity = new Intent(this, MainActivityNew.class);
                        startMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);

                        //if app open call startActivity() and it ll go to onNewIntent else show notification
                        if(ThisUserConfig.getInstance().getBool(ThisUserConfig.MAINACTIVITYISSHOWING))
                        {
                            //dont show notification just send intent to main activity
                            startActivity(startMainActivity);
                        }
                        else {
                            //show notification here:
                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(this)
                                            .setSmallIcon(R.drawable.notification_icon)
                                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_largeicon))
                                            .setContentTitle("Booking confirmed")
                                            .setContentInfo("Booking ID:" + newBookingID)
                                            .setContentText("Your booking is confirmed." + newBookingMoverName + " will visit you " + dateTimeStr)
                                            .setAutoCancel(true);
                            PendingIntent resultPendingIntent =
                                    PendingIntent.getActivity(
                                            this,
                                            0,
                                            startMainActivity,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);
                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            // mId allows you to update the notification later on.
                            mNotificationManager.notify(newBookingID, mBuilder.build());
                        }
                    break;*/
//                    case GCMCONSTANTS.SHOWRECEIPTTOUSER:
//                        String jsonStr = info.getString(GCMCONSTANTS.GCMDATASRT);
//                        try {
//                            JSONObject json = new JSONObject(jsonStr);
//                            String bookingId = json.getString(UserAttributes.BOOKINGID);
//                            Intent showReceipt = new Intent(Platform.getInstance().getContext(), ReceiptActivity.class);
//                            showReceipt.putExtra(ReceiptActivity.BOOKINGID,bookingId);
//                            showReceipt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            Platform.getInstance().getContext().startActivity(showReceipt);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case GCMCONSTANTS.SHOWREPORTTOUSER:
//                        String jsonReportStr = info.getString(GCMCONSTANTS.GCMDATASRT);
//                        try {
//                            JSONObject json = new JSONObject(jsonReportStr);
//                            int uniqueid = Integer.valueOf(json.getString(UserAttributes.UNIQUEID));
//                            String reportid = json.getString(UserAttributes.REPORTID);
//                            String rporttype = json.getString(UserAttributes.REPORTTYPE);
//                            Intent showReport = new Intent(Platform.getInstance().getContext(), KioskReportActivity.class);
//                            showReport.putExtra(UserAttributes.REPORTID,reportid);
//                            showReport.putExtra(UserAttributes.REPORTTYPE,rporttype);
//                            showReport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            //Platform.getInstance().getContext().startActivity(showReport);
//                            //if app open call startActivity() and it ll go to onNewIntent else show notification
//                            if(ThisUserConfig.getInstance().getBool(ThisUserConfig.MAINACTIVITYISSHOWING))
//                            {
//                                //dont show notification just send intent to main activity
//                                startActivity(showReport);
//                            }
//                            else {
//                                //show notification here:
//                                NotificationCompat.Builder mBuilder =
//                                        new NotificationCompat.Builder(this)
//                                                .setSmallIcon(R.drawable.notification_icon)
//                                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_largeicon))
//                                                .setContentTitle("New Health Report..")
//                                                        //.setContentInfo("Booking ID:" + newBookingID)
//                                                        //.setContentText("Your booking is confirmed." + newBookingMoverName + " will visit you " + dateTimeStr)
//                                                .setAutoCancel(true);
//                                PendingIntent resultPendingIntent =
//                                        PendingIntent.getActivity(
//                                                this,
//                                                0,
//                                                showReport,
//                                                PendingIntent.FLAG_UPDATE_CURRENT
//                                        );
//                                mBuilder.setContentIntent(resultPendingIntent);
//                                NotificationManager mNotificationManager =
//                                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                // mId allows you to update the notification later on.
//                                mNotificationManager.notify(uniqueid, mBuilder.build());
//
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    default:
//                }
               // sendNotification("Received: " + info.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    /*private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, DemoActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_gcm)
                        .setContentTitle("GCM Notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }*/
}