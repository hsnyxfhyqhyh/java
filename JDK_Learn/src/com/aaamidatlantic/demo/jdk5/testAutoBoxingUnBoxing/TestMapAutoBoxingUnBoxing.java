package com.aaamidatlantic.demo.jdk5.testAutoBoxingUnBoxing;

import java.util.*;

/*******************************************************************************
 * This is a rewrite of TestMap.java in TestContainer folder. It shows how to
 * use the autoboxing/unboxing functionality provided by JDK 5.0.
 ******************************************************************************/
public class TestMapAutoBoxingUnBoxing {
	public static void main(String args[]) {

		Map m1 = new HashMap();
		/***********************************************************************
		 * Before JDK 5.0 when you want to save an integer into a container, you
		 * have to create an Integer object and put it into the container
		 * because container only accepts object. So the code before JDK 6.0
		 * would be as following:
		 * 
		 * 		m1.put("one",new Integer(1)); 
		 * 		m1.put("two",new Integer(2));
		 * 		m1.put("three",new Integer(3));
		 * 
		 * With JDK 5.0, you can directly use int type and put it into
		 * container. The container then will autobox the int value into Integer 
		 * object. This will make the code more elegant, readable:
		 * 
		 * 		m1.put("one", 1); 
		 * 		m1.put("two", 2); 
		 * 		m1.put("three", 3);
		 * 
		 **********************************************************************/

		m1.put("one", 1);
		m1.put("two", 2);
		m1.put("three", 3);

		Map m2 = new TreeMap();

		// m2.put("A",new Integer(1));
		// m2.put("B",new Integer(2));
		m2.put("A", 1);
		m2.put("B", 2);

		System.out.println(m1.size());
		System.out.println(m1.containsKey("one"));

		// System.out.println (m2.containsValue(new Integer(1)));
		System.out.println(m2.containsValue(1));

		/****************************************************************************
		 * When it comes to retrieve the Integer value, JDK 5.0 will automatically 
		 * unbox the Integer into an int value. Then this int value can be used for
		 * mathematical calculation  
		 * Code before JDK 5.0:
		 * 		int i = ((Integer)m1.get("two")).intValue();
		 * 
		 * Code with JDK 5.0
		 * 		//NOTE: if use Generic, the cast to Integer can be eliminated too
		 * 		//		please refer to TestMapAutoBoxingUnBoxing2.java for more 
		 * 		//		details		
		 * 		int i = (Integer) m1.get("two");  
		 ****************************************************************************/
		if (m1.containsKey("two")) {
			int i = (Integer) m1.get("two");
			System.out.println(i);
		}

		Map m3 = new HashMap(m1);
		m3.putAll(m2);

		System.out.println(m3);
	}
}