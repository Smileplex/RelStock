package com.ssmm.stockcrawler.parser.agent;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.TopSearchedStock;
import com.ssmm.stockcrawler.parser.PageReader;
import com.ssmm.stockcrawler.parser.TopSearchedStockParser;
import com.ssmm.stockcrawler.service.TopSearchedStockService;
import org.jsoup.nodes.Document;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class NaverTopSearchedStockParser implements TopSearchedStockParser {
    private final String baseUrl = "https://finance.naver.com/sise/lastsearch2.nhn";
    private PageReader pageReader;
    private TopSearchedStockService topSearchedStockService;


    @Inject
    public NaverTopSearchedStockParser(PageReader pageReader, TopSearchedStockService topSearchedStockService) {
        this.pageReader = pageReader;
        this.topSearchedStockService = topSearchedStockService;
    }

    @Override
    public void parse() {
        Document document = pageReader.read(baseUrl);
        generateTopSearchedStocks(parseTopSearchedStocks(document));
    }

    private void generateTopSearchedStocks(List<TopSearchedStock> topSearchedStocks) {
        topSearchedStocks.forEach(element->{
            TopSearchedStock searchedStock = topSearchedStockService.findByName(element.getName());
            if(Objects.isNull(searchedStock)){
                element.setUpdatedDate(new Date());
                topSearchedStockService.save(element);
                return;
            }

            searchedStock.setRank(element.getRank());
            searchedStock.setUpdatedDate(new Date());
            topSearchedStockService.save(searchedStock);
        });
    }

    private List<TopSearchedStock> parseTopSearchedStocks(Document document) {
        return document.select("table.type_5 tbody tr:not(.type1)").stream().skip(1)
                .map(elem -> elem.select("td"))
                .filter(elem -> !Strings.isNullOrEmpty(elem.get(0).text()))
                .map(elem -> new TopSearchedStock(
                        Integer.valueOf(elem.get(0).text()),
                        elem.get(1).text(),
                        elem.get(2).text(),
                        elem.get(3).text(),
                        elem.get(4).text(),
                        elem.get(5).text(),
                        0
                )).collect(Collectors.toList());
    }
}
