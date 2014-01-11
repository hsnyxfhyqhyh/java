package com.ying.util.decimal;

public interface QuestionFormatter {
	/*
	 * write out the questions according to the page layout defined in the template HTML
	 * 16 rows per page, with 3 paragraph blocks at the bottom acts as page break when printing out
	 */
	public String format(String[] questions);
	
	public int getRowCount();
	public int getColumnCount();
		
//
//	
//	public static String formatPlainFiles(String[] questions) {
//		StringBuffer sb = new StringBuffer();
//		int countQ = 0;
//		int total = questions.length;
//		
//		while (countQ < total) {
//			sb.append("#" + questions[countQ] + "#\n" );
//			
//			//increase the question counter in the array
//			countQ++;
//		}
//		
//		return sb.toString();
//	}
}
