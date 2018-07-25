package com.ssmm.stockcrawler.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockcrawler.model.Stock;
import com.ssmm.stockcrawler.model.StockResult;

public class StockServiceImpl extends GenericService<Stock> implements StockService {

	public Stock findByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		return session.queryForObject(getEntityType(), "match (n:Stock{name:{name}}) return n", params);
	}

	public Stock findByCode(int code) {
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

	public Stock createOrUpdateBy(StockResult stockResult) {
		// TODO Auto-generated method stub
		// Stock searchedStock = f
		Stock searchedStock = findByCode(0);
		if (searchedStock == null) {
//			Stock stock = new Stock();
////			stock.setName(stockResult.getStockName());	
////			stock.setCode(stockCode);
//			stock.setPrice(stockResult.getOpenVal());
//			stock.setPricePrev(stockResult.getPrevClose());
//			stock.setPriceMax(stockResult.getHighVal());
//			stock.setPriceMin(stockResult.getLowVal());
//			stock.setFluct(stockResult.getRisefall());
//			stock.setFluctRate(stockResult.getPriceChangeRate());
//			stock.setRiseFall(stockResult.getRisefall());
//			stock.setChartDaily(stockResult.getChartDailyUrl());
//			stock.setChartWeekly(stockResult.getChartWeeklyUrl());
//			stock.setChartMonthly(stockResult.getChartMonthlyUrl());
//			stock.setDateCreated(new Date());
//			stock.setDateUpdated(new Date());

		}
		return null;
	}

}
