package com.ssmm.stockcrawler.parser;

import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.parser.model.KeywordInfo;

public interface StockKeywordParser {
	KeywordInfo parse(Document pageHtml);

}
