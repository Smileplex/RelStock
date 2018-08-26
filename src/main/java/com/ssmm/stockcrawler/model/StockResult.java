package com.ssmm.stockcrawler.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockResult {
	private String name;
	private String code;
	private int end;
	private int marketSum;
	private int risefall;
	private String tradeStopYn;
	private int openVal;
	private int volumeTrade;
	private int prevClose;
	private int highVal;
	private int lowVal;
	private double frgnAcqRatio;
	private int faceVal;
	private int high52week;
	private int low52week;
	private String meetingDate;
	private double debtRatio;
	private int dividend;
	
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

	public String getTradeStopYn() {
		return tradeStopYn;
	}

	public void setTradeStopYn(String tradeStopYn) {
		this.tradeStopYn = tradeStopYn;
	}

	public int getOpenVal() {
		return openVal;
	}

	public void setOpenVal(int openVal) {
		this.openVal = openVal;
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

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public double getDebtRatio() {
		return debtRatio;
	}

	public void setDebtRatio(double debtRatio) {
		this.debtRatio = debtRatio;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	@Override
	public String toString() {
		return "StockResult [end=" + end + ", marketSum=" + marketSum + ", risefall=" + risefall + ", tradeStopYn="
				+ tradeStopYn + ", openVal=" + openVal + ", volumeTrade=" + volumeTrade + ", prevClose=" + prevClose
				+ ", highVal=" + highVal + ", lowVal=" + lowVal + ", frgnAcqRatio=" + frgnAcqRatio + ", faceVal="
				+ faceVal + ", high52week=" + high52week + ", low52week=" + low52week + ", meetingDate=" + meetingDate
				+ ", debtRatio=" + debtRatio + ", dividend=" + dividend + "]";
	}

}