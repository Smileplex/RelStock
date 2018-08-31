package com.ssmm.stockupdater.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockValue {
	private String nm;
	private String cd;
	private String rf;
	private int hv;
	private int lv;
	private int ov;
	private int nv;
	private int sv;
	private Double cr;
	private int cv;
	private Long aq;
	private Long aa;

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getRf() {
		return rf;
	}

	public void setRf(String rf) {
		this.rf = rf;
	}

	public int getHv() {
		return hv;
	}

	public void setHv(int hv) {
		this.hv = hv;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getOv() {
		return ov;
	}

	public void setOv(int ov) {
		this.ov = ov;
	}

	public int getNv() {
		return nv;
	}

	public void setNv(int nv) {
		this.nv = nv;
	}

	public int getSv() {
		return sv;
	}

	public void setSv(int sv) {
		this.sv = sv;
	}

	public Double getCr() {
		return cr;
	}

	public void setCr(Double cr) {
		this.cr = cr;
	}

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}

	public Long getAq() {
		return aq;
	}

	public void setAq(Long aq) {
		this.aq = aq;
	}

	public Long getAa() {
		return aa;
	}

	public void setAa(Long aa) {
		this.aa = aa;
	}

}
