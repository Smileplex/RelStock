package com.ssmm.stockcrawler.parser;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.ssmm.stockcrawler.controller.DetailLinkQueue;
import com.ssmm.stockcrawler.model.DetailLink;
import com.ssmm.stockcrawler.parser.model.Detail;
import com.ssmm.stockcrawler.parser.model.EmptyDetail;
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

		if (detail instanceof EmptyDetail) {
			String.format("(%s) can not parse (%s)", Thread.currentThread().getName(), detail.getResult());
			return;
		}

		if (detailGenerator.generate(detail, detailLink) == 0L) {
			String.format("(%s) can not generate stock (%s)", Thread.currentThread().getName(), detail.getResult());
			return;
		}

	}
}
