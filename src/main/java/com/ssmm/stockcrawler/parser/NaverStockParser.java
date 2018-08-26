package com.ssmm.stockcrawler.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.Detail;

public class NaverStockParser implements PageDetailParser {
	public static final String STOCK_REQUEST_URL = "https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=%s";
	private final PageReader pageReader;
	private final ObjectMapper objectMapper;

	@Inject
	public NaverStockParser(PageReader pageReader) {
		this.pageReader = pageReader;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Detail parse(Document pageHtml) {
		// TODO Auto-generated method stub
		try {
			Document rawResult = pageReader.read(String.format(STOCK_REQUEST_URL, getStockCode(pageHtml)));
			StockResult stockResult = objectMapper.readValue(getJsonStock(rawResult), StockResult.class);
			stockResult.setName(getStockName(pageHtml));
			stockResult.setCode(getStockCode(pageHtml));
			return new Detail(objectMapper.writeValueAsString(stockResult));
			
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
