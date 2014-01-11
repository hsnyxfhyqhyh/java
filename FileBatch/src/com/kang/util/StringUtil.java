package com.kang.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	/*
	 * get today's date as format in yyyy-MM-dd
	 */
	public static String getDateOfToday() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	
}
