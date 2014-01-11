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

	/*
	 * This function provides style sheet to the final html output.
	 */
	public static String getStyleSheet() {
		String style = "<STYLE>";
		style = style + "A:link, A:visited, A:active {color: #ffffff;text-decoration: none;}";
		style = style + ".newsTitle{font-size: 28px;}";
		style = style + ".newsSummary{font-size: 22px;}";
		style = style + ".newsContent{font-size: 22px;}";
		style = style + "</STYLE>";
		return style;
	}

	/*
	 * When we know the start and the end string detectors for a part, we can
	 * retrieve the content
	 */
	public static String getWebPart(String input, String prefix, String suffix) {
		String part;

		int index = 0;

		index = input.indexOf(prefix) + prefix.length();

		input = input.substring(index);

		index = input.indexOf(suffix);

		part = input.substring(0, index);
		return part;
	}
	
	/*
	 * When we know the start and the end string detectors for a part, we can
	 * retrieve the content
	 */
	public static String removePart(String input, String prefix, String suffix) {
		String part;

		int index = 0;

		index = input.indexOf(prefix);
		
		if (index<0) return input;
		
		part = input.substring(0, index);
		

		index = input.indexOf(suffix);

		part = input.substring(index, input.length());
		return part;
	}
}
