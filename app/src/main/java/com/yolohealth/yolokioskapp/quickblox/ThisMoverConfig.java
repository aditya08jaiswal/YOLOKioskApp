package com.yolohealth.yolokioskapp.quickblox;


import com.yolohealth.yolokioskapp.helperclasses.ConfigBase;
import com.yolohealth.yolokioskapp.helperclasses.Constants;


public class ThisMoverConfig extends ConfigBase {


	public static final String USERID = "userid";
	public static final String USER_PROFILE = "profilepic";
	public static final String EMAIL = "email";
	public static final String QUALIFICATION = "qualification";
	public static final String SKILLNAMESTR = "skillnamestr";
	public static final String SPECIALISATION = "specialization";
	public static final String SKILLSTR = "skillstr";
	public static final String MEDIAFILE = "mediafile";
	public static final String CMAERA_WIDTH = "camerawidth";
	public static final String CMAERA_HEIGHT = "cameraheight";
	private static ThisMoverConfig instance = null;
	
	
	public static final String MOVER_ID = "moverid";
	public static final String MOVERNAME = "movername";
	public static final String PHONE = "phone";
	public static final String QBUSERNAME = "qbusername";
	public static final String QBPASSWORD = "qbpassword";
	public static final String QBID = "qbid";
	public static final String MOVERGROUPS = "movergroups";
	public static final String SKILLSET = "skillset";
    public static final String ISBOOKED = "isbooked";
    public static final String ISAVAILABLE = "isavailable";
	public static final String ISBUSY = "isbusy";
	public static final String MAINACTIVITYSHOWING = "mainactivityshowing";
	public static final String USERNAME = "movername";
	public static final String MOVERDETAILS = "moverdetails"; //full json saved here causing a lil duplication


	private ThisMoverConfig(){super(Constants.MOVER_CONF_FILE);}
	
	public static ThisMoverConfig getInstance()
	{
		if(instance == null)
			instance = new ThisMoverConfig();
		return instance;
		
	}
}
