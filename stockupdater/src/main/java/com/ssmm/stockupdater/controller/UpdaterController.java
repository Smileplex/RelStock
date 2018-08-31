package com.ssmm.stockupdater.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.ssmm.stockupdater.updater.StockUpdater;

public class UpdaterController {
	private ExecutorService executor;
	private StockUpdater stockUpdater;

	@Inject
	public UpdaterController(ExecutorService executor, StockUpdater stockUpdater) {
		// TODO Auto-generated constructor stub
		this.executor = executor;
		this.stockUpdater = stockUpdater;
	}

	public void start() {
		executor.submit(stockUpdater);

//		setInitialLink();
//
//		for (int i = 0; i < AppSettings.THREAD_WORKERS/2; i++) {
//			executor.submit(keywordParser);
//		}
//		
//		for (int i = 0; i < AppSettings.THREAD_WORKERS/2; i++) {
//			executor.submit(detailParser);
//		}
		
		// stop();
	}

	public void stop() {
		try {
			System.out.println("attemp to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(50, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}

}
