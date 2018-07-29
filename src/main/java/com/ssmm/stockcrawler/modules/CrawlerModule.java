package com.ssmm.stockcrawler.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.internal.SingletonScope;
import com.ssmm.stockcrawler.AppSettings;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.parser.DetailParser;
import com.ssmm.stockcrawler.parser.KeywordParser;
import com.ssmm.stockcrawler.parser.DetailParserImpl;
import com.ssmm.stockcrawler.parser.KeywordParserImpl;
import com.ssmm.stockcrawler.parser.NaverStockKeywordParser;
import com.ssmm.stockcrawler.parser.PageReader;
import com.ssmm.stockcrawler.parser.PageReaderImpl;
import com.ssmm.stockcrawler.parser.PageParser;
import com.ssmm.stockcrawler.service.KeywordGenerator;
import com.ssmm.stockcrawler.service.StockKeywordGeneratorImpl;
import com.ssmm.stockcrawler.service.StockKeywordService;
import com.ssmm.stockcrawler.service.StockKeywordServiceImpl;

public class CrawlerModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ExecutorService.class).toInstance(Executors.newFixedThreadPool(AppSettings.THREAD_WORKERS));
		bind(KeywordParser.class).to(KeywordParserImpl.class);
		bind(DetailParser.class).to(DetailParserImpl.class);
		bind(PageReader.class).to(PageReaderImpl.class);
		bind(PageParser.class).to(NaverStockKeywordParser.class);
		bind(StockKeywordService.class).to(StockKeywordServiceImpl.class);
		bind(KeywordGenerator.class).to(StockKeywordGeneratorImpl.class);
		bind(KeywordLinkQueue.class).toInstance(new KeywordLinkQueue());
		bind(DetailLinkQueue.class).toInstance(new DetailLinkQueue());
	}
}
