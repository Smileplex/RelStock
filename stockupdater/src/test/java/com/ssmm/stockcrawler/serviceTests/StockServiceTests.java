package com.ssmm.stockcrawler.serviceTests;

import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.model.StockResult;
import com.ssmm.stockupdater.service.StockService;
import com.ssmm.stockupdater.service.StockServiceImpl;

import junit.framework.TestCase;

public class StockServiceTests extends TestCase {
	private StockService stockService;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		this.stockService = new StockServiceImpl();
	}

	public void testStockSaving() {
//		String randomName = "random-" + new Random().nextInt() * 10 + 1;
//		Stock stock = new Stock(randomName);
//		stockService.save(stock);

		Stock searchedStock = stockService.findByName("삼성전자");
		System.out.println(searchedStock);
//		assertEquals(stock.getName(), searchedStock.getName());
	}

	public void testDuplicatedSavingBlock() {
		String randomName = "random-" + new Random().nextInt() * 10 + 1;
		Stock stock = new Stock(randomName);
	}
	
	public void testGetTop500UndatedStocks() {
		Iterable<Stock> stocks = stockService.findTop10UnupdatedStocks();
		System.out.println(stocks);
	}
	
	public void testConvertingStockResultToStock() {
		String stockJson = "{\"end\":\"1\",\"marketSum\":\"12829988\",\"risefall\":\"5\",\"tradeStopYn\":\"N\",\"openVal\":\"80900\",\"volumeTrade\":\"737467\",\"prevClose\":\"81000\",\"highVal\":\"81200\",\"lowVal\":\"78100\",\"frgnAcqRatio\":\"35.15\",\"faceVal\":\"5000\",\"high52week\":\"114500\",\"low52week\":\"66300\",\"meetingDate\":\"20180316\",\"debtRatio\":\"180.918\",\"dividend\":\"400\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			StockResult stock = mapper.readValue(stockJson, StockResult.class);
			System.out.println(stock.toString());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
