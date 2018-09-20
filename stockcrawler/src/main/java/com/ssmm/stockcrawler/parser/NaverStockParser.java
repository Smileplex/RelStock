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
	private StockPriceValues stockPriceValues;

	@Inject
	public NaverStockParser(PageReader pageReader, ObjectMapper objectMapper, StockPriceValues stockPriceValues) {
		this.pageReader = pageReader;
		this.objectMapper = objectMapper;
		this.stockPriceValues = stockPriceValues;
	}

	@Override
	public Detail parse(Document pageHtml) {
		// TODO Auto-generated method stub
		try {
			StockResult stockResult = objectMapper.readValue(getJsonStock(pageHtml), StockResult.class);
			stockResult.setName(getStockName(pageHtml));
			stockResult.setCode(getStockCode(pageHtml));

			stockPriceValues.setValues(stockResult);
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
		Document rawResult = pageReader.read(String.format(NaverStockUrls.STOCK_REQUEST_URL, getStockCode(pageHtml),getStockCode(pageHtml)));
		return Helper.cutStringInRange(rawResult.toString(), "\"result\":", "})");
	}

	private String getStockName(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemName : \"", "\"");
	}

	private String getStockCode(Document document) {
		return Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
	}
}
