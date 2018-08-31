package com.ssmm.stockupdater.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.model.StockResult;

public class StockServiceImpl extends GenericService<Stock> implements StockService {

	public Stock findByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		return session.queryForObject(getEntityType(), "match (n:Stock{name:{name}}) return n", params);
	}

	public Stock findByCode(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		return session.queryForObject(getEntityType(), "match (n:Stock{code:{code}}) return n", params);
	}

	@Override
	public Class<Stock> getEntityType() {
		// TODO Auto-generated method stub
		return Stock.class;
	}

	@Override
	public Iterable<Stock> findTop10UnupdatedStocks() {
		// TODO Auto-generated method stub
		Iterable<Stock> stocks = session.query(getEntityType(), "match (n:Stock) return n order by n.updatedDate asc limit 1", Collections.emptyMap());
		return stocks;
	}

}
