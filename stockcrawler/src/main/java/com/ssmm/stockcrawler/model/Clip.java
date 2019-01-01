package com.ssmm.stockcrawler.model;

public class Clip {
    private String title;
    private String link;
    private String thumbnail;
    private String source;
    private String ago;

    public Clip(String title, String link, String thumbnail, String source, String ago) {
        this.title = title;
        this.link = link;
        this.thumbnail = thumbnail;
        this.source = source;
        this.ago = ago;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSource() {
        return source;
    }

    public String getAgo() {
        return ago;
    }

    @Override
    public String toString() {
        return "Clip{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", source='" + source + '\'' +
                ", ago='" + ago + '\'' +
                '}';
    }
}
