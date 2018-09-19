package com.ssmm.stockanalytics_api.controller;


import com.ssmm.stockanalytics_api.api.ApiConstants;
import com.ssmm.stockanalytics_api.model.Stock;
import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.resource.D3SearchResource;
import com.ssmm.stockanalytics_api.service.StockKeywordService;
import com.ssmm.stockanalytics_api.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(ApiConstants.SEARCH_ENDPOINT)
public class StockKeywordSearchController {
    @Autowired
    private StockKeywordService stockKeywordService;

    @Autowired
    public StockKeywordSearchController(StockKeywordService stockKeywordService) {
        this.stockKeywordService = stockKeywordService;
    }

    @RequestMapping(value = "/stockkeyword/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockKeyword> findStockKeywordById(@PathVariable("id") Long id) {
        StockKeyword stockKeyword = stockKeywordService.find(id);
        if (Objects.isNull(stockKeyword))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(stockKeyword, HttpStatus.OK);
    }

    @RequestMapping(value = "/stockkeyword/{name}/2depth", method = RequestMethod.GET)
    public ResponseEntity<D3SearchResource> findAllStockKeywordsWithin2Depth(@PathVariable("name") String name) {
        List<StockKeyword> stockKeywords = (List<StockKeyword>) stockKeywordService.findAllWithin2DepthByName(name);
        try {
            return new ResponseEntity<>(new D3SearchResource(stockKeywords), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}