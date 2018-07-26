package com.ssmm.stockcrawler.service;

import java.util.Date;
import java.util.Objects;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public class StockKeywordGeneratorImpl implements StockKeywordGenerator {
	private StockKeywordService stockKeywordService;

	@Inject
	public StockKeywordGeneratorImpl(StockKeywordService stockKeywordService) {
		this.stockKeywordService = stockKeywordService;
		// TODO Auto-generated constructor stub
	}

	@Override
	public StockKeyword generate(KeywordInfo keywordInfo, KeywordLink keywordLink) {
		// TODO Auto-generated method stub
		StockKeyword searchedKeyword = stockKeywordService.findByName(keywordInfo.getKeywordName());
		if (Objects.nonNull(searchedKeyword)) {
			searchedKeyword.setUpdatedDate(new Date());
			return stockKeywordService.save(searchedKeyword);
		}

		StockKeyword entity = new StockKeyword();
		entity.setAgentId(keywordLink.getAgentId());
		entity.setName(keywordInfo.getKeywordName());
		entity.setLink(keywordLink.getLink());
		entity.setCreatedDate(new Date());
		entity.setUpdatedDate(new Date());
		entity.setType(keywordInfo.getKeywordType());
		entity.setStatus(1);

		StockKeyword relatedKeyword = stockKeywordService.find(keywordLink.getParentId());
		if (Objects.nonNull(relatedKeyword))
			entity.addStockKeyword(relatedKeyword);
		return stockKeywordService.save(entity);
	}
}
