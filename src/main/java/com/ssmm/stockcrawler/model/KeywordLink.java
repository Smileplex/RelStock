package com.ssmm.stockcrawler.model;

import java.sql.Timestamp;

public class KeywordLink {
	private Long id;
	private String link;
	private int status;
	private Timestamp registeredDate;
	private Timestamp bookingDate;
	private int agentId;
	private Long parentId;

	public KeywordLink() {
		// TODO Auto-generated constructor stub
	}

	public KeywordLink(String link) {
		this.link = link;
	}

	public KeywordLink(String link, int status, Timestamp registeredDate, Timestamp bookingDate, int agentId,
			Long parentId) {
		this.link = link;
		this.status = status;
		this.registeredDate = registeredDate;
		this.bookingDate = bookingDate;
		this.agentId = agentId;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public int getStatus() {
		return status;
	}

	public Timestamp getRegisteredDate() {
		return registeredDate;
	}

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public int getAgentId() {
		return agentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
