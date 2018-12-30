package com.ssmm.stockanalytics_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class StockKeyword {
	@Id
	@GeneratedValue
	private Long id;
	private int agentId;
	private String name;
	private String link;
	private Date createdDate;
	private Date updatedDate;
	private int type;
	private int status;

	@JsonIgnore
	@Relationship(type = "RELATED_TO")
	private Set<StockKeyword> stockKeywords;

	@JsonIgnore
	@Relationship(type = "HAS")
	private Stock stock;

	public StockKeyword() {
		// TODO Auto-generated constructor stub
	}

	public StockKeyword(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Set<StockKeyword> getStockKeywords() {
		return stockKeywords;
	}

	public void addStockKeyword(StockKeyword stockKeyword) {
		if (stockKeywords == null)
			stockKeywords = new HashSet<>();

		stockKeywords.add(stockKeyword);
	}

	@Override
	public String toString() {
		return "StockKeyword{" +
				"id=" + id +
				", agentId=" + agentId +
				", name='" + name + '\'' +
				", link='" + link + '\'' +
				", createdDate=" + createdDate +
				", updatedDate=" + updatedDate +
				", type=" + type +
				", status=" + status +
				", stockKeywords=" + stockKeywords +
				", stock=" + stock +
				'}';
	}
}
