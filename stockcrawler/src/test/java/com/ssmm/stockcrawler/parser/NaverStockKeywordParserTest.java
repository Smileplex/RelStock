package com.ssmm.stockcrawler.parser;

import com.ssmm.stockcrawler.parser.agent.NaverStockKeywordParser;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;


public class NaverStockKeywordParserTest {

    private Document pageHtml;
    private PageParser pageParser;

    @Before
    public void setUp() throws Exception {
        pageHtml = new PageReaderImpl().read("https://m.search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=lg%EB%94%94%EC%8A%A4%ED%94%8C%EB%A0%88%EC%9D%B4&where=m");
        pageParser = new NaverStockKeywordParser();

    }

    @Test
    public void should_return_articleNameAndUrl() {


    }
}