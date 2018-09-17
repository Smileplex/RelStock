package com.ssmm.stockanalytics_api.resource;

import java.util.Map;

public class Node {
    private Long id;
    private String name;
    private String label;
    private Map<String, Object> properties;

    public Node(Long id, String name, String label, Map<String, Object> properties) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
