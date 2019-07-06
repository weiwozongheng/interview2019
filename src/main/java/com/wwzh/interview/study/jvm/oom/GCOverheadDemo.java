package com.wwzh.interview.study.jvm.oom;

import java.util.LinkedList;
import java.util.List;

/**
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * GC回收时间过长时会抛出OutOfMemoryError。
 * 过长的定义是，超过99%的时间用来做GC并且回收了不到2%的堆内存，连续多次GC都只是回收了不到2%的极端情况下才会抛出。
 * 假如不抛出GC overhead limit 错误会发生什么情况呢？
 * 那就是GC清理的这么点儿内存很快会被再次填满，迫使GC再次执行，这样就形成恶性循环。
 * CPU使用率一直是100%，而GC却没有任何成果。
 */
public class GCOverheadDemo {

	public static void main(String[] args) {
		int i = 0;
		List<String> list = new LinkedList<String>();
		try {
			while (true) {
				list.add(String.valueOf(i++).intern());
			}
		} catch (Throwable throwable) {
			System.out.println("********** i = " + i);
			throwable.printStackTrace();
			throw throwable;
		}
	}

}
