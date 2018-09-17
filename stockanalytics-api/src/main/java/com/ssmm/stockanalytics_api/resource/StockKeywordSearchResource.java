package com.ssmm.stockanalytics_api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmm.stockanalytics_api.model.Stock;
import com.ssmm.stockanalytics_api.model.StockKeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StockKeywordSearchResource {
    private List<Node> nodes;
    private List<Link> links;

    public StockKeywordSearchResource() {
    }

    public StockKeywordSearchResource(List<StockKeyword> stockKeywords) throws Exception {
        nodes = new ArrayList<>();
        links = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (StockKeyword stockKeyword : stockKeywords) {
            Stock stock = stockKeyword.getStock();

            Map properties;
            if (Objects.nonNull(stockKeyword.getStock())) {
                properties = objectMapper.convertValue(stock, Map.class);
            } else {
                properties = objectMapper.convertValue(stockKeyword, Map.class);
            }

            Node node = new Node(stockKeyword.getId(), stockKeyword.getName(), stockKeyword.getClass().getSimpleName(), properties);
            nodes.add(node);

            if (Objects.nonNull(stockKeyword.getStockKeywords())) {
                for (StockKeyword relatedKeyword : stockKeyword.getStockKeywords()) {
                    Link relationship = new Link(stockKeyword.getId(), relatedKeyword.getId(), "related_to");
                    links.add(relationship);
                }
            }
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "StockKeywordSearchResource{" +
                "nodes=" + nodes +
                ", links=" + links +
                '}';
    }
}
