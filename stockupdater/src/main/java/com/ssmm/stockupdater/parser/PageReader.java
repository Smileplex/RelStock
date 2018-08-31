package com.ssmm.stockupdater.parser;

import org.jsoup.nodes.Document;

public interface PageReader {
	Document read(String url);
}
