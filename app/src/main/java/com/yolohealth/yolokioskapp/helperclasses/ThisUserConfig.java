package com.yolohealth.yolokioskapp.helperclasses;


public class ThisUserConfig extends ConfigBase{


	public static final String USER_PROFILE = "userProfile";
	public static final String CONSULT_ID = "consultid";
	public static final String USERID = "userid";
	public static final String APP_SESSION_TOKEN = "appsessiontoken";
	public static final String CURRENT_CITY = "currentCity";
	public static final String CITY_NAME = "cityname";
	public static final String DOB = "dob";
    private static ThisUserConfig instance = null;
	public static final String HEIGHT = "height";
	public static final String WEIGHT  ="weight";
	public static final String USER_ID = "userid";
	public static final String USERNAME = "username";
	public static final String USER_ROLE = "userRole";
	public static final String PHONE = "phone";
	public static final String TOKEN_GENERATED = "false";
	public static final String GENDER = "gender";
	public static final String AGE = "age";
	public static final String EMAIL = "email";
	public static final String QBUSERNAME = "qbusername";
	public static final String QBPASSWORD = "qbpassword";
	public static final String QBID = "qbid";
	public static final String SKILLSET = "skillset";
	public static final String AtmId = "id";
    public static final String ISBOOKED = "isbooked";
    public static final String ISAVAILABLE = "isavailable";
    public static final String MAINACTIVITYISSHOWING = "mainactivityisshowing";
	public static final String ACTIVECOACHQBIDS = "activecoachqbids"; //is a semicolon delimited list

	private ThisUserConfig()
	{
		super(Constants.USER_CONF_FILE);
	}
	
	public static ThisUserConfig getInstance()
	{
		if(instance == null)
			instance = new ThisUserConfig();
		return instance;
		
	}


}
