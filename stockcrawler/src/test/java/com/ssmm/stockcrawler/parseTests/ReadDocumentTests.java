package com.ssmm.stockcrawler.parseTests;

import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.parser.PageReader;
import com.ssmm.stockcrawler.parser.PageReaderImpl;

import junit.framework.TestCase;

public class ReadDocumentTests extends TestCase {
	private PageReader pageReader;

	@Override
	protected void setUp() throws Exception {
		this.pageReader = new PageReaderImpl();
	}

	public void testReadingUrl() {
		Document document = pageReader.read("https://m.search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90&where=m&sm=mtp_hty");
		assertNotNull(document);
		System.out.println(document.toString());
	}

}
