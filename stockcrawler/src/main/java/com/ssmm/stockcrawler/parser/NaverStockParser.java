package com.ssmm.stockcrawler.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.model.Article;
import com.ssmm.stockcrawler.model.StockResult;
import com.ssmm.stockcrawler.parser.model.Detail;
import com.ssmm.stockcrawler.parser.model.EmptyDetail;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NaverStockParser implements PageDetailParser {
    private final PageReader pageReader;
    private final ObjectMapper objectMapper;
    private StockPriceValues stockPriceValues;

    @Inject
    public NaverStockParser(PageReader pageReader, ObjectMapper objectMapper, StockPriceValues stockPriceValues) {
        this.pageReader = pageReader;
        this.objectMapper = objectMapper;
        this.stockPriceValues = stockPriceValues;
    }

    @Override
    public Detail parse(Document pageHtml) {
        // TODO Auto-generated method stub
        try {
            StockResult stockResult = objectMapper.readValue(getJsonStock(pageHtml), StockResult.class);
            stockResult.setName(getStockName(pageHtml));
            stockResult.setCode(getStockCode(pageHtml));
            stockResult.setArticles(getStockArticles(pageHtml));

            stockPriceValues.setValues(stockResult);
            return new Detail(objectMapper.writeValueAsString(stockResult));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EmptyDetail(getStockName(pageHtml));
    }

    private String getJsonStock(Document pageHtml) {
        Document rawResult = pageReader.read(String.format(NaverStockUrls.STOCK_REQUEST_URL, getStockCode(pageHtml), getStockCode(pageHtml)));
        return Helper.cutStringInRange(rawResult.toString(), "\"result\":", "})");
    }

    private String getStockName(Document document) {
        return Helper.cutStringInRange(document.toString(), "sItemName : \"", "\"");
    }

    private String getStockCode(Document document) {
        return Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
    }

    private List<Article> getStockArticles(Document pageHtml) {
        Document document = pageReader.read(String.format(NaverStockUrls.STOCK_ARTICLE_URL, getStockCode(pageHtml)));
        List<Article> articles = document.select("tbody tr:not(.relation_lst)")
                .stream().map(element -> {
                    String title = element.select("td.title a").text();
                    String link = element.select("td.title a").attr("href");
                    String source = element.select("td.info").text();
                    Date createdDate = null;
                    try {
                        createdDate = new SimpleDateFormat("yyyy.mm.dd hh:mm").parse(document.select("td.date").text());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new Article(title, link, source, createdDate);
                }).collect(Collectors.toList());
        return articles;
    }
}
