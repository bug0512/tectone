package com.tectone.doubleguard.frg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tectone.doubleguard.BaseFragment;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.RecommandListData;
import com.tectone.doubleguard.StyledTextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioMuteSettingFrg extends BaseFragment implements View.OnClickListener, View.OnKeyListener {


    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;

    Context frag_activity = null;


    public AudioMuteSettingFrg() {
        // Required empty public constructor
    }

    public AudioMuteSettingFrg newInstance() {
        AudioMuteSettingFrg fragment = new AudioMuteSettingFrg();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag_activity = this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_layout, container, false);
        StyledTextView title_text = (StyledTextView) mainView.findViewById(R.id.fragment_title);
        title_text.setText("Audio Mute");

        browse_list = (ListView) mainView.findViewById(R.id.frage_submenu_list);
        //ADD HEADER
        //header = inflater.inflate(R.layout.list_suggest_header, null, false);
        //browse_list.addHeaderView(header);

        setSuggestData();

        setSlideBackButton();

        return mainView;
    }

    public void init() {

    }

    public void setSuggestData() {
        //Suggest List 생성

        //TEST DATA INSERT
        arrayData = new ArrayList<RecommandListData>();

        data = new RecommandListData("Audio Mute", "", 0, "0");
        arrayData.add(data);

        adapter = new BadgeListAdapt(this.getActivity(), android.R.layout.simple_list_item_1, arrayData);
        browse_list.setAdapter(adapter);

        browse_list.setVisibility(View.VISIBLE);


        final LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // ListView 아이템 터치 시 이벤트 추가
        browse_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        dialogAudioMutenOff();
                        break;
                    default:
                        break;
                }


            }


        });

    }

    /**
     * 오디오 알람 기능 설정
     */
    private void dialogAudioMutenOff() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("Audio Mute");
            ab.setSingleChoiceItems(choice_items, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // 각 리스트를 선택했을때
                }
            }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Cancel 버튼 클릭시
                        }
                    }
            );

            ab.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        int getId = v.getId();

        Log.v("id : ", "=================>" + v.getId());

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
        */
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        return false;
    }
}

