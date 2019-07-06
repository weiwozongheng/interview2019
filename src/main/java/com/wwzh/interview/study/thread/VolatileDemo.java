package com.wwzh.interview.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * 1 验证volatile的可见性
 * 1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2 添加了 volatile int number = 0; 可以解决可见性问题。
 * 
 * 2 验证volatile不保证原子性
 * 2.1 原子性指的是什么意思？
 *     不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 * 
 * 2.2 volatile不保证原子性的案例演示
 * 
 */
public class VolatileDemo {

	public static void main(String[] args) {
		MyData myData = new MyData();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "\t come in");

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			myData.add();
			System.out.println(Thread.currentThread().getName() + "\t updated number value: " + myData.number);
		}, "AAA").start();

		// 第2个线程就是我们的main线程
		while (myData.number == 0) {
			// main线程就一直在这里等待循环，直到number值不再等于0
		}
		System.out.println(Thread.currentThread().getName() + "\t mission is over");
	}

}

class MyData {
	// int number = 0;
	volatile int number = 0;

	public void add() {
		this.number = 60;
	}

}