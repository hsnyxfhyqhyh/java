package com.ying.util.yiyuanyici;

import java.util.ArrayList;
import java.util.Collections;

import com.kang.util.FileUtil;

public class MathMaster {
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		generate();
	}
	
	public static void generate() {
		ArrayList al = new ArrayList();
		StringBuffer sb = new StringBuffer();
		
		
		for (int i = 0; i<60; i++ ){
			al.add(generateYiYuanYiCiQuestion1());
			al.add(generateYiYuanYiCiQuestion2());
			al.add(generateYiYuanYiCiQuestion3());
			al.add(generateYiYuanYiCiQuestion4());
			al.add(generateYiYuanYiCiQuestion5());
			al.add(generateYiYuanYiCiQuestion6());
			al.add(generateYiYuanYiCiQuestion7());
			al.add(generateYiYuanYiCiQuestion8());
			al.add(generateYiYuanYiCiQuestion9());
			al.add(generateYiYuanYiCiQuestion10());
			al.add(generateYiYuanYiCiQuestion11());
			al.add(generateYiYuanYiCiQuestion12());
		}
		
		Collections.shuffle(al);
		
		String htmlHeader = "<html>\n" +
							"	<head>\n" + 
							"		<style type=\"text/css\">\n" +
							" 			td {color:black;font-family:\"serif\";font-size:26px;}\n" +
							"			tr {height: 50px;}\n" +
							" 		</style>\n" +
							" 	</head>\n" +
							" 	<body>\n" ;
		
		String htmlFooter = " 	</body>\n" +
							"</html>\n";
		
		sb.append(htmlHeader);
		sb.append("<table cellspacing=0 cellpadding=0 width=100% align=center>\n");
		for (int i=0; i<al.size(); i++){
			String q = (String)al.get(i);
			sb.append(	"<tr>\n" +
					" 	<td width='50'></td><td>" + q + "</td>\n" +
					"	</tr>" 
			);
		}
		sb.append("</table>");
		
		sb.append(htmlFooter);
		
		String filename = "Questions.html";
		
		FileUtil.writeFile(sb.toString(), filename);
	}
	
	public static void generateQuestion() 
	{
		int x = 0, y=0;
		
		x = getNumberInRange (10, 20);
		y = getNumberInRange (5, 15);
		
		int a = 0, b = 0;
		
		a = getNumberInRange (3, 7);
		b = getNumberInRange (5, 9);
		
		int c = 0, d = 0;
		
		c = getNumberInRange (5, 11);
		d = getNumberInRange (8, 16);
		
		int equationR1 = x*a + y*b;
		int equationR2 = x*c + y*d;
		
		System.out.println("" + a + "X + " + b + "Y = " +  equationR1);
		System.out.println("" + c + "X + " + d + "Y = " +  equationR2);
		
		System.out.println("X = " + x + " ; Y = " + y);
	}
	

	// 3 + 4 * 5 -6 = 17
	public static String generateYiYuanYiCiQuestion1() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (1, 10);
		
		int sumAfterTime = x + y*z;
//		System.out.println(sumAfterTime);
		
		int minusNumber = getNumberInRange (1, sumAfterTime - 1);
		
		int result = sumAfterTime - minusNumber;
		
		String exp = "%s + %sX - %s = %s";
		
		return String.format(exp, x , y , minusNumber, result);
	}
	
	//(9 + 5) x (10 - 2) =  
	public static String generateYiYuanYiCiQuestion2() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (5, 10);
		int a = getNumberInRange (1, z-1);
		
		int result = (x + y) * (z - a);
		String exp = "(%s + X) * (%s - %s) = %s";
		return String.format(exp, x, z, a, result);
	}
	
	// (3 + 4 + 5) * 6
	public static String generateYiYuanYiCiQuestion3() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (5, 10);
		int a = getNumberInRange (2, 10);
		
		int result = (x + y + z) * a; 
		
		String exp = "(%s + X + %s) * %s = %s";
		return String.format(exp, x , z, a, result) ;
	}
	
	// 10 - 2 × 3 + 4 ÷ 2          
	public static String generateYiYuanYiCiQuestion4() 
	{
		int x = getNumberInRange (30, 40);
		int y = getNumberInRange (2, 5);
		int z = getNumberInRange (3, 6);
		int a = getNumberInRange (2, 5);
		int b = getNumberInRange (3, 6);
		int a1 = a * b;
		
		int result = x - (y * z) + (a1 /b) ;
		
		String exp = "%s - %s * %s + X ÷ %s = %s";
		
		return String.format(exp, x, y, z, b, result);
	}
	
	//( 3 + 4 × ( 5- 1)) ÷ 19
	public static String generateYiYuanYiCiQuestion5() 
	{
		
		int z = getNumberInRange (11, 17);
		int a = getNumberInRange (3, 6);
		
		int y = getNumberInRange (7, 12);
		
		int temp = z - a;
		
		int b = getNumberInRange (12, 17);
		int x0 = (y * temp) % b;
		
		int x = b - x0;
		
		int result = (x + y * (z-a))/b;
		
		String exp = "( %s + %s * ( X - %s)) ÷ %s = %s";
		
		return String.format(exp, x, y, z, b, result);
	}
	
	
	//3 * 4 + 5 * 6 - 7        
	public static String generateYiYuanYiCiQuestion6() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		int a = getNumberInRange (4, 8);
		int b = getNumberInRange (20, 23);
		
		int result = x*y + z*a - b;
		String exp = "%s * %s + %sX - %s =  %s";
		
		
		return String.format(exp, x, y, z, b, result);
		
	}
	
	
	// 3 × 4 + 7
	public static String generateYiYuanYiCiQuestion7() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		int result = x*y+z;
		String exp = "%sX + %s = %s";
		
		return String.format(exp, x, z, result);
	}

	//3 × 4 ÷ 5 =    
	public static String generateYiYuanYiCiQuestion8() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		int yz = y * z ; 
		
		int result = x * yz / z;
		
		String exp = "%sX ÷ %s = %s";
		
		return String.format(exp, x, z, result);
	}

	
	//11 - 4 ÷ 2 =   
	public static String generateYiYuanYiCiQuestion9() 
	{
		int x = getNumberInRange (20, 30);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		int yz = y * z ; 
		
		int result = x - yz / z; 
			
		String exp = "%s - X ÷ %s = %s";
		
		return String.format(exp, x, z, result);
	}

	
	public static String generateYiYuanYiCiQuestion10() 
	{
		int x = 0;
		x = getNumberInRange (10, 40);
//		System.out.println("x = " + x );
		
		int a = 0;
		a = getNumberInRange (4, 19);
		
		int ax = a*x; 
		
		int b = 0;
		b = getNumberInRange (11, 20);
		
		if(a==b){
			b = getNumberInRange (11, 20);
		}
		
		int _1st = 0;
		
		String equation = "";
		if (ax % b == 0) {
			equation = "" + a + "X ÷ " + b + " = " + (ax /b)  ;
		} else {
			int c = ax %b; 
			equation = "(" + a + "X-" + c +  ") ÷ " + b + " = " + (ax/b);
		}
		
		return equation;
	}
	
	public static String generateYiYuanYiCiQuestion11() 
	{
		int flag = getNumberInRange(1, 2); 
		
		int x = 0;
		x = getNumberInRange (10, 20);
		
		int a = 0;
		a = getNumberInRange (3, 7);
		
		int b = 0;
		b = getNumberInRange (5, 11);
		
		int c = 0;
		c = getNumberInRange (5, 11);
		
//		System.out.println("x = " + x );
		
		int equationR1 = x*a + b;
		int equationR2 = x*a - b;
		
		if (flag==1) {
			return "" + a + "X + " + b + " = " +  equationR1;
		} else {
			return "" + a + "X - " + b + " = " +  equationR2;			
		}
	}
	
	public static String generateYiYuanYiCiQuestion12() 
	{
		
		int a = getNumberInRange (4, 19);
		int b = getNumberInRange (5, 10);
		int c = getNumberInRange (3, 13);
	
		int x = a*b;
		
		int d = getNumberInRange (3, 13);
		
		int equationR = x * c /a + d;
		
		return "" + c + "X ÷ " + a + " + " + d + " = " + equationR; 
	}
	
	private static int getNumberInRange(int min, int max) {
		int number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}
}
