package com.ssmm.stockcrawler.parser;

public class NaverStockUrls {
	public static final String STOCK_REQUEST_URL = "https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=%s";
	public static final String STOCK_VALUE_URL = "https://polling.finance.naver.com/api/realtime.nhn?_callback=window.__jindo_callback._416&query=SERVICE_ITEM:%s";
}
