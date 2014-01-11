package com.ying.util.handwriting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.kang.util.FileUtil;

public class HandWritingMaster {
	public static void main(String args[]){
		//get lines 
		ArrayList lines = new ArrayList();
		File wf  = new File("handwriting.txt");
		FileUtil.getContents(lines, wf);
		
//		FileUtil.writeFile(printLines(lines), "handwriting.htm");
		
		Collections.shuffle(lines);
		FileUtil.writeFile(printSightwords(lines), "handwriting.htm");
		
		System.out.println("DONE");
	}
	
	private static String printSightwords(ArrayList lines){
		int repeat = 3;
		StringBuffer sb = new StringBuffer();
		sb.append(getHTMLHeader());
		
		//loop through each line
		for (int i=0; i< lines.size(); i++) {
			String line = (String) lines.get(i);
			
			if ("Christmas".equals(line) 
					|| "birthday".equals(line)
			) {
				repeat = 2;
			} else {
				repeat = 3;
			}
			
			//print a "print" word;
			for (int columnCounter = 0; columnCounter < line.length(); columnCounter ++) {
				String c = line.substring(columnCounter, columnCounter+1);
				sb.append(getLetterHTMLTransformation(c, "print"));
			}
			//give a little space
			sb.append(getLetterHTMLTransformation(" ", "dot"));
			
			for (int wordCount = 0; wordCount < repeat; wordCount++) {
				for (int columnCounter = 0; columnCounter < line.length(); columnCounter ++) {
					String c = line.substring(columnCounter, columnCounter+1);
					sb.append(getLetterHTMLTransformation(c, "dot"));
				}
				
				//give a little space
				sb.append(getLetterHTMLTransformation(" ", "dot"));
			}
			
			sb.append("			<p>&nbsp;</p>");
		}
		
		
		
		sb.append(getHTMLFooter());
		
		return sb.toString();
	}
	
	private static String printLines(ArrayList lines){
		StringBuffer sb = new StringBuffer();
		sb.append(getHTMLHeader());
		
		//loop through each line
		for (int i=0; i< lines.size(); i++) {
			String line = (String) lines.get(i);
			
			if (line != null && !line.trim().equals("")){
				for (int columnCounter = 0; columnCounter < line.length(); columnCounter ++) {
					String c = line.substring(columnCounter, columnCounter+1);
					sb.append(getLetterHTMLTransformation(c, "dot"));
				}
				
				sb.append("			<br></br>");
			}
		}
		
		sb.append(getHTMLFooter());
		
		return sb.toString();
	}
	
	private static String getHTMLHeader() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html lang=\"en-US\">");
		
		sb.append("<head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		sb.append("		<title>test</title>");
		sb.append("		<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet/reset-font-grids.css\" />");
		sb.append("		<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet/style.css\" />");
		sb.append("</head>");
		
		sb.append("<body>");
		
		sb.append("<div id=\"container\">");
		sb.append("		<div id=\"header\" style=\"width: 642px;\">");
		sb.append("		<table class=\"top_table\">");
		sb.append("			<tbody><tr>");
		sb.append("			<td class=\"student_name\">Name: ____________________________</td>");
		sb.append("			<td class=\"date\">Date: ________________</td>");
		sb.append("			</tr>");
		sb.append("		</tbody></table>");
		
		sb.append("<div class=\"title\"><h2></h2></div>");
		sb.append("</div>");
		
		
		sb.append("<div id=\"body\">");
		sb.append("<table class=\"main_table\">");
		sb.append("		<tbody><tr><td style=\"width: 642px;\">");
		
		return sb.toString();
	}
	
	private static String getHTMLFooter() {
		StringBuffer sb = new StringBuffer();
		sb.append("		</td></tr></tbody></table></div>");
		
		
		sb.append("</div>");

		sb.append("</body>");
		
		sb.append("</html>");
		return sb.toString();
		
	}
	
	private static String getLetterHTMLTransformation(String s, String style){
		String font_family = "dn";
		
		String prefix = "<img src=\"images/fonts/" + font_family + "/block";
		String suffix = "block" + style + ".gif\" style=\"height: 50px;\" />";
		
		if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(s)>=0){
			return prefix + "/uppercase/" + s + "-" + font_family + suffix;
		}
		
		if ("abcdefghijklmnopqrstuvwxyz".indexOf(s)>=0){
			return prefix + "/lowercase/" + s + "-" + font_family + suffix;
		}
		
		if (s.equals(" ")) {
			return prefix + "/space-"  + font_family + suffix;
		}
		return "";
		
		
	}
}
