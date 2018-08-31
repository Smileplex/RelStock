package com.ssmm.stockcrawler.model;

public class DetailLink {
	private String link;
	private Long parentId;

	public DetailLink() {
		// TODO Auto-generated constructor stub
	}

	public DetailLink(String link, Long parentId) {
		this.link = link;
		this.parentId = parentId;
	}

	public String getLink() {
		return link;
	}

	public Long getParentId() {
		return parentId;
	}

}
