package com.requin.DelaySearcher;

public class RailRouteEntity {
    private final String company;
    private final String name;
    private final String source;

    public RailRouteEntity(String company, String name, String source){
        this.company = company;
        this.name = name;
        this.source = source;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }
}
