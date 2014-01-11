package com.aaamidatlantic.demo.testThread.sync;

/******************************************************************
 * 
 * This example has no lock for the shared static property "num". 
 * The author tries to print "1" for thread 1, and 2 for thread 2. 
 * However both thread print out "2".
 ******************************************************************/
public class TestSync implements Runnable {
	Timer timer = new Timer();

	public static void main(String[] args) {
		TestSync test = new TestSync();

		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);

		t1.setName("t1");
		t2.setName("t2");

		t1.start();
		t2.start();
	}

	public void run() {
		timer.add(Thread.currentThread().getName());
	}
}

class Timer {
	private static int num = 0;

	public void add(String name) {

		num++;
		
		try {
			//The line below will have the current thread to sleep for 1 mill-second, 
			//which is enough for the next thread to start and change the value of shared
			//property num
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
		
		System.out.println(name + ", You are user number " + num);

	}
}