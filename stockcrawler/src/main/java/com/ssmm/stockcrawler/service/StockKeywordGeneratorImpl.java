package com.ssmm.stockcrawler.service;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public class StockKeywordGeneratorImpl implements KeywordGenerator {
	private StockKeywordService stockKeywordService;
	private ObjectMapper objectMapper;

	@Inject
	public StockKeywordGeneratorImpl(StockKeywordService stockKeywordService) {
		this.stockKeywordService = stockKeywordService;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Long generate(KeywordInfo keywordInfo, KeywordLink keywordLink) {
		// TODO Auto-generated method stub
		StockKeyword searchedKeyword = stockKeywordService.findByName(keywordInfo.getKeywordName());
		if (Objects.isNull(searchedKeyword)) {
			return createStockKeyword(keywordInfo, keywordLink);
		}
		return updateStockKeyword(searchedKeyword, keywordInfo);
	}

	private Long createStockKeyword(KeywordInfo keywordInfo, KeywordLink keywordLink) {
		StockKeyword entity = new StockKeyword();
		entity.setAgentId(keywordLink.getAgentId());
		entity.setName(keywordInfo.getKeywordName());
		entity.setLink(keywordLink.getLink());
		entity.setCreatedDate(new Date());
		entity.setUpdatedDate(new Date());
		entity.setType(keywordInfo.getKeywordType());
		setMassMedia(keywordInfo, entity);
		entity.setStatus(1);

		StockKeyword relatedKeyword = stockKeywordService.find(keywordLink.getParentId());
		if (Objects.nonNull(relatedKeyword))
			entity.addStockKeyword(relatedKeyword);

		return stockKeywordService.save(entity).getId();
	}

	private Long updateStockKeyword(StockKeyword searchedKeyword, KeywordInfo keywordInfo) {
		searchedKeyword.setUpdatedDate(new Date());
		setMassMedia(keywordInfo, searchedKeyword);
		return stockKeywordService.save(searchedKeyword).getId();
	}

	private void setMassMedia(KeywordInfo keywordInfo, StockKeyword entity) {
		try {
			entity.setClips(objectMapper.writeValueAsString(keywordInfo.getClips()));
			entity.setArticles(objectMapper.writeValueAsString(keywordInfo.getArticles()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
