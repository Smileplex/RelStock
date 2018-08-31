package com.ssmm.stockupdater.parser;

import org.jsoup.nodes.Document;

import com.ssmm.stockupdater.parser.model.Detail;

public interface PageDetailParser {
	Detail parse(String code);
}
