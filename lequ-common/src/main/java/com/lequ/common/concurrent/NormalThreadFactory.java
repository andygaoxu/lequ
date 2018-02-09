package com.lequ.common.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gaoxu
 *
 */
public class NormalThreadFactory implements ThreadFactory {
	static final AtomicInteger poolNumber = new AtomicInteger(1);
	final ThreadGroup group;
	final AtomicInteger threadNumber = new AtomicInteger(1);
	final String namePrefix;

	public NormalThreadFactory(String namePrefix) {
		this.namePrefix = "LeQu HongBao " + poolNumber.getAndIncrement() + namePrefix;
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		return t;
	}
}
