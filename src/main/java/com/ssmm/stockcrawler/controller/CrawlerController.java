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

		for (int i = 0; i < AppSettings.THREAD_WORKERS/2; i++) {
			executor.submit(keywordParser);
		}
		
		for (int i = 0; i < AppSettings.THREAD_WORKERS/2; i++) {
			executor.submit(detailParser);
		}
		
		// stop();
	}

	public void setInitialLink() {
		KeywordLink keywordLink = new KeywordLink(
				"https://m.search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=lg%EC%A0%84%EC%9E%90&where=m");
		keywordLink.setParentId(53L);
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
