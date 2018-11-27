package com.ssmm.stockcrawler.model;

import java.util.Date;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Stock {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String code;

	private int pricePrev;
	private int price;
	private int priceMax;
	private int priceMin;
	private int fluct;

	private int marketSum;
	private int faceVal;
	private double frgnAcqRatio;

	private int volumeTrade;
	private Long volumeTradeAmount;

	private String articles;

	private Double fluctRate;
	private int riseFall;
	private String dailyChart;
	private String weeklyChart;
	private String monthlyChart;
	private Date createdDate;
	private Date updatedDate;

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Stock(String name) {
		this.name = name;
	}

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

	public String getDailyChart() {
		return dailyChart;
	}

	public void setDailyChart(String dailyChart) {
		this.dailyChart = dailyChart;
	}

	public String getWeeklyChart() {
		return weeklyChart;
	}

	public void setWeeklyChart(String weeklyChart) {
		this.weeklyChart = weeklyChart;
	}

	public String getMonthlyChart() {
		return monthlyChart;
	}

	public void setMonthlyChart(String monthlyChart) {
		this.monthlyChart = monthlyChart;
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

	public int getMarketSum() {
		return marketSum;
	}

	public void setMarketSum(int marketSum) {
		this.marketSum = marketSum;
	}

	public int getFaceVal() {
		return faceVal;
	}

	public void setFaceVal(int faceVal) {
		this.faceVal = faceVal;
	}

	public double getFrgnAcqRatio() {
		return frgnAcqRatio;
	}

	public void setFrgnAcqRatio(double frgnAcqRatio) {
		this.frgnAcqRatio = frgnAcqRatio;
	}

	public int getVolumeTrade() {
		return volumeTrade;
	}

	public void setVolumeTrade(int volumeTrade) {
		this.volumeTrade = volumeTrade;
	}

	public Long getVolumeTradeAmount() {
		return volumeTradeAmount;
	}

	public void setVolumeTradeAmount(Long volumeTradeAmount) {
		this.volumeTradeAmount = volumeTradeAmount;
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", pricePrev=" + pricePrev +
				", price=" + price +
				", priceMax=" + priceMax +
				", priceMin=" + priceMin +
				", fluct=" + fluct +
				", marketSum=" + marketSum +
				", faceVal=" + faceVal +
				", frgnAcqRatio=" + frgnAcqRatio +
				", volumeTrade=" + volumeTrade +
				", volumeTradeAmount=" + volumeTradeAmount +
				", fluctRate=" + fluctRate +
				", riseFall=" + riseFall +
				", dailyChart='" + dailyChart + '\'' +
				", weeklyChart='" + weeklyChart + '\'' +
				", monthlyChart='" + monthlyChart + '\'' +
				", createdDate=" + createdDate +
				", updatedDate=" + updatedDate +
				'}';
	}
}
