package com.wwzh.interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(5);

		System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(5, 2019)
				+ " current data = " + atomicInteger.get());

		System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(5, 2019)
				+ " current data = " + atomicInteger.get());
	}

}
