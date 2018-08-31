package com.ssmm.stockcrawler.threadTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class AtomicIntegerTests {
	public static void main(String[] args) {
		AtomicInteger atomicInt = new AtomicInteger(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 1000).forEach(i -> {
			Runnable task = () -> atomicInt.accumulateAndGet(i, new IntBinaryOperator() {
				
				@Override
				public int applyAsInt(int left, int right) {
					// TODO Auto-generated method stub
					return left+right;
				}
			});
			executor.submit(task);
		});

		stop(executor);
		System.out.println(atomicInt.get());

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
}
