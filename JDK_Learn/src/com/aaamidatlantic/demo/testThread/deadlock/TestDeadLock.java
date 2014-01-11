package com.aaamidatlantic.demo.testThread.deadlock;

/****************************************************************
 * 
 * Please refer to examples for synchronization first.
 * 
 * This is an example of deadlock. When both threads try to lock 2 
 * different objects (static property o1 and o2 as below) to finish 
 * the task, but lock the objects in different order: 
 * 
 *   thread t1 has flag equals to 1, thread t2 has flag equals to 2. 
 *   t1 will lock object o1 first, then tries to lock o2 to complete task, however t1 falls to sleep before 
 *   locking o2, this will give t2 chance to lock o2. 
 *   After t2 locks o2 and falls to sleep, t1 will wake up and try to lock o2 to accomplish task, but at 
 *   this time t2 has locked o2 already so t1 has to wait until o2 is released. t2 then wakes up and tries to lock
 *   o1 to finish job which is not possible because o1 is locked by t1, t2 has to wait.       
 * 
 ****************************************************************/

public class TestDeadLock implements Runnable {
	public int flag = 1;

	static Object o1 = new Object(), o2 = new Object();

	public static void main(String[] args) {
		TestDeadLock td1 = new TestDeadLock();
		TestDeadLock td2 = new TestDeadLock();
		
		td1.flag = 1;
		td2.flag = 2;
		
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		
		t1.start();
		t2.start();

	}
	
	public void run() {
		System.out.println("flag=" + flag);
		if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("1");
				}
			}
		}
		if (flag == 2) {
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("2");
				}
			}
		}
	}

	
}