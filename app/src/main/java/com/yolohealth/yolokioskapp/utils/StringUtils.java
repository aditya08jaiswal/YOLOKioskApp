package com.yolohealth.yolokioskapp.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {	
	
    
    public static boolean isEmpty(String s){
        return (s == null || s.length() == 0);
    }

    public static boolean isBlank(String s){
        if (isEmpty(s)) {
            return true;
        }
            
        int strLen = s.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(s.charAt(i)) == false){
                return false;
            }
        }

        return true;
    }
    
    public static String formatDate(String fromFormat,String toFormat,String date_time)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
    	Date date;
    	String newFormat = date_time;
		try {
			date = formatter.parse(date_time);
			formatter.applyPattern(toFormat);
			newFormat = formatter.format(date);	    	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newFormat;
    }
    
    public static String gettodayDateInFormat(String format)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date now = new Date();
		String date = dateFormat.format(now);
		return date;
    }
    
    public static String getFutureDateInformat(int future_num_days, String format)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);		
		cal.add(Calendar.DATE, future_num_days);
		Date travelDate = cal.getTime();
		String date = dateFormat.format(travelDate);
		return date;
    }
    
    public static String getFutureTimeInformat(int add_minutes, String format)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);		
		cal.add(Calendar.MINUTE, add_minutes);
		Date travelDate = cal.getTime();
		String date = dateFormat.format(travelDate);
		return date;
    }
    
    public static String getDateFromTplusString(String TplusString,String format)
    {    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		Date travelDate = cal.getTime();
		int days = TplusString.charAt(TplusString.length()-1)-48;
		if(days>0 && days<9)
		{
			cal.add(Calendar.DATE, days);
			travelDate = cal.getTime();
		}		
		String date = dateFormat.format(travelDate);
		return date;		
    }
    
    public static boolean checkIfRequestExpired(String dateTime)
	{
		//currently chking for 2 hrs
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date =  formatter.parse(dateTime);
			Long currentTime = System.currentTimeMillis();
			Long instaTime = date.getTime();
			if( currentTime - instaTime  > 7.2e6)
				return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}
    
    public static String parseName(String paramString)
    {
         if (paramString == null) {
           return null;
        }
        int i = paramString.lastIndexOf("@");
        if (i <= 0) {
           return paramString;
         }
    
         return paramString.substring(0, i);
     }
    
    public static Spannable getSpannedText(String label, String text)
	{
		StyleSpan bold = new StyleSpan(android.graphics.Typeface.BOLD);
		Spannable label_span = new SpannableString(label + " "+text);
		label_span.setSpan(bold, 0,label.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return label_span;
	}
    
    public static Spannable getSpannedTextUptoChar(String character, String text,int indexNum)
   	{
   		StyleSpan bold = new StyleSpan(android.graphics.Typeface.BOLD);
   		Spannable label_span = new SpannableString(text);
   		int start = -1;
   		int length =0;
   		for(int i=1 ;i<=indexNum; i++)
   		{
   			length = text.indexOf(character, start+1);
   			start = text.indexOf(character, start);
   		}
   		if(length<=0)
   			length = text.length();
   		label_span.setSpan(bold, 0,length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
   		return label_span;
   	}
	
    public static String getFBPicURLFromFBID(String fbid)
    {
    	String imageurl = "https://graph.facebook.com/" + fbid + "/picture?type=small";
    	return imageurl;
    }
    
    public static String getLargeFBPicURLFromFBID(String fbid)
    {
    	String imageurl = "https://graph.facebook.com/" + fbid + "/picture?type=large";
    	return imageurl;
    }
    
    public static String getFBCoverPicGraphPathFromFBID(String fbid)
    {
    	String imageurl = "https://graph.facebook.com/" + fbid;// + "?fields=cover";
    	//String imageurl = fbid + "?fields=cover";
    	return imageurl;
    }
    
    public static String getGooglePlayReferrerString(String id,String medium)
    {    	
    	String url = "https://play.google.com/store/apps/details?id=in.yolohealth&referrer=utm_source%3Dandroid_app%26utm_medium%3D" + medium+"%26utm_term%3D"+id;
    	return url;
    }
    
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}.";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    
    public static String fixedLengthString(String string, int length) {
        return String.format("%1." + length + "s", string);
    }
    
  
    
	
}

