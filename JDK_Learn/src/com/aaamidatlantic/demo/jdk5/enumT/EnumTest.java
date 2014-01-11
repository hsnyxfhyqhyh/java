package com.aaamidatlantic.demo.jdk5.enumT;

/****************************************************
 * 
 * enum is at the same level as of class/interface. 
 * It is new introduced in JDK 5.0 
 * 
 * If it is the only type in a particular file, then it 
 * 		must be defined as public (refer to Color.java)
 * Otherwise it can be defined without public keyword as in this file, 
 * 		as OpConstant
 * 
 * It can have a main function defined in it just like in normal class
 *  
 * enum type has 2 static methods: valueOf(), values()
 *
 ****************************************************/
public class EnumTest
{

	public static void doOperation(OpConstant opConstant)
	{
		switch (opConstant) {
		case TURN_LEFT:
			System.out.println("turn left");
			break;
		case TURN_RIGHT:
			System.out.println("turn right");
			break;
		case SHOOT:
			System.out.println("shoot");
			break;
		}
	}

	public static void main(String[] args)
	{
		doOperation(OpConstant.SHOOT);
	}
}

enum OpConstant
{
	TURN_LEFT, TURN_RIGHT, SHOOT
}
