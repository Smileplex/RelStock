package com.ssmm.stockcrawler.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.model.Stock;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.Detail;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

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
            if (Objects.isNull(stockResult))
                return 0L;

            Stock searchedStock = stockService.findByName(stockResult.getName());
            if (Objects.isNull(searchedStock)) {
                return createStock(detailLink, stockResult);
            }

            return updateStock(searchedStock, stockResult);

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

    private Long createStock(DetailLink detailLink, StockResult stockResult) {
        Stock stock = convertFromResult(new Stock(), stockResult);
        stockKeywordService.addHasRelation(detailLink.getParentId(), stock);

        System.out.println(String.format("(Stock : %s) saved", stock.getName()));
        return stockService.save(stock).getId();
    }

    private Long updateStock(Stock searchedStock, StockResult stockResult) {
        System.out.println(String.format("(Stock : %s) updated", searchedStock.getName()));
        return stockService.save(convertFromResult(searchedStock, stockResult)).getId();
    }

    private Stock convertFromResult(Stock entity, StockResult stockResult) {
        entity.setName(stockResult.getName());
        entity.setCode(stockResult.getCode());
        entity.setPricePrev(stockResult.getPrevClose());
        entity.setPrice(stockResult.getNowVal());
        entity.setPriceMax(stockResult.getHighVal());
        entity.setPriceMin(stockResult.getLowVal());
        entity.setRiseFall(stockResult.getRisefall());
        entity.setFluct(stockResult.getFluct());
        entity.setFluctRate(stockResult.getFluctRate());
        entity.setMarketSum(stockResult.getMarketSum());
        entity.setFaceVal(stockResult.getFaceVal());
        entity.setFrgnAcqRatio(stockResult.getFrgnAcqRatio());
        entity.setVolumeTrade(stockResult.getVolumeTrade());
        entity.setVolumeTradeAmount(stockResult.getVolumeTradeAmount());

        if (Objects.isNull(entity.getCreatedDate())) {
            entity.setCreatedDate(new Date());
        }
        entity.setUpdatedDate(new Date());
        entity.setDailyChart(stockResult.getDailyChartUrl());
        entity.setWeeklyChart(stockResult.getWeeklyChartUrl());
        entity.setMonthlyChart(stockResult.getMonthlyChartUrl());
        try {
            entity.setArticles(objectMapper.writeValueAsString(stockResult.getArticles()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
