package com.ssmm.stockcrawler.parser;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.KeywordLink;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.EmptyParsingResult;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.parser.model.ParsingResult;
import com.ssmm.stockcrawler.service.PageReader;

public class KeywordParserImpl implements KeywordParser {
	private KeywordLinkQueue linkQueue;
	private PageReader pageReader;
	private StockKeywordParser stockKeywordParser;

	@Inject
	public KeywordParserImpl(KeywordLinkQueue linkQueue, StockKeywordParser stockKeywordParser, PageReader pageReader) {
		this.linkQueue = linkQueue;
		// TODO Auto-generated constructor stub
		this.pageReader = pageReader;
		this.stockKeywordParser = stockKeywordParser;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			parse(linkQueue.get().getLink());
		}
	}

	public void parse(String url) {
		// TODO Auto-generated method stub
		Document document = pageReader.read(url);
		KeywordInfo keywordInfo = stockKeywordParser.parse(document);
		if (keywordInfo instanceof EmptyKeywordInfo)
			return;

		for (String link : keywordInfo.getRelatedKeywordLinks())
			linkQueue.put(new KeywordLink(link));

		System.out.println("(" + Thread.currentThread().getName() + ") " + keywordInfo.getKeywordName());
	}
}
