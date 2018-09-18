package com.ssmm.stockanalytics_api.controller;


import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.service.StockKeywordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockKeywordSearchController.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockKeywordService stockKeywordService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_return_stockDetail() throws Exception {
        StockKeyword stockKeyword = new StockKeyword();
        stockKeyword.setName("test");

        given(stockKeywordService.find(0L)).willReturn(stockKeyword);

        mvc.perform(get("/search/stock/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(stockKeyword.getName())));
    }
}