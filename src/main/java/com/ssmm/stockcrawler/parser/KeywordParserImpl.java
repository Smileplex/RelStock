package com.ssmm.stockcrawler.parser;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.EmptyParsingResult;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.parser.model.ParsingResult;
import com.ssmm.stockcrawler.service.StockKeywordGenerator;
import com.ssmm.stockcrawler.service.StockKeywordService;

public class KeywordParserImpl implements KeywordParser {
	private KeywordLinkQueue linkQueue;
	private PageReader pageReader;
	private StockKeywordParser stockKeywordParser;
	private StockKeywordGenerator stockKeywordGenerator;

	@Inject
	public KeywordParserImpl(KeywordLinkQueue linkQueue, PageReader pageReader, StockKeywordParser stockKeywordParser,
			StockKeywordGenerator stockKeywordGenerator) {
		this.linkQueue = linkQueue;
		this.pageReader = pageReader;
		this.stockKeywordParser = stockKeywordParser;
		this.stockKeywordGenerator = stockKeywordGenerator;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			parse(linkQueue.get());
		}
	}

	public void parse(KeywordLink keywordLink) {
		// TODO Auto-generated method stub
		Document document = pageReader.read(keywordLink.getLink());
		KeywordInfo keywordInfo = stockKeywordParser.parse(document);
		if (keywordInfo instanceof EmptyKeywordInfo)
			return;

		StockKeyword generatedKeyword = stockKeywordGenerator.create(keywordInfo, keywordLink);
		pushKeywordLinkQueue(keywordLink, keywordInfo, generatedKeyword);

		System.out.println("(" + Thread.currentThread().getName() + ") " + keywordInfo.getKeywordName());
	}

	private void pushKeywordLinkQueue(KeywordLink keywordLink, KeywordInfo keywordInfo, StockKeyword generatedKeyword) {
		for (String link : keywordInfo.getRelatedKeywordLinks()) {
			KeywordLink relatedLink = new KeywordLink(link);
			relatedLink.setAgentId(keywordLink.getAgentId());
			relatedLink.setParentId(generatedKeyword.getId());
			relatedLink.setBookingDate(new Timestamp(new Date().getTime()));
			relatedLink.setRegisteredDate(new Timestamp(new Date().getTime()));
			relatedLink.setStatus(1);
			linkQueue.put(relatedLink);
		}
	}
}
