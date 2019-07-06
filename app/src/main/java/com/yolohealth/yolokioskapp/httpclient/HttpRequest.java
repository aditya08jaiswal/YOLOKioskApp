package com.yolohealth.yolokioskapp.httpclient;

import android.content.Intent;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.user.UserAttributes;
import com.yolohealth.yolokioskapp.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class HttpRequest {

	Request mRequest = null;
	HTTPListener mHTTPListener = null;

	public abstract String getTAG();

	//do not add this to initial add user request
	public JSONObject GetServerAuthenticatedJSON()
	{
		JSONObject jObj = new JSONObject();
		try {
			jObj.put(ThisAppConfig.APPUUID, ThisAppConfig.getInstance().getString(ThisAppConfig.APPUUID));
			jObj.put(ThisUserConfig.APP_SESSION_TOKEN, ThisUserConfig.getInstance().getString(ThisUserConfig.APP_SESSION_TOKEN));
			jObj.put(ThisUserConfig.USER_ID, ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID));

		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return jObj;
	}

	public String GetServerAuthenticatedURL(String URL)
	{
		Uri.Builder builder = Uri.parse(URL).buildUpon();
		builder.appendQueryParameter(UserAttributes.AUTHKEY, ThisAppConfig.getInstance().getString(UserAttributes.AUTHKEY));
		builder.appendQueryParameter(UserAttributes.AUTH_SECRET, ThisAppConfig.getInstance().getString(UserAttributes.AUTH_SECRET));

		builder.appendQueryParameter(ThisAppConfig.APPUUID, ThisAppConfig.getInstance().getString(ThisAppConfig.APPUUID));
		builder.appendQueryParameter(ThisUserConfig.USER_ID, ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID));
		builder.appendQueryParameter(ThisUserConfig.APP_SESSION_TOKEN, ThisUserConfig.getInstance().getString(ThisUserConfig.APP_SESSION_TOKEN));
		return builder.build().toString();
	}

	public void executeRequest()
	{
		Platform.getInstance().addToRequestQueue(mRequest);
	}

	public void cancelRequest()
	{
		mRequest.cancel();
	}

	Response.Listener<JSONObject> basejsonsuccesslistener = new Response.Listener<JSONObject>() {
		@Override
		public void onResponse(JSONObject response) {
			Logger.d(getTAG(), response.toString());
			if(mHTTPListener!=null)
				mHTTPListener.onSuccess(response);
		}
	};

	Response.Listener<String> basestringsuccesslistener = new Response.Listener<String>() {
		@Override
		public void onResponse(String response) {
			Logger.d(getTAG(), response);
			try {
				JSONObject json = new JSONObject(response);
				if(mHTTPListener!=null)
					mHTTPListener.onSuccess(json);
			} catch (JSONException e) {
				if(mHTTPListener!=null)
					mHTTPListener.onError("Cant convert string to JSON");
				e.printStackTrace();
			}
		}

	};

	Response.ErrorListener baseerrorlistener = new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError error) {
			String errorData;
			String message;
			if(mHTTPListener!=null && error.networkResponse!=null) {
				errorData=error.networkResponse.data +""+ error.networkResponse.statusCode;
				mHTTPListener.onError(errorData);
			}
			if (error instanceof NetworkError) {
				Intent in = new Intent("networklost");
				Platform.getInstance().getContext().sendBroadcast(in);
				message = "Cannot connect to Internet...Please check your connection!";
			} else if (error instanceof ServerError) {
				message = "The server could not be found. Please try again after some time!!";
			} else if (error instanceof AuthFailureError) {
				message = "Cannot connect to Internet...Please check your connection!";
			} else if (error instanceof ParseError) {
				message = "Parsing error! Please try again after some time!!";
			} else if (error instanceof NoConnectionError) {
				message = "Cannot connect to Internet...Please check your connection!";
			} else if (error instanceof TimeoutError) {
				message = "Connection TimeOut! Please check your internet connection.";
			}
			Logger.e(getTAG(),error.toString());
		}
	};


	public void  makeRequest(String URL,JSONObject params,HTTPListener listener)
	{
		//POST request
		Logger.d(getTAG(),URL);
		Logger.d(getTAG(),params.toString());
		mHTTPListener = listener;
		mRequest = new JsonObjectRequest(Request.Method.POST,URL,params,basejsonsuccesslistener,baseerrorlistener);
		mRequest.setRetryPolicy(new DefaultRetryPolicy(
				30000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

	public void  makeRequest(String URL,HTTPListener listener)
	{
		//GET REQUEST
		Logger.d(getTAG(),URL);
		mHTTPListener = listener;
		mRequest = new StringRequest(Request.Method.GET,URL,basestringsuccesslistener,baseerrorlistener);
		mRequest.setRetryPolicy(new DefaultRetryPolicy(
				30000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

}
