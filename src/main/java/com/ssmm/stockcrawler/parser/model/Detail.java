package com.ssmm.stockcrawler.parser.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockcrawler.model.StockResult;

public class Detail {
	private String detailJson;

	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(StockResult stockResult) {
		// TODO Auto-generated constructor stub
		try {
			this.detailJson = new ObjectMapper().writeValueAsString(stockResult);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
