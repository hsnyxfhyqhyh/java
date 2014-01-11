package com.aaamidatlantic.demo.testThread.sync;

/******************************************************************
 * 
 * Please read example TestSync.java first
 * 
 * This example has lock for a block of code in the add function. The author
 * tries to print "1" for thread 1, and 2 for thread 2. Because of the keyword 
 * synchronized, the 2nd child thread started and had to wait just before the line
 * of "synchronized" until the 1st thread release the lock then it can continue.
 * By this way, the author can achieve the ideal result.
 ******************************************************************/
public class TestSync1 implements Runnable {
	Timer1 timer = new Timer1();

	public static void main(String[] args) {
		TestSync1 test = new TestSync1();

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

class Timer1 {
	private static int num = 0;

	public void add(String name) {
		
		//add a lock for the code block in the parentheses
		synchronized (this) {
			num++;

			try {
				// The line below will have the current thread to sleep for 1 mill-second,
				// which is enough for the next thread to start, but it has to wait until 
				// the lock is released in the previous thread.
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}

			System.out.println(name + ", You are user number " + num);
		}
	}
}