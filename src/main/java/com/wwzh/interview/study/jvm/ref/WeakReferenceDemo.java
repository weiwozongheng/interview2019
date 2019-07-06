package com.wwzh.interview.study.jvm.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

	public static void main(String[] args) {
		Object o1 = new Object();
		WeakReference<Object> weakReference = new WeakReference<Object>(o1);
		System.out.println(o1);
		System.out.println(weakReference.get());

		o1 = null;
		System.gc();
		System.out.println("------------------------------");

		System.out.println(o1);
		System.out.println(weakReference.get());
	}

}
