package com.yolohealth.yolokioskapp.helperclasses;

import android.content.SharedPreferences;

import com.yolohealth.yolokioskapp.platform.Platform;

//import in.yolohealth.saas.platform.Platform;


public  class ConfigBase {
	
	private String config_file = null;
	private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    
   	public ConfigBase(String filename)
    {
    	config_file = filename;
    	if(Platform.getInstance()!=null && Platform.getInstance().getContext()!=null) {
			settings = Platform.getInstance().getContext().getSharedPreferences(config_file,0);
			editor = settings.edit();
		}
    }
    
	public String getString(String key)
	{
		return settings.getString(key,"");
	}
	
	public void putString(String key,String value){
		editor.putString(key, value);
		editor.commit();
	}
	
	public long getLong(String key){
		return settings.getLong(key, -1);
	}
	
	
	public void putLong(String key,long value){
		editor.putLong(key, value);
		editor.commit();
	}
	
	public int getInt(String key)
	{
		return settings.getInt(key, -1);
	}
	
	public void putInt(String key,int value){
		editor.putInt(key, value);
		editor.commit();
	}

	public boolean getBool(String key)
	{
		return settings.getBoolean(key, false);
	}
	
	public void putBool(String key,boolean value){
		editor.putBoolean(key, value);
		editor.commit();
	}
	public void clear() {
		editor.clear();
		editor.commit();
	}

}
