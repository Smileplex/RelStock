package com.ssmm.stockcrawler.parser;

import java.net.URL;

import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.model.ParsingResult;

public interface KeywordParser extends Runnable {
	void parse(KeywordLink keywordLink);
}
