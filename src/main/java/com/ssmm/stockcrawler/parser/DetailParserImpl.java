package com.ssmm.stockcrawler.parser;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;

public class DetailParserImpl implements DetailParser {
	private KeywordLinkQueue linkQueue;

	@Inject
	public DetailParserImpl(KeywordLinkQueue linkQueue) {
		this.linkQueue = linkQueue;
		// TODO Auto-generated constructor stub
	}

	public void run() {
		// TODO Auto-generated method stub
//		while (true) {
//			try {
//				Integer number = linkQueue.get();
//				TimeUnit.MILLISECONDS.sleep(100);
//				System.out.println(Thread.currentThread().getName() + " = " + number);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public void parse(URL url) {
		// TODO Auto-generated method stub

	}
}
