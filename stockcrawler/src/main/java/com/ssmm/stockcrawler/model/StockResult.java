package com.ssmm.stockcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockResult {
    private String name;
    private String code;
    private int end;
    private int marketSum;
    private int risefall;
    private int volumeTrade;
    private int prevClose;
    private int nowVal;
    private int openVal;
    private int highVal;
    private int lowVal;
    private int fluct;
    private Double fluctRate;
    private String tradeStopYn;
    private double frgnAcqRatio;
    private int faceVal;
    private int high52week;
    private int low52week;
    private String dailyChartUrl;
    private String weeklyChartUrl;
    private String monthlyChartUrl;

    public StockResult() {
        // TODO Auto-generated constructor stub
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

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getMarketSum() {
        return marketSum;
    }

    public void setMarketSum(int marketSum) {
        this.marketSum = marketSum;
    }

    public int getRisefall() {
        return risefall;
    }

    public void setRisefall(int risefall) {
        this.risefall = risefall;
    }

    public int getVolumeTrade() {
        return volumeTrade;
    }

    public void setVolumeTrade(int volumeTrade) {
        this.volumeTrade = volumeTrade;
    }

    public int getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(int prevClose) {
        this.prevClose = prevClose;
    }

    public int getNowVal() {
        return nowVal;
    }

    public void setNowVal(int nowVal) {
        this.nowVal = nowVal;
    }

    public int getOpenVal() {
        return openVal;
    }

    public void setOpenVal(int openVal) {
        this.openVal = openVal;
    }

    public int getHighVal() {
        return highVal;
    }

    public void setHighVal(int highVal) {
        this.highVal = highVal;
    }

    public int getLowVal() {
        return lowVal;
    }

    public void setLowVal(int lowVal) {
        this.lowVal = lowVal;
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

    public String getTradeStopYn() {
        return tradeStopYn;
    }

    public void setTradeStopYn(String tradeStopYn) {
        this.tradeStopYn = tradeStopYn;
    }

    public double getFrgnAcqRatio() {
        return frgnAcqRatio;
    }

    public void setFrgnAcqRatio(double frgnAcqRatio) {
        this.frgnAcqRatio = frgnAcqRatio;
    }

    public int getFaceVal() {
        return faceVal;
    }

    public void setFaceVal(int faceVal) {
        this.faceVal = faceVal;
    }

    public int getHigh52week() {
        return high52week;
    }

    public void setHigh52week(int high52week) {
        this.high52week = high52week;
    }

    public int getLow52week() {
        return low52week;
    }

    public void setLow52week(int low52week) {
        this.low52week = low52week;
    }

    public String getDailyChartUrl() {
        return String.format(StockCharts.CHART_DAILY_URL, getCode());
    }

    public String getWeeklyChartUrl() {
        return String.format(StockCharts.CHART_WEEKLY_URL, getCode());
    }

    public String getMonthlyChartUrl() {
        return String.format(StockCharts.CHART_MONTHLY_URL,getCode());
    }
}