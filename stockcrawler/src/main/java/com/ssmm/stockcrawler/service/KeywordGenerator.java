package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public interface KeywordGenerator {
	Long generate(KeywordInfo keywordInfo, KeywordLink keywordLink);
}
