package com.ssmm.stockanalytics_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.model.StockResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockKeywordServiceTest {

    @Autowired
    private StockKeywordService stockKeywordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_stockKeyword_and_its_relations_within_2detph() {
        List<StockKeyword> stockKeywords = (List<StockKeyword>) stockKeywordService.findAllWithin2DepthByName("삼성전자");
        stockKeywords.forEach(System.out::println);
    }

    @Test
    public void should_return_stockKeywordDetail_whenIdGiven() {
        StockKeyword stockKeyword = stockKeywordService.find(4995L);
        assertEquals("카카오뱅크", stockKeyword.getName());
    }

    @Test
    public void should_return_findAllWithin2Depth() {
        StockResult stockResult = stockKeywordService.findAllWithin2Depth("lg전자");
        System.out.println(stockResult.getStocks());
    }
}
