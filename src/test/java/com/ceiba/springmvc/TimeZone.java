package com.ceiba.springmvc;

public class TimeZone {

    private int offSet;
    private String name;

    public int getOffset() {
        return offSet;
    }

    public void setOffset(int offSet) {
        this.offSet = offSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int normalizedHour(int hour){
        return hour-offSet;
    }
}
