package com.ssmm.stockcrawler.parser;

import org.jsoup.nodes.Document;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.model.StockResult;

public class StockPriceValuesImpl implements StockPriceValues {
	private PageReader pageReader;

	@Inject
	public StockPriceValuesImpl(PageReader pageReader) {
		this.pageReader = pageReader;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setValues(StockResult stockResult) {
		// TODO Auto-generated method stub
		Document rawResult = readValueJson(stockResult);
		stockResult.setNowVal(getStockNowVal(rawResult.toString()));
		stockResult.setFluct(getStockFluct(rawResult.toString()));
		stockResult.setFluctRate(getStockFluctRate(rawResult.toString()));
		stockResult.setVolumeTrade(getVolumeTrade(rawResult.toString()));
		stockResult.setVolumeTradeAmount(getVolumeTradeAmount(rawResult.toString()));
	}

	private Document readValueJson(StockResult stockResult) {
		return pageReader.read(String.format(NaverStockUrls.STOCK_VALUE_URL, stockResult.getCode()));
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

	private int getVolumeTrade(String rawResult) {
		int volumeTrade = 0;
		try {
			String cutStringInRange = Helper.cutStringInRange(rawResult, "\"aq\":", ",");
			if (!Strings.isNullOrEmpty(cutStringInRange))
				volumeTrade = Integer.parseInt(cutStringInRange);
		} catch (Exception e) {
			System.out.println("Can not parse volumeTrade : " + volumeTrade);
		}
		return volumeTrade;
	}

	private Long getVolumeTradeAmount(String rawResult) {
		long volumeTradeAmount = 0;
		try {
			String cutStringInRange = Helper.cutStringInRange(rawResult, "\"aa\":", ",");
			if (!Strings.isNullOrEmpty(cutStringInRange))
				volumeTradeAmount = Long.valueOf(cutStringInRange);
		} catch (Exception e) {
			System.out.println("Can not parse volumeTradeAmount : " + volumeTradeAmount);
		}
		return volumeTradeAmount;
	}


}