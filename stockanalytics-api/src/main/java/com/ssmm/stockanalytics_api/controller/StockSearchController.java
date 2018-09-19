package com.ssmm.stockanalytics_api.controller;

import com.ssmm.stockanalytics_api.api.ApiConstants;
import com.ssmm.stockanalytics_api.model.Stock;
import com.ssmm.stockanalytics_api.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(ApiConstants.SEARCH_ENDPOINT)
public class StockSearchController {
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
    public ResponseEntity<Stock> findStockById(@PathVariable("id") Long id) {
        Stock stock = stockService.find(id);

        if (Objects.isNull(stock))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
