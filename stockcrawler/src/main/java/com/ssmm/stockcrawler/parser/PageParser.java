package com.ssmm.stockcrawler.parser;

import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import org.jsoup.nodes.Document;

public interface PageParser {
	KeywordInfo parse(Document pageHtml);
}
