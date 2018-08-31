package com.ssmm.stockcrawler.parseTests;

import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockupdater.parser.NaverStockParser;
import com.ssmm.stockupdater.parser.PageDetailParser;
import com.ssmm.stockupdater.parser.PageReader;
import com.ssmm.stockupdater.parser.PageReaderImpl;

import junit.framework.TestCase;

public class NaverStockParserTests extends TestCase {
	private PageDetailParser pageDetailParser;

	@Override
	protected void setUp() throws Exception {
		this.pageDetailParser = new NaverStockParser(new PageReaderImpl(), new ObjectMapper());
	}

	public void testReadingStockByCode() {
		System.out.println(pageDetailParser.parse("034220"));
	}

}
