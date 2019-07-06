package com.yolohealth.yolokioskapp.httpclient;

import android.content.Intent;

import com.yolohealth.yolokioskapp.helperclasses.MoverAttributes;
import com.yolohealth.yolokioskapp.helperclasses.ServerConstants;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.quickblox.ThisMoverConfig;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by yolo on 2/10/15.
 */
public class SetUserOnlineStatusRequest extends HttpRequest{

    private static String RESTAPI="setOnlineStatusForUser";
    public static final String URL = ServerConstants.SERVER_ADDRESS + ServerConstants.USER + "/"+RESTAPI+"/";
    private static final String TAG = "HttpClient.SetDoctorOnlineStatusRequest";

    public SetUserOnlineStatusRequest(int onlinestatus, HTTPListener listener) {

        JSONObject jsonObject= GetServerAuthenticatedJSON();

        try {
            jsonObject.put(MoverAttributes.ONLINESTATUS, onlinestatus);
//            jsonObject.put(UserAttributes.APPSECRET, Platform.APPSECRET);
            jsonObject.put(MoverAttributes.UNIQUEID, ThisAppConfig.getInstance().getString(ThisAppConfig.APPUUID));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(listener==null) {
            listener = new HTTPListener() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONObject body = response.getJSONObject("body");
                        boolean onlinestatus = body.getBoolean(MoverAttributes.ONLINESTATUS);
                        boolean isbusy = body.getBoolean(MoverAttributes.ISBUSY);
                        if (isbusy)
                            ThisMoverConfig.getInstance().putBool(ThisMoverConfig.ISBUSY, true);
                        else
                            ThisMoverConfig.getInstance().putBool(ThisMoverConfig.ISBUSY, false);

                        Intent i = new Intent("in.yolohealth.yolo_doctorapp_android.ONLINESTATUSRECEIVED");
                        Platform.getInstance().getContext().sendBroadcast(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String error) {

                }
            };
        }
        makeRequest(URL,jsonObject,listener);
    }

    @Override
    public String getTAG() {
        return TAG;
    }

}
