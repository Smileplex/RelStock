package com.ssmm.stockanalytics_api.resource;

import java.util.Date;

public class StockResource {

    private Long id;
    private String name;
    private String code;
    private int pricePrev;
    private int price;
    private int priceMax;
    private int priceMin;
    private int fluct;
    private Double fluctRate;
    private int riseFall;
    private String chartDaily;
    private String chartWeekly;
    private String chartMonthly;
    private Date createdDate;
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPricePrev() {
        return pricePrev;
    }

    public void setPricePrev(int pricePrev) {
        this.pricePrev = pricePrev;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public int getFluct() {
        return fluct;
    }

    public void setFluct(int fluct) {
        this.fluct = fluct;
    }

    public Double getFluctRate() {
        return fluctRate;
    }

    public void setFluctRate(Double fluctRate) {
        this.fluctRate = fluctRate;
    }

    public int getRiseFall() {
        return riseFall;
    }

    public void setRiseFall(int riseFall) {
        this.riseFall = riseFall;
    }

    public String getChartDaily() {
        return chartDaily;
    }

    public void setChartDaily(String chartDaily) {
        this.chartDaily = chartDaily;
    }

    public String getChartWeekly() {
        return chartWeekly;
    }

    public void setChartWeekly(String chartWeekly) {
        this.chartWeekly = chartWeekly;
    }

    public String getChartMonthly() {
        return chartMonthly;
    }

    public void setChartMonthly(String chartMonthly) {
        this.chartMonthly = chartMonthly;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
