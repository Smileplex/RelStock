package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.Stock;

public interface StockService extends Service<Stock> {
	Stock findByCode(String code);
}
