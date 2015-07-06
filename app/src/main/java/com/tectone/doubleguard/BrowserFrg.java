package com.tectone.doubleguard;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowserFrg extends BaseFragment implements View.OnClickListener {
    public static final String className = "BrowserFrg";

    boolean s1CheckState = false;
    boolean s2CheckState = false;
    boolean s3CheckState = false;
    boolean s4CheckState = false;
    boolean s5CheckState = false;
    boolean s6CheckState = false;
    boolean a1CheckState = false;
    boolean a2CheckState = false;
    boolean a3CheckState = false;
    boolean a4CheckState = false;
    boolean a5CheckState = false;

    ImageButton sensor1 = null;
    ImageButton sensor2 = null;
    ImageButton sensor3 = null;
    ImageButton sensor4 = null;
    ImageButton sensor5 = null;
    ImageButton sensor6 = null;
    ImageButton action1 = null;
    ImageButton action2 = null;
    ImageButton action3 = null;
    ImageButton action4 = null;
    ImageButton action5 = null;

    boolean checkData = false;

    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;

    public BrowserFrg() {
        // Required empty public constructor
    }

    public BrowserFrg newInstance() {
        BrowserFrg fragment = new BrowserFrg();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_system, container, false);

        mainView = inflater.inflate(R.layout.fragment_system, container, false);
        header = inflater.inflate(R.layout.list_suggest_header, null, false);
        browse_list = (ListView) mainView.findViewById(R.id.system_list);
        //ADD HEADER
        browse_list.addHeaderView(header);

        //Browse 버튼제어
        /*
        sensor1 = (ImageButton) mainView.findViewById(R.id.sensor1);
        sensor1.setOnClickListener(this);
        sensor2 = (ImageButton) mainView.findViewById(R.id.sensor2);
        sensor2.setOnClickListener(this);
        sensor3 = (ImageButton) mainView.findViewById(R.id.sensor3);
        sensor3.setOnClickListener(this);
        sensor4 = (ImageButton) mainView.findViewById(R.id.sensor4);
        sensor4.setOnClickListener(this);
        sensor5 = (ImageButton) mainView.findViewById(R.id.sensor5);
        sensor5.setOnClickListener(this);
        sensor6 = (ImageButton) mainView.findViewById(R.id.sensor6);
        sensor6.setOnClickListener(this);
        action1 = (ImageButton) mainView.findViewById(R.id.action1);
        action1.setOnClickListener(this);
        action2 = (ImageButton) mainView.findViewById(R.id.action2);
        action2.setOnClickListener(this);
        action3 = (ImageButton) mainView.findViewById(R.id.action3);
        action3.setOnClickListener(this);
        action4 = (ImageButton) mainView.findViewById(R.id.action4);
        action4.setOnClickListener(this);
        action5 = (ImageButton) mainView.findViewById(R.id.action5);
        action5.setOnClickListener(this);

        init();
        */

        setSuggestData();
        setSlideBackButton();

        return mainView;
    }

    public void init() {

        if (s1CheckState) sensor1.setSelected(true);
        if (s2CheckState) sensor2.setSelected(true);
        if (s3CheckState) sensor3.setSelected(true);
        if (s4CheckState) sensor4.setSelected(true);
        if (s5CheckState) sensor5.setSelected(true);
        if (s6CheckState) sensor6.setSelected(true);
        if (a1CheckState) action1.setSelected(true);
        if (a2CheckState) action2.setSelected(true);
        if (a3CheckState) action3.setSelected(true);
        if (a4CheckState) action4.setSelected(true);
        if (a5CheckState) action5.setSelected(true);
    }

    public void setSuggestData() {
        //Suggest List 생성
        checkData = (s1CheckState || s2CheckState || s3CheckState || s4CheckState || s5CheckState || s6CheckState) && (a1CheckState || a2CheckState || a3CheckState || a4CheckState || a5CheckState);
        if (checkData) {
            //TEST DATA INSERT
            arrayData = new ArrayList<RecommandListData>();
            data = new RecommandListData("My Bathroom unit number 01", "Wake me up when september ends", R.drawable.badge_small_01, "0");
            arrayData.add(data);
            data = new RecommandListData("My Bathroom unit number 02", "Wake me up when september starts", R.drawable.badge_small_02, "0");
            arrayData.add(data);


            adapter = new BadgeListAdapt(this.getActivity(), android.R.layout.simple_list_item_1, arrayData);
            browse_list.setAdapter(adapter);

            browse_list.setVisibility(View.VISIBLE);
        } else {
            browse_list.setVisibility(View.INVISIBLE);
            //onCoachMark();
        }
    }


    @Override
    public void onClick(View v) {
        int getId = v.getId();
        switch (getId) {

            /*
            case R.id.sensor1:
                if(s1CheckState) {
                   sensor1.setSelected(false);
                   s1CheckState = false;
                } else {
                   sensor1.setSelected(true);
                    s1CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.sensor2:
                if(s2CheckState) {
                    sensor2.setSelected(false);
                    s2CheckState = false;
                } else {
                    sensor2.setSelected(true);
                    s2CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.sensor3:
                if(s3CheckState) {
                    sensor3.setSelected(false);
                    s3CheckState = false;
                } else {
                    sensor3.setSelected(true);
                    s3CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.sensor4:
                if(s4CheckState) {
                    sensor4.setSelected(false);
                    s4CheckState = false;
                } else {
                    sensor4.setSelected(true);
                    s4CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.sensor5:
                if(s5CheckState) {
                    sensor5.setSelected(false);
                    s5CheckState = false;
                } else {
                    sensor5.setSelected(true);
                    s5CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.sensor6:
                if(s6CheckState) {
                    sensor6.setSelected(false);
                    s6CheckState = false;
                } else {
                    sensor6.setSelected(true);
                    s6CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.action1:
                if(a1CheckState) {
                    action1.setSelected(false);
                    a1CheckState = false;
                } else {
                    action1.setSelected(true);
                    a1CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.action2:
                if(a2CheckState) {
                    action2.setSelected(false);
                    a2CheckState = false;
                } else {
                    action2.setSelected(true);
                    a2CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.action3:
                if(a3CheckState) {
                    action3.setSelected(false);
                    a3CheckState = false;
                } else {
                    action3.setSelected(true);
                    a3CheckState = true;
                }
                setSuggestData();
                break;

            case R.id. action4:
                if(a4CheckState) {
                    action4.setSelected(false);
                    a4CheckState = false;
                } else {
                    action4.setSelected(true);
                    a4CheckState = true;
                }
                setSuggestData();
                break;

            case R.id.action5:
                if(a5CheckState) {
                    action5.setSelected(false);
                    a5CheckState = false;
                } else {
                    action5.setSelected(true);
                    a5CheckState = true;
                }
                setSuggestData();
                break;

        */
        }
    }

    public void onCoachMark() {
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.coach_mark);
        dialog.setCanceledOnTouchOutside(true);
        //for dismissing anywhere you touch
        View masterView = dialog.findViewById(R.id.coach_mark_master_view);
        masterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
