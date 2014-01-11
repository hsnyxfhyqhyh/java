package com.aaamidatlantic.demo.jdk5.testAutoBoxingUnBoxing;

import java.util.*;

/*******************************************************************************
 * This is a rewrite of TestMap2.java in TestContainer folder. It shows how to
 * use the autoboxing/unboxing functionality provided by JDK 5.0.
 ******************************************************************************/
public class TestMapAutoBoxingUnBoxing2 {
	public static void main(String args[]) {
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		m1.put("one", 1);
		m1.put("two", 2);
		m1.put("three", 3);

		System.out.println(m1.size());
		System.out.println(m1.containsKey("one"));
		
		if (m1.containsKey("two")) {
			/****************************************************************************
			 * When it comes to retrieve the Integer value, JDK 5.0 will automatically 
			 * unbox the Integer into an int value. Then this int value can be used for
			 * mathematical calculation  
			 * Code before JDK 5.0:
			 * 		int i = ((Integer)m1.get("two")).intValue();
			 * 
			 * Code with JDK 5.0
			 * 		//NOTE: since Generic is used, the cast to Integer can be eliminated too
			 * 		int i = m1.get("two");  
			 ****************************************************************************/
			
			int i = m1.get("two");
			System.out.println(i);
		}

	}
}