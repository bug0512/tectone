package com.tectone.doubleguard.util;

/**
 * Created by JamesLee on 2014-10-25.
 */
public class CommonConst {
    public static String HTTP_URL;

    //public static String HTTP_URL = "http://192.168.0.45:5531";
    public CommonConst(String HTTP_URL) {
        this.setHTTP_URL(HTTP_URL);
    }

    public static String getHTTP_URL() {
        return HTTP_URL;
    }

    public void setHTTP_URL(String HTTP_URL) {
        CommonConst.HTTP_URL = HTTP_URL;
    }
}
