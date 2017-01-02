package com.kang.option;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;

import com.hp.hpl.sparta.ParseException;
import com.kang.util.FileUtil;

public class WatchList {
	private static final String commentLineStarter = "##";
	private static final String stockList = "optionList.txt";
	private static final String optionDatePattern = "yyMMdd";
	
	
	/**
	 * @param args
	 * if there is an input, a date of a specific week, only generate the options for that week. 
	 * otherwise , use friday or wednesday of next week. 
	 */
	public static void main(String[] args) {
		ArrayList lines = new ArrayList();
		lines = FileUtil.getContents(lines, new File(stockList));
		lines = FileUtil.filterLines(lines, commentLineStarter);

		if (args.length > 1) {
			System.out.println("invalid parameters");
		}
		
		String baseDate = "" ; 
		
		if (args.length>0) {
			baseDate= args[0];
		} 

		if (baseDate ==null) {
			baseDate = "";
		} else {
			baseDate = baseDate.trim();
		}
		
		if (!baseDate.equalsIgnoreCase("")  && baseDate.length() !=10){
			System.out.println("invalid date format");
			System.exit(-1);
		}
		
		for (int j = 0; j < lines.size(); j++) {
			parseStock(lines.get(j).toString(), baseDate);
		}
		System.out.println("---DONE----");
	}

	
	
	private static void parseStock(String stockLine, String baseDate) {
		//	Stock=SPY#CurrentPrice=223#PriceDifference=6#HalfDollarFlag=true#WeekDay=5
		StringTokenizer st = new StringTokenizer(stockLine, "#");

		String stock = "";
		String optionPrefix = ".";
		String currentPrice = "";
		int priceDifference = 0;
		String halfDollarFlag = "";
		String weekDay = "";
		int dayOfWeek = 5;

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			stock = st1.nextToken();
			optionPrefix = optionPrefix + stock;
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			currentPrice = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			String pd = st1.nextToken();
			
			priceDifference = Integer.parseInt(pd);
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			halfDollarFlag = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			weekDay = st1.nextToken();
			
			dayOfWeek = Integer.parseInt(weekDay);
		}

		BigDecimal cP = new BigDecimal(currentPrice);
		BigDecimal spc = cP.add(new BigDecimal(priceDifference * (-1))); // call start price 
		BigDecimal spp = cP.add(new BigDecimal(priceDifference * (-1))); // put start price
		BigDecimal ep = cP.add(new BigDecimal(priceDifference));
		BigDecimal ip = new BigDecimal(1);
		if (halfDollarFlag.trim().equals("true")){
			ip = new BigDecimal(0.5);
		}
		
		String optionDate = getDateOfOption(dayOfWeek, baseDate);
		
		//process call first  -start 
		ArrayList optionCallLines = new ArrayList();
		String direction = "C";
		
		optionCallLines.add(getOption(optionPrefix, optionDate, direction, spc));
		while (spc.compareTo(ep) == -1) {
			spc = spc.add(ip);
			optionCallLines.add(getOption(optionPrefix, optionDate, direction, spc));
		}

		String callOptionFileName = "optionCall_" + stock + "_" + optionDate + ".txt";
		WriteOptionFiles(optionCallLines, callOptionFileName);
		//process call first  -end 
		
		//process put  -start 
		ArrayList optionPutLines = new ArrayList();
		direction = "P";
		
		optionPutLines.add(getOption(optionPrefix, optionDate, direction, spp));
		while (spp.compareTo(ep) == -1) {
			spp = spp.add(ip);
			optionPutLines.add(getOption(optionPrefix, optionDate, direction, spp));
		}

		String putOptionFileName = "optionPut_" + stock + "_" + optionDate + ".txt";
		WriteOptionFiles(optionPutLines, putOptionFileName);
		//process put  -end 
	}
	

	private static String getOption(String optionPrefix, String date,
			String direction, BigDecimal price) {
		String result = optionPrefix + date + direction.toUpperCase()
				+ price.toString();
		return result.replace(".0", "");
	}
	

	public static void WriteOptionFiles(ArrayList optionLines, String fileName) {
		String content = "";
		
		for (int j = 0; j < optionLines.size(); j++) {
			content = content + (String) optionLines.get(j) + "\n";
		}
		
		FileUtil.writeFile(content, fileName);
	}

	private static String getDateOfOption(int dayOfweek, String  baseDate) {
		//WednesDay's dayOfWeek is actually 4, Friday's dayOfWeek is actually 6. both are 1 bigger than the value passed in
		dayOfweek++;
		
		String sOptionDate = "";
		
	    try {
	    	
			Calendar date = Calendar.getInstance();
			
			//if base date is not blank then use the date passed in as string; otherwise advance to net week
			if (!baseDate.trim().equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date inputDate = dateFormat.parse(baseDate);
				
				date.setTime(inputDate);
			} else {
				date.add(Calendar.DAY_OF_MONTH, 7);
			}
			
			int diff = dayOfweek - date.get(Calendar.DAY_OF_WEEK);
			
			date.add(Calendar.DAY_OF_MONTH, diff);
			
			
		    SimpleDateFormat dateFormat = new SimpleDateFormat(optionDatePattern);
		    //System.out.println(dateFormat.format(date.getTime()));
		    
		    sOptionDate = dateFormat.format(date.getTime());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return sOptionDate;
	}
	
}
