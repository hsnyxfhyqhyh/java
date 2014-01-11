package com.aaamidatlantic.demo.jdk5.testEnhancedFor;

import java.util.*;

/**********************************************************************************
 *																				  	
 * Pros: 																		  	
 *  	$ easy to iterate through the container or array				      
 *  																			  
 * Cons:																		  	
 * 		$ It is difficult to get the index of an element in the container	
 *  	$ For the same reason above, it is hard to remove an item from the container  
 *  				compares to using the container's iterator								  	
 **********************************************************************************/
public class EnhancedFor {
	public static void main(String[] args) {
		
		/****************************************
		 * See how enhanced for works for array *
		 ****************************************/
		int[] arr = {1, 2, 3, 4, 5};
		for(int i : arr) {
			System.out.println(i);
		}
		
		/********************************************
		 * See how enhanced for works for container *
		 ********************************************/
		Collection c = new ArrayList();
		c.add(new String("aaa"));
		c.add(new String("bbb"));
		c.add(new String("ccc"));
		
		for(Object o : c) {
			System.out.println(o);
		}
	}
}