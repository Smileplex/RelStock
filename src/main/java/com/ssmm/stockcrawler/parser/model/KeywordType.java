package com.ssmm.stockcrawler.parser.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.helper.Helper;

public abstract class KeywordType {
	public static final int STOCK_KEYWORD = 1;
	public static final int STOCK_RELATED_KEYWORD = 2;
	public static final int NON_STOCK_KEYWORD = 3;

	public static int classify(Document pageHtml) {
		if (isStockKeyword(pageHtml))
			return STOCK_KEYWORD;

		if (isStockRelatedKeyword(pageHtml))
			return STOCK_RELATED_KEYWORD;

		else
			return NON_STOCK_KEYWORD;

	}

	private static boolean isStockKeyword(Document pageHtml) {
		return pageHtml.toString().contains("<div class=\"stock_tlt\">");
	}

	private static boolean isStockRelatedKeyword(Document pageHtml) {
		return Helper.containValue("관련주,연관주,테마주,수혜주,대장주", getKeywordName(pageHtml));
	}

	private static String getKeywordName(Document pageHtml) {
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

}