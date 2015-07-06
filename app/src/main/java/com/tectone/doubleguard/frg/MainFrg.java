package com.tectone.doubleguard.frg;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TableLayout;

import com.tectone.doubleguard.BadgeListAdapt;
import com.tectone.doubleguard.BaseFragment;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.RecommandListData;
import com.tectone.doubleguard.video.Play;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFrg extends BaseFragment implements View.OnClickListener {
    public static final String className = "MainFrg";

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

    boolean checkData = false;

    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    View header;
    TableLayout btn_webcam;
    boolean showActionItems = true;

    public MainFrg() {
        // Required empty public constructor
    }

    public MainFrg newInstance() {
        MainFrg fragment = new MainFrg();
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
        mainView = inflater.inflate(R.layout.fragment_main, container, false);

        //ADD HEADER
        //header = inflater.inflate(R.layout.list_suggest_header, null, false);
        //browse_list.addHeaderView(header);

        btn_webcam = (TableLayout) mainView.findViewById(R.id.btn_webcam);
        btn_webcam.setOnClickListener(this);

        setSlideBackButton();

        return mainView;
    }

    public void init() {

    }


    @Override
    public void onClick(View v) {
        int getId = v.getId();
        switch (getId) {
            case R.id.btn_webcam:
                Intent intent = new Intent();
                //intent.setClass(frag_activity, Play.class);
                intent.setClass(getActivity(), Play.class);
                intent.putExtra("name", "admin");
                intent.putExtra("psk", "admin");
                intent.putExtra("id", "lx520.e09bf0.china.nabto.net");
                intent.putExtra("ip", "192.168.100.1");
                intent.putExtra("port", 554);
                intent.putExtra("voice", true);
                startActivity(intent);
                break;

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

    public void showActionItems(boolean show) {
        this.showActionItems = show;
    }


}
