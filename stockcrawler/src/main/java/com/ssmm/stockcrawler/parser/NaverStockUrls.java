package com.ssmm.stockcrawler.parser;

public class NaverStockUrls {
	public static final String SEARCH_QUERY = "https://m.search.naver.com/search.naver";
//	public static final String STOCK_REQUEST_URL = "https://finance.naver.com/api/item/itemJson.nhn?_callback=window.__jindo_callback._2171&code=%s&symbol=%s";
	public static final String STOCK_REQUEST_URL = "https://finance.naver.com/api/item/itemJson.nhn?_callback=window.__jindo2_callback._7589&code=%s";
	public static final String STOCK_ARTICLE_URL = "https://finance.naver.com/item/news_news.nhn?code=%s";
	public static final String STOCK_VALUE_URL = "https://polling.finance.naver.com/api/realtime.nhn?_callback=window.__jindo_callback._416&query=SERVICE_ITEM:%s";
}
