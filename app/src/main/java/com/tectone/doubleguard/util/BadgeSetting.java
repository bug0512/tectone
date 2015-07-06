package com.tectone.doubleguard.util;

/**
 * Created by JamesLee on 2014-11-02.
 */
public class BadgeSetting {

    public int getSencitivityMax(String sencerListName) {
        int rtnString = 0;
        if (sencerListName.equals("1,B,1")) {
            rtnString = 100;
        } else if (sencerListName.equals("1,A,1")) {
            rtnString = 100;
        } else if (sencerListName.equals("6,N,2")) {
            rtnString = 100;
        } else if (sencerListName.equals("4,L,3")) {
            rtnString = 10000;
        } else if (sencerListName.equals("2,C,2")) {
            rtnString = 1000;
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = 10000;
        } else if (sencerListName.equals("6,G,2")) {
            rtnString = -1;
        } else if (sencerListName.equals("4,E,2")) {
            rtnString = 10000;
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = 100;
        } else if (sencerListName.equals("4,L,1")) {
            rtnString = 10000;
        } else if (sencerListName.equals("4,E,1")) {
            rtnString = 10000;
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = 10000;
        } else if (sencerListName.equals("1,I,4")) {
            rtnString = 100;
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = 100;
        } else if (sencerListName.equals("5,F,2")) {
            rtnString = 100;
        } else if (sencerListName.equals("3,K,4")) {
            rtnString = 100;
        } else if (sencerListName.equals("6,G,1")) {
            rtnString = 100;
        } else {
            rtnString = 1000;
        }

        return rtnString;
    }

    public String getMaxSencitivity(String sencerListName) {
        String rtnString = null;
        if (sencerListName.equals("1,B,1")) {
            rtnString = "100 %";
        } else if (sencerListName.equals("1,A,1")) {
            rtnString = "100 ℃";
        } else if (sencerListName.equals("6,N,2")) {
            rtnString = "100 Inch";
        } else if (sencerListName.equals("4,L,3")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("2,C,2")) {
            rtnString = "1000 Lux";
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("6,G,2")) {
            rtnString = "0";
        } else if (sencerListName.equals("4,E,2")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = "100 Inch";
        } else if (sencerListName.equals("4,L,1")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("4,E,1")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = "빠르기 10000";
        } else if (sencerListName.equals("1,I,4")) {
            rtnString = "100 %";
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = "100 Inch";
        } else if (sencerListName.equals("5,F,2")) {
            rtnString = "100 Inch";
        } else if (sencerListName.equals("3,K,4")) {
            rtnString = "100 Level";
        } else if (sencerListName.equals("6,G,1")) {
            rtnString = "100 Level";
        } else {
            rtnString = "10";
        }
        return rtnString;
    }

    public String getMinSencitivity(String sencerListName) {
        String rtnString = null;
        if (sencerListName.equals("1,B,1")) {
            rtnString = "0 %";
        } else if (sencerListName.equals("1,A,1")) {
            rtnString = "0 ℃";
        } else if (sencerListName.equals("6,N,2")) {
            rtnString = "0 Inch";
        } else if (sencerListName.equals("4,L,3")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("2,C,2")) {
            rtnString = "0 Lux";
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("6,G,2")) {
            rtnString = "0";
        } else if (sencerListName.equals("4,E,2")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = "0 Inch";
        } else if (sencerListName.equals("4,L,1")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("4,E,1")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("4,L,2")) {
            rtnString = "빠르기 1";
        } else if (sencerListName.equals("1,I,4")) {
            rtnString = "0 %";
        } else if (sencerListName.equals("5,F,5")) {
            rtnString = "0 Inch";
        } else if (sencerListName.equals("5,F,2")) {
            rtnString = "0 Inch";
        } else if (sencerListName.equals("3,K,4")) {
            rtnString = "0 Level";
        } else if (sencerListName.equals("6,G,1")) {
            rtnString = "0 Level";
        } else {
            rtnString = "0";
        }

        return rtnString;
    }


}
