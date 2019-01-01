package com.ssmm.stockcrawler.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

public class Article {
    private String title;
    private String link;
    private String source;
    private String ago;
    private Date createdDate;


    public Article() {
    }

    public Article(String title, String link, String source, Date createdDate) {
        this.title = title;
        this.link = link;
        this.source = source;
        this.createdDate = createdDate;
    }

    public Article(String title, String link, String source, String ago) {
        this.title = title;
        this.link = link;
        this.source = source;
        this.ago=ago;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getAgo() {
        return ago;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", source='" + source + '\'' +
                ", ago='" + ago + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
