package com.kang.stock;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FundTrend {
	
	String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String connName = "jdbc:microsoft:sqlserver://localhost:1433;User=sa;Password=jianing;DatabaseName=dbStock";
//	String INDEX_NAME = "DowJones_Close";
//	String INDEX_NAME = "Nasdaq_Close";
	String INDEX_NAME = "SP500_Close";
    
    Connection conn=null;
	PreparedStatement pstmt1=null;
    ResultSet rs=null;
    
    PrintWriter out = null;
    
	public static void main(String[] args) {
		FundTrend mc = new FundTrend();
		
		ArrayList funds = new ArrayList();
        
//		funds.add("Vangaurd_Long_term_Invest_grow_Inv");
		funds.add("Vanguar_Target_Retirement_2005");
		funds.add("Vanguar_Target_Retirement_2010");
		funds.add("Vanguar_Target_Retirement_2015");
		funds.add("Vanguar_Target_Retirement_2020");
		funds.add("Vanguar_Target_Retirement_2025");
		funds.add("Vanguar_Target_Retirement_2030");
		funds.add("Vanguar_Target_Retirement_2035");
		funds.add("Vanguar_Target_Retirement_2040");
		funds.add("Vanguar_Target_Retirement_2045");
		funds.add("Vanguar_Target_Retirement_2050");
		funds.add("Vanguard_Wellington_Fund_Inv");
		funds.add("Vanguard_Explorer_Fund_Investor");
		funds.add("Vanguard_Total_Stock_Mkt_Idx_Inv");
		funds.add("Vanguar_US_Growth_Fund_Investor");
		funds.add("Vanguard_Windsor_Fund_Investor");
		funds.add("Vanguard_International_Growth_Inv");
		
		try {
			for (int i=0; i< funds.size(); i++ ){
				String fundName = (String)funds.get(i);
				mc.prepareDB();
				mc.prepareFile(fundName);
				
				mc.generateFileForFund(fundName);
				mc.destroyDB();
				mc.destroyFile();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Done Processing Fund Trend");
	}
	
	private void destroyFile() {
		out.flush();
		out.close();
		out = null;
	}

	private void prepareFile(String fundName) throws Exception {
		if (out!=null) out = null;
		
		out = new PrintWriter (new FileOutputStream(fundName + "_" + INDEX_NAME + ".html"));
		
	}

	public void prepareDB() throws Exception {
		Class.forName(driverName).newInstance();
        
		conn = DriverManager.getConnection(connName);
        pstmt1 = conn.prepareStatement("SELECT * FROM tbl401K WHERE isVisible=1 ORDER BY ROWID ASC");
        rs= pstmt1.executeQuery();   
	}
	
	public void destroyDB() throws Exception {
		rs.close();
        pstmt1.close();
        conn.close();
        
        rs=null;
        pstmt1=null;
        conn=null;
	}
	
	public void generateFileForFund(String fundname){
		try {
            double myMoney = 10000;
            
            int i=1;
            double previousIndex = 0;
            double previousPrice = 0;
            double currentIndex = 0;
            double currentPrice = 0;
            
            double share;
            double earnPerDay;
                         
            if (rs !=null)
            {
            	printHTMLStart(fundname);
            	printTableHeader();
            	while(rs.next())
            	{
            		printRowHeader();
            		if (i==1){
            			try {
	            			previousIndex = formatDouble(rs.getString(INDEX_NAME));
	            			previousPrice = formatDouble(rs.getString(fundname));
            			} catch (Exception e) {
            				System.out.println (fundname + ": this fund does not contain data");
            				printRowFooter();
            				break;
            			}
            			
            			printCell(rs.getString("indexDate"));
            			printCell(rs.getString(INDEX_NAME));
            			printCell("&nbsp;");
            			printCell(rs.getString(fundname));
            			printCell("&nbsp;");
            			printCell("&nbsp;");
            			printCell("&nbsp;");
            			
            			i++;
            			continue;
            		} else {
            			try {
            				currentIndex = formatDouble(rs.getString(INDEX_NAME));
            				currentPrice = formatDouble(rs.getString(fundname));
            			} catch (Exception e) {
            				printRowFooter();
            				continue;
            			}
            			
            			double indexDiff = formatDouble(currentIndex - previousIndex);
            			
            			share = formatDouble(formatDouble(myMoney)/formatDouble(previousPrice));
            			earnPerDay = formatDouble(share * (currentPrice - previousPrice));
            			
            			printCell(rs.getString("indexDate"));
            			printCell(previousIndex);
            			printCell(currentIndex);
            			printCell(previousPrice);
            			printCell(currentPrice);
            			printCell(indexDiff);
            			printCell(earnPerDay);
            			
            			previousIndex = currentIndex;
            			previousPrice = currentPrice;
            		}
            		printRowFooter();
            	}
            	printTableFooter();
            	printHTMLEnd();
            	
            }
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}
	private void printHTMLEnd() {
		out.println("</body>");
		out.println("</html>");
	}
	private void printHTMLStart(String fundName) {
		out.println("<html><head><Title>" + fundName + " VS " + INDEX_NAME + "</title></head>");
		out.println("<body>");
//		out.println("<H1>" + fundName + "</H1>");
		out.println("<b>" + fundName + " VS " + INDEX_NAME + "</b>");
		
	}
	private void printCell(String origValue){
		out.print("<td align=right>" + origValue + "</td>");
	}
	
	private void printCell(double origValue){
		out.print("<td align=right>" + formatDouble(origValue) + "</td>");
	}
	
	private void printTableHeader(){
		out.println("<table border=1 cellspacing=1 cellpadding=1>");
		out.println("<tr>" +
							"<td align=right><b>DATE</b></td>"  + 
							"<td align=right><b>Previous Index</b></td>" + 
							"<td align=right><b>Current Index</b></td>" + 
							"<td align=right><b>Previous Price</b></td>" + 
							"<td align=right><b>Current Price</b></td>" + 
							"<td align=right width=12><b>Index Diff</b></td>" + 
							"<td align=right width=12><b>EPD</b></td>" +
							"</tr>"
						);
	}
	
	private void printTableFooter(){
		out.println("<tr>" +
				"<td align=right><b>DATE</b></td>"  + 
				"<td align=right><b>Previous Index</b></td>" + 
				"<td align=right><b>Current Index</b></td>" + 
				"<td align=right><b>Previous Price</b></td>" + 
				"<td align=right><b>Current Price</b></td>" + 
				"<td align=right><b>Index Diff</b></td>" + 
				"<td align=right><b>EPD</b></td>" +
				"</tr>"
			);
		out.print("</table>");
	}
	
	private void printRowHeader(){
		out.println("<tr>");
	}
	
	private void printRowFooter(){
		out.println("</tr>");
	}

	private double formatDouble(String origValue) {
		double result = Double.parseDouble(origValue);
		return formatDouble(result);
	}
	
	private double formatDouble(double origValue) {
		double result;
		DecimalFormat df = new DecimalFormat("#.##"); 
        df.setMinimumFractionDigits(2);
        result =Double.parseDouble(df.format(origValue));

        return result;
	}
}
