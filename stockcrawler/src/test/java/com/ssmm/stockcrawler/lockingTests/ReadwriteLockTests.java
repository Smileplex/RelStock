package com.ssmm.stockcrawler.lockingTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class ReadwriteLockTests {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();
		executor.submit(()->{
			long stamp = lock.readLock();
			try {
				System.out.println(Thread.currentThread().getName()+"="+"read lock acquired"+stamp);
				sleep(5);
			}finally {
				lock.unlock(stamp);
			}
		});
		
		executor.submit(()->{
			long stamp = lock.tryWriteLock();
			try {
				System.out.println(Thread.currentThread().getName()+"="+"write lock acquired"+stamp);
				sleep(3);
			}finally {
				lock.unlock(stamp);
			}
		});
		
		stop(executor);
	}

	public static void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("termination interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}

	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
