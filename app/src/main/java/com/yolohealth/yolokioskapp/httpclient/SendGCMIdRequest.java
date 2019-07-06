package com.yolohealth.yolokioskapp.httpclient;

import com.yolohealth.yolokioskapp.helperclasses.ServerConstants;
import com.yolohealth.yolokioskapp.user.UserAttributes;

import org.json.JSONException;
import org.json.JSONObject;


public class SendGCMIdRequest extends HttpRequest{
	private static String RESTAPI="setGCMRegistrationId";
    public static final String URL = ServerConstants.SERVER_ADDRESS + ServerConstants.USER + "/"+RESTAPI+"/";
    private static final String TAG = "GCMRequest";


	public SendGCMIdRequest(String userid, String gcm, HTTPListener listener)
	{
		JSONObject jsonObject= GetServerAuthenticatedJSON();
				
		try {
			jsonObject.put(UserAttributes.USERID, userid);
			jsonObject.put(UserAttributes.GCMREGID, gcm);
//			jsonObject.put(UserAttributes.APPSECRET, Platform.APPSECRET);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		makeRequest(URL,jsonObject,listener);

	}

	@Override
	public String getTAG() {
		return TAG;
	}
}

