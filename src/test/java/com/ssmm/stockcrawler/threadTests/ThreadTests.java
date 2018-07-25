package com.ssmm.stockcrawler.threadTests;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import junit.framework.TestCase;

public class ThreadTests extends TestCase {

//	public void testThread1() {
//		Runnable task = () -> {
//			try {
//				String threadName = Thread.currentThread().getName();
//				System.out.println("Foo " + threadName);
//				TimeUnit.SECONDS.sleep(2);
//				System.out.println("Bar " + threadName);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		};
//
//		task.run();
//
//		Thread thread = new Thread(task);
//		thread.start();
//		System.out.println("Done!");
//	}
//	
	public void testThread2() throws InterruptedException, ExecutionException, TimeoutException {
		Callable<Integer> task = ()->{
			try {
				TimeUnit.SECONDS.sleep(3);
				return 123;
			}catch(InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		
		System.out.println("future done?" + future.isDone());
		Integer result = future.get(5, TimeUnit.SECONDS);
		System.out.println("future done?" + future.isDone());
		System.out.println("result: " + result);
		
	}
	
	public void testThread3() throws InterruptedException {
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(()->"task1", ()->"task2", ()->"task3");
		
		executor.invokeAll(callables).stream().map(future->{
			try {
				return future.get();
			}catch(Exception e) {
				throw new IllegalStateException(e);
			}
		}).forEach(System.out::println);
	}
	
	public void testThread4() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(callable("task1",2),callable("task2",1),callable("task3",3));
		
		String result = executor.invokeAny(callables);
		System.out.println(result);
	}
	
	public void testThreadScheduled() throws InterruptedException, ExecutionException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		Runnable task = () -> System.out.println("Scheduling : " + System.nanoTime());
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
		
		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms\n", remainingDelay);
		System.out.println(future.get());
	}
	
	public void testThreadFixedRateScheduled() throws InterruptedException, ExecutionException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		Runnable task = () -> System.out.println("Scheduling : " + System.nanoTime());
		
		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}
	

	Callable<String> callable(String result, long sleepSeconds){
		return ()->{
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		};
	}
}
