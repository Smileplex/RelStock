package com.ssmm.stockcrawler.parser;

import java.net.URL;

public interface DetailParser extends Runnable {
	void parse(URL url);
}
