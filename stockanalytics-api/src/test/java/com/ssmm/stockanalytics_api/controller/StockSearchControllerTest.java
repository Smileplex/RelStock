package com.ssmm.stockanalytics_api.controller;

import com.ssmm.stockanalytics_api.model.Stock;
import com.ssmm.stockanalytics_api.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockSearchController.class)
public class StockSearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void should_return_stockDetail() throws Exception {
        Stock stock = new Stock();
        stock.setName("현대로템");
        stock.setCode("1444");
        given(stockService.find(2917L)).willReturn(stock);

        mockMvc.perform(get("/search/stock/2917").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is("현대로템")))
                .andExpect((ResultMatcher) jsonPath("$.code", is("1444")));
    }

    @Test
    public void should_response_inNoContentStatus() throws Exception{
        given(stockService.find(0L)).willReturn(new Stock());

        mockMvc.perform(get("/search/stock/2917").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(204));
    }
}