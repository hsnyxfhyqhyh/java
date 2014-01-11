package com.aaamidatlantic.demo.jdk5.enumT;

/**************************************************************
 * 
 * This is a very simple example, for more details please read example EnumTest.java
 */
public enum Color {
	//Red, blue and White are defined "instance" in enum Color
	//For example: Red is like: 
	//		public static final Color Red;
	Red, Blue, White;
	
	public static void main(String[] args) {
		//To define an instance of Color then use it
		Color myColor = Color.Blue;
		
		System.out.println(myColor);
		
		//enhanced for to print out all values defined in enum Color
		for (Color c: Color.values()){
			System.out.println(c);
		}
	}
}
