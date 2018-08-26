package com.ssmm.stockcrawler.service;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.parser.model.Detail;

public class StockDetailGeneratorImpl implements DetailGenerator {
	private StockService stockService;

	@Inject
	public StockDetailGeneratorImpl(StockService stockService) {
		this.stockService = stockService;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long generate(Detail detail, DetailLink detailLink) {
		// TODO Auto-generated method stub
		System.out.println(detail.getDetailJson());
		return null;
	}

}
