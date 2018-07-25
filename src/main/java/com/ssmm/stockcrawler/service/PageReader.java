package com.ssmm.stockcrawler.service;

import org.jsoup.nodes.Document;

public interface PageReader {
	Document read(String url);
}
