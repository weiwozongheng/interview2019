package com.wwzh.interview.study.jvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		WeakReference<Object> weakReference = new WeakReference<Object>(o1, referenceQueue);

		System.out.println(o1);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());

		System.out.println("------------------------------");
		o1 = null;
		System.gc();

		Thread.sleep(500);

		System.out.println(o1);
		System.out.println(weakReference.get());
		System.out.println(referenceQueue.poll());
	}

}
