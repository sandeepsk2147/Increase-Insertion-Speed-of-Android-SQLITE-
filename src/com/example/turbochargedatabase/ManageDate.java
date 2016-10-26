package com.example.turbochargedatabase;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ManageDate {
	
	
	public static int Date(){
		 Calendar cal = Calendar.getInstance();
			return (cal.get(Calendar.DAY_OF_MONTH));
	}
		
	public static int Month(){
		 Calendar cal = Calendar.getInstance();
		return  (cal.get(Calendar.MONTH)+1);
}
	public static int Year(){
		 Calendar cal = Calendar.getInstance();
		return  (cal.get(Calendar.YEAR));
		}
	public static int Hour(){
		 Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
		}
	public static int Minute(){
		 Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
		}
	public static int Second(){
		 Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
		}
	
	
	
}
