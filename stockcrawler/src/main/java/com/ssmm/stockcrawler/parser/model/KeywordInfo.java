package com.ssmm.stockcrawler.parser.model;

import java.util.List;

public class KeywordInfo {
	private String keywordName;
	private int keywordType;
	private List<String> relatedKeywordLinks;

	public KeywordInfo() {
		// TODO Auto-generated constructor stub
	}

	public KeywordInfo(String keywordName, int keywordType, List<String> relatedKeywordLinks) {
		this.keywordName = keywordName;
		this.keywordType = keywordType;
		this.relatedKeywordLinks = relatedKeywordLinks;
	}

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public int getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(int keywordType) {
		this.keywordType = keywordType;
	}

	public List<String> getRelatedKeywordLinks() {
		return relatedKeywordLinks;
	}

	public void setRelatedKeywordLinks(List<String> relatedKeywordLinks) {
		this.relatedKeywordLinks = relatedKeywordLinks;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "KeywordInfo [keywordName=" + keywordName + ", keywordType=" + keywordType + ", relatedKeywordLinks="
				+ (relatedKeywordLinks != null
						? relatedKeywordLinks.subList(0, Math.min(relatedKeywordLinks.size(), maxLen))
						: null)
				+ "]";
	}

}
