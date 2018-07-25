package com.ssmm.stockcrawler.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.AppSettings;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.DetailParser;
import com.ssmm.stockcrawler.parser.KeywordParser;

public class CrawlerController {
	private ExecutorService executor;
	private KeywordParser keywordParser;
	private DetailParser detailParser;
	private KeywordLinkQueue keywordLinkQueue;

	@Inject
	public CrawlerController(ExecutorService executor, KeywordParser keywordParser, DetailParser detailParser,
			KeywordLinkQueue keywordLinkQueue) {
		// TODO Auto-generated constructor stub
		this.executor = executor;
		this.keywordParser = keywordParser;
		this.detailParser = detailParser;
		this.keywordLinkQueue = keywordLinkQueue;
	}

	public void start() {
		setInitialLink();

		for (int i = 0; i < AppSettings.THREAD_WORKERS; i++) {
			executor.submit(keywordParser);
		}

		// executor.submit(detailParser);
		// stop();
	}

	public void setInitialLink() {
		KeywordLink keywordLink = new KeywordLink(
				"https://m.search.naver.com/search.naver?where=m&query=%ED%95%9C%EA%B5%AD%EC%A0%84%EB%A0%A5&sm=mtb_she&qdt=0");
		keywordLinkQueue.put(keywordLink);
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
