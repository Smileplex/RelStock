package com.ssmm.stockupdater.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockupdater.helper.Helper;
import com.ssmm.stockupdater.model.StockValue;
import com.ssmm.stockupdater.parser.model.Detail;
import com.ssmm.stockupdater.parser.model.EmptyDetail;

public class NaverStockParser implements PageDetailParser {
	public static final String STOCK_VALUE_URL = "https://polling.finance.naver.com/api/realtime.nhn?query=SERVICE_ITEM:%s";
	private final PageReader pageReader;
	private final ObjectMapper objectMapper;

	@Inject
	public NaverStockParser(PageReader pageReader, ObjectMapper objectMapper) {
		this.pageReader = pageReader;
		this.objectMapper = objectMapper;
	}

	@Override
	public Detail parse(String code) {
		// TODO Auto-generated method stub
		StockValue stockValue = null;
		try {
			stockValue = objectMapper.readValue(getJsonStock(code), StockValue.class);
			return new Detail(objectMapper.writeValueAsString(stockValue));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EmptyDetail(stockValue.getNm());

	}

	private String getJsonStock(String code) {
		Document rawResult = pageReader.read(String.format(STOCK_VALUE_URL, code));
		System.out.println(code);
		System.out.println(rawResult);
		return Helper.cutStringInRange(rawResult.toString(), "{\"datas\":[", "]");
	}

}
