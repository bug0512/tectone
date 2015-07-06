package com.tectone.doubleguard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.tectone.doubleguard.model.NotiBean;
import com.tectone.doubleguard.util.CommonUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class NotificationService extends Service implements Runnable {

    ArrayList<NotiBean> n_arrayData;
    NotiBean n_data;
    private NotificationManager nm;
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    NotificationService();
                    break;
                case 2:  // 기타 전달인자로 인한 처리도 가능
                    break;
                case 3:
                    break;
            }
        }
    };


    public NotificationService() {

        //NOTIFICATION SERVICE
        Thread time_thread = new Thread(this); //자식쓰레드 생성
        time_thread.start(); // 쓰레드 시작
    }

    public static InputStream getInputStreamFromUrl(String url) {
        InputStream contentStream = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            //String c_Url = URLEncoder.encode(url, "utf-8");
            HttpResponse response = httpclient.execute(new HttpGet(url));
            contentStream = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentStream;
    }

    private void NotificationService() {
        try {
            //NOTIFICATION START
            String url = "http://192.168.0.46:5531/Service/GetNotification";
            n_arrayData = new ArrayList<NotiBean>();

            try {
                JSONObject object = new JSONObject(CommonUtil.readFileFromAssets("noti.txt", getApplicationContext()));
                //JSON URL 세팅
                //String line = getStringFromUrl(url);
                // JSONObject object = new JSONObject(line);
                JSONArray Array = new JSONArray(object.getString("notification"));

                for (int i = 0; i < Array.length(); i++) {
                    JSONObject insideObject = Array.getJSONObject(i);
                    n_data = new NotiBean(insideObject.getString("scenario_id"), insideObject.getString("scenario_content"), insideObject.getString("badge_name"));
                    n_arrayData.add(n_data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            PendingIntent intent = PendingIntent.getActivity(getBaseContext(), 0, new Intent(getBaseContext(), NotificationService.class), 0);

            if (n_arrayData.size() > 0) {
                for (int i = 0; i < n_arrayData.size(); i++) {
                    String ticker = n_arrayData.get(i).getScenario_content();
                    String title = n_arrayData.get(i).getBadge_name();

                    // Create Notification Object
                    Notification notification = new Notification(android.R.drawable.ic_input_add, ticker, System.currentTimeMillis());
                    notification.setLatestEventInfo(getBaseContext(), ticker, title, intent);

                    nm.notify(1234, notification);
//                    Toast.makeText(this, "Notification Registered.", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStringFromUrl(String url) throws UnsupportedEncodingException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getInputStreamFromUrl(url), "UTF-8"));
        StringBuffer sb = new StringBuffer();

        try {
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // 1초의 딜레이
                Log.d("Thread Start", "=====================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.sendMessage(Message.obtain(mHandler, 1));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
