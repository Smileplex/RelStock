package com.ssmm.stockcrawler.serviceTests;

import java.sql.Timestamp;
import java.util.Date;

import com.ssmm.stockcrawler.model.Stock;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.service.StockKeywordService;
import com.ssmm.stockcrawler.service.StockKeywordServiceImpl;
import com.ssmm.stockcrawler.service.StockService;
import com.ssmm.stockcrawler.service.StockServiceImpl;

import junit.framework.TestCase;

public class StockKeywordServiceTests extends TestCase {
	private StockKeywordService stockKeywordService;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		this.stockKeywordService = new StockKeywordServiceImpl();
	}

	public void testStockKeywordHasStock() {
		StockKeyword stockKeyword = new StockKeyword();
		stockKeyword.setName("LG전자");
		stockKeyword.setLink(
				"https://m.search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=lg%EC%A0%84%EC%9E%90&where=m");
		stockKeyword.setType("self");
		stockKeyword.setAgentId(0);
		stockKeyword.setStatus(1);
		stockKeyword.setDateCreated(new Date());
		stockKeyword.setDateUpdated(new Date());
		
		Stock stock = new Stock("LG전자");
		stockKeyword.setStock(stock);
		stockKeywordService.save(stockKeyword);
	}
	
	public void testStockKeywordRelatedToOthers() {
		StockKeyword stockKeyword1 = stockKeywordService.findByName("LG전자");
		if(stockKeyword1==null) {
			stockKeyword1 = new StockKeyword();
			stockKeyword1.setName("LG전자");
			stockKeyword1.setLink(
					"https://m.search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=lg%EC%A0%84%EC%9E%90&where=m");
			stockKeyword1.setType("self");
			stockKeyword1.setAgentId(0);
			stockKeyword1.setStatus(1);
			stockKeyword1.setDateCreated(new Date());
			stockKeyword1.setDateUpdated(new Date());
		}
		
		StockKeyword stockKeyword2 = new StockKeyword();
		stockKeyword2.setName("삼성전자");
		stockKeyword2.setLink(
				"https://m.search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=lg%EC%A0%84%EC%9E%90&where=m");
		stockKeyword2.setType("self");
		stockKeyword2.setAgentId(0);
		stockKeyword2.setStatus(1);
		stockKeyword2.setDateCreated(new Date());
		stockKeyword2.setDateUpdated(new Date());
		
		stockKeyword1.addStockKeyword(stockKeyword2);
		stockKeywordService.save(stockKeyword1);
		
	}
	

}
