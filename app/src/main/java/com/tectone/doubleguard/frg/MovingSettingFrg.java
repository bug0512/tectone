package com.tectone.doubleguard.frg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tectone.doubleguard.BaseFragment;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.RecommandListData;
import com.tectone.doubleguard.StyledTextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovingSettingFrg extends BaseFragment implements View.OnClickListener, View.OnKeyListener {

    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;

    Context frag_activity = null;


    public MovingSettingFrg() {
        // Required empty public constructor
    }

    public MovingSettingFrg newInstance() {
        MovingSettingFrg fragment = new MovingSettingFrg();
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
        title_text.setText("움직임 감지 설정");

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

        data = new RecommandListData("움직임 감지 기능", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("자이로센서 감도", "", 0, "0");
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
                        dialogMovingOnOff();
                        break;
                    case 1:
                        dialogMovingCensor();
                        break;

                    default:
                        break;
                }


            }


        });

    }

    /**
     * 절전 기능
     */
    private void dialogMovingOnOff() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("움직임 감지기능 설정");
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

    /**
     * 저조도 감도 설정
     */
    private void dialogMovingCensor() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(frag_activity);
        final TextView title = new TextView(frag_activity);
        title.setText("자이로센서 감도");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.rgb(0, 153, 204));
        title.setTextSize(23);
        builder.setCustomTitle(title);

        LayoutInflater inflater_s = (LayoutInflater) frag_activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout_spinners = inflater_s.inflate(R.layout.spinner_layout, null);
        Spinner sp_titulos_carpetas = (Spinner) layout_spinners.findViewById(R.id.spinner_titulo_carpetas);

        builder.setView(layout_spinners);
        builder.setCancelable(false);
        final DialogInterface mPopupDlg = builder.show();


        ArrayList<String> lista_k = new ArrayList<String>();
        for (int i = 0; i < 256; i++) {
            lista_k.add("" + i);
        }

        ArrayAdapter<String> carpetas = new ArrayAdapter<String>(frag_activity, android.R.layout.simple_spinner_item, lista_k);
        carpetas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_titulos_carpetas.setAdapter(carpetas);

        Button btn_ok = (Button) layout_spinners.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) layout_spinners.findViewById(R.id.btn_cancel);


        sp_titulos_carpetas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(" ========================> CHOICE : ", position + " : " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd");
                mPopupDlg.dismiss();
            }
        });

    }

    /**
     * 저조도 감지 사용시간
     */
    private void dialogIlluminanceTime() {

        try {
            final CharSequence[] choice_items = {"1분", "5분", "10분", "30분"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("저조도 감지 시간");
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

        switch (getId) {

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

