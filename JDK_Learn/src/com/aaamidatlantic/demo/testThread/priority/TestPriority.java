package com.aaamidatlantic.demo.testThread.priority;

/****************************************************************************************
 * Java virtual machine's time is divided into pieces then JVM divides the time pieces among 
 * all threads. At any time only the thread get the current time piece will run, but because 
 * the cpu is pretty fast, people can feel all the thread are running simultaneously
 * 
 * Thread can have priority. Higher priority means the thread will get more time pieces, which
 * does not mean the thread with higher priority will run and finish then the lower priority 
 * thread can start. 
 * 
 * This example's output will show both thread are running at the same time, but t1 will finish 
 * first because it has higher priority
 ****************************************************************************************/
public class TestPriority {
	public static void main(String[] args) {
		Thread t1 = new Thread(new T1());
		Thread t2 = new Thread(new T2());
		
		//t1 will have higher priority than t2
		t1.setPriority(Thread.NORM_PRIORITY + 3);
		
		t1.start();
		t2.start();
	}
}

class T1 implements Runnable {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("T1: " + i);
		}
	}
}

class T2 implements Runnable {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("------T2: " + i);
		}
	}
}