package com.aaamidatlantic.demo.jdk5.testVarargs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*******************************************************************************
 * 
 * Imagine a situation when you want to pass uncertain number of parameters of 
 * the same type to a function. For example you want to calculate the sum of a 
 * few integers, but not sure how many integers will be involved. You can achieve 
 * this by declare a collection and add all integers to the collection, then 
 * iterate through the collection and calculate the sum. Of course if you don't 
 * have work to do, you can overload the function to handle 2 integers, 3 
 * integers.....
 * 
 ******************************************************************************/

public class TestWithoutVarargs {
	public static void main(String[] args) {
		 
		Collection<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);

		System.out.println(calculateSum(al));
	}

	private static int calculateSum(Collection<Integer> al) {
		int sum = 0;

		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			sum += (Integer) iterator.next();
		}
		return sum;
	}

}
