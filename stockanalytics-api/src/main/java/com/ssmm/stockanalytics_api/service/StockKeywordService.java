package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.StockKeyword;

public interface StockKeywordService extends Service<StockKeyword> {
	StockKeyword findByName(String name);
	StockKeyword save(StockKeyword entity);
	Iterable<StockKeyword> findAllWithin2DepthByName(String name);
}
