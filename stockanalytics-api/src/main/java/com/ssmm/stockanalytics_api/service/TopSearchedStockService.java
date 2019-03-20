package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.TopSearchedStock;

import java.util.Collection;
import java.util.Iterator;

public interface TopSearchedStockService extends Service<TopSearchedStock> {
    TopSearchedStock findByName(String name);
    Iterable<TopSearchedStock> findAllOrderByUpdatedDateAndRank();

}
