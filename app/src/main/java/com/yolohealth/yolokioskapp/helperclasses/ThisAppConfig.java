package com.yolohealth.yolokioskapp.helperclasses;

public class ThisAppConfig extends ConfigBase{

    public static final String CONSULTID = "consultID";
//    public static final String KIOSK_ID = "160";//prod
//    public static final String KIOSK_ID = "107";
//    public static String KIOSK_ID = "199";
//    public static String KIOSK_ID = "100";
//    public static final String KIOSK_ID = "213";
    public static  String KIOSK_ID = "208";//doctel
//    public static String KIOSK_ID = "160";//gcare
//    public static final String KIOSK_ID = "212";//ujjain
    public static final String CALLEND = "callend";
    public static final String DEFAULT_OTP = "123321";
    public static final String VIDEO_RESOLUTION = "videoResolution";
    private static ThisAppConfig instance = null;

	public static final String APPUUID = "userappuuid";
	
	//app settings

    public static final String GCM_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String PROPERTY_ON_SERVER_EXPIRATION_TIME ="onServerExpirationTimeMs";
    public static final String DEVICEID = "device_id";
    public static final String REFERRER_STRING = "referrer_string";
    public static final String NOTIFICATION_SETTINGS = "not_setting";
    public static final String SOUND_SETTINGS = "sound_setting";

    public static final String EXTRA_MESSAGE = "message";
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	private ThisAppConfig(){super(Constants.APP_CONF_FILE);}
	
	public static ThisAppConfig getInstance()
	{
		if(instance == null)
			instance = new ThisAppConfig();
		return instance;
		
	}
}
