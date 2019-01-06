package com.ssmm.stockcrawler.parser;

import com.ssmm.stockcrawler.parser.agent.NaverTopSearchedStockParser;
import com.ssmm.stockcrawler.service.TopSearchedStockService;
import com.ssmm.stockcrawler.service.TopSearchedStockServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class TopSearchedStockParserImplTest {

    private TopSearchedStockParser parser;
    private TopSearchedStockService topSearchedStockService;

    @Before
    public void setUp(){
        parser = new NaverTopSearchedStockParser(new PageReaderImpl(), new TopSearchedStockServiceImpl());
    }

    @Test
    public void getTopSearchedKeywords(){
        parser.parse();

    }

}