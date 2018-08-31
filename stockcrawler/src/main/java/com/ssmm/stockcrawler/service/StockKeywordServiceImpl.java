package com.ssmm.stockcrawler.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.neo4j.ogm.transaction.Transaction;

import com.ssmm.stockcrawler.model.Stock;
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
	public StockKeyword save(StockKeyword entity) {
		// TODO Auto-generated method stub
		try (Transaction tx = session.beginTransaction()) {
			session.save(entity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Class<StockKeyword> getEntityType() {
		// TODO Auto-generated method stub
		return StockKeyword.class;
	}

	@Override
	public void addHasRelation(Long id, Stock stock) {
		// TODO Auto-generated method stub
		StockKeyword stockKeyword = find(id);
		if (Objects.nonNull(stockKeyword)) {
			stockKeyword.setStock(stock);
			save(stockKeyword);
		}
	}
}