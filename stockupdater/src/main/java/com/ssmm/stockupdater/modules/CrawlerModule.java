package com.ssmm.stockupdater.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.ssmm.stockupdater.AppSettings;
import com.ssmm.stockupdater.parser.NaverStockParser;
import com.ssmm.stockupdater.parser.PageDetailParser;
import com.ssmm.stockupdater.parser.PageReader;
import com.ssmm.stockupdater.parser.PageReaderImpl;
import com.ssmm.stockupdater.service.StockManager;
import com.ssmm.stockupdater.service.StockManagerImpl;
import com.ssmm.stockupdater.service.StockService;
import com.ssmm.stockupdater.service.StockServiceImpl;
import com.ssmm.stockupdater.updater.StockUpdater;
import com.ssmm.stockupdater.updater.StockUpdaterImpl;

public class CrawlerModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ExecutorService.class).toInstance(Executors.newFixedThreadPool(AppSettings.THREAD_WORKERS));

		bind(PageReader.class).to(PageReaderImpl.class);
		bind(PageDetailParser.class).to(NaverStockParser.class);
		bind(StockService.class).to(StockServiceImpl.class);
		bind(StockUpdater.class).to(StockUpdaterImpl.class);

		bind(StockManager.class).to(StockManagerImpl.class);
		bind(ObjectMapper.class).toInstance(new ObjectMapper());
	}
}
