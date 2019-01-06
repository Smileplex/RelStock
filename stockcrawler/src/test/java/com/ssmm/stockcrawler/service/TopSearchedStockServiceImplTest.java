package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.TopSearchedStock;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TopSearchedStockServiceImplTest
{
    private TopSearchedStockService topSearchedStockService;

    @Before
    public void setUp() throws Exception {
        topSearchedStockService = new TopSearchedStockServiceImpl();
    }

    @Test
    public void getTopSearchedStocks() {
        topSearchedStockService.findAllOrderByUpdatedDateAndRank().forEach(a-> System.out.println(a.getName()));
    }

    @Test
    public void saveSearchedStock() {
        TopSearchedStock topSearchedStock = new TopSearchedStock(1,"곰땡이", "0.89", "71300", "2100","2.86",5);
        topSearchedStock.setUpdatedDate(new Date());
        topSearchedStockService.save(topSearchedStock);
    }

}