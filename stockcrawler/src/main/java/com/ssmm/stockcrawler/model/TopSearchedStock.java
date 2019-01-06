package com.ssmm.stockcrawler.model;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

@NodeEntity
public class TopSearchedStock {
    @Id
    @GeneratedValue
    private Long id;
    private int rank;

    private String name;
    private String searchRate;
    private String price;
    private String fluct;
    private String fluctRate;
    private int riseFall;

    private Date updatedDate;

    public TopSearchedStock() {
    }

    public TopSearchedStock(int rank, String name, String searchRate, String price, String fluct, String fluctRate, int riseFall) {
        this.rank = rank;
        this.name = name;
        this.searchRate = searchRate;
        this.price = price;
        this.fluct = fluct;
        this.fluctRate = fluctRate;
        this.riseFall = riseFall;
    }

    public Long getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getSearchRate() {
        return searchRate;
    }

    public String getPrice() {
        return price;
    }

    public String getFluct() {
        return fluct;
    }

    public String getFluctRate() {
        return fluctRate;
    }

    public int getRiseFall() {
        return riseFall;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "TopSearchedStock{" +
                "id=" + id +
                ", rank=" + rank +
                ", name='" + name + '\'' +
                ", searchRate='" + searchRate + '\'' +
                ", price='" + price + '\'' +
                ", fluct='" + fluct + '\'' +
                ", fluctRate='" + fluctRate + '\'' +
                ", riseFall=" + riseFall +
                '}';
    }
}
