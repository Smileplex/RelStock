package com.ssmm.stockcrawler.service;

import java.util.HashMap;
import java.util.Map;

import com.ssmm.stockcrawler.model.StockKeyword;

public class StockKeywordServiceImpl extends GenericService<StockKeyword> implements StockKeywordService {

	@Override
	public StockKeyword findByName(String name) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		return session.queryForObject(getEntityType(), "match (n:StockKeyword{name:{name}}) return n", params);
	}

	@Override
	public Class<StockKeyword> getEntityType() {
		// TODO Auto-generated method stub
		return StockKeyword.class;
	}
}