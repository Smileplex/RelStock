package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public interface StockKeywordGenerator {
	StockKeyword create(KeywordInfo keywordInfo, KeywordLink keywordLink);
}
