package com.yolohealth.yolokioskapp.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yolohealth.yolokioskapp.helperclasses.BundleConstants;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.model.FileModel;
import com.yolohealth.yolokioskapp.user.UserAttributes;
import com.yolohealth.yolokioskapp.utils.UploadImageFileService;
import com.yolohealth.yolokioskapp.utils.UploadReportService;
import com.yolohealth.yolokioskapp.utils.UploadService;



public class RetryUploadService extends BroadcastReceiver {
    private static final String TAG = "RetryReceiver";
    public static final String ACTION_PROFILE_UPDATE_RETRY  ="retryProfileUpdate";
    public static final String ACTION_REPORT_RETRY = "reportRetry";
    public static final String ACTION_VIDEO_RETRY = "videoRetry";
    private String reporttitle="";
    private String reportdescription="";
    private String filePath="";
    private String fileName="";
    private String mUserId="";

    @Override
    public void onReceive(Context context, Intent pIntent) {

        Log.i(TAG,"Retry clicked");
        if(pIntent.getExtras()!=null) {
            filePath = pIntent.getExtras().getString(BundleConstants.FILE_PATH);
            fileName = pIntent.getExtras().getString(BundleConstants.FILE_NAME);
        }
        //video upload
        if(pIntent.getAction()!=null && pIntent.getAction().equals(ACTION_VIDEO_RETRY)) {
            String consultId = pIntent.getExtras().getString(BundleConstants.CONSULT_ID);
            Intent intent = new Intent(context, UploadService.class);
            intent.putExtra("updatecapturereport", true);
            intent.putExtra(BundleConstants.FILE_PATH,filePath);
            intent.putExtra(BundleConstants.FILE_NAME,fileName);
            intent.putExtra("consultID",""+ ThisAppConfig.getInstance().getInt(ThisAppConfig.CONSULTID));
            context.startService(intent);
        }else if(pIntent.getAction()!=null && pIntent.getAction().equals(ACTION_PROFILE_UPDATE_RETRY)){
            //profile photo upload
            FileModel fileModel= (FileModel) pIntent.getExtras().getSerializable("fileModel");
            mUserId= ThisUserConfig.getInstance().getString(UserAttributes.USERID);
            Intent intent = new Intent(context, UploadImageFileService.class);
            intent.putExtra(BundleConstants.FILE_PATH,fileModel.getFilePath());
            intent.putExtra(BundleConstants.FILE_NAME,fileModel.getFilename());
            intent.putExtra(BundleConstants.USER_ID,mUserId);
            context.startService(intent);

        }else if(pIntent.getAction()!=null && pIntent.getAction().equals(ACTION_REPORT_RETRY)) {
            //Report upload
            reporttitle =  ThisUserConfig.getInstance().getString("reporttitle");
            reportdescription =  ThisUserConfig.getInstance().getString("reportdescription");
            Intent intent = new Intent(context, UploadReportService.class);// call to upload service
            try {
                intent.putExtra(BundleConstants.FILE_PATH,filePath);
                intent.putExtra(BundleConstants.FILE_NAME,fileName);
                intent.putExtra("reporttitle", reporttitle);
                intent.putExtra("reportdescription", reportdescription);

            } catch (Exception e) {
                e.printStackTrace();
            }
            context.startService(intent);
        }


        Toast.makeText(context,"File Uploading..", Toast.LENGTH_LONG).show();

    }
}
