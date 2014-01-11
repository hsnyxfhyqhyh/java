package com.aaamidatlantic.demo.jdk5.enumT.example;

public class ShowEnum
{
	public static void main(String[] args)
	{
		/*
		 * valueOf() method of enum returns the enum constant of
		 * the specified enum type with the specified name.
	 	 */
		enumCompareTo(OpConstant.valueOf(args[0]));
	}

	public static void enumCompareTo(OpConstant constant)
	{
		System.out.println(constant);
		for (OpConstant c : OpConstant.values())
		{
			System.out.println(constant.compareTo(c));
		}
	}  
}


enum OpConstant
{
	TURN_LEFT, TURN_RIGHT, SHOOT
}