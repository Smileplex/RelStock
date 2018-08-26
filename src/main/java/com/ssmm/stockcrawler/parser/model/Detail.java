package com.ssmm.stockcrawler.parser.model;

public class Detail {
	private String detailJson;

	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(String result) {
		// TODO Auto-generated constructor stub
		this.detailJson = result;
	}

	public String getDetailJson() {
		return detailJson;
	}

	public void setDetailJson(String detailJson) {
		this.detailJson = detailJson;
	}

}
