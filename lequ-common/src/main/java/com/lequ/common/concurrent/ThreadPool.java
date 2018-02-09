package com.lequ.common.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * @author gaoxu
 * 
 */
public abstract class ThreadPool {
	private static final Logger logger = Logger.getLogger(ThreadPool.class);

	// 使用自定义的TrackingExecutor，可以在线程池停止的时候获取未处理的任务以及未完成的任务
	private static TrackingExecutorWapper sendPacketThreadPool;
	private static TrackingExecutorWapper robPacketThreadPool;
	private static TrackingExecutorWapper userBalanceTreadPool;

	// 默认线程池大小
	private static final int DEFAULT_SUBMIT_POOL_SIZE = 5;
	// 默认任务队列大小
	private static final int TASK_QUEUE_CAPACITY = 500;

	static {
		// 在线程池提交任务的时候，使用自定义的任务拒绝策略，保证队列在满的时候，任务暂时保存在
		// 数据库中，避免过度使用内存
		sendPacketThreadPool = new TrackingExecutorWapper(
				new ThreadPoolExecutor(DEFAULT_SUBMIT_POOL_SIZE,
						DEFAULT_SUBMIT_POOL_SIZE, 0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>(TASK_QUEUE_CAPACITY),
						new NormalThreadFactory("send packet !"),
						new RejectedExecutionHandler() {
							@Override
							public void rejectedExecution(Runnable r,
									ThreadPoolExecutor executor) {
								if (r instanceof Rejectable) {
									((Rejectable) r).reject();
								}
							}
						}));

		robPacketThreadPool = new TrackingExecutorWapper(
				new ThreadPoolExecutor(DEFAULT_SUBMIT_POOL_SIZE,
						DEFAULT_SUBMIT_POOL_SIZE, 0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>(TASK_QUEUE_CAPACITY),
						new NormalThreadFactory("rob packet !"),
						new RejectedExecutionHandler() {
							@Override
							public void rejectedExecution(Runnable r,
									ThreadPoolExecutor executor) {
								if (r instanceof Rejectable) {
									((Rejectable) r).reject();
								}
							}
						}));

		userBalanceTreadPool = new TrackingExecutorWapper(
				new ThreadPoolExecutor(DEFAULT_SUBMIT_POOL_SIZE,
						DEFAULT_SUBMIT_POOL_SIZE, 0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>(TASK_QUEUE_CAPACITY),
						new NormalThreadFactory("user balance !"),
						new RejectedExecutionHandler() {
							@Override
							public void rejectedExecution(Runnable r,
									ThreadPoolExecutor executor) {
								if (r instanceof Rejectable) {
									((Rejectable) r).reject();
								}
							}
						}));

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				logger.info("==shutdown all bizsync worker thread==");
				shutdownAllThreadPool();
			}
		}));
	}

	// 发送红包统计
	public static ExecutorService getSendPacketThreadPool() {
		return sendPacketThreadPool;
	}

	public static Future<?> addSendPacketThreadPoolTask(Runnable task) {
		return sendPacketThreadPool.submit(task);
	}

	// 抢红包统计
	public static Future<?> addRobPacketThreadPoolTask(Runnable task) {
		return robPacketThreadPool.submit(task);
	}

	// 用户余额与体现
	public static Future<?> addUserBalanceThreadPoolTask(Runnable task) {
		return userBalanceTreadPool.submit(task);
	}

	public static void shutdownAllThreadPool() {
		List<Runnable> tasks = null;
		List<Runnable> deliverTask = null;
		try {
			tasks = sendPacketThreadPool.shutdownNow();
			tasks.addAll(sendPacketThreadPool.getCancelledTasks());
		} catch (Exception ex) {
			logger.error("", ex);
		} finally {
			saveCancelledTasks(tasks);
			saveCancelledTasks(deliverTask);
		}
	}

	private static void saveCancelledTasks(List<Runnable> tasks) {
		for (Runnable r : tasks) {
			if (r instanceof Cancellable) {
				((Cancellable) r).cancel();
			} else {
				logger.warn("没有实现Cancellable接口的task，不做取消处理");
			}
		}
	}
}
