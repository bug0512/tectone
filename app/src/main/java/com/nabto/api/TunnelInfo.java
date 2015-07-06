package com.nabto.api;

public class TunnelInfo {
    public int nabtoStateInt;
    public int nabtoStatusInt;
    private NabtoStatus nabtoStatus;
    private NabtoState nabtoState;

    public TunnelInfo(int nabtoStatus, int nabtoState) {
        this.nabtoStatus = NabtoStatus.fromInteger(nabtoStatus);
        this.nabtoState = NabtoState.fromInteger(nabtoState);
        this.nabtoStateInt = nabtoState;
        this.nabtoStatusInt = nabtoStatus;
    }

    public NabtoStatus getNabtoStatus() {
        return nabtoStatus;
    }

    public NabtoState getNabtoState() {
        return nabtoState;
    }
}