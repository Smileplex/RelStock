package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.model.StockResult;

public interface StockKeywordService extends Service<StockKeyword> {
	StockKeyword findByName(String name);
	StockKeyword save(StockKeyword entity);
	Iterable<StockKeyword> findAllWithin2DepthByName(String name);
	StockResult findAllWithin2Depth(String name);
}
