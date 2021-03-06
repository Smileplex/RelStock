package com.ssmm.stockanalytics_api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.service.StockKeywordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockKeywordSearchResourceTest {
    @Autowired
    private StockKeywordService stockKeywordService;

    @Test
    public void shoud_return_convertedResourceForD3Js() {
        List<StockKeyword> stockKeywords = (List<StockKeyword>) stockKeywordService.findAllWithin2DepthByName("삼성전자");
        try {
            D3SearchResource stockKeywordSearchResource = new D3SearchResource(stockKeywords);
            System.out.println(new ObjectMapper().writeValueAsString(stockKeywordSearchResource));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}