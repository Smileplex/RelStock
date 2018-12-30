package com.ssmm.stockanalytics_api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockanalytics_api.model.Stock;
import com.ssmm.stockanalytics_api.model.StockKeyword;
import com.ssmm.stockanalytics_api.model.StockResult;

import java.util.*;

public class D3SearchResource {
    private Set<Node> nodes;
    private Set<Link> links;

    public D3SearchResource() {
    }

    public D3SearchResource(List<StockKeyword> stockKeywords) throws Exception {
        nodes = new HashSet<>();
        links = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (StockKeyword stockKeyword : stockKeywords) {
            Stock stock = stockKeyword.getStock();

            Map properties;
            String labelType;
            if (Objects.nonNull(stockKeyword.getStock())) {
                properties = objectMapper.convertValue(stock, Map.class);
                labelType = "Stock";
            } else {
                properties = objectMapper.convertValue(stockKeyword, Map.class);
                labelType = "StockKeyword";
            }

            Node node = new Node(stockKeyword.getId(), stockKeyword.getName(), labelType, properties);
            nodes.add(node);

            if (Objects.nonNull(stockKeyword.getStockKeywords())) {
                for (StockKeyword relatedKeyword : stockKeyword.getStockKeywords()) {
                    Link relationship = new Link(stockKeyword.getId(), relatedKeyword.getId(), "related_to");
                    links.add(relationship);
                }
            }
        }
    }
    public D3SearchResource(StockResult stockResult) throws Exception {
        nodes = new HashSet<>();
        links = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();

        Map properties;
        String labelType;
        for(Stock stock : stockResult.getStocks()){
            properties = objectMapper.convertValue(stock, Map.class);
            labelType = "Stock";
            nodes.add(new Node(stock.getId(), stock.getName().toLowerCase(), labelType, properties));
        }

        for(StockKeyword stockKeyword : stockResult.getStockKeywords()){
            properties = objectMapper.convertValue(stockKeyword, Map.class);
            labelType = "StockKeyword";
            nodes.add(new Node(stockKeyword.getId(), stockKeyword.getName().toLowerCase(), labelType, properties));
        }


    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "D3SearchResource{" +
                "nodes=" + nodes +
                ", links=" + links +
                '}';
    }
}
