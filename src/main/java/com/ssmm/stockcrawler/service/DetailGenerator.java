package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.Detail;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public interface DetailGenerator {	
	Long generate(Detail detail, KeywordLink keywordLink);
}
