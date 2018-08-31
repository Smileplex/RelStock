package com.ssmm.stockupdater;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssmm.stockupdater.controller.UpdaterController;
import com.ssmm.stockupdater.modules.CrawlerModule;

public class AppMain {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new CrawlerModule());
		UpdaterController crawlerController = injector.getInstance(UpdaterController.class);
		crawlerController.start();
	}
}
