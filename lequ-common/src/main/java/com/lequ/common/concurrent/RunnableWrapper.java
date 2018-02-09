package com.lequ.common.concurrent;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;

/**
 * @author gaoxu
 *
 */
public class RunnableWrapper implements Runnable, Rejectable, Cancellable {
	private final Runnable runnable;
	private final List<Runnable> runningTasksAtShutdown;
	private final AbstractExecutorService executorService;

	public RunnableWrapper(Runnable runnable, List<Runnable> runningTasksAtShutdown,
			TrackingExecutorWapper executorService) {
		this.runnable = runnable;
		this.runningTasksAtShutdown = runningTasksAtShutdown;
		this.executorService = executorService;
	}

	@Override
	public void run() {
		try {
			runnable.run();
		} finally {
			if (executorService.isShutdown() && Thread.currentThread().isInterrupted()) {
				this.runningTasksAtShutdown.add(runnable);
			}
		}
	}

	@Override
	public void cancel() {
		if (runnable instanceof Cancellable) {
			((Cancellable) runnable).cancel();
		}
	}

	@Override
	public void reject() {
		if (runnable instanceof Rejectable) {
			((Rejectable) runnable).reject();
		}
	}

}
