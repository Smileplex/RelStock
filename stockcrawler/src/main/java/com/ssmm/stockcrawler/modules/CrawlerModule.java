package com.ssmm.stockcrawler.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.internal.SingletonScope;
import com.ssmm.stockcrawler.AppSettings;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.parser.*;
import com.ssmm.stockcrawler.service.DetailGenerator;
import com.ssmm.stockcrawler.service.KeywordGenerator;
import com.ssmm.stockcrawler.service.StockGeneratorImpl;
import com.ssmm.stockcrawler.service.StockKeywordGeneratorImpl;
import com.ssmm.stockcrawler.service.StockKeywordService;
import com.ssmm.stockcrawler.service.StockKeywordServiceImpl;
import com.ssmm.stockcrawler.service.StockService;
import com.ssmm.stockcrawler.service.StockServiceImpl;

public class CrawlerModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ExecutorService.class).toInstance(Executors.newFixedThreadPool(AppSettings.THREAD_WORKERS));
		bind(KeywordParser.class).to(KeywordParserImpl.class);
		bind(DetailParser.class).to(DetailParserImpl.class);
		
		bind(PageReader.class).to(PageReaderImpl.class);
		bind(PageParser.class).to(NaverStockKeywordParser.class);
		bind(PageDetailParser.class).to(NaverStockParser.class);

		bind(StockPriceValues.class).to(StockPriceValuesImpl.class);
		bind(StockKeywordService.class).to(StockKeywordServiceImpl.class);
		bind(StockService.class).to(StockServiceImpl.class);
		bind(KeywordGenerator.class).to(StockKeywordGeneratorImpl.class);
		bind(DetailGenerator.class).to(StockGeneratorImpl.class);
		
		bind(KeywordLinkQueue.class).toInstance(new KeywordLinkQueue());
		bind(DetailLinkQueue.class).toInstance(new DetailLinkQueue());
		
		bind(ObjectMapper.class).toInstance(new ObjectMapper());

	}
}
