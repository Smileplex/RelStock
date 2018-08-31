package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.model.Detail;

public interface DetailGenerator {	
	Long generate(Detail detailInfo, DetailLink detailLink);
}
