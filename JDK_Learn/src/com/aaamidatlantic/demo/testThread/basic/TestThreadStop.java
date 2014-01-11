package com.aaamidatlantic.demo.testThread.basic;

/*********************************************************************************************
 * Thread has a method stop to stop the Thread. It will force stop the thread immediately. 
 * Imagine a situation when a file is open or database connection is open, the stop method 
 * call will not give the child thread way to exit elegantly. The way to handle this situation
 * is to have some kind of flag set up so the thread can check the flag and determine whether 
 * to exit or not by itself. 
 * 
 * The example below has a flag variable set up so the main function can call the shutdown method
 * to turn it off. The child thread will check the flag to determine whether to stop or not.
 *********************************************************************************************/
public class TestThreadStop {
	public static void main(String args[]) {
		Runner2 r = new Runner2();
		Thread t = new Thread(r);
		t.start();
		
		for (int i = 0; i < 100000; i++) {
			if (i % 10000 == 0 & i > 0)
				System.out.println("\nin thread main i=" + i);
		}
		
		r.shutDown();
		//t.stop();
		System.out.println("\n\nThread main is over");
	}
}

class Runner2 implements Runnable {
	private boolean flag = true;

	public void run() {
		int i = 0;
		while (flag == true) {
			System.out.print(" " + i++);
		}
	}

	public void shutDown() {
		flag = false;
	}
}