package com.ssmm.stockcrawler.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ssmm.stockcrawler.helper.Helper;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordType;

public class NaverStockKeywordParser implements PageParser {
	private final String SEARCH_QUERY = "https://m.search.naver.com/search.naver";
	private Document pageHtml;

	@Override
	public KeywordInfo parse(Document pageHtml) {
		// TODO Auto-generated method stub
		this.pageHtml = pageHtml;

		int keywordType = getKeywordType(pageHtml);
		if (keywordType == KeywordType.NON_STOCK_KEYWORD) {
			return new EmptyKeywordInfo();
		}

		return new KeywordInfo(getKeywordName(), keywordType, getRelatedKeywordLinks());
	}

	private int getKeywordType(Document pageHtml) {
		return KeywordType.classify(pageHtml);
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

	private List<String> getRelatedKeywordLinks() {
		List<String> collectedLinks = new ArrayList<>();
		for (Element anchorTag : getAnchorTags()) {
			String link = combineLinkWithSearchQuery(anchorTag);
			if (!collectedLinks.contains(link))
				collectedLinks.add(link);
		}
		return collectedLinks;
	}

	private Elements getAnchorTags() {
		return pageHtml.select("div._rk_hcheck a");
	}

	private String combineLinkWithSearchQuery(Element anchorTag) {
		return SEARCH_QUERY + anchorTag.attr("href");
	}

}
