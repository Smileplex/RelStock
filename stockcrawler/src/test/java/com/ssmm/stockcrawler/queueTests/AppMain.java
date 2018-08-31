package com.ssmm.stockcrawler.queueTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ssmm.stockcrawler.controller.KeywordLinkQueue;

public class AppMain {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		KeywordLinkQueue linkQueue = new KeywordLinkQueue();
		
		executor.submit(new LinkProducer(linkQueue));
		executor.submit(new LinkConsumer(linkQueue));
		executor.submit(new LinkConsumer(linkQueue));
		
	}
}
