package com.ssmm.stockcrawler.parser.model;

import com.ssmm.stockcrawler.model.Article;
import com.ssmm.stockcrawler.model.Clip;

import javax.print.attribute.standard.Media;
import java.util.List;

public class KeywordInfo {
    private String keywordName;
    private int keywordType;
    private List<String> relatedKeywordLinks;
    private MassMedia massMedia;

    public KeywordInfo() {
        // TODO Auto-generated constructor stub
    }

    public KeywordInfo(String keywordName, int keywordType, List<String> relatedKeywordLinks, MassMedia massMedia) {
        this.keywordName = keywordName;
        this.keywordType = keywordType;
        this.relatedKeywordLinks = relatedKeywordLinks;
        this.massMedia = massMedia;
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

    public List<Article> getArticles(){
        return massMedia.getArticles();
    }

    public List<Clip> getClips(){
        return massMedia.getClips();
    }
}
