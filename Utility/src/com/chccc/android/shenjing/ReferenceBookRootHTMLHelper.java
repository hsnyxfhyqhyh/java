package com.chccc.android.shenjing;

public class ReferenceBookRootHTMLHelper {
	private static String prefix = "01DT";
	private static String suffix = ".htm";
	private static int chapterTotal = 60;
	private static String hrefTemplate = "<a href=\"%s\">%s</a><br>&nbsp;<br>";
	
	private static String newLine = "\n";
	
	
	public static void main (String args[]){
		String html = getReferenceInHTML();
		
		System.out.println(html);
	}
	
	private static String paddingLeadingzeros(int length, int number) { 
		
		 String n = number + "";
		 while (n.length() < length) {
			 n = "0" + n;
		 }
		
		 return n;
	}
	
	private static String getReferenceInHTML() {
		String html = "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("<HTML>" + newLine);
		sb.append("<HEAD>" + newLine +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>" + newLine +
				"</HEAD>" + newLine);
		sb.append("<BODY>" + newLine);
		
		
		int initNumber = 0;
		for (int i=0; i< chapterTotal; i++){
			String link = prefix + paddingLeadingzeros (2,  i )+ suffix;
			String text = "第" + i + "章";
			String href = String.format(hrefTemplate, link, text );
			sb.append(href + newLine);
		}
		
		
		sb.append("</BODY>" + newLine);
		sb.append("</HTML>");
		
		html = sb.toString();
		return html;
	}
}
