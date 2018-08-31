package com.ssmm.stockcrawler.lockingTests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampLockTests {
	private static int count = 0;
	public static void main(String[] args) {
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		Map<String, String> map = new HashMap<>();
//		StampedLock lock = new StampedLock();
//
//		
//		Runnable readTask = () -> {
//			long stamp = lock.readLock();
//			System.out.println("r-stamp = " + stamp);
//			try {
//				System.out.println(map.get("foo"));
//				sleep(1);
//			} finally {
//				lock.unlockRead(stamp);
//			}
//		};
//
//		executor.submit(readTask);
//		executor.submit(readTask);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();
		
		executor.submit(() -> {
			long stamp = lock.writeLock();
			System.out.println("w-stamp = " + stamp);
			try {
				sleep(3);

			} finally {
				lock.unlockWrite(stamp);
			}
		});

		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    try {
		        if (count == 0) {
		            stamp = lock.tryConvertToWriteLock(stamp);
		            if (stamp == 0L) {
		                System.out.println("Could not convert to write lock");
		                stamp = lock.writeLock();
		            }
		            count = 23;
		        }
		        System.out.println(count);
		    } finally {
		        lock.unlock(stamp);
		    }
		});

		stop(executor);
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
