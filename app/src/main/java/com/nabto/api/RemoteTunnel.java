package com.nabto.api;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;


public class RemoteTunnel {
    private NabtoApi _nabtoApi;
    private Session _session;
    private List<Tunnel> _tunnelList = new ArrayList<Tunnel>();
    private OnResultListener _onResultListener;
    private int _tryTimes = 15;

    public RemoteTunnel(Context ctx) {
        _nabtoApi = new NabtoApi(ctx);
        _nabtoApi.setStaticResourceDir();
        _nabtoApi.startup();
    }

    /*
    NTCS_CLOSED,
    NTCS_CONNECTING,
    NTCS_READY_FOR_RECONNECT,
    NTCS_UNKNOWN,
    NTCS_LOCAL,
    NTCS_REMOTE_P2P,
    NTCS_REMOTE_RELAY,
    NTCS_REMOTE_RELAY_MICRO,
    FAILED;
     */
    public void openTunnel(int id, int localPort, int remotePort, String remoteAddress) {
        new TunnelAsyncTask(id).execute(
                new TunnelParams(localPort, remotePort, remoteAddress));
    }

    public void closeTunnels() {
        // close all open tunnels
        if (_tunnelList.size() != 0) {
            _nabtoApi.close(_session);
            for (Tunnel tunnel : _tunnelList)
                _nabtoApi.nabtoTunnelCloseTcp(tunnel.getTunnel());
            _tunnelList.clear();
        }
    }

    //region event
    public void setOnResultListener(OnResultListener listener) {
        _onResultListener = listener;
    }

    public interface OnResultListener {
        void onResult(int id, String result);
    }

    class TunnelParams {
        public int localPort;
        public int remotePort;
        public String remoteAddress;

        public TunnelParams(int localPort, int remotePort, String remoteAddress) {
            this.localPort = localPort;
            this.remotePort = remotePort;
            this.remoteAddress = remoteAddress;
        }
    }

    class TunnelAsyncTask extends AsyncTask<TunnelParams, Void, String> {

        private int _id;

        public TunnelAsyncTask(int id) {
            _id = id;
        }

        @Override
        protected String doInBackground(TunnelParams... params) {
            TunnelParams tunnelParams = params[0];
            String state;

            _session = _nabtoApi.openSession("guest", "foobar");
            final Tunnel tunnel =
                    _nabtoApi.nabtoTunnelOpenTcp(
                            tunnelParams.localPort,
                            tunnelParams.remoteAddress,
                            "127.0.0.1",
                            tunnelParams.remotePort,
                            _session.getSession());

            if (tunnel == null ||
                    !tunnel.getStatus().toString().equals("OK"))
                return null;

            _tunnelList.add(tunnel);
            int tryTimes = 0;

            for (; ; ) {
                TunnelInfo tunnelInfo = _nabtoApi.nabtoTunnelInfo(tunnel.getTunnel());
                state = tunnelInfo.getNabtoState().toString();

                if (state.equals("NTCS_CONNECTING")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    tryTimes++;

                    if (tryTimes >= _tryTimes) {
                        state = "CONNECT_TIMEOUT";
                        break;
                    }

                    continue;
                }
                break;
            }

            return state;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            _onResultListener.onResult(_id, s);
        }
    }
    //endregion
}
