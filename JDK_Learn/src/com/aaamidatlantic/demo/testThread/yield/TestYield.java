package com.aaamidatlantic.demo.testThread.yield;

/*****************************************************************************
 * current running thread can yield to give up current time piece of cpu so 
 * other thread will get chance to run. This is a one time thing, the current 
 * thread will keep running when getting the next time piece.
 * 
 * The following example shows the 2 child threads will yield and give each other 
 * chance to run whenever their counters ended with 0(because of the mod by 10 in 
 * the run function).  
 *****************************************************************************/
public class TestYield {
	public static void main(String[] args) {
		MyThread t1 = new MyThread("t1");
		MyThread t2 = new MyThread("t2");
		
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {
	MyThread(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + ": " + i);
			if (i % 10 == 0) {
				yield();
			}
		}
	}
}
