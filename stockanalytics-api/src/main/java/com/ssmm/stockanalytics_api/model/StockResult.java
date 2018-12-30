package com.ssmm.stockanalytics_api.model;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
public class StockResult {
    private List<StockKeyword> stockKeywords;
    private List<Stock> stocks;

    public List<StockKeyword> getStockKeywords() {
        return stockKeywords;
    }

    public void setStockKeywords(List<StockKeyword> stockKeywords) {
        this.stockKeywords = stockKeywords;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "StockResult{" +
                "stockKeywords=" + stockKeywords +
                ", stocks=" + stocks +
                '}';
    }
}
