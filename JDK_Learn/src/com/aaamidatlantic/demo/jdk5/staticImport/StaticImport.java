package com.aaamidatlantic.demo.jdk5.staticImport;

import static com.aaamidatlantic.demo.jdk5.staticImport.common.Common.add;
import static com.aaamidatlantic.demo.jdk5.staticImport.common.Common.COUNTRY;

/*******************************************************************************
*
* Please read WithoutStaticImport.java first
* 
* This is an example using static import. To use the static property or function 
* in class: com.aaamidatlantic.demo.jdk5.staticImport.common.Common,
*  
* you can do static import for the static property/function as  
*		import static com.aaamidatlantic.demo.jdk5.staticImport.common.Common.add;
* 		import static com.aaamidatlantic.demo.jdk5.staticImport.common.Common.COUNTRY;
* 		 
* Then you can use the static property/function in class Commmon just like it belongs 
* to you.
* 
* Note: 
* Overuse static import will make code less maintainable. The other programmer may 
* be confused to figure out where the property/function is defined.
*******************************************************************************/

public class StaticImport
{
	public static void main(String[] args)
	{
		System.out.println(add(1,2));
		System.out.println(COUNTRY);
	}
}
