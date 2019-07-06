package com.wwzh.interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.ArrayBlockingQueue<E>：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * java.util.concurrent.LinkedBlockingQueue<E>：是一个基于链表结构的有界阻塞队列，此队列按FIFO（先进先出）排序元素，吞吐量通常要高于ArrayBlockingQueue。
 * java.util.concurrent.SynchronousQueue<E>：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue。
 */
public class BlockingQueueDemo {

	public static void main(String[] args) throws Exception {
		ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

		/*System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));

		// java.lang.IllegalStateException: Queue full
		// System.out.println(blockingQueue.add("x"));

		System.out.println(blockingQueue.element());

		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());

		// Exception in thread "main" java.util.NoSuchElementException
		// System.out.println(blockingQueue.remove());*/

		/*System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("b"));
		System.out.println(blockingQueue.offer("c"));
		// false
		System.out.println(blockingQueue.offer("x"));

		System.out.println(blockingQueue.peek());

		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		// null
		System.out.println(blockingQueue.poll());*/

		/*blockingQueue.put("a");
		blockingQueue.put("b");
		blockingQueue.put("c");

		// blockingQueue.put("x");

		System.out.println("====================");

		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());

		// System.out.println(blockingQueue.take());*/

		System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
	}

}
