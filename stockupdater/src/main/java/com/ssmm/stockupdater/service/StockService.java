package com.ssmm.stockupdater.service;

import java.util.Collection;

import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.model.StockResult;

public interface StockService extends Service<Stock> {
	Iterable<Stock> findTop10UnupdatedStocks();
	Stock findByName(String name);
	Stock findByCode(String code);
}
