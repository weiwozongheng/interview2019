package com.wwzh.interview.study.jvm.oom;

/**
 * 高并发请求服务器时，经常出现如下异常：
 * java.lang.OutOfMemoryError: unable to create new native thread
 * 准确地讲，该native thread异常与对应的操作系统平台有关。
 * 
 * 导致原因：
 * 1. 应用创建了太多的线程，一个应用进程创建多个线程，超过系统承载极限。
 * 2. 服务器并不允许你的应用程序创建这么多的线程，linux系统默认允许单个进程创建的线程数是1024个。
 * 应用创建线程超过这个数量，就会报java.lang.OutOfMemoryError: unable to create new native thread
 * 
 * 解决办法：
 * 1. 想办法降低应用程序创建的线程数量，分析应用是否真的需要创建这么多线程。如果不是，修改相关代码讲线程数降到最低。
 * 2. 对于有的应用，确实需要创建很多线程，远超过linux系统默认的1024个线程的限制，可以通过修改linux服务器配置，扩大上限。
 */
public class UnableCreateNewThreadDemo {

	public static void main(String[] args) {

		for (int i = 1;; i++) {
			System.out.println(">>>>>>>>>> i = " + i);

			new Thread(() -> {
				try {
					Thread.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "" + i).start();
		}
	}

}
