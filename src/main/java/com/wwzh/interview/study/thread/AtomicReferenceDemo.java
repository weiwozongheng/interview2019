package com.wwzh.interview.study.thread;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

	public static void main(String[] args) {
		User zhangsan = new User("zhangsan", 23);
		User lisi = new User("lisi", 23);
		AtomicReference<User> atomicReference = new AtomicReference<User>();

		atomicReference.set(zhangsan);

		System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t" + atomicReference.get().toString());
		System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t" + atomicReference.get().toString());
	}

}

class User {
	private String username;
	private Integer age;

	public User() {
	}

	public User(String username, Integer age) {
		super();
		this.username = username;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + "]";
	}
}