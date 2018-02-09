package com.lequ.common.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoxu
 *  自定义的线程池包装类，用来弥补默认线程池不能获得线程池关闭时未完成的任务清单
 */
public class TrackingExecutorWapper extends AbstractExecutorService {
	private final ExecutorService exec;
	private List<Runnable> runningTasksAtShutdown = Collections.synchronizedList(new ArrayList<Runnable>());

	public TrackingExecutorWapper(ExecutorService exec) {
		this.exec = exec;
	}

	public List<Runnable> getCancelledTasks() {
		if (!isTerminated()) {
			return Collections.emptyList();
		}
		return runningTasksAtShutdown;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return exec.awaitTermination(timeout, unit);
	}

	@Override
	public boolean isShutdown() {
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return exec.isTerminated();
	}

	@Override
	public void shutdown() {
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	@Override
	public void execute(final Runnable command) {
		RunnableWrapper wrapperTask = new RunnableWrapper(command, runningTasksAtShutdown, this);
		exec.execute(wrapperTask);
	}
}
