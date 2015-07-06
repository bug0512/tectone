package com.tectone.doubleguard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.tectone.doubleguard.model.GraphBean;
import com.tectone.doubleguard.util.CommonConst;
import com.tectone.doubleguard.util.CommonUtil;
import com.tectone.doubleguard.util.Scene;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DashboardDetail extends ActionBarActivity {
    Context m_Context;
    ArrayList<BadgeListData> arrayData;
    ArrayList<GraphBean> g_ArrayData;
    BadgeListData data;
    GraphBean g_Data;
    DashboardDetailListAdapt m_adapter;
    ListView dashboard_list;

    ArrayList<SuggestListData> s_arrayData;
    ListView suggest_list;
    SuggestListData s_data;
    DashboardSuggestListAdapt m_dashboardSuggestListAdapt;

    LinearLayout linearLayout;

    boolean actState = false;
    boolean notiState = false;

    boolean rtnResult;

    String activationF = null;
    String notificationF = null;
    BadgeListData badgeListData = null;

    TextView activationF_btn;
    TextView notificationF_btn;

    int s_SuggestItem;
    String s_SuggestItemTitle;
    boolean mapping_Suggest = false;
    boolean del_Falg = false;
    View suggestList;

    AlertDialog dialog;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_detail);

        //actionbar hide
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();

        m_Context = this;
        suggestList = getLayoutInflater().inflate(R.layout.custom_dashboard_detail_layout, null, false);

        //전달된 INTENT 값 수신
        Intent intent = getIntent();
        badgeListData = (BadgeListData) intent.getSerializableExtra("badge_id");

        //badge list
        dashboard_list = (ListView) findViewById(R.id.selectBadge);
        arrayData = new ArrayList<BadgeListData>();
        arrayData.add(badgeListData);

        final String scenario_id = badgeListData.getScenario_id();
        final String scenarioName = badgeListData.getBadgeTitle();


        m_adapter = new DashboardDetailListAdapt(this, android.R.layout.simple_list_item_1, arrayData);
        dashboard_list.setAdapter(m_adapter);

        //suggest list
        suggest_list = (ListView) findViewById(R.id.dashboardSuggest);

        Scene scene = new Scene();
        String[] badgeListId = badgeListData.getBadge_list_id();

        //2014.11.18 Badge 시나리오 매핑이 하드코딩되어 있음.
        if (badgeListId.length == 1) {
            s_arrayData = scene.getSuggesList(badgeListId[0]);
            m_dashboardSuggestListAdapt = new DashboardSuggestListAdapt(this, android.R.layout.simple_list_item_1, s_arrayData);
            suggest_list.setAdapter(m_dashboardSuggestListAdapt);

        } else if (badgeListId.length == 2) {
            s_arrayData = scene.getSuggesListTwo(badgeListId[0], badgeListId[1]);
            m_dashboardSuggestListAdapt = new DashboardSuggestListAdapt(this, android.R.layout.simple_list_item_1, s_arrayData);
            suggest_list.setAdapter(m_dashboardSuggestListAdapt);
        } else {
            s_arrayData = scene.getSuggesList(badgeListId[0]);
            m_dashboardSuggestListAdapt = new DashboardSuggestListAdapt(this, android.R.layout.simple_list_item_1, s_arrayData);
            suggest_list.setAdapter(m_dashboardSuggestListAdapt);
        }

        //2014.11.25 추가
        setListViewHeightBasedOnChildren(suggest_list);

        suggest_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectSuggest(position, scenarioName);

                //시나리오 리스트와 상관없이 가상의 시나리오매핑으로 변경
                selectSuggestTemp(s_arrayData.get(position).getSuggestTitle(), scenarioName);
            }
        });

        //Graph Drawing

        /*
        int num = 70;
        GraphView.GraphViewData[] data = new GraphView.GraphViewData[num];
        double vV = 0;
        for (int i=0; i<num; i++) {
            vV += 0.2;
            data[i] = new GraphView.GraphViewData(i, Math.sin(vV));
        }
        */

        int num = 20;
        GraphView.GraphViewData[] data = new GraphView.GraphViewData[num];
        ArrayList<GraphBean> resultGraph = getJsonGraph(scenarioName);

        ArrayList<String> time = new ArrayList<String>();
        String[] times = null;
        if (resultGraph.size() > 0) {
            for (int i = 0; i < resultGraph.size(); i++) {
                data[i] = new GraphView.GraphViewData(Double.parseDouble(resultGraph.get(i).getX()), Double.parseDouble(resultGraph.get(i).getY()));

                String timeValue = resultGraph.get(i).getTime();
                time.add(timeValue);
            }
            times = time.toArray(new String[time.size()]);

        } else {
            for (int i = 0; i < num; i++) {
                data[i] = new GraphView.GraphViewData(i, 0);
            }
            times = new String[]{"00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00"};
        }

        GraphViewSeries exampleSeries = new GraphViewSeries("Test curve", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(255, 255, 255), 10), data);
        GraphView graphView = new LineGraphView(this, "");
        graphView.addSeries(exampleSeries); // data
        graphView.getGraphViewStyle().setGridColor(Color.WHITE);
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
        graphView.getGraphViewStyle().setNumHorizontalLabels(20);
        graphView.getGraphViewStyle().setVerticalLabelsWidth(45);
        graphView.setHorizontalLabels(times);

        //graphView.;
        linearLayout = (LinearLayout) findViewById(R.id.graph);
        linearLayout.addView(graphView);

        //버튼
        ImageButton b_back = (ImageButton) findViewById(R.id.badge_detail_back);
        b_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //ActivationFlag, NotificationFlag
        activationF_btn = (TextView) findViewById(R.id.activationFlag);
        notificationF_btn = (TextView) findViewById(R.id.notificationFlag);

        activationF = badgeListData.getBadge_act_flag();
        notificationF = badgeListData.getBadge_noti_flag();

        if (activationF.equals("0")) {
            activationF_btn.setText("ON");
            actState = false;
        } else {
            activationF_btn.setText("OFF");
            actState = true;
        }

        if (notificationF.equals("0")) {
            notificationF_btn.setText("ON");
            notiState = false;
        } else {
            notificationF_btn.setText("OFF");
            notiState = true;
        }

        activationF_btn.setOnClickListener(new View.OnClickListener() {
            String send_activation_value = "";

            public void onClick(View v) {
                if (actState) {
                    activationF_btn.setText("ON");
                    actState = false;
                    send_activation_value = "0";

                } else {
                    activationF_btn.setText("OFF");
                    actState = true;
                    send_activation_value = "1";
                }
                //Activation Server 전송
                showToeast(SendByHttp(scenarioName, "0", send_activation_value));
            }
        });

        notificationF_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String send_notification_value = "";
                if (notiState) {
                    notificationF_btn.setText("ON");
                    notiState = false;
                    send_notification_value = "0";

                } else {
                    notificationF_btn.setText("OFF");
                    notiState = true;
                    send_notification_value = "1";
                }
                //Notification Server 전송
                showToeast(SendByHttp(scenarioName, "1", send_notification_value));
            }
        });

        //삭제
        ImageView delBadge = (ImageView) findViewById(R.id.del_badge);
        delBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del_Falg = SendByHttpDel(scenarioName);
                if (del_Falg) {
                    showDelMessage(del_Falg, "Delete success");
                    //CommonUtil.showAlertDialog(m_Context, "Delete success", 2000);
                } else {
                    showDelMessage(del_Falg, "Delete fail");
                    //CommonUtil.showAlertDialog(m_Context, "Delete fail", 2000);
                }
            }
        });
    }

    /*
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void selectSuggest(int postion, String scenarioName) {
        switch (postion) {
            case 0:
                s_SuggestItem = 1;
                s_SuggestItemTitle = s_arrayData.get(postion).getSuggestTitle();
                break;
            case 1:
                s_SuggestItem = 2;
                s_SuggestItemTitle = s_arrayData.get(postion).getSuggestTitle();
                break;
            case 2:
                s_SuggestItem = 3;
                s_SuggestItemTitle = s_arrayData.get(postion).getSuggestTitle();
                break;
            case 3:
                s_SuggestItem = 4;
                s_SuggestItemTitle = s_arrayData.get(postion).getSuggestTitle();
                break;
            case 4:
                s_SuggestItem = 5;
                s_SuggestItemTitle = s_arrayData.get(postion).getSuggestTitle();
                break;
        }
        onCoachMark(scenarioName); //Alert 확인
    }

    private void selectSuggestTemp(String postionTitle, String scenarioName) {
        if (postionTitle.equals("LED로 옷장 습도 알림")) {
            s_SuggestItem = 1;
        } else if (postionTitle.equals("가스레인지를 켜 둔 상태에서 자리를 비우면 알림")) {
            s_SuggestItem = 2;
        } else if (postionTitle.equals("화장실에서의 시간 흐름을 LED 색 변화로 알림")) {
            s_SuggestItem = 3;
        } else if (postionTitle.equals("집 앞에서 사람이 서성거리면 TV on")) {
            s_SuggestItem = 4;
        } else if (postionTitle.equals("Sensor Badge를 흔들면 연인의 Sensor Badge에 신호를 보냄")) {
            s_SuggestItem = 5;
        } else {
            s_SuggestItem = 0;
        }

        s_SuggestItemTitle = postionTitle;
        Log.d("s_SuggestItem", "=============================== :" + s_SuggestItem);
        onCoachMark(scenarioName); //Alert 확인
    }

    public void onCoachMark(final String scenarioName) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.alert_layout);
        dialog.setCanceledOnTouchOutside(false);

        //for dismissing anywhere you touch
        final View masterView = dialog.findViewById(R.id.alert_master_view);
        TextView cancel = (TextView) masterView.findViewById(R.id.cancel);
        TextView apply = (TextView) masterView.findViewById(R.id.apply);
        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.cancel:
                        dialog.dismiss();
                        break;
                    case R.id.apply:
                        //Badge Scence 추가
                        //SendByHttp(Integer.toString(id), m_tagId);
                        //mapping_Suggest = SendByHttpChangeS(scenarioName, Integer.toString(s_SuggestItem));
                        mapping_Suggest = SendByHttpChangeS(scenarioName, s_SuggestItemTitle);
                        if (mapping_Suggest) {
                            // 클릭시 값을 전달받기 때문에 자체 표시로 기능 변경
                            TextView tv2 = (TextView) suggestList.findViewById(R.id.badgeContent);
                            tv2.setText(s_SuggestItemTitle);
                            tv2.setTextColor(Color.DKGRAY);
                            Log.d("s_SuggestItemTitle", s_SuggestItemTitle);
                            arrayData.get(0).setBadgeContent(s_SuggestItemTitle);
                            onResume();

                            CommonUtil.showAlertDialog(m_Context, "The new scene has been applied successfully.", 2000);
                        } else {
                            CommonUtil.showAlertDialog(m_Context, "The new scene has been applied unsuccessful.", 2000);
                        }
                        dialog.dismiss();
                        break;
                }
            }
        };
        cancel.setOnClickListener(cl);
        apply.setOnClickListener(cl);
        masterView.setOnClickListener(cl);
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        m_adapter = new DashboardDetailListAdapt(this, android.R.layout.simple_list_item_1, arrayData);
        dashboard_list.setAdapter(m_adapter);

        activationF = badgeListData.getBadge_act_flag();
        notificationF = badgeListData.getBadge_noti_flag();

        if (activationF.equals("0")) {
            activationF_btn.setText("ON");
            actState = false;
        } else {
            activationF_btn.setText("OFF");
            actState = true;
        }

        if (notificationF.equals("0")) {
            notificationF_btn.setText("ON");
            notiState = false;
        } else {
            notificationF_btn.setText("OFF");
            notiState = true;
        }
    }

    private void showDelMessage(boolean result, String message) {
        if (result) {
            dialog = CommonUtil.showAlertDialog(m_Context, message, 2000);

            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();

        } else {
            dialog = CommonUtil.showAlertDialog(m_Context, message, 2000);
        }
    }

    private void showToeast(boolean result) {
        if (result) {
            CommonUtil.showAlertDialog(m_Context, "The status has been applied successfully.", 2000);
        } else {
            CommonUtil.showAlertDialog(m_Context, "The status has been applied unsuccessful.", 2000);
        }
    }

    /*
     * JSON 서버 호출
     * BY. LEE
     */
    private boolean SendByHttp(String scenarioName, String variable, String value) {

        String ACTIVATE_URL = CommonConst.getHTTP_URL() + "/Service/SetActivation";
        String NOTIFICATE_URL = CommonConst.getHTTP_URL() + "/Service/SetNotification";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            if (variable.equals("0")) {
                //get = new HttpGet(URL + "/"+ scenario_id + "/" + value+ );
                get = new HttpGet(ACTIVATE_URL + "/" + URLEncoder.encode(scenarioName + "/" + value, "UTF-8"));
            } else {
                get = new HttpGet(NOTIFICATE_URL + "/" + URLEncoder.encode(scenarioName + "/" + value, "UTF-8"));
            }
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    private boolean SendByHttpChangeS(String scenarioName, String s_SuggestItemTitle) {

        String ACTIVATE_URL = CommonConst.getHTTP_URL() + "/Service/SetScenario";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(ACTIVATE_URL + "/" + URLEncoder.encode(scenarioName + "/" + s_SuggestItemTitle, "UTF-8"));

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    private boolean SendByHttpDel(String scenarioName) {

        String URL = CommonConst.getHTTP_URL() + "/Service/DeleteBand";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(URL + "/" + URLEncoder.encode(scenarioName, "UTF-8"));

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    /*
     * GRAPH DATA JSON PHASE
     * BY LEE.
     */
    public ArrayList<GraphBean> getJsonGraph(String bandName) {
        try {
            String url = CommonConst.getHTTP_URL() + "/Service/GetLog/" + URLEncoder.encode(bandName, "UTF-8");
            g_ArrayData = new ArrayList<GraphBean>();
            //JSON URL 세팅
            String line = getStringFromUrl(url);
            JSONObject object = new JSONObject(line);
            JSONArray Array = new JSONArray(object.getString("graph"));

            for (int i = 0; i < Array.length(); i++) {
                JSONObject insideObject = Array.getJSONObject(i);

                g_Data = new GraphBean(insideObject.getString("x"), insideObject.getString("y"), insideObject.getString("time"));
                g_ArrayData.add(g_Data);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return g_ArrayData;
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
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null)
            dialog.dismiss();
    }

}
