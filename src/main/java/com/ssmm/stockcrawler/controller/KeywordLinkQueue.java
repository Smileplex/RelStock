package com.ssmm.stockcrawler.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.inject.Singleton;
import com.ssmm.stockcrawler.model.KeywordLink;

public class KeywordLinkQueue {
	final BlockingQueue<KeywordLink> queue = new LinkedBlockingQueue<>();
	final Set<String> duplicateChecker = new HashSet<>();

	public KeywordLink get() {
		try {
			KeywordLink data = queue.take();
			return data;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void put(KeywordLink data) {
		try {
			if(duplicateChecker.add(data.getLink())) {
				queue.put(data);
				System.out.println(String.format("[%s/%s]", queue.size(),duplicateChecker.size()));
			}
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
