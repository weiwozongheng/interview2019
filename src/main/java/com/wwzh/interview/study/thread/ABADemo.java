package com.wwzh.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题演示及解决
 */
public class ABADemo {
	private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
	private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);

	public static void main(String[] args) {
		new Thread(() -> {
			atomicReference.compareAndSet(100, 101);

			atomicReference.compareAndSet(101, 100);
		}, "t1").start();

		new Thread(() -> {
			try {
				// 暂停一秒钟，确保上面操作执行完成
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
		}, "t2").start();

		// --------------- 解决 ---------------
		try {
			// 暂停一秒钟，确保上面操作执行完成
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------- 解决 ---------------");

		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();

			System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);
			try {
				// 暂停一秒钟，确保上面操作执行完成
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName() + "\t 第二次版本号：" + atomicStampedReference.getStamp());

			atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
			System.out.println(Thread.currentThread().getName() + "\t 第三次版本号：" + atomicStampedReference.getStamp());
		}, "t3").start();

		new Thread(() -> {
			int stamp = atomicStampedReference.getStamp();

			System.out.println(Thread.currentThread().getName() + "\t 第一次版本号：" + stamp);
			try {
				// 暂停一秒钟，确保上面操作执行完成
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
			System.out.println(Thread.currentThread().getName() + "\t 修改结果：" + result);

			System.out.println(Thread.currentThread().getName() + "\t 当前版本号：" + atomicStampedReference.getStamp());

			System.out.println(Thread.currentThread().getName() + "\t 当前最新值：" + atomicStampedReference.getReference());
		}, "t4").start();
	}

}
