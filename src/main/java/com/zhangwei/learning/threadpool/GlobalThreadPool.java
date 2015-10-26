package com.zhangwei.learning.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 全局线程池,用于运行任务
 * 
 * @author Administrator
 * 
 */
public class GlobalThreadPool {

	private final static int poolsize = 100;

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(poolsize);

	/**
	 * 执行的任务需要得到执行结果,所以如果线程调用了这个接口,会导致线程挂起,等待任务执行完成
	 * 
	 * @param task
	 * @return
	 */
	public static <T> T submitTaskWithResult(Callable<T> task) {
		Future<T> resultFuture = executorService.submit(task);
		T resultT = null;
		try {
			resultT = resultFuture.get();
		} catch (InterruptedException e) {
			//
			e.printStackTrace();
		} catch (ExecutionException e) {
			//
			e.printStackTrace();
		}
		return resultT;
	}

	/**
	 * 提交的任务无需得到执行结果,将会异步执行
	 * 
	 * @param task
	 */
	public static void submitTaskWithoutResult(Runnable task) {
		executorService.execute(task);
	}

	private GlobalThreadPool() {

	}
}
