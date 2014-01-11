package com.ying.util.fraction;

import java.util.StringTokenizer;

public class HTMLVertical3ColumnFormatter implements QuestionFormatter{
	private static final int COLUMN = 3;
	private static final int ROW = 4;
	
	@Override
	public String format(String[] questions) 
	{
		StringBuffer sb = new StringBuffer();
		
		int total = questions.length;
		
		String htmlHeader = "<html>\n" +
							"	<head>\n" + 
							"		<style type=\"text/css\">\n" +
							" 			td {color:black;font-family:\"serif\";font-size:26px;font-weight:bold;}\n" +
							"			tr {height: 224px; vertical-align:top;}\n" +
							" 		</style>\n" +
							" 	</head>\n" +
							" 	<body>\n" ;
		
		String htmlFooter = " 	</body>\n" +
							"</html>\n";
		
		try {
			sb.append(htmlHeader);
			
			int countQ = 0;
			
			while (countQ < total) {
				int pageRemainder = countQ% (ROW * COLUMN);
				
				//every page has a table and a page breaker....
				
				//page starts here.
				if (pageRemainder ==0) {
					sb.append("<table cellspacing=0 cellpadding=0 width=100% align=center>\n");
				}
				
				int remainder = countQ % COLUMN;
				
//				System.out.println(toVertical(questions[countQ]));
				
				//calculate the starting point of a row.
				if (remainder==0){
					sb.append(	"<tr>\n"); 
				}
				
				sb.append (		" 	<td>" + toVertical(questions[countQ]) + "</td>\n" ); 

				if (remainder==COLUMN-1){
					sb.append(	"</tr>\n"); 
				}			
				
				//table ends here, and adds the page breaker of <P> tags...
				if (pageRemainder == (ROW*COLUMN-1)) {
					sb.append	("</table>\n" + 
								"<br>\n" 
								);
				}
				
				//increase the question counter in the array
				countQ++;
			}
			
			sb.append(htmlFooter);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private String toVertical(String question){
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(question, "=");
		
		String q = null;
		while(st.hasMoreTokens()){
			q = st.nextToken().trim();
			if (!q.equals("")){
				break;
			}
		}
		
		int i = 0;
		st = new StringTokenizer(q, " ");
		while(st.hasMoreTokens()){
			if (i==0){
				sb.append("<table cellspacing=0 cellpadding=0 border=0><tr><td align=right>" + st.nextToken().trim() +"<br>");
			} else if (i==1){
				sb.append("<U><B>" + st.nextToken() + " ");
			} else {
				sb.append(st.nextToken() + "<br></B></U></td></tr></table>");
			}
			i++;
		}
		
		return sb.toString();
	}

	@Override
	public int getColumnCount() {
		return COLUMN;
	}

	@Override
	public int getRowCount() {
		return ROW;
	}
	
}
