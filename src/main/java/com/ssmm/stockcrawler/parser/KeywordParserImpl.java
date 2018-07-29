package com.ssmm.stockcrawler.parser;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.model.StockKeyword;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.EmptyParsingResult;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.parser.model.ParsingResult;
import com.ssmm.stockcrawler.service.KeywordGenerator;
import com.ssmm.stockcrawler.service.StockKeywordService;

public class KeywordParserImpl implements KeywordParser {
	private KeywordLinkQueue keywordLinkQueue;
	private DetailLinkQueue detailLinkQueue;
	private KeywordGenerator keywordGenerator;
	private PageReader pageReader;
	private PageParser pageParser;

	@Inject
	public KeywordParserImpl(KeywordLinkQueue keywordLinkQueue, DetailLinkQueue detailLinkQueue, PageReader pageReader,
			PageParser pageParser, KeywordGenerator keywordGenerator) {
		this.keywordLinkQueue = keywordLinkQueue;
		this.detailLinkQueue = detailLinkQueue;
		this.pageReader = pageReader;
		this.pageParser = pageParser;
		this.keywordGenerator = keywordGenerator;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				parse(keywordLinkQueue.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void parse(KeywordLink keywordLink) {
		// TODO Auto-generated method stub
		Document document = pageReader.read(keywordLink.getLink());
		KeywordInfo keywordInfo = pageParser.parse(document);
		if (keywordInfo instanceof EmptyKeywordInfo)
			return;

		System.out.println("(" + Thread.currentThread().getName() + ") " + keywordInfo.getKeywordName());
		Long generatedKeywordId = keywordGenerator.generate(keywordInfo, keywordLink);

		pushKeywordLinkQueue(keywordLink, keywordInfo, generatedKeywordId);
		pushDetailLinkQueue(keywordLink, generatedKeywordId);
	}

	private void pushKeywordLinkQueue(KeywordLink keywordLink, KeywordInfo keywordInfo, Long generatedKeywordId) {
		keywordInfo.getRelatedKeywordLinks().forEach(link -> {
			keywordLinkQueue.put(new KeywordLink(link, 1, new Timestamp(new Date().getTime()),
					new Timestamp(new Date().getTime()), keywordLink.getAgentId(), generatedKeywordId));
		});
	}

	private void pushDetailLinkQueue(KeywordLink keywordLink, Long generatedKeywordId) {
		detailLinkQueue.put(new DetailLink(keywordLink.getLink(), generatedKeywordId));
	}
}
