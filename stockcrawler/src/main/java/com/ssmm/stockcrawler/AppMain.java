package com.ssmm.stockcrawler;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssmm.stockcrawler.controller.CrawlerController;
import com.ssmm.stockcrawler.modules.CrawlerModule;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new CrawlerModule());
		CrawlerController crawlerController = injector.getInstance(CrawlerController.class);
		crawlerController.start();
	}
}
