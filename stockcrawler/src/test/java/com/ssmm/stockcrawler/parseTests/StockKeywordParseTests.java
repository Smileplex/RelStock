package com.ssmm.stockcrawler.parseTests;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.parser.NaverStockKeywordParser;
import com.ssmm.stockcrawler.parser.PageReader;
import com.ssmm.stockcrawler.parser.PageReaderImpl;

import junit.framework.TestCase;

public class StockKeywordParseTests {
	private PageReader pageReader;
	private NaverStockKeywordParser naverStockKeywordParser;
	private Document pageHtml;

	@Before
	public void setUp() throws Exception {
		this.pageReader = new PageReaderImpl();
		this.naverStockKeywordParser = new NaverStockKeywordParser();
	}

	@Test
	public void getKeywordNameTests() {
		try {
			String keywordName1 = URLDecoder.decode(pageReader.read(
					"https://m.search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90&where=m&sm=mtp_hty")
					.select("input#nx_query").attr("value").replaceAll("주가", "").replaceAll("주식", ""), "UTF-8").trim();
			assertEquals("삼성전자", keywordName1);

			String keywordName2 = URLDecoder.decode(pageReader.read(
					"https://m.search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90+%EC%A3%BC%EA%B0%80&sm=mtb_hty.top&where=m&oquery=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90&tqi=T1PbmlpVuoVssv26ysNssssss5K-357503")
					.select("input#nx_query").attr("value").replaceAll("주가", "").replaceAll("주식", ""), "UTF-8").trim();
			assertEquals("삼성전자", keywordName2);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getKeywordType() {
		int keywordType;
		this.pageHtml = pageReader.read(
				"https://m.search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90+%EC%88%98%ED%98%9C%EC%A3%BC&sm=mtb_hty.top&where=m&oquery=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90+%EC%A3%BC%EA%B0%80&tqi=T1P55dpySpZssvpG504ssssstCl-439475");
		if (!isStockKeyword()) {
			if (isStockRelatedKeyword())
				keywordType = 2;
			else
				keywordType = 3;
		} else {
			keywordType = 1;
		}
		assertEquals(2, keywordType);
	}

	@Test
	public void getRelatedKeywordLinks() {
		this.pageHtml = pageReader.read(
				"https://m.search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90+%EC%A3%BC%EA%B0%80&sm=mtb_hty.top&where=m&oquery=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90&tqi=T1PfWwpySCGssZFGIb4ssssstQV-029926");
		
		List<String> collectedLinks = new ArrayList<>();
		for (Element anchorTag : getAnchorTags()) {
			String link = combineLinkWithSearchQuery(anchorTag);
			if (!collectedLinks.contains(link))
				collectedLinks.add(link);
		}
	}

	private Elements getAnchorTags() {
		return pageHtml.select("div._rk_hcheck a");
	}

	private String combineLinkWithSearchQuery(Element anchorTag) {
		return "https://m.search.naver.com/search.naver" + anchorTag.attr("href");
	}

	private String getKeywordName() {
		try {
			return URLDecoder
					.decode(pageHtml.select("input#nx_query").attr("value").replaceAll("주가", "").replaceAll("주식", ""),
							"UTF-8")
					.trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private boolean isStockKeyword() {
		return pageHtml.toString().contains("<div class=\"stock_tlt\">");
	}

	private boolean isStockRelatedKeyword() {
		return Helper.containValue("관련주,연관주,테마주,수혜주,대장주", getKeywordName());
	}

}
