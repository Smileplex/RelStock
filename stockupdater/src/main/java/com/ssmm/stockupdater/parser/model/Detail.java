package com.ssmm.stockupdater.parser.model;

public class Detail {
	private String result;

	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(String result) {
		// TODO Auto-generated constructor stub
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Detail [result=" + result + "]";
	}

}
