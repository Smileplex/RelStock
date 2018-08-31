package com.ssmm.stockcrawler.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.Detail;
import com.ssmm.stockcrawler.parser.model.EmptyDetail;

public class NaverStockParser implements PageDetailParser {
	private final PageReader pageReader;
	private final ObjectMapper objectMapper;

	@Inject
	public NaverStockParser(PageReader pageReader, ObjectMapper objectMapper) {
		this.pageReader = pageReader;
		this.objectMapper = objectMapper;
	}

	@Override
	public Detail parse(Document pageHtml) {
		// TODO Auto-generated method stub
		StockResult stockResult;
		try {
			stockResult = objectMapper.readValue(getJsonStock(pageHtml), StockResult.class);
			stockResult.setName(getStockName(pageHtml));
			stockResult.setCode(getStockCode(pageHtml));

			setStockPriceValues(stockResult);
			return new Detail(objectMapper.writeValueAsString(stockResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new EmptyDetail(getStockName(pageHtml));
	}

	private String getJsonStock(Document pageHtml) {
		Document rawResult = pageReader.read(String.format(NaverStockUrls.STOCK_REQUEST_URL, getStockCode(pageHtml)));
		return Helper.cutStringInRange(rawResult.toString(), "{\"result\":", ",\"resultCode\"");
	}

	private String getStockName(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemName : \"", "\"");
	}

	private String getStockCode(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
	}

	private void setStockPriceValues(StockResult stockResult) {
		// TODO Auto-generated method stub
		Document rawResult = pageReader.read(String.format(NaverStockUrls.STOCK_VALUE_URL, stockResult.getCode()));
		stockResult.setNowVal(getStockNowVal(rawResult.toString()));
		stockResult.setFluct(getStockFluct(rawResult.toString()));
		stockResult.setFluctRate(getStockFluctRate(rawResult.toString()));
	}

	private int getStockNowVal(String rawResult) {
		// TODO Auto-generated method stub
		int nowVal = 0;
		try {
			String cutStringInRange = Helper.cutStringInRange(rawResult, "\"nv\":", ",");
			if (!Strings.isNullOrEmpty(cutStringInRange))
				nowVal = Integer.parseInt(cutStringInRange);
		} catch (Exception e) {
			System.out.println("Can not parse current value : " + nowVal);
		}
		return nowVal;
	}

	private int getStockFluct(String rawResult) {
		// TODO Auto-generated method stub
		int fluct = 0;
		try {
			String cutStringInRange = Helper.cutStringInRange(rawResult, "\"cv\":", ",");
			if (!Strings.isNullOrEmpty(cutStringInRange))
				fluct = Integer.parseInt(cutStringInRange);
		} catch (Exception e) {
			System.out.println("Can not parse fluct : " + fluct);
		}
		return fluct;
	}

	private Double getStockFluctRate(String rawResult) {
		// TODO Auto-generated method stub
		double fluctRate = 0.0;
		try {
			String cutStringInRange = Helper.cutStringInRange(rawResult, "\"cr\":", ",");
			if (!Strings.isNullOrEmpty(cutStringInRange))
				fluctRate = Double.parseDouble(cutStringInRange);
		} catch (Exception e) {
			System.out.println("Can not parse fluct rate : " + fluctRate);
		}
		return fluctRate;
	}
}
