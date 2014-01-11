package com.aaamidatlantic.demo.testThread.basic;

/*******************************************************************************************
 * There are 2 ways to create Thread. One is to implement the "Runnable" interface as in 
 * Class Runner below, the other is to extend "Thread" as in MyThread below. Because java Class 
 * can only extend from one parent Class but can implement multiple interfaces at the same time, 
 * in my opinion the first method is more preferred because of the flexibility.
 * 
 * The example below shows how to use both ways to create thread and run.
 *******************************************************************************************/
public class TestThread {
	public static void main(String args[]) {
		testExtendsThread();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		testRunnable();
	}

	private static void testExtendsThread() {
		MyThread t1 = new MyThread();
		t1.start();

	}

	public static void testRunnable() {
		Runner r = new Runner();
		Thread t1 = new Thread(r);

		t1.start();
	}
}

class Runner implements Runnable {
	public void run() {
		System.out.println("--------I am in Runner---------");
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println("Runner No. " + i);
		}
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println("--------I am in MyThread---------");
		for (int i = 0; i < 1; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println("My Thread No. " + i);
		}
	}
}
