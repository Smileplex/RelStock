package com.ssmm.stockcrawler.parser;

import com.google.inject.Inject;

import java.util.concurrent.TimeUnit;

public class RealtimeParserImpl implements RealtimeParser {
    private TopSearchedStockParser topSearchedStockParser;

    @Inject
    public RealtimeParserImpl(TopSearchedStockParser topSearchedStockParser) {
        this.topSearchedStockParser = topSearchedStockParser;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Currently parsing popular stocks!");
                topSearchedStockParser.parse();
                TimeUnit.MINUTES.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
