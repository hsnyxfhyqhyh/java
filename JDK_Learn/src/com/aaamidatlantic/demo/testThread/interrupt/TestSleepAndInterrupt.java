package com.aaamidatlantic.demo.testThread.interrupt;

import java.util.*;

/**************************************************************************************************
 * sleep is a static method in Thread class. When it gets called, it will let the current thread 
 * stop running for a certain time (in millseconds). 
 * 
 * sleep method needs to be wrapped with try/catch block of InterruptedException
 * 
 * When a thread goes to sleep, it can be interrupted by the "interrupt" method and an 
 * InterruptedException will be thrown out inside of it (the run method). 
 * 
 * This program is to create a child thread and start it. The main thread goes to sleep then the child 
 * thread will print the time every second for 10 times until the main thread interrupts it.  
 **************************************************************************************************/
public class TestSleepAndInterrupt {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		try {
			//sleep is a static method, the following line will cause the main thread of this program to sleep
			Thread.sleep(10000);	
		} catch (InterruptedException e) {
			System.out.println("Main thread is interrupted");
		}
		
		//to interrupt the child thread
		myThread.interrupt();
	}
}

class MyThread extends Thread {
	boolean flag = true;

	public void run() {
		while (flag) {
			System.out.println("===" + new Date() + "===");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("My thread is interrupted");
				return;
			}
		}
	}
}
