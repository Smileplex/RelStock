package com.ssmm.stockcrawler.queueTests;

import java.util.concurrent.TimeUnit;

import com.ssmm.stockcrawler.controller.KeywordLinkQueue;

public class LinkProducer implements Runnable {
	private KeywordLinkQueue linkQueue;

	public LinkProducer(KeywordLinkQueue linkQueue) {
		this.linkQueue = linkQueue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		generateNumbers();
	}

	private void generateNumbers() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 1000; i++) {
//			linkQueue.put(i);
		}
	}

}
