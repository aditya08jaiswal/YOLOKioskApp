package com.yolohealth.yolokioskapp.utils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.BundleConstants;
import com.yolohealth.yolokioskapp.helperclasses.ServerConstants;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.model.FileModel;
import com.yolohealth.yolokioskapp.reciever.RetryUploadService;
import com.yolohealth.yolokioskapp.user.UserAttributes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import static android.app.Notification.EXTRA_NOTIFICATION_ID;


public class UploadImageFileService extends IntentService {

    String url = ServerConstants.SERVER_ADDRESS+ "/Upload/uploadFiles/";
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private String captureReportPath="";

    private String captureReportBackupPath="/sdcard/yoloreportbackup";
    private boolean updatecapturereport = true;
    private String reporttitle = "";
    private String reportdescription = "";

    File mCaptureReportBackup = new File(captureReportBackupPath);
    int id = 111;
    private String filePath;
    private String fileName;
    private String mUserId;
    private Notification notification;
    int notifyID = 123321;
    String CHANNEL_ID = "yolo_channel_02";
    private FileModel mFileModel;

    public UploadImageFileService() {
        super(UploadImageFileService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        filePath=intent.getStringExtra(BundleConstants.FILE_PATH);
        fileName=intent.getStringExtra(BundleConstants.FILE_NAME);
        mUserId =intent.getStringExtra(BundleConstants.USER_ID);
        mFileModel=new FileModel();
        mFileModel.setFilename(fileName);
        mFileModel.setFilePath(filePath);
        captureReportPath = "/sdcard/YoloHealth/Images";

        //Setting notification here
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       showNotification();
        // calling Async task
        new UploadFileToServer().execute(filePath,fileName);
    }



    private void showNotification() {
        // The id of the channel.
        CharSequence name = "channel";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            mNotifyManager.createNotificationChannel(mChannel);
            notification =
                    new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.a21)
                            .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.a21))
                            .setContentTitle("Uploading Profile")
                            .setContentText("Upload profile in progress")
                            .setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark))
                            .setChannelId(CHANNEL_ID).build();
            mNotifyManager.notify(notifyID , notification);
        }else {
            mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify")
                    .setSmallIcon(R.drawable.a21)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.a21))
                    .setContentTitle(getBaseContext().getResources().getString(R.string.app_name))
                    .setContentTitle("Uploading Profile")

                    //.setContentInfo("Booking ID:" + newBookingID)
                    .setContentText("Upload profile in progress")
                    .setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark))
                    .setAutoCancel(true);

            mNotifyManager.notify(id, mBuilder.build());

        }


    }
    private class UploadFileToServer extends AsyncTask<String, Integer, String> {
        private static final String TAG = "UploadFileToServer";

        String fileNameToSend="";
        String filePathtosend="";
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero

            super.onPreExecute();
//            mBuilder.setProgress(100, 0, false);
//            mNotifyManager.notify(id, mBuilder.build());
        }

        @Override
        protected String doInBackground(String... params) {
            // return uploadProfilePic(params[0]);
            String responseStr = "";
            filePathtosend=params[0];
            fileNameToSend=params[1];
            responseStr = uploadCaptutrImg(params[0]);

           // mBuilder.setProgress(50, 0, false);
            return responseStr;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Intent retryIntent = new Intent(getApplicationContext(), RetryUploadService.class);
            retryIntent.setAction(RetryUploadService.ACTION_PROFILE_UPDATE_RETRY);
            retryIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
            retryIntent.putExtra(BundleConstants.FILE_PATH,filePathtosend);
            retryIntent.putExtra(BundleConstants.FILE_NAME,fileNameToSend);
            retryIntent.putExtra("fileModel",mFileModel);
            retryIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            retryIntent.putExtra(BundleConstants.USER_ID,""+ ThisUserConfig.getInstance().getString(UserAttributes.USERID));
            PendingIntent retryPendingIntent =
                    PendingIntent.getBroadcast(getApplicationContext(), 0, retryIntent, PendingIntent.FLAG_ONE_SHOT);
            if(mBuilder!=null) {
                if (result.equalsIgnoreCase("success")) {
                    if (mBuilder != null) {
                        mBuilder.setContentText("Upload Profile");
                        mBuilder.setContentText("Upload profile completed");
                    }
                } else {
                    mBuilder.setContentText("Upload profile Failed. Please try again");
                    mBuilder.setAutoCancel(true);
                    mBuilder.addAction(R.drawable.ic_history_black_24dp, "retry",
                            retryPendingIntent);
                }
                // Removes the progress bar
                mNotifyManager.notify(id, mBuilder.build());
            }else if(notification!=null){
                if (result.equalsIgnoreCase("success")) {

                    notification =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.a21)
                                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.a21))
                                    .setContentTitle(" Profile of user:" + ThisUserConfig.getInstance().getString(ThisUserConfig.USERNAME))
                                    .setContentText("Profile upload completed")
                                    .setAutoCancel(true)
                                    .setChannelId(CHANNEL_ID).build();
                }else{
                    notification =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.a21)
                                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.a21))
                                    .setContentTitle("Uploading user Profile :" + ThisUserConfig.getInstance().getString(ThisUserConfig.USERNAME))
                                    .setContentText("Upload Profile Failed. Please try again")
                                    .setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark))
                                    .addAction(R.drawable.ic_history_black_24dp, "retry",
                                            retryPendingIntent)
                                    .setAutoCancel(true)
                                    .setChannelId(CHANNEL_ID).build();
                }
                mNotifyManager.notify(notifyID , notification);
            }
        }

        //called to upload profile pic, working, tested on 26/05/15
        private String uploadCaptutrImg(String path) {
            String responseString = null;

            //reNameProfilePic(path);
            HttpClient client = new DefaultHttpClient();
            File file = new File(filePath);

            HttpPost post = new HttpPost(url);

            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entityBuilder.addBinaryBody(fileName, file);
            entityBuilder.addTextBody("filenames", fileName);
            entityBuilder.addTextBody("filetype", "userprofilepic");
            entityBuilder.addTextBody("userphone", ThisUserConfig.getInstance().getString(ThisUserConfig.PHONE));
            entityBuilder.addTextBody("userid", mUserId);
            // add more key/value pairs here as needed

            HttpEntity entity = entityBuilder.build();
            post.setEntity(entity);

            HttpResponse response = null;
            try {
                response = client.execute(post);
            } catch (IOException e1) {
                e1.printStackTrace();
                return "error";
            }
            try {
                HttpEntity httpEntity = response.getEntity();
                String jsonStr = EntityUtils.toString(httpEntity);
                JSONObject responseJSON = new JSONObject(jsonStr);
                responseString = responseJSON.getString("Status");
            } catch (JSONException e) {
                e.printStackTrace();
                responseString = "error";
                Log.e(TAG, "error");
                return "error";
            } catch (Exception e) {
                responseString = "error";
                e.printStackTrace();
                Log.e(TAG, "error");
                return "error";
            }
            Log.d(TAG, responseString);

            return responseString;
        }


        private void reNameProfilePic(String path) {
            File from = new File(path, "capture.jpg");
            File to = new File(path, "capture.jpg");
            from.renameTo(to);
            from.delete();
        }

        public void copyDirectory(File sourceLocation, File targetLocation)
                throws IOException {

            if (sourceLocation.isDirectory()) {
                if (!targetLocation.exists() && !targetLocation.mkdirs()) {
                    throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
                }

                String[] children = sourceLocation.list();
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]),
                            new File(targetLocation, children[i]));
                }
            } else {

                // make sure the directory we plan to store the recording in exists
                File directory = targetLocation.getParentFile();
                if (directory != null && !directory.exists() && !directory.mkdirs()) {
                    throw new IOException("Cannot create dir " + directory.getAbsolutePath());
                }

                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();
            }
            sourceLocation.delete();
        }
    }
}