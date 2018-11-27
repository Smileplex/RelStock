package com.ssmm.stockcrawler.parser.model;

import com.ssmm.stockcrawler.model.Article;

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

    public int getKeywordType() {
        return keywordType;
    }

    public List<String> getRelatedKeywordLinks() {
        return relatedKeywordLinks;
    }

}
