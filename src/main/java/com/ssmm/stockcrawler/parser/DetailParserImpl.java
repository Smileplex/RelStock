package com.ssmm.stockcrawler.parser;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.parser.model.DetailInfo;
import com.ssmm.stockcrawler.parser.model.EmptyDetailInfo;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.service.DetailGenerator;

public class DetailParserImpl implements DetailParser {

	private DetailLinkQueue detailLinkQueue;
	private PageReader pageReader;
	private DetailPageParser detailPageParser;
	private DetailGenerator detailGenerator;

	@Inject
	public DetailParserImpl(DetailLinkQueue linkQueue, PageReader pageReader, DetailPageParser detailPageParser,
			DetailGenerator detailGenerator) {
		this.detailLinkQueue = linkQueue;
		this.pageReader = pageReader;
		// TODO Auto-generated constructor stub
		this.detailPageParser = detailPageParser;
		this.detailGenerator = detailGenerator;
	}

	public void run() {
		while (true) {
			try {
				parse(detailLinkQueue.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void parse(DetailLink detailLink) {
		// TODO Auto-generated method stub
		Document document = pageReader.read(detailLink.getLink());
		DetailInfo detailInfo = detailPageParser.parse(document);
		if (detailInfo instanceof EmptyDetailInfo)
			return;

		System.out.println("(" + Thread.currentThread().getName() + ") " + detailInfo);
	}
}
