package com.aaamidatlantic.demo.testThread.join;

/****************************************************************************************************
 * The join function is not used often, if you are busy you can skip
 * 
 * The join function will have the child thread to be included in the main thread. 
 * This is pretty much like a direct method call to the run function in child thread object
 * 
 * The following example will have the child thread to be joined in the main thread. So the main 
 * thread will continue only after the child thread's run function operation finishes. If the join 
 * function call is commented out as in TestJoin2.java, you will see the main thread and child thread 
 * will run in parallel. 
 ****************************************************************************************************/
public class TestJoin {
	public static void main(String[] args) {
		MyThread myThread = new MyThread("John");
		myThread.start();
		try {
			myThread.join();
		} catch (InterruptedException e) {
		}

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

class MyThread extends Thread {
	MyThread(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("i am " + getName());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
