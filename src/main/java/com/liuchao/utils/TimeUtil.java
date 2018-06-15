package com.liuchao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static String getDate(String Format) {
	    Date date = new Date();
	    SimpleDateFormat DateFormat = new SimpleDateFormat("Format");
	    System.out.println("当前时间：" + DateFormat.format(date).toString());
	    return DateFormat.format(date).toString();
	}

}
