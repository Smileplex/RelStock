package com.ssmm.stockcrawler.parser;

import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.parser.model.Detail;

public interface PageDetailParser {
	Detail parse(Document pageHtml);
}
