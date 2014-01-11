package com.kang.stock;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kang.util.HttpUtil;



public class VanguardFundPriceGrabber {
	
	public static Map fundMap;
	public static Map indexInfoMap;
	public static ArrayList funds;
	public static String insertQuery;
	public static String rssPrefix = "https://personal.vanguard.com/us/FundsRSS?FundId=";
	public static String indexWebpage = "http://money.cnn.com/?cnn=yes";
	
	public static String log = "";
	
	public static void main(String[] args) {
			
		initFunds();
		
		println("<html><body>");
		
		insertQuery = buildInsertQuery();
		
		boolean isFirstFund = true;

		for (int i = 0; i < funds.size(); i++) {
			try{Thread.sleep(1000);} catch(Exception e){}
			
			String fundID = (String) funds.get(i);

			String webPageContent = HttpUtil.getWebPageInFullHttpResponse(rssPrefix + fundID);

			// Price as of 02/13/2009: $9.37 (-$0.06) -- this is the price node
			// value for the fund
			String priceNodeText = getPriceNodeValue(webPageContent);
			String priceDate = priceNodeText.substring(priceNodeText.indexOf(":") - 10, priceNodeText.indexOf(":"));
			String price = priceNodeText.substring(
					priceNodeText.indexOf("$") + 1,
					priceNodeText.indexOf(".") + 3);
			
			String fundDBColumnName = (String) fundMap.get(fundID);
			
			
			if (isFirstFund) {
				getIndexInfo();
				
				Object[] keys = indexInfoMap.keySet().toArray();
				for (int j=0; j<keys.length; j++){
					String key = keys[j].toString();
					if (!key.equalsIgnoreCase("$IndexDate")) {
						insertQuery = insertQuery.replace(key, "'" + indexInfoMap.get(key) + "'");
					}
				}
				insertQuery = insertQuery.replace("$IndexDate", "'" + priceDate + "'");

				println("##############################<br>");
				
				println("<b>Fund Price Date: </b>" + priceDate +"<br>");
				isFirstFund = false;
			}
			
			insertQuery = insertQuery.replace("$" + fundDBColumnName, "'"+ price + "'");
			
			System.out.println(fundDBColumnName);
			System.out.println(priceDate);
			System.out.println(price);

			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
		
		System.out.println(insertQuery);
		
		println("<font color=green>" + insertQuery + "</font>");
		println("</body></html>");
		
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(getDateOfToday()+ ".html"));
	
			out.print(log);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
		
		System.out.println("Done");
		
	}
	
	private static String getPriceNodeValue(String rssFeed) {
		String priceNodeText = "";
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();

		try {
			DocumentBuilder domBuilder = builderFactory.newDocumentBuilder();
			Document doc;

			ByteArrayInputStream bs = new ByteArrayInputStream(rssFeed
					.getBytes());
			doc = domBuilder.parse(bs);

			NodeList nl = doc.getElementsByTagName("title");

			String nodeText = "";

			for (int nodeCnt = 0; nodeCnt < nl.getLength(); nodeCnt++) {
				Node node = nl.item(nodeCnt).getFirstChild();
				if (node != null) {
					nodeText = node.getNodeValue();
					if (nodeText.indexOf("") >= 0) {
						priceNodeText = nodeText;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return priceNodeText;
	}

	private static void initFunds() {
		funds = new ArrayList();
		funds.add("0302");
		funds.add("0681");
		funds.add("0303");
		funds.add("0682");
		funds.add("0304");
		funds.add("0695");
		funds.add("0305");
		funds.add("0696");
		funds.add("0306");
		funds.add("0699");
		funds.add("0021");
		funds.add("0024");
		funds.add("0085");
		funds.add("0023");
		funds.add("0022");
		funds.add("0081");
		
		fundMap = new HashMap();
		fundMap.put("0302", "Vanguar_Target_Retirement_2005");
		fundMap.put("0681", "Vanguar_Target_Retirement_2010");
		fundMap.put("0303", "Vanguar_Target_Retirement_2015");
		fundMap.put("0682", "Vanguar_Target_Retirement_2020");
		fundMap.put("0304", "Vanguar_Target_Retirement_2025");
		fundMap.put("0695", "Vanguar_Target_Retirement_2030");
		fundMap.put("0305", "Vanguar_Target_Retirement_2035");
		fundMap.put("0696", "Vanguar_Target_Retirement_2040");
		fundMap.put("0306", "Vanguar_Target_Retirement_2045");
		fundMap.put("0699", "Vanguar_Target_Retirement_2050");
		fundMap.put("0021", "Vanguard_Wellington_Fund_Inv");
		fundMap.put("0024", "Vanguard_Explorer_Fund_Investor");
		fundMap.put("0085", "Vanguard_Total_Stock_Mkt_Idx_Inv");
		fundMap.put("0023", "Vanguar_US_Growth_Fund_Investor");
		fundMap.put("0022", "Vanguard_Windsor_Fund_Investor");
		fundMap.put("0081", "Vanguard_International_Growth_Inv");
		
	}
	
	private static String buildInsertQuery() {
		String insertQuery = "INSERT INTO tbl401K (" + "<br>" +
							"  IndexDate, DowJones_Close, Nasdaq_Close, SP500_Close, " + "<br>" +
							"  Vanguar_Target_Retirement_2005, Vanguar_Target_Retirement_2010, Vanguar_Target_Retirement_2015, " + "<br>" +
							"  Vanguar_Target_Retirement_2020, Vanguar_Target_Retirement_2025, Vanguar_Target_Retirement_2030, " + "<br>" +
							"  Vanguar_Target_Retirement_2035, Vanguar_Target_Retirement_2040, Vanguar_Target_Retirement_2045, " + "<br>" +
							"  Vanguar_Target_Retirement_2050, Vanguard_Wellington_Fund_Inv, Vanguard_Explorer_Fund_Investor,  " + "<br>" +
							"  Vanguard_Total_Stock_Mkt_Idx_Inv, Vanguar_US_Growth_Fund_Investor, Vanguard_Windsor_Fund_Investor, " + "<br>" +
							"  Vanguard_International_Growth_Inv, isVisible " + "<br>" +
							") VALUES (" + "<br>" +
							"  $IndexDate, $DowJones_Close, $Nasdaq_Close, $SP500_Close, " + "<br>" +
							"  $Vanguar_Target_Retirement_2005, $Vanguar_Target_Retirement_2010, $Vanguar_Target_Retirement_2015, " + "<br>" +
							"  $Vanguar_Target_Retirement_2020, $Vanguar_Target_Retirement_2025, $Vanguar_Target_Retirement_2030, " + "<br>" +
							"  $Vanguar_Target_Retirement_2035, $Vanguar_Target_Retirement_2040, $Vanguar_Target_Retirement_2045, " + "<br>" +
							"  $Vanguar_Target_Retirement_2050, $Vanguard_Wellington_Fund_Inv, $Vanguard_Explorer_Fund_Investor,  " + "<br>" +
							"  $Vanguard_Total_Stock_Mkt_Idx_Inv, $Vanguar_US_Growth_Fund_Investor, $Vanguard_Windsor_Fund_Investor, " + "<br>" +
							"  $Vanguard_International_Growth_Inv, 1 " + "<br>" +
							") " ;
		return insertQuery;
		
							
	}
	
	private static String formatIndexDate(String olddate){
		String m, d, y;
		
		m = olddate.substring(0, olddate.indexOf("/"));
		if (m.length() == 1) {m = "0" + m;}
		
		d = olddate.substring(olddate.indexOf("/") + 1, olddate.lastIndexOf("/"));
		if (d.length() == 1) {d = "0" + d;}
		
		y = olddate.substring(olddate.lastIndexOf("/") + 1);	
		return m + "/" + d + "/" + y;
	}
	
	private static void getIndexInfo() {
		indexInfoMap = new HashMap();
		
		String indexwebcontent = HttpUtil.getWebPageInFullHttpResponse(indexWebpage);
		
		String indexPrefix = "<div id=\"marketRibbon\">";
		String indexSuffix = "<div id=\"LQ_10yr\">";
		
		String valueTable = indexwebcontent.substring(
				indexwebcontent.indexOf(indexPrefix)  ,
				indexwebcontent.indexOf(indexSuffix)  ) + "</div>";
		
		String prefix = "mwfield=\"Price\" mwformat=\",2\">";
		String suffix = "</span>";
		
		//get dow value
		valueTable = valueTable.substring(valueTable.indexOf(prefix));
		String dowValue = valueTable.substring(valueTable.indexOf(prefix)+ prefix.length(), valueTable.indexOf(suffix));
		valueTable = valueTable.substring(valueTable.indexOf(suffix) + suffix.length());
		println("<b>DJIA: </b>" + dowValue + "<br>");
		indexInfoMap.put("$DowJones_Close", dowValue.replace(",", ""));
		
		//get nasdaq value
		valueTable = valueTable.substring(valueTable.indexOf(prefix));
		String nasdaqValue = valueTable.substring(valueTable.indexOf(prefix)+ prefix.length(), valueTable.indexOf(suffix));
		valueTable = valueTable.substring(valueTable.indexOf(suffix) + suffix.length());
		println("<b>Nasdaq: </b>" + nasdaqValue + "<br>");
		indexInfoMap.put("$Nasdaq_Close", nasdaqValue.replace(",", ""));
		
		//get sp500 value
		valueTable = valueTable.substring(valueTable.indexOf(prefix));
		String sp500Value = valueTable.substring(valueTable.indexOf(prefix)+ prefix.length(), valueTable.indexOf(suffix));
		valueTable = valueTable.substring(valueTable.indexOf(suffix) + suffix.length());
		println("<b>SP500: </b>" + sp500Value + "<br>");
		indexInfoMap.put("$SP500_Close", sp500Value.replace(",", ""));
		
	}
	
	/*
	 * controls the print out format
	 */
	private static void println(String entry) {
		log = log + entry + "<br>";
	}
	
	public static String getDateOfToday() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date()) + "-" + new Random().nextInt();
	}
}


/*
 
<div id="marketRibbon">
	<div id="LQ_djia">
		<div class="quoteSymbol">
			<a href="JavaScript:GoURL(dowURL,'')">DOW</a>
		</div>
		<div class="priceChange">
			<span class="mwbannerquote" mwsymbol="djia" mwfield="Change" mwformat="2-">
				<span class="changePositive">178.73</span>
			</span>
		</div>
		<div class="percentChange">
			<span class="mwbannerquote" mwsymbol="djia" mwfield="PercentChange" mwformat="%+">
				<span class="changePositive">+2.48%</span>
			</span>
		</div>
		<div class="quotePrice">
			<span class="mwbannerquote" mwsymbol="djia" mwfield="Price" mwformat=",2">7,395.70</span>
		</div>
	</div>
	<div id="LQ_nasdaq">
		<div class="quoteSymbol">
			<a href="JavaScript:GoURL(nasdaqURL,'')">NASDAQ</a>
		</div>
		<div class="priceChange">
			<span class="mwbannerquote" mwsymbol="nasdaq" mwfield="Change" mwformat="2-">
				<span class="changePositive">58.09</span>
			</span>
		</div>
		<div class="percentChange">
			<span class="mwbannerquote" mwsymbol="nasdaq" mwfield="PercentChange" mwformat="%+">
				<span class="changePositive">+4.14%</span>
			</span>
		</div>
		<div class="quotePrice">
			<span class="mwbannerquote" mwsymbol="nasdaq" mwfield="Price" mwformat=",2">1,462.11</span>
		</div>
	</div>
	<div id="LQ_sp500">
		<div class="quoteSymbol">
			<a href="JavaScript:GoURL(sandpURL,'')">SP500</a>
		</div>
		<div class="priceChange">
			<span class="mwbannerquote" mwsymbol="sp500" mwfield="Change" mwformat="2-">
				<span class="changePositive">24.23</span>
			</span>
		</div>
		<div class="percentChange">
			<span class="mwbannerquote" mwsymbol="sp500" mwfield="PercentChange" mwformat="%+">
				<span class="changePositive">+3.21%</span>
			</span>
		</div>
		<div class="quotePrice">
			<span class="mwbannerquote" mwsymbol="sp500" mwfield="Price" mwformat=",2">778.12</span>
		</div>
	</div>
</div>
 
 */