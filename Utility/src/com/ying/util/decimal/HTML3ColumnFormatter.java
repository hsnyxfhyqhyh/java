package com.ying.util.decimal;

public class HTML3ColumnFormatter implements QuestionFormatter{
	private static final int COLUMN = 2;
	private static final int ROW = 8;
	
	@Override
	public String format(String[] questions) 
	{
		StringBuffer sb = new StringBuffer();
		
		int total = questions.length;
		
		String htmlHeader = "<html>\n" +
							"	<head>\n" + 
							"		<style type=\"text/css\">\n" +
							" 			td {color:black;font-family:\"serif\";font-size:26px;font-weight:bold;}\n" +
							"			tr {height: 114px;}\n" +
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
				
				//calculate the starting point of a row.
				if (remainder==0){
					sb.append(	"<tr>\n" +
								" 	<td>" + questions[countQ] + "</td>\n" 
							);
				} else if (remainder==1){
					sb.append(	"	<td>" + questions[countQ] + "</td>\n"); 
				} else {
					//this is the end of the row.
					sb.append(	"	<td>" + questions[countQ] + "</td>\n" +
								"</tr>");
				}
				
				//table ends here, and adds the page breaker of <P> tags...
				if (pageRemainder == (ROW*COLUMN-1)) {
					sb.append	("</table>\n" //+ 
//								"<p>&nbsp;</p>" +
//								"<p>&nbsp;</p>" +
//								"<p>&nbsp;</p>\n" 
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

	@Override
	public int getColumnCount() {
		return COLUMN;
	}

	@Override
	public int getRowCount() {
		return ROW;
	}
	
}
