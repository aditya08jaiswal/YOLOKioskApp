package com.yolohealth.yolokioskapp.httpclient;

import android.net.Uri;

import com.yolohealth.yolokioskapp.helperclasses.ServerConstants;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.user.UserAttributes;


/**
 * Created by yolo on 22/3/17.
 */

public class GetAllUsersRequest extends HttpRequest {
    private static String RESTAPI="getAllUserList";
    public static final String URL = ServerConstants.SERVER_ADDRESS + ServerConstants.USER + "/"+RESTAPI+"/";
    private static final String TAG = "getAllUserList";

    public GetAllUsersRequest(){

    }

    public GetAllUsersRequest(String phone, String otp, HTTPListener getConsultIdListner) {
        String authenticatedURL = GetServerAuthenticatedURL(URL);
        Uri.Builder builder = Uri.parse(authenticatedURL).buildUpon();
        builder.appendQueryParameter(ThisAppConfig.APPUUID, ThisAppConfig.getInstance().getString(ThisAppConfig.APPUUID));
        builder.appendQueryParameter(UserAttributes.SEARCHPARAMETER, "Phone");
        builder.appendQueryParameter(UserAttributes.OTP, otp);
        builder.appendQueryParameter(UserAttributes.SEARCHPARAMETERVALUE, phone);
        builder.appendQueryParameter(UserAttributes.PHONE, phone);
//        builder.appendQueryParameter(UserAttributes.APPSECRET, Platform.APPSECRET);
        String URL = builder.build().toString();
        makeRequest(URL,getConsultIdListner);
    }


    @Override
    public String getTAG() {
        return TAG;
    }
}
