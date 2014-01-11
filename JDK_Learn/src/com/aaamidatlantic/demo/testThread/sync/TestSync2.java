package com.aaamidatlantic.demo.testThread.sync;

/***************************************************************************
 * 
 * Please read TestSync.java and TestSync1.java first
 * 
 * This is another approach of TestSync1.java, it locks the whole function
 ******************************************************************/
public class TestSync2 implements Runnable {
	Timer2 timer = new Timer2();

	public static void main(String[] args) {
		TestSync2 test = new TestSync2();

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

class Timer2 {
	private static int num = 0;

	//add a lock for the function 
	public synchronized void add(String name) {
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