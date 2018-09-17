package com.ssmm.stockanalytics_api.resource;

public class Link {
    private Long source;
    private Long target;
    private String type;

    public Link(Long source, Long target, String type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Link{" +
                "source=" + source +
                ", target=" + target +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
