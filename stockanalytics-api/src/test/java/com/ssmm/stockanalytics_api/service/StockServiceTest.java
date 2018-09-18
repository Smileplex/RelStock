package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Test
    public void should_return_stockDetail_whenIdGiven() {
        Stock stock = stockService.find(5038L);
        assertEquals("068270", stock.getCode());
    }
}