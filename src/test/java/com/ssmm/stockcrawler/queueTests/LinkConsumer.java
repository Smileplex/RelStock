package com.ssmm.stockcrawler.queueTests;

import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.KeywordLink;

public class LinkConsumer implements Runnable {
	private KeywordLinkQueue linkQueue;

	public LinkConsumer(KeywordLinkQueue linkQueue) {
		this.linkQueue = linkQueue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			KeywordLink number = linkQueue.get();
			System.out.println(Thread.currentThread().getName() + " result: " + number);
		}
	}
}
