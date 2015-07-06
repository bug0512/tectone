package com.nabto.api;

public class Tunnel {

    private static int numberOfTunnel;
    private String tunnelId;
    private Object tunnel;
    private NabtoStatus nabtoStatus;

    public Tunnel(Object _tunnel, int _nabtoStatus) {
        tunnelId = "Tunnel " + numberOfTunnel;
        tunnel = _tunnel;
        nabtoStatus = NabtoStatus.fromInteger(_nabtoStatus);
        numberOfTunnel++;
    }

    public Object getTunnel() {
        return tunnel;
    }

    public NabtoStatus getStatus() {
        return nabtoStatus;
    }

    @Override
    public String toString() {
        return tunnelId;
    }
}
