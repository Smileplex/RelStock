package com.ssmm.stockcrawler.parser;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.controller.KeywordLinkQueue;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.parser.model.Detail;
import com.ssmm.stockcrawler.parser.model.EmptyDetail;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.service.DetailGenerator;

public class DetailParserImpl implements DetailParser {

	private DetailLinkQueue detailLinkQueue;
	private PageReader pageReader;
	private PageDetailParser pageDetailParser;
	private DetailGenerator detailGenerator;

	@Inject
	public DetailParserImpl(DetailLinkQueue linkQueue, PageReader pageReader, PageDetailParser pageDetailParser,
			DetailGenerator detailGenerator) {
		this.detailLinkQueue = linkQueue;
		this.pageReader = pageReader;
		// TODO Auto-generated constructor stub
		this.pageDetailParser = pageDetailParser;
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
		Detail detail = pageDetailParser.parse(document);
		if (Objects.isNull(detail)) {
			System.out.println("(" + Thread.currentThread().getName() + ") " + " can not parse it ");
			return;
		}
		detailGenerator.generate(detail, detailLink);

		if (detail instanceof EmptyDetail)
			return;
	}
}
