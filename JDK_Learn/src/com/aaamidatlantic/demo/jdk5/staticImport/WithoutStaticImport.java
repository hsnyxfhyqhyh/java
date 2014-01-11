package com.aaamidatlantic.demo.jdk5.staticImport;

import com.aaamidatlantic.demo.jdk5.staticImport.common.Common;

/*******************************************************************************
 *
 * This is an example without using static import. To use the static property 
 * or function in class: com.aaamidatlantic.demo.jdk5.staticImport.common.Common, 
 * you have to import the class like: 
 *		import com.aaamidatlantic.demo.jdk5.staticImport.common.Common; 		 
 *
 * Then you can use the static property/function in class Commmon as in the example,
 * you need to have a "Common." in front of the static property/function...
 * 
 *******************************************************************************/
public class WithoutStaticImport
{
	public static void main(String[] args)
	{
		System.out.println(Common.add(1,2));
		System.out.println(Common.COUNTRY);
	}
}
