package com.wwzh.interview.study.jvm.oom;

import java.util.Random;

/**
 * -Xms5m -Xmx5m -XX:+PrintGCDetails
 */
public class JavaHeapSpaceDemo {

	public static void main(String[] args) {
		String str = "weiwozongheng";
		while (true) {
			// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
			str += str + new Random(11111111) + new Random(222222);
			str.intern();
		}
	}

}
