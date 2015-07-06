package com.tectone.doubleguard.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Looper;

import com.tectone.doubleguard.model.NotiBean;
import com.tectone.doubleguard.util.CommonUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by JamesLee on 2014-10-22.
 */
public class NotiService extends Service {
    ArrayList<NotiBean> arrayData;
    NotiBean data;
    private NotificationManager nm;

    private Looper m_ServiceLooper;

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

    @Override
    public void onCreate() {
        super.onCreate();
        new NotiTask().execute();
    }

    public ArrayList<NotiBean> getJsonText() {
        String url = "";
        arrayData = new ArrayList<NotiBean>();

        try {
            //TEST CODE
            JSONObject object = new JSONObject(CommonUtil.readFileFromAssets("noti.txt", getApplicationContext()));

            //JSON URL 세팅
            //String line = getStringFromUrl(url);
            //JSONObject object = new JSONObject(line);

            JSONArray Array = new JSONArray(object.getString("notification"));


            for (int i = 0; i < Array.length(); i++) {
                JSONObject insideObject = Array.getJSONObject(i);
                data = new NotiBean(insideObject.getString("scenario_id"), insideObject.getString("scenario_content"), insideObject.getString("badge_name"));

                arrayData.add(data);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayData;
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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class NotiTask extends AsyncTask<String, Void, ArrayList<NotiBean>> {
        @Override
        protected ArrayList<NotiBean> doInBackground(String... strs) {
            return getJsonText();
        } // doInBackground : 백그라운드 작업을 진행한다.

        @Override
        protected void onPostExecute(ArrayList<NotiBean> result) {
            //NOTIFICATION 알림

            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            PendingIntent intent = PendingIntent.getActivity(NotiService.this, 0, new Intent(NotiService.this, NotiService.class), 0);

            for (int i = 0; i < result.size(); i++) {
                String ticker = result.get(i).getScenario_content();
                String title = result.get(i).getBadge_name();

                // Create Notification Object
                Notification notification = new Notification(android.R.drawable.ic_input_add, ticker, System.currentTimeMillis());
                notification.setLatestEventInfo(NotiService.this, title, title, intent);

                nm.notify(1234, notification);
//                Toast.makeText(NotiService.this, "Notification Registered.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
