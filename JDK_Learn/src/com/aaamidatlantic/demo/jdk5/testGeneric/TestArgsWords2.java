package com.aaamidatlantic.demo.jdk5.testGeneric;

import java.util.*;

/****************************************************************************************
 * This is a version using Generics and autoboxing/unboxing
 * 
 * This program is trying to detect the distinct words from the parameters args[] and
 * count how many times each word is used.
 * 
 * java TestArgsWords aaa bbb ccc aaa ccc ddd
 * 
 * 		output will be: 
 * 			4 distinct words detected:
 *			{ccc=2, aaa=2, ddd=1, bbb=1}
 * 
 ***************************************************************************************/
public class TestArgsWords2 {
	private static final int ONE = 1;

	public static void main(String args[]) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (int i = 0; i < args.length; i++) {

			if (!m.containsKey(args[i])) {
				m.put(args[i], ONE);
			} else {
				int freq = m.get(args[i]);
				m.put(args[i], freq + 1);
			}

		}
		System.out.println(m.size() + " distinct words detected:");
		System.out.println(m);
	}
}
