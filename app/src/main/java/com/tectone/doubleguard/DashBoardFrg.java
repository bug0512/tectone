package com.tectone.doubleguard;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tectone.doubleguard.util.CommonConst;

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
 * A simple {@link Fragment} subclass.
 * Use the {@link DashBoardFrg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFrg extends BaseFragment {
    public static Context mContext;

    ArrayList<BadgeListData> arrayData;
    BadgeListData data;
    DashboardListAdapt adapter;
    View customView;
    TextView emptyView;
    ListView dashboard_list;

    int checkCount = 0;
    int badgeCount = 0; //Badge Join Count

    boolean showActionItems = true;

    public DashBoardFrg() {
        // Required empty public constructor
    }

    public static DashBoardFrg newInstance() {
        DashBoardFrg fragment = new DashBoardFrg();
        return fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //JSON PHASE
        new JsonLoadingTask().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        //JSON PHASE
        new JsonLoadingTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard, container, false);
        mContext = this.getActivity();
        mainView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboard_list = (ListView) mainView.findViewById(R.id.dashboard_list);
        customView = inflater.inflate(R.layout.custom_dashboard_layout, container, false);
        emptyView = (TextView) mainView.findViewById(R.id.emptyRow);

        final ImageView addBadge = (ImageView) mainView.findViewById(R.id.add_new_bade);
        addBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddBadge.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }
        });

        //JSON PHASE
        new JsonLoadingTask().execute();

        setSlideBackButton();

        return mainView;
    }

    public void showActionItems(boolean show) {
        this.showActionItems = show;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // Show or hide action item
        menu.findItem(R.id.action_search).setVisible(showActionItems);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                /*
                startActivity(
                        new Intent(Intent.ACTION_WEB_SEARCH)
                                .putExtra(SearchManager.QUERY, "Lorem ipsum"));
                */
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_text, menu);
    }

    /*
     * BADGE LIST JSON PHASE
     * BY LEE.
     */
    public ArrayList<BadgeListData> getJsonText() {
        String url = CommonConst.getHTTP_URL() + "/Service/GetBadgeList";
        arrayData = new ArrayList<BadgeListData>();

        try {
            //TEST CODE
            //JSONObject object = new JSONObject(CommonUtil.readFileFromAssets("badgeList.txt", getActivity().getApplicationContext()));

            //JSON URL 세팅
            String line = getStringFromUrl(url);
            JSONObject object = new JSONObject(line);

            JSONArray Array = new JSONArray(object.getString("badge_lists"));


            for (int i = 0; i < Array.length(); i++) {
                JSONObject insideObject = Array.getJSONObject(i);
                JSONArray Array2 = new JSONArray(insideObject.getString("badge_image_list"));


                String[] badge_list_id = new String[Array2.length()];

                for (int j = 0; j < Array2.length(); j++) {
                    JSONObject insideImg = Array2.getJSONObject(j);

                    badge_list_id[j] = insideImg.getString("badge_list_id");
                }

                data = new BadgeListData(insideObject.getString("scenario_id"), insideObject.getString("badge_name"), insideObject.getString("badge_scenario_name"), badge_list_id, insideObject.getString("badge_use_flag"), insideObject.getString("badge_act_flag"), insideObject.getString("badge_noti_flag"));
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

    public void reload() {
        new JsonLoadingTask().execute();
    }

    private class JsonLoadingTask extends AsyncTask<String, Void, ArrayList<BadgeListData>> {
        @Override
        protected ArrayList<BadgeListData> doInBackground(String... strs) {

            return getJsonText();
        }

        @Override
        protected void onPostExecute(ArrayList<BadgeListData> result) {
            //VIEW 그리기
            checkCount = result.size();

            if (checkCount > 0) {
                adapter = new DashboardListAdapt(getActivity(), android.R.layout.simple_list_item_1, result);
                dashboard_list.setAdapter(adapter);

            } else {
                // CASE DATA NULL
                dashboard_list.setEmptyView(emptyView);
            }
        }
    }

}
