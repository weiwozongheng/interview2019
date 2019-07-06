package com.wwzh.interview.study.jvm.oom;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * JVM参数：-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * 
 * Java8及之后的版本使用Metaspace来替换永久代。
 * 
 * Metaspace是方法区在HotSpot中的实现，它与永久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存。
 * 也即在java8中，class metadata（the virtual machines internal presentation od java class）
 * 被存储在叫做Metaspace的native memory。
 * 
 * 永久代（java8后被元空间（Metaspace）取代了）存放了以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * 
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的。
 */
public class MetaspaceDemo {

	static class OomTest {
	}

	public static void main(String[] args) {
		int i = 0;
		try {
			while (true) {
				i++;

				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(OomTest.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new MethodInterceptor() {

					@Override
					public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy)
							throws Throwable {
						return methodProxy.invokeSuper(obj, objects);
					}
				});
				enhancer.create();
			}
		} catch (Exception e) {
			System.err.println("创建 " + i + " 次后发生异常！");
			// Caused by: java.lang.OutOfMemoryError: Metaspace
			e.printStackTrace();
		}
	}

}
