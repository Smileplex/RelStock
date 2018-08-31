package com.ssmm.stockcrawler.service;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.model.Stock;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.Detail;

public class StockGeneratorImpl implements DetailGenerator {
	private StockService stockService;
	private StockKeywordService stockKeywordService;
	private ObjectMapper objectMapper;

	@Inject
	public StockGeneratorImpl(StockService stockService, StockKeywordService stockKeywordService,
			ObjectMapper objectMapper) {
		this.stockService = stockService;
		this.stockKeywordService = stockKeywordService;
		// TODO Auto-generated constructor stub
		this.objectMapper = objectMapper;
	}

	@Override
	public Long generate(Detail detail, DetailLink detailLink) {
		// TODO Auto-generated method stub
		try {
			StockResult stockResult = objectMapper.readValue(detail.getResult(), StockResult.class);
			if (Objects.nonNull(stockResult)) {
				Stock searchedStock = stockService.findByName(stockResult.getName());
				if (Objects.isNull(searchedStock)) {
					Stock stock = convertFromResult(stockResult);
					stockKeywordService.addHasRelation(detailLink.getParentId(), stock);

					String.format("(Stock : %s) saved", stock.getName());
					return stockService.save(stock).getId();
				}
				return searchedStock.getId();
			}
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
		return 0L;
	}

	// {"name":"LG전자","code":"066570","end":0,"marketSum":12175397,"risefall":5,"tradeStopYn":"N","openVal":74400,"volumeTrade":699292,"prevClose":74700,"highVal":75200,"lowVal":73700,"frgnAcqRatio":32.66,"faceVal":5000,"high52week":114500,"low52week":70800,"meetingDate":"20180316","debtRatio":180.918,"dividend":400}
	private Stock convertFromResult(StockResult stockResult) {
		// TODO Auto-generated method stub
		Stock stock = new Stock();
		stock.setName(stockResult.getName());
		stock.setCode(stockResult.getCode());
		stock.setPricePrev(stockResult.getPrevClose());
		stock.setPrice(stockResult.getNowVal());
		stock.setPriceMax(stockResult.getHighVal());
		stock.setPriceMin(stockResult.getLowVal());
		stock.setRiseFall(stockResult.getRisefall());
		stock.setFluct(stockResult.getFluct());
		stock.setFluctRate(stockResult.getFluctRate());
		stock.setCreatedDate(new Date());
		stock.setUpdatedDate(new Date());
		return stock;
	}
}
