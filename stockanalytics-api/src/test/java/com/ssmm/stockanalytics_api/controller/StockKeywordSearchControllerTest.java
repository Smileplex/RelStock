package com.ssmm.stockanalytics_api.controller;

import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.service.StockKeywordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockKeywordSearchController.class)
public class StockKeywordSearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockKeywordService stockKeywordService;

    @Test
    public void should_return_stockKeywordDetail() throws Exception {
        StockKeyword stockKeyword = new StockKeyword();
        stockKeyword.setName("삼성전자 관련주");
        stockKeyword.setAgentId(0);
        stockKeyword.setType(1);

        given(stockKeywordService.find(440L)).willReturn(stockKeyword);

        mockMvc.perform(get("/search/stockkeyword/440").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is("삼성전자 관련주")))
                .andExpect((ResultMatcher) jsonPath("$.agentId", is(0)));
    }

    @Test
    public void should_response_inNoContentStatus() throws Exception{
        given(stockKeywordService.find(0L)).willReturn(new StockKeyword());
        mockMvc.perform(get("/search/stockkeyword/2917").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(204));
    }
}