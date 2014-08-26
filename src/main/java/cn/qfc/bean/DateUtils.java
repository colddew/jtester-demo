package cn.qfc.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		
		Calendar cal = new GregorianCalendar();
		cal.set(year, month, day, hour, minute, second);
		
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		Date date = DateUtils.getDate(2013, 10, 19, 14, 50, 30);
		System.out.println(date);
	}
}
