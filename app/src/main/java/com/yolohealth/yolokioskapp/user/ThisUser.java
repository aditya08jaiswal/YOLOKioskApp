package com.yolohealth.yolokioskapp.user;

import android.util.Log;

import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.platform.Platform;


/***
 * This class has latest data to set all current req data of this user
 * any activity to be updated like list map picks from this location
 * @author arpit87
 *
 */
public class ThisUser {
	
		
	private static final String TAG = "Mover.ThisMover";
	private static ThisUser instance = null;

	private String mSkillSetStr = "";
	private String mName;
	private String mPhoneNumber;
    private String mMoverID;
	
	private ThisUser()
	{
        mMoverID = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
        mSkillSetStr = ThisUserConfig.getInstance().getString(ThisUserConfig.SKILLSET);
	}
				

	public void setMoverID(String moverID) {
		if (Platform.getInstance().isLoggingEnabled()) Log.i(TAG, "set mover id");
		this.mMoverID = moverID;
	}
	
	public String getMoverID() {
		if (Platform.getInstance().isLoggingEnabled()) Log.i(TAG, "get mover id" + this.mMoverID);
		return this.mMoverID;
	}

	public String getPhoneNumber() {
		return mPhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.mPhoneNumber = phoneNumber;
	}

    public String getSkillSet() {
        return mSkillSetStr;
    }

    public void setSkillSet(String skillSet) {
        this.mSkillSetStr = skillSet;
    }


    public static ThisUser getInstance() {
		if(instance == null)
			instance = new ThisUser();
		 return instance;
	}
	
	public static void clearAllData()
    {
    	instance = new ThisUser();
    }	

}
