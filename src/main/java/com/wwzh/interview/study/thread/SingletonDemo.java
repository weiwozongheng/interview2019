package com.wwzh.interview.study.thread;

public class SingletonDemo {
	// private static SingletonDemo INSTANCE = null;
	private static volatile SingletonDemo INSTANCE = null;

	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName() +" new SingletonDemo()...");
	}

	// public static synchronized SingletonDemo getInstance() {
	public static SingletonDemo getInstance() {
		if (INSTANCE == null) {
			synchronized(SingletonDemo.class) {
				if (INSTANCE == null) {
					INSTANCE = new SingletonDemo();
				}
			}
		}
		return INSTANCE;
	}

	public static void main(String[] args) {
		// System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		// System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
		// System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

		// 多线程并发后，情况发生了很大的变化
		for (int i = 0; i <= 10; i++) {
			new Thread(() -> {
				SingletonDemo.getInstance();
			}, String.valueOf(i)).start();
		}
	}

}
