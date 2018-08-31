package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.Stock;
import com.ssmm.stockcrawler.model.StockResult;

public interface StockService extends Service<Stock> {
	Stock createOrUpdateBy(StockResult stockResult);
	Stock findByName(String name);
	Stock findByCode(int code);
}
