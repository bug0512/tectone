package com.tectone.doubleguard.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by kookai on 2015-07-01.
 */
public class NetworkManager {
    private final Context mContext;
    NetworkInfo networkInfo = null;
    ConnectivityManager manager = null;

    public NetworkManager(Context context) {
        this.mContext = context;
        manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);


    }

    public static boolean isNetworkStat(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo lte_4g = manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
        boolean blte_4g = false;
        if (lte_4g != null)
            blte_4g = lte_4g.isConnected();
        if (mobile != null) {
            if (mobile.isConnected() || wifi.isConnected() || blte_4g)
                return true;
        } else {
            if (wifi.isConnected() || blte_4g)
                return true;
        }


        return false;
    }

    public boolean isNetworkStat(int _checktype) {
        boolean res = false;


        switch (_checktype) {
            case 1:
                //3G
                networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                break;
            case 2:
                //wifi
                networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                break;
            case 3:
                //lte
                networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
                break;
        }
        System.out.println("getTypeName==========================>" + networkInfo.getTypeName());
        System.out.println("getExtraInfo==========================>" + networkInfo.getExtraInfo());
        System.out.println("getSubtypeName==========================>" + networkInfo.getSubtypeName());


        if (networkInfo != null) {
            if (networkInfo.isConnected()) {
                res = true;
            }
        }
        return res;
    }

    public void showNetworkalert() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(mContext);
        dlg.setTitle("네트워크 오류");
        dlg.setMessage("네트워크 상태를 확인해 주십시요.");

        dlg.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        dlg.show();
    }

    public void showWifiAlert() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(mContext);
        dlg.setTitle("WIFI 오류");
        dlg.setMessage("확인을 누르시면 WIFI설정 창으로 이동합니다.");

        dlg.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_WIFI_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });
        dlg.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.cancel();
                    }
                });

        dlg.show();
    }


    public void showWifiAlert(String _message) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(mContext);
        dlg.setTitle("WIFI 오류");
        dlg.setMessage(_message);

        dlg.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_WIFI_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });
        dlg.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.cancel();
                    }
                });

        dlg.show();
    }

    public int getType() {
        return networkInfo.getType();
    }

    public int getSubtype() {
        return networkInfo.getSubtype();
    }

    public String getTypeName() {
        return networkInfo.getTypeName();

    }

    public String getSubtypeName() {
        return networkInfo.getSubtypeName();
    }

    public boolean isConnectedOrConnecting() {
        return networkInfo.isConnectedOrConnecting();
    }

    public boolean isConnected() {
        return networkInfo.isConnected();
    }

    public boolean isAvailable() {
        return networkInfo.isAvailable();
    }

    public boolean isFailover() {
        return networkInfo.isFailover();
    }

    public boolean isRoaming() {
        return networkInfo.isRoaming();
    }

    /**
     * wifi ap 단말 명
     *
     * @return
     */
    public String getExtraInfo() {
        return networkInfo.getExtraInfo();
    }
}
