package com.ssmm.stockcrawler.parser.model;

import com.ssmm.stockcrawler.model.Article;
import com.ssmm.stockcrawler.model.Clip;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.function.Function;

public class MassMedia {
    private List<Article> articles;
    private List<Clip> clips;

    public static final String articleList = "ul.type01 li";
    public static Function<Element, Article> getArticles = (element) -> {
        String title = element.select("dl dt a").attr("title");
        String link = element.select("dl dt a").attr("href");
        String source = element.select("dl dd.txt_inline span._sp_each_source").text();

        String ago = element.select("dl dd.txt_inline").size() > 0 ? element.select("dl dd.txt_inline").get(0).ownText() : "";
        if (!ago.isEmpty())
            return new Article(title, link, source, ago);
        return null;
    };

    public static final String clipList = "ul._video_lst li";
    public static Function<Element, Clip> getClips = (element) -> {
        String title = element.select("dl dt a.title").attr("title");
        String link = element.select("a.video_thum").attr("href");
        String thumbnail = element.select("a.video_thum img").attr("src");
        String source = element.select("dl dd.sub_info span.cite a").text();
        String ago = element.select("dl dd.sub_info span.info").get(1).text();
        return new Clip(title, link, thumbnail, source, ago);
    };

    public MassMedia(List<Article> articles, List<Clip> clips) {
        this.articles = articles;
        this.clips = clips;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Clip> getClips() {
        return clips;
    }
}
