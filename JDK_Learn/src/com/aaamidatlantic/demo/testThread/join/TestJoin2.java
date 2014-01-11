package com.aaamidatlantic.demo.testThread.join;

/****************************************
 * Please refer to TestJoin.java first 
 ****************************************/
public class TestJoin2 {
	public static void main(String[] args) {
		MyThread myThread = new MyThread("John");
		myThread.start();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("i am main thread");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}


