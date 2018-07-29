package com.ssmm.stockcrawler.parser;

import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.parser.model.DetailInfo;

public interface DetailPageParser {
	DetailInfo parse(Document pageHtml);
}
