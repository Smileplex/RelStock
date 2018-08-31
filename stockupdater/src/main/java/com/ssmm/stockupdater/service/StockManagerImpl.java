package com.ssmm.stockupdater.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.model.StockValue;
import com.ssmm.stockupdater.parser.model.Detail;

public class StockManagerImpl implements StockManager {
	private StockService stockService;
	private ObjectMapper objectMapper;

	@Inject
	public StockManagerImpl(StockService stockService, ObjectMapper objectMapper) {
		this.stockService = stockService;
		// TODO Auto-generated constructor stub
		this.objectMapper = objectMapper;
	}

	@Override
	public Stock update(Detail result) {
		// TODO Auto-generated method stub
		try {
			StockValue stockValue = objectMapper.readValue(result.getResult(), StockValue.class);
			Stock entity = stockService.findByCode(stockValue.getCd());
			if (Objects.nonNull(entity)) {
				entity.setPricePrev(stockValue.getSv());
				entity.setPrice(stockValue.getNv());
				entity.setPriceMax(stockValue.getHv());
				entity.setPriceMin(stockValue.getLv());
				entity.setRiseFall(Integer.parseInt(stockValue.getRf()));
				entity.setFluct(stockValue.getCv());
				entity.setFluctRate(stockValue.getCr());
				entity.setUpdatedDate(new Date());
				stockService.save(entity);
				return entity;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Stock> findTop10UnupdatedStocks() {
		// TODO Auto-generated method stub
		return (List<Stock>) stockService.findTop10UnupdatedStocks();
	}
}