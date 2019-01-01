package com.ssmm.stockcrawler.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockcrawler.model.Article;
import com.ssmm.stockcrawler.model.Clip;
import com.ssmm.stockcrawler.parser.model.EmptyKeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordInfo;
import com.ssmm.stockcrawler.parser.model.KeywordType;
import com.ssmm.stockcrawler.parser.model.MassMedia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NaverStockKeywordParser implements PageParser {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Document pageHtml;

    @Override
    public KeywordInfo parse(Document pageHtml) {
        // TODO Auto-generated method stub
        this.pageHtml = pageHtml;

        int keywordType = getKeywordType(pageHtml);
        if (keywordType == KeywordType.NON_STOCK_KEYWORD) {
            return new EmptyKeywordInfo();
        }

        return new KeywordInfo(getKeywordName(), keywordType, getRelatedKeywordLinks(), getMassMedia());
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
        return getAnchorTags().stream()
                .map(this::combineLinkWithSearchQuery).distinct().collect(Collectors.toList());
    }

    private Elements getAnchorTags() {
        return pageHtml.select("div._rk_hcheck a");
    }

    private String combineLinkWithSearchQuery(Element anchorTag) {
        return NaverStockUrls.SEARCH_QUERY + anchorTag.attr("href");
    }

    private MassMedia getMassMedia() {
        return new MassMedia(getArticles(), getClips());
    }

    private List<Article> getArticles() {
        try {
            return Jsoup.parse(new URL(String.format(NaverStockUrls.STOCK_KEYWORD_ARTICLE_URL, URLEncoder.encode(getKeywordName(),"utf-8"))), 2000)
                    .select(MassMedia.articleList).stream()
                    .map(MassMedia.getArticles)
                    .filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Clip> getClips() {
        try {
            return Jsoup.parse(new URL(String.format(NaverStockUrls.STOCK_KEYWORD_CLIP_URL, URLEncoder.encode(getKeywordName(),"utf-8"))), 2000)
                    .select(MassMedia.clipList).stream()
                    .map(MassMedia.getClips)
                    .filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}