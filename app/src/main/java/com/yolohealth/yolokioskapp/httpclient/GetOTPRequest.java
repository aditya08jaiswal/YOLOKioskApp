package com.yolohealth.yolokioskapp.httpclient;

import android.net.Uri;

import com.yolohealth.yolokioskapp.helperclasses.ServerConstants;
import com.yolohealth.yolokioskapp.user.UserAttributes;


public class GetOTPRequest extends HttpRequest{

	private String TAG = "GetOTPRequest";
    private static String RESTAPI="getOTP";
    public static final String URL = ServerConstants.SERVER_ADDRESS + ServerConstants.COMMUNICATIONS + "/"+RESTAPI+"/";



	public GetOTPRequest(String phone, HTTPListener listener)
	{
		String authenticatedURL = GetServerAuthenticatedURL(URL);
		Uri.Builder builder = Uri.parse(authenticatedURL).buildUpon();
		builder.appendQueryParameter(UserAttributes.USERPHONE, phone );
		//		builder.appendQueryParameter(UserAttributes.APPSECRET, Platform.APPSECRET);
		String URL = builder.build().toString();
		makeRequest(URL,listener);
						
		}


	@Override
	public String getTAG() {
		return TAG;
	}
	
	

}
