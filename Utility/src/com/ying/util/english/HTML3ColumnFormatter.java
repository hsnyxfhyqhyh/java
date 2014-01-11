package com.ying.util.english;

public class HTML3ColumnFormatter {
	private static final int COLUMN = 3;
	private static final int ROW = 4;
	
	public String format(String[] questions) 
	{
		StringBuffer sb = new StringBuffer();
		
		int total = questions.length;
		int page = total / (COLUMN* ROW);
		
		int pagecount = 1;
		
		String htmlHeader = "<html>\n" +
							"	<head>\n" + 
							"		<style type=\"text/css\">\n" +
							" 			td {text-align:center;width:25%;vertical-align:middle;color:black;font-family:\"Comic Sans MS\";font-size:44px;font-weight:bold;}\n" +
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
					sb.append("<table cellspacing=0 border=3 cellpadding=0 width=100% align=center >\n");
				}
				
				int remainder = countQ % COLUMN;
				
				//calculate the starting point of a row.
				if (remainder==0){
					sb.append(	"<tr height=50px>\n" +
								" 	<td height=200px>" + questions[countQ] + "</td>\n" 
							);
				} else if (remainder != COLUMN-1){
					sb.append(	"	<td>" + questions[countQ] + "</td>\n"); 
				} else {
					//this is the end of the row.
					sb.append(	"	<td>" + questions[countQ] + "</td>\n" +
								"</tr>");
				}
				
				//table ends here, and adds the page breaker of <P> tags...
				if (pageRemainder == (ROW*COLUMN-1)) {
//					sb.append	("<tr height=105px><td colspan=3></td></tr></table>\n");
					
					sb.append	("</table><br>&nbsp;</br><br>&nbsp;</br><br>&nbsp;</br>\n");
					
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

	public int getColumnCount() {
		return COLUMN;
	}

	public int getRowCount() {
		return ROW;
	}
	
}
