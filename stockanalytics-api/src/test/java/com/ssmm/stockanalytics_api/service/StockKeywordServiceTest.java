package com.ssmm.stockanalytics_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockanalytics_api.model.StockKeyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;


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
        assertEquals("카카오뱅크",stockKeyword.getName());
    }
}
