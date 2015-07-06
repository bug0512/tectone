package com.tectone.doubleguard.model;

/**
 * Created by JamesLee on 2014-11-13.
 */
public class GraphBean {
    String x;
    String y;
    String time;


    public GraphBean(String x, String y, String time) {
        this.setX(x);
        this.setY(y);
        this.setTime(time);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
