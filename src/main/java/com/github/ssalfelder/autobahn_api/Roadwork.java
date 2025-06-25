package com.github.ssalfelder.autobahn_api;

public class Roadwork {

    private String road;
    private String section;
    private String direction;
    private String start;
    private String end;

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Roadwork(String road, String section, String direction, String start, String end) {
        this.road = road;
        this.section = section;
        this.direction = direction;
        this.start = start;
        this.end = end;
    }

}
