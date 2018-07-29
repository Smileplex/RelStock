package com.ssmm.stockcrawler.parser;

import java.net.URL;

import com.ssmm.stockcrawler.model.DetailLink;

public interface DetailParser extends Runnable {
	void parse(DetailLink detailLink);
}
