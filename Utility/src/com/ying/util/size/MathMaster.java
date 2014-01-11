package com.ying.util.size;

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
//		System.out.println(generateQuestion9());
	}
	
	public static void generate() {
		ArrayList al = new ArrayList();
		StringBuffer sb = new StringBuffer();
		
		
		for (int i = 0; i < 100; i++ ){
			String equation = generateQuestion1();
			al.add(equation);
			
			equation = generateQuestion2();
			al.add(equation);
			
			equation = generateQuestion3();
			al.add(equation);
			
			equation = generateQuestion4();
			al.add(equation);
			
			equation = generateQuestion5();
			al.add(equation);
			
			equation = generateQuestion6();
			al.add(equation);
			
			equation = generateQuestion7();
			al.add(equation);
			
			equation = generateQuestion8();
			al.add(equation);
			
			equation = generateQuestion9();
			al.add(equation);
			
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
		
		int row = al.size() / 2;
		int counter = 0;
		
		for (int i=0; i < row; i++){
			sb.append(	"<tr>\n");

			String q = (String)al.get(counter);
			sb.append(" 	<td width='30'></td><td>" + q + "</td>\n" );
			counter ++;

			q = (String)al.get(counter);
			sb.append(" 	<td width='30'></td><td>" + q + "</td>\n" );
			counter ++;
			
			sb.append("	</tr>"); 
		}
		sb.append("</table>");
		
		sb.append(htmlFooter);
		
		String filename = "Questions.html";
		
		FileUtil.writeFile(sb.toString(), filename);
	}
	
	
	// 3 + 4 * 5 -6
	public static String generateQuestion1() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (1, 10);
		
		int sumAfterTime = x + y*z;
		System.out.println(sumAfterTime);
		
		int minusNumber = getNumberInRange (1, sumAfterTime - 1);
		
		String exp = "%s + %s x %s - %s = ";
		
		return String.format(exp, x , y , z , minusNumber);
	}
	
	// (3 + 4) * (5 -6)
	public static String generateQuestion2() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (5, 10);
		int a = getNumberInRange (1, z-1);
		
		String exp = "(%s + %s) x (%s - %s) = ";
		return String.format(exp, x, y, z, a);
	}
	
	// (3 + 4 + 5) * 6
	public static String generateQuestion3() 
	{
		int x = getNumberInRange (1, 10);
		int y = getNumberInRange (1, 10);
		int z = getNumberInRange (5, 10);
		int a = getNumberInRange (2, 10);
		
		String exp = "(%s + %s + %s) x %s = ";
		return String.format(exp, x , y , z ,a) ;
	}
	
	// 10 - 2 × 3 + 4 ÷ 2          
	public static String generateQuestion4() 
	{
		int x = getNumberInRange (30, 40);
		int y = getNumberInRange (2, 5);
		int z = getNumberInRange (3, 6);
		int a = getNumberInRange (2, 5);
		int b = getNumberInRange (3, 6);
		int a1 = a * b;
		
		String exp = "%s - %s x %s + %s ÷ %s = ";
		
		return String.format(exp, x, y, z, a1, b);
	}
	
	//( 3 + 4 × ( 5- 1)) ÷ 19
	public static String generateQuestion5() 
	{
		
		int z = getNumberInRange (11, 17);
		int a = getNumberInRange (3, 6);
		
		int y = getNumberInRange (7, 12);
		
		int temp = z - a;
		
		int b = getNumberInRange (12, 17);
		int x0 = (y * temp) % b;
		
		int x = b - x0;
		
		
		
		String exp = "( %s + %s x ( %s - %s)) ÷ %s = ";
		
		return String.format(exp, x, y, z, a, b);
	}
	
	
	//3 * 4 + 5 * 6 - 7        
	public static String generateQuestion6() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		int a = getNumberInRange (4, 8);
		int b = getNumberInRange (20, 23);
		
		String exp = "%s x %s + %s x %s - %s = ";
		
		return String.format(exp, x, y, z, a, b);
	}
	
	
	// 3 × 4 + 7
	public static String generateQuestion7() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		String exp = "%s x %s + %s = ";
		
		return String.format(exp, x, y, z);
	}

	//3 × 4 ÷ 5 =    
	public static String generateQuestion8() 
	{
		int x = getNumberInRange (7, 11);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		int yz = y * z ; 
		
		String exp = "%s x %s ÷ %s = ";
		
		return String.format(exp, x, yz, z);
	}

	
	//11 - 4 ÷ 2 =   
	public static String generateQuestion9() 
	{
		int x = getNumberInRange (20, 30);
		int y = getNumberInRange (4, 8);
		int z = getNumberInRange (3, 9);
		
		int yz = y * z ; 
		
		String exp = "%s - %s ÷ %s = ";
		
		return String.format(exp, x, yz, z);
	}
	
	private static int getNumberInRange(int min, int max) {
		int number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}
	
}
