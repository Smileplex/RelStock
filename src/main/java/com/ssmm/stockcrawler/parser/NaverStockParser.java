package com.ssmm.stockcrawler.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.DetailInfo;

public class NaverStockParser implements DetailPageParser {
	public static final String STOCK_REQUEST_URL = "https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=%s";
	private final PageReader pageReader;
	private final ObjectMapper objectMapper;

	public NaverStockParser(PageReader pageReader) {
		this.pageReader = pageReader;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public DetailInfo parse(Document pageHtml) {
		// TODO Auto-generated method stub
		String stockName = getStockName(pageHtml);
		String stockCode = getStockCode(pageHtml);

		try {
			Document rawResult = pageReader.read(String.format(STOCK_REQUEST_URL, stockCode));
			StockResult stockResult = objectMapper.readValue(getJsonStock(rawResult), StockResult.class);
//			stockResult.setStockName(stockName);
//			stockResult.setStockCode(stockCode);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
//			logger.warning("거래정지 종목!");
		}
		return null;
	}

	private String getJsonStock(Document rawResult) {
		return Helper.cutStringInRange(rawResult.toString(), "{\"result\":", ",\"resultCode\"");
	}

	private String getStockName(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemName : \"", "\"");
	}

	private String getStockCode(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
	}
}
