package com.nguyenvando.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MyAppUtil {
	
	public  String Date_To_String(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String datestring = dateFormat.format(date);
		 return datestring;
	}
	
	public Date String_To_Date(String dateString){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	      try {

	         Date date =  formatter.parse(dateString);
	         return date;

	      } catch (Exception e) {
	         return null;
	      }

	}
	
	public  String  TimeFormat(String timeStr){
		String[] result = timeStr.split(":");
		try{
			int hour = Integer.parseInt(result[0]);
			if(hour > 12)
				return result[0]+":"+result[1] +" PM";
			else{
				return result[0]+":"+result[1] +" AM";
			}
		}catch(ParseException e){
			return "Can't parse to Integer!";
		}catch (Exception e) {
			return "Bug! hehe ";
		}
	
	}	
	
	public  Date getSystemDateTime(){ // get date and time System
		Date systemDate = new Date();		
		return systemDate;
	}
	
	public  boolean is_date1_affer_date2(Date date1, Date date2){
		if(date1.after(date2)){
			return true;
		}
		return false;
	}
	
}
