package com.wwzh.interview.study.jvm.oom;

/**
 * -Xms5m -Xmx5m -XX:+PrintGCDetails
 */
public class StackOverflowErrorDemo {

	public static void main(String[] args) {
		StackOverflowError();
	}

	private static void StackOverflowError() {
		// Exceprion in thread "main" java.lang.StackOverflowError
		StackOverflowError();
	}

}
