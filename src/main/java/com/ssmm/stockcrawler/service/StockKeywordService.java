package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.StockKeyword;

public interface StockKeywordService extends Service<StockKeyword> {
	StockKeyword findByName(String name);
	StockKeyword save(StockKeyword entity);
}
