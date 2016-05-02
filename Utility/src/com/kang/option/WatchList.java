package com.kang.option;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import com.kang.util.FileUtil;

public class WatchList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList lines = new ArrayList();
		

		lines = FileUtil.getContents(lines, new File("optionList.txt"));

		// printWords(lines);

		for (int j = 0; j < lines.size(); j++) {
			
			String line = (String) lines.get(j);

			ArrayList optionLines = new ArrayList();
			String fileName = parseStock(line, "P", optionLines);
			WriteOptionFiles(optionLines, fileName);

			optionLines = new ArrayList();
			fileName = parseStock(line, "C", optionLines);
			WriteOptionFiles(optionLines, fileName);
		}
		System.out.println("---DONE----");
	}

	public static String parseStock(String stockLine, String direction, ArrayList optionLines) {
		// ArrayList optionLine = new ArrayList();
		StringTokenizer st = new StringTokenizer(stockLine, "#");

		String stock = "";
		String startPrice = "";
		String endPrice = "";
		String increment = "";
		String date = "";

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			stock = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			startPrice = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			endPrice = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			increment = st1.nextToken();
		}

		if (st.hasMoreTokens()) {
			StringTokenizer st1 = new StringTokenizer(st.nextToken(), "=");
			String k = st1.nextToken();
			date = st1.nextToken();
		}

		String optionPrefix = "." + stock;

		BigDecimal sp = new BigDecimal(startPrice);
		BigDecimal ep = new BigDecimal(endPrice);
		BigDecimal ip = new BigDecimal(increment);

		optionLines.add(getOption(optionPrefix, date, direction, sp));
		while (sp.compareTo(ep) == -1) {
			sp = sp.add(ip);
			optionLines.add(getOption(optionPrefix, date, direction, sp));
		}

		// optionLine.add(getOption(optionPrefix, date, "P", ep));

		// optionLine.add("stock: " + stock);
		// optionLine.add("startPrice: " + startPrice);
		// optionLine.add("endPrice: " + endPrice);
		// optionLine.add("increment: " + increment);
		// optionLine.add("date: " + date);

		return stock + date + direction;

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

}
