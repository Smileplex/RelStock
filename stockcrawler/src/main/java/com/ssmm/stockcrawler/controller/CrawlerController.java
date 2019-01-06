package com.ssmm.stockcrawler.controller;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.DetailParser;
import com.ssmm.stockcrawler.parser.KeywordParser;
import com.ssmm.stockcrawler.parser.RealtimeParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CrawlerController {
	private ExecutorService executor;
	private KeywordParser keywordParser;
	private DetailParser detailParser;
	private RealtimeParser realtimeParser;
	private KeywordLinkQueue keywordLinkQueue;

	@Inject
	public CrawlerController(ExecutorService executor, KeywordParser keywordParser, DetailParser detailParser, RealtimeParser realtimeParser,
			KeywordLinkQueue keywordLinkQueue) {
		// TODO Auto-generated constructor stub
		this.executor = executor;
		this.keywordParser = keywordParser;
		this.detailParser = detailParser;
		this.realtimeParser = realtimeParser;
		this.keywordLinkQueue = keywordLinkQueue;
	}

	public void start() {
		setInitialLink();

		for (int i = 0; i < 4; i++) {
			executor.submit(keywordParser);
		}
		
		for (int i = 0; i < 5; i++) {
			executor.submit(detailParser);
		}

		for (int i = 0; i < 1; i++) {
			executor.submit(realtimeParser);
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
