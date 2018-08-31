package com.ssmm.stockupdater.service;

import java.util.List;

import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.parser.model.Detail;

public interface StockManager {
	List<Stock> findTop10UnupdatedStocks();
	Stock update(Detail result);
}
