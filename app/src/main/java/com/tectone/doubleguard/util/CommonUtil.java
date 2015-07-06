package com.tectone.doubleguard.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.tectone.doubleguard.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by JamesLee on 2014-10-22.
 */
public class CommonUtil {
    /*
     * TEST JSON TEST FILE
     * BY LEE.
     */
    public static String readFileFromAssets(String fileName, Context c) {
        try {
            InputStream is = c.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);

            return text;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getBadgeImageSmall(String badge_list_id) {
        int resourceNum = -1;

        if (badge_list_id.equals("1,D,2")) { //Temperature - Humid - LED
            resourceNum = R.drawable.badge_small_01;
        }
        if (badge_list_id.equals("1,A,1")) { //Temperature - Hot - LED
            resourceNum = R.drawable.badge_small_02;
        }
        if (badge_list_id.equals("6,N,1")) { //Motion - Away - speaker
            resourceNum = R.drawable.badge_small_04;
        }
        if (badge_list_id.equals("2,C,2")) { //Light - Bright - LED
            resourceNum = R.drawable.badge_small_03;
        }
        if (badge_list_id.equals("6,G,1")) { //Motion - Stay - Speaker
            resourceNum = R.drawable.badge_small_05;
        }
        if (badge_list_id.equals("4,E,1")) { //Accelerometer - Idle - Speaker
            resourceNum = R.drawable.badge_small_06;
        }
        if (badge_list_id.equals("4,L,2")) { //Accelerometer - Move - LED
            resourceNum = R.drawable.badge_small_07;
        }
        if (badge_list_id.equals("4,L,1")) { // Accelerometer - Move - Speaker
            resourceNum = R.drawable.badge_small_08;
        }
        return resourceNum;
    }

    public static int getBadgeImageBig(String badge_list_id) {
        int resourceNum = -1;

        if (badge_list_id.equals("1D2")) { //Temperature - Humid - LED
            resourceNum = R.drawable.badge_big_01;
        }
        if (badge_list_id.equals("1A2")) { //Temperature - Hot - LED
            resourceNum = R.drawable.badge_big_02;
        }
        if (badge_list_id.equals("6N1")) { //Motion - Away - speaker
            resourceNum = R.drawable.badge_big_04;
        }
        if (badge_list_id.equals("2C1")) { //Light - Bright - LED
            resourceNum = R.drawable.badge_big_03;
        }
        if (badge_list_id.equals("6G1")) { //Motion - Stay - Speaker
            resourceNum = R.drawable.badge_big_05;
        }
        if (badge_list_id.equals("4E1")) { //Accelerometer - Idle - Speaker
            resourceNum = R.drawable.badge_big_06;
        }
        if (badge_list_id.equals("4L2")) { //Accelerometer - Move - LED
            resourceNum = R.drawable.badge_big_07;
        }
        if (badge_list_id.equals("4L1")) { // Accelerometer - Move - Speaker
            resourceNum = R.drawable.badge_big_08;
        }
        if (badge_list_id.equals("8D8")) { // Tag Sample ID
            resourceNum = R.drawable.badge_big_08;
        }
        if (badge_list_id.equals("04C")) { // Tag Sample ID
            resourceNum = R.drawable.badge_big_07;
        }
        return resourceNum;
    }

    public static int[] getBigIndivisualImg(String badge_list_id) {
        int[] resourceNum = new int[3];
        /*
         * badge_list_id의 3자리는 sensor, activate, output 으로 구성됨(구분자 ",")
         */
        String[] badge_list = badge_list_id.split(",");

        //sensor 이미지획득
        resourceNum[0] = getSensorImageBig(badge_list[0]);
        //activate 이미지획득
        resourceNum[1] = getActivateImageBig(badge_list[1]);
        //output 이미지획득
        resourceNum[2] = getOutputImageBig(badge_list[2]);

        return resourceNum;
    }

    public static int[] getNewBigIndivisualImg(String badge_list_id) {
        int[] resourceNum = new int[3];

        badge_list_id = badge_list_id.substring(0, 3);

        //sensor 이미지획득
        resourceNum[0] = getSensorImageBig(Character.toString(badge_list_id.charAt(0)));
        //activate 이미지획득
        resourceNum[1] = getActivateImageBig(Character.toString(badge_list_id.charAt(1)));
        //output 이미지획득
        resourceNum[2] = getOutputImageBig(Character.toString(badge_list_id.charAt(2)));

        return resourceNum;
    }


    public static int getSensorImageBig(String badge_list_id) {
        int rtnNum = 0;

        if (badge_list_id.equals("1")) { //Temperature Humidity
            rtnNum = R.drawable.badge_browse_sensor_02_n;
        }
        if (badge_list_id.equals("2")) { //Light
            rtnNum = R.drawable.badge_browse_sensor_01_n;
        }
        if (badge_list_id.equals("3")) { //Microphone
            rtnNum = R.drawable.badge_browse_sensor_06_n;
        }
        if (badge_list_id.equals("4")) { //Accelerometer
            rtnNum = R.drawable.badge_browse_sensor_05_n;
        }
        if (badge_list_id.equals("5")) { //Proximity
            rtnNum = R.drawable.badge_browse_sensor_03_n;
        }
        if (badge_list_id.equals("6")) { //Motion
            rtnNum = R.drawable.badge_browse_sensor_04_n;
        }
        return rtnNum;
    }


    public static int getActivateImageBig(String badge_list_id) {
        int rtnNum = 0;

        if (badge_list_id.equals("A")) { //Hot
            rtnNum = R.drawable.badge_browse_activator_01_n;
        }
        if (badge_list_id.equals("B")) { //Humid
            rtnNum = R.drawable.badge_browse_activator_03_n;
        }
        if (badge_list_id.equals("C")) { //Bright
            rtnNum = R.drawable.badge_browse_activator_05_n;
        }
        if (badge_list_id.equals("D")) { //Quiet
            rtnNum = R.drawable.badge_browse_activator_14_n;
        }
        if (badge_list_id.equals("E")) { //Idle
            rtnNum = R.drawable.badge_browse_activator_12_n;
        }
        if (badge_list_id.equals("F")) { //Near
            rtnNum = R.drawable.badge_browse_activator_07_n;
        }
        if (badge_list_id.equals("G")) { //Stay
            rtnNum = R.drawable.badge_browse_activator_09_n;
        }
        if (badge_list_id.equals("H")) { //Cold
            rtnNum = R.drawable.badge_browse_activator_02_n;
        }
        if (badge_list_id.equals("I")) { //Dry
            rtnNum = R.drawable.badge_browse_activator_04_n;
        }
        if (badge_list_id.equals("J")) { //Dark
            rtnNum = R.drawable.badge_browse_activator_06_n;
        }
        if (badge_list_id.equals("K")) { //Noise--TODO.이미지잘못됨 변경필요
            rtnNum = R.drawable.badge_browse_activator_13_n;
        }
        if (badge_list_id.equals("L")) { //Move
            rtnNum = R.drawable.badge_browse_activator_11_n;
        }
        if (badge_list_id.equals("M")) { //Far
            rtnNum = R.drawable.badge_browse_activator_08_n;
        }
        if (badge_list_id.equals("N")) { //Away
            rtnNum = R.drawable.badge_browse_activator_10_n;
        }

        return rtnNum;
    }

    public static int getOutputImageBig(String badge_list_id) {
        int rtnNum = 0;
        if (badge_list_id.equals("1")) { //LED
            rtnNum = R.drawable.badge_browse_output_05_n;
        }
        if (badge_list_id.equals("2")) { //Speaker
            rtnNum = R.drawable.badge_browse_output_04_n;
        }
        if (badge_list_id.equals("3")) { //Push Message
            rtnNum = R.drawable.badge_browse_output_01_n;
        }
        if (badge_list_id.equals("4")) { //Badge
            rtnNum = R.drawable.badge_browse_output_02_n;
        }
        if (badge_list_id.equals("5")) { //Device TV
            rtnNum = R.drawable.badge_browse_output_03_n;
        }
        return rtnNum;
    }

    public static int getPopupTitleFirst(String badge_list_id) {
        int rtnNum = 0;

        if (badge_list_id.equals("1")) { //Temperature Humidity
            rtnNum = R.drawable.popup_what_temperature;
        }
        if (badge_list_id.equals("2")) { //Light
            rtnNum = R.drawable.popup_what_light;
        }
        if (badge_list_id.equals("3")) { //Microphone
            rtnNum = R.drawable.popup_what_mic;
        }
        if (badge_list_id.equals("4")) { //Accelerometer
            rtnNum = R.drawable.popup_what_accelerometer;
        }
        if (badge_list_id.equals("5")) { //Proximity
            rtnNum = R.drawable.popup_what_proximity;
        }
        if (badge_list_id.equals("6")) { //Motion
            rtnNum = R.drawable.popup_what_motion;
        }
        return rtnNum;
    }

    public static int getPopupTitleSecond(String badge_list_id) {
        int rtnNum = 0;

        if (badge_list_id.equals("A")) { //Hot
            rtnNum = R.drawable.popup_when_hot;
        }
        if (badge_list_id.equals("B")) { //Humid
            rtnNum = R.drawable.popup_when_humid;
        }
        if (badge_list_id.equals("C")) { //Bright
            rtnNum = R.drawable.popup_when_bright;
        }
        if (badge_list_id.equals("D")) { //Quiet
            rtnNum = R.drawable.popup_when_quiet;
        }
        if (badge_list_id.equals("E")) { //Idle
            rtnNum = R.drawable.popup_when_idle;
        }
        if (badge_list_id.equals("F")) { //Near
            rtnNum = R.drawable.popup_when_near;
        }
        if (badge_list_id.equals("G")) { //Stay
            rtnNum = R.drawable.popup_when_stay;
        }
        if (badge_list_id.equals("H")) { //Cold
            rtnNum = R.drawable.popup_when_cold;
        }
        if (badge_list_id.equals("I")) { //Dry
            rtnNum = R.drawable.popup_when_dry;
        }
        if (badge_list_id.equals("J")) { //Dark
            rtnNum = R.drawable.popup_when_dark;
        }
        if (badge_list_id.equals("K")) { //Sound
            rtnNum = R.drawable.popup_when_sound;
        }
        if (badge_list_id.equals("L")) { //Move
            rtnNum = R.drawable.popup_when_move;
        }
        if (badge_list_id.equals("M")) { //Far
            rtnNum = R.drawable.popup_when_far;
        }
        if (badge_list_id.equals("N")) { //Away
            rtnNum = R.drawable.popup_when_away;
        }

        return rtnNum;
    }

    public static int getPopupTitleThird(String badge_list_id) {
        int rtnNum = 0;
        if (badge_list_id.equals("1")) { //LED
            rtnNum = R.drawable.popup_how_led;
        }
        if (badge_list_id.equals("2")) { //Speaker
            rtnNum = R.drawable.popup_how_speaker;
        }
        if (badge_list_id.equals("3")) { //Push Message
            rtnNum = R.drawable.popup_how_linker_sms_05;
        }
        if (badge_list_id.equals("4")) { //Badge
            rtnNum = R.drawable.popup_how_linker_badge_05;
        }
        if (badge_list_id.equals("5")) { //Device TV
            rtnNum = R.drawable.popup_how_tv_05;
        }

        return rtnNum;
    }


    public static String getBadgeType(int resourceId) {
        String rtnString = null;
        switch (resourceId) {
            case R.drawable.badge_browse_sensor_01_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_sensor_02_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_sensor_03_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_sensor_04_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_sensor_05_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_sensor_06_n:
                rtnString = "S";
                break;
            case R.drawable.badge_browse_activator_01_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_02_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_03_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_04_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_05_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_06_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_07_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_08_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_09_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_10_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_11_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_12_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_activator_13_n:
                rtnString = "A";
                break;
            case R.drawable.badge_browse_output_01_n:
                rtnString = "O";
                break;
            case R.drawable.badge_browse_output_02_n:
                rtnString = "O";
                break;
            case R.drawable.badge_browse_output_03_n:
                rtnString = "O";
                break;
            case R.drawable.badge_browse_output_04_n:
                rtnString = "O";
                break;
            case R.drawable.badge_browse_output_05_n:
                rtnString = "O";
                break;
        }

        return rtnString;
    }


    public static void showDiaglog(Context mContext, boolean result, String message) {
        Toast toast = new Toast(mContext);
        if (result) {
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static AlertDialog showAlertDialog(Context context, String message, long delayMillis) {

        LayoutInflater factory = LayoutInflater.from(context);
        final View layout = factory.inflate(R.layout.toast_layout, null);
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(message);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(layout);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        }, delayMillis);

        return dialog;
    }


}
