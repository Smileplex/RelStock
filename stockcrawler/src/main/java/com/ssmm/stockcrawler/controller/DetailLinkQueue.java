package com.ssmm.stockcrawler.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.ssmm.stockcrawler.model.DetailLink;

public class DetailLinkQueue {
	final BlockingQueue<DetailLink> queue = new LinkedBlockingQueue<>();
	final Set<String> duplicateChecker = new HashSet<>();

	public DetailLink get() {
		try {
			DetailLink data = queue.take();
			return data;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void put(DetailLink data) {
		try {
			if (duplicateChecker.add(data.getLink())) {
				queue.put(data);
				System.out.println(String.format("DetailLinkQueue[%s/%s]", queue.size(), duplicateChecker.size()));
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
