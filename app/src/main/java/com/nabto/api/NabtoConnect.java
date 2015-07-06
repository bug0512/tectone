package com.nabto.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NabtoConnect {
    public static long timeSpan;
    boolean downloading = false;
    Timer timer = null;
    TimerTask timerTask = null;
    private NabtoApi nabtoApi;
    private Session session;
    private List<Tunnel> tunnelList;

    void Connect() {
        //nabtoApi = new NabtoApi(this);
        nabtoApi.setStaticResourceDir();
        nabtoApi.startup();
        session = nabtoApi.openSession("guest", "foobar");

        tunnelList = new ArrayList<Tunnel>();
    }

}
