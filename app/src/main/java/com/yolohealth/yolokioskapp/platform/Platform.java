package com.yolohealth.yolokioskapp.platform;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.yolohealth.yolokioskapp.gcm.GCMCONSTANTS;
import com.yolohealth.yolokioskapp.helperclasses.ThisAppConfig;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.httpclient.SendGCMIdRequest;
import com.yolohealth.yolokioskapp.user.ThisUser;
import com.yolohealth.yolokioskapp.utils.StringUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class Platform {
	
	private final static String TAG = "Platform.Platform";
	private static Platform instance = null;
	private Context context;
	private Handler handler;
    GoogleCloudMessaging gcm;
	private boolean ENABLE_LOGGING = false;
    String regid;
//    public static String APPSECRET = "y0l0pr0dappsecret";
    public static String APPSECRET = "y0l0adm1n";
    private RequestQueue mRequestQueue;
		
	private Platform() {
	}
	
	public static Platform getInstance()
	{
		if(instance == null)
			instance = new Platform();
		return instance;
	}
	
	public boolean isLoggingEnabled() {
		return ENABLE_LOGGING;
	}	

	public Context getContext(){
		return context;
	}	
	
	public Handler getHandler(){
		return handler;
	}
	
	public void initialize(Context context) {
        Log.i(TAG,"INIT SUCCESS");
        //disableSSLCertificateChecking();
		this.context= context;			
		handler = new Handler();
		ENABLE_LOGGING = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
		ThisUser.getInstance();

        //setup gcm
        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this.getContext());
            regid = getRegistrationId(context);

            String userId = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
            if (regid.isEmpty() && !StringUtils.isBlank(userId)) {
                registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
	}

    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    public String getRegistrationId(Context context) {
        String registrationId = ThisAppConfig.getInstance().getString(ThisAppConfig.GCM_REG_ID);
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = ThisAppConfig.getInstance().getInt(ThisAppConfig.PROPERTY_APP_VERSION);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public int getAppVersion() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    @SuppressLint("StaticFieldLeak")
    public void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(GCMCONSTANTS.GCM_SENDER_ID);
                    msg = "GCM registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.

                    sendRegistrationIdToBackend(regid);

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    ThisAppConfig.getInstance().putString(ThisAppConfig.GCM_REG_ID, regid);
                    ThisAppConfig.getInstance().putInt(ThisAppConfig.PROPERTY_APP_VERSION,getAppVersion());
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
//                if(Platform.getInstance().isLoggingEnabled())
//                     ToastTracker.showToast(msg);
            }
        }.execute(null, null, null);
    }

    private void sendRegistrationIdToBackend(String gcmRegId)
    {
        String userId = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
        SendGCMIdRequest gcmRegReq = new SendGCMIdRequest(userId,gcmRegId,null);
        gcmRegReq.executeRequest();
    }
	
	public String getThisAppVersionName()
	{
	 try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
	}



    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

	
	/*public void startChatService(){
	     
        Intent i = new Intent("in.beepchat.ChatService.ChatService");                  
        if (Platform.getInstance().isLoggingEnabled()) Log.d( TAG, "Service starting" );
        context.startService(i);
       
       }
            
	
	 public void stopChatService() {		
	          Intent i = new Intent("in.beepchat.ChatService.ChatService");
	          context.stopService(i);       
	          
	          if (Platform.getInstance().isLoggingEnabled()) Log.d( TAG, "Service stopped" );	         
	             
    }*/

    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                }
        };

        try {

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }

            });
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

