package com.aaamidatlantic.demo.jdk5.testVarargs;

/*******************************************************************************
 * 
 * Please read example TestWithoutVarargs.java first
 * 
 * varargs can simplify this, it allows you to pass in as many int value as 
 * possible. To do it, just declare the parameter with "..." after the type (int)
 * in the function. Of course you are free to use data type other than int too...
 * 
 * Note: varargs parameter must be the last parameter, see example function test() below.
 *******************************************************************************/

public class TestVarargs {
	public static void main(String[] args) {
		calculateSum(1, 2, 3, 4, 5, 6);
		
		test("The sum is: ", 1, 2, 3, 4, 5, 6);
	}
	
	private static void calculateSum(int... nums) {
		int sum = 0;
		
		//this is an example of enhanced for, read testEnhancedFor if needed.
		for (int num : nums) {
			sum += num;
		}
		
		System.out.println(sum);
	}
	
	private static void test(String a, int... nums) {
		System.out.println(a);
		
		int sum = 0;
		
		//this is an example of enhanced for, read testEnhancedFor if needed.
		for (int num : nums) {
			sum += num;
		}
		
		System.out.println(sum);
	}

}
