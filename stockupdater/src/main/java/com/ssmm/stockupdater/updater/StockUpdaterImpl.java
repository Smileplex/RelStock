package com.ssmm.stockupdater.updater;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.ssmm.stockupdater.model.Stock;
import com.ssmm.stockupdater.parser.PageDetailParser;
import com.ssmm.stockupdater.parser.PageReader;
import com.ssmm.stockupdater.parser.model.Detail;
import com.ssmm.stockupdater.service.StockManager;

public class StockUpdaterImpl implements StockUpdater {
	private PageDetailParser pageDetailParser;
	private StockManager stockManager;

	@Inject
	public StockUpdaterImpl(PageReader pageReader, PageDetailParser pageDetailParser, StockManager stockManager) {
		this.pageDetailParser = pageDetailParser;
		this.stockManager = stockManager;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				List<Stock> stocks = stockManager.findTop10UnupdatedStocks();
				stocks.forEach(a -> update(a.getCode() + ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void update(String code) {
		// TODO Auto-generated method stub
		Detail result = pageDetailParser.parse(code);
		Stock stock = stockManager.update(result);
		if (Objects.isNull(stock)) {
			System.out.println(code + " can not be updated");
			return;
		}
		System.out.println(stock.getName() + " successfully updated");

	}

}