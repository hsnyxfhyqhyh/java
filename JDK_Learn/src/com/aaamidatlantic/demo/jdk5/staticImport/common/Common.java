package com.aaamidatlantic.demo.jdk5.staticImport.common;

/***************************************************************************
 *
 * This is a class used by the examples. It contains a static property and 
 * a function which can be shared by other class
 * 
 ***************************************************************************/
public class Common
{
	public static final String COUNTRY = "USA";

	public static int add(int a, int b)
	{
		return a + b;
	}
}
