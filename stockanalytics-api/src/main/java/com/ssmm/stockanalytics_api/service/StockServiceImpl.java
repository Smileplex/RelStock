package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.Stock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockServiceImpl extends GenericService<Stock> implements StockService {

    public Stock findByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return session.queryForObject(getEntityType(), "match (n:Stock{name:{name}}) return n", params);
    }

    @Override
    public Class<Stock> getEntityType() {
        return Stock.class;
    }

    @Override
    public Stock findByCode(int code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        return session.queryForObject(getEntityType(), "match (n:Stock{code:{code}}) return n", params);

    }
}
