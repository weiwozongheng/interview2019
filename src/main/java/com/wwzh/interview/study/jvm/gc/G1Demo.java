package com.wwzh.interview.study.jvm.gc;

import java.util.Random;

/**
 * JVM参数：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC
 */
public class G1Demo {

	public static void main(String[] args) {
		String str = "weiwozongheng";
		while (true) {
			str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
			str.intern();
		}
	}

}
