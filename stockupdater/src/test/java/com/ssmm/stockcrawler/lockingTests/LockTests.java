package com.ssmm.stockcrawler.lockingTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import junit.framework.TestCase;

public class LockTests extends TestCase {
	private int count = 0;
	ReentrantLock lock = new ReentrantLock();

	public void testLock1() {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		IntStream.range(0, 10000).forEach(i -> executor.submit(this::increment));

		stop(executor);

		System.out.println(count);

	}

	void increment() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}

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

	public void testReentrantLock() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(() -> {
			lock.lock();
			try {
				sleep(1);
			} finally {
				lock.unlock();
			}
		});
		
		executor.submit(() -> {
			System.out.println("Locked : " + lock.isLocked());
			System.out.println("Held by me : " + lock.isHeldByCurrentThread());
			boolean locked = lock.tryLock();
			System.out.println("Lock aquired : " + locked);
		});
		
		stop(executor);
	}
	
	
	
	
	
	public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
