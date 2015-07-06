package com.tectone.doubleguard.frg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tectone.doubleguard.BadgeListAdapt;
import com.tectone.doubleguard.BaseFragment;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.RecommandListData;
import com.tectone.doubleguard.StyledTextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFrg extends BaseFragment implements View.OnClickListener, View.OnKeyListener {
    public static final String className = "ReservationFrg";


    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;

    Context codecActivity = null;
    PopupWindow pwindo;
    boolean showActionItems = true;
    int exit_cnt = 0;
    private AlertDialog mDialog = null;

    public ReservationFrg() {
        // Required empty public constructor
    }

    public ReservationFrg newInstance() {
        ReservationFrg fragment = new ReservationFrg();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        codecActivity = this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mainView = inflater.inflate(R.layout.fragment_reservation, container, false);
        mainView = inflater.inflate(R.layout.fragment_layout, container, false);
        StyledTextView title_text = (StyledTextView) mainView.findViewById(R.id.fragment_title);
        title_text.setText("예약 녹화 설정");

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

        data = new RecommandListData("예약 녹화 설정", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("녹화 시간 설정", "", 0, "0");
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
                        dialogReservationRecordingOnOff();
                        //popdialog();
                        break;
                    case 1:
                        dialogReservationRecordingTime();
                        break;
                    default:
                        break;
                }


            }


        });

    }

    /**
     * 예약녹화 설정
     */
    private void dialogReservationRecordingOnOff() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(codecActivity);
            ab.setTitle("예약 녹화 설정");
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
     * 예약녹화 설정
     */
    private void dialogReservationRecordingTime() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(codecActivity);
            ab.setTitle("녹화시간 설정");
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


    private void popdialog(View view) {
        //클릭시 팝업 윈도우 생성
        PopupWindow popup = new PopupWindow(view);
        //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //팝업으로 띄울 커스텀뷰를 설정하고
        LayoutInflater inflater_s = (LayoutInflater) codecActivity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout_spinners = inflater_s.inflate(R.layout.spinner_layout, null);

        View v = inflater_s.inflate(R.layout.test_popup_window, null);
        popup.setContentView(v);
        //팝업의 크기 설정
        popup.setWindowLayoutMode(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //팝업 뷰 터치 되도록
        popup.setTouchable(true);
        //팝업 뷰 포커스도 주고
        popup.setFocusable(true);
        //팝업 뷰 이외에도 터치되게 (터치시 팝업 닫기 위한 코드)
        popup.setOutsideTouchable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        //인자로 넘겨준 v 아래로 보여주기
        popup.showAsDropDown(v);
    }

    private void DialogRadio() {
        System.out.println("==========================>" + codecActivity);

        final CharSequence[] PhoneModels = {"mp4", "avi", "wmv"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(codecActivity);
        alt_bld.setIcon(R.drawable.ic_launcher);
        alt_bld.setTitle("압축품질 변경");
        alt_bld.setSingleChoiceItems(PhoneModels, -1,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(codecActivity, "choice = "
                                + PhoneModels[item], Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    private void DialogSelectOption() {

        try {
            final String items[] = {"h.264", "h.263", "h.262"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(codecActivity);
            ab.setTitle("코덱종류 선택");
            ab.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
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

    public void showActionItems(boolean show) {
        this.showActionItems = show;

    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        return false;
    }
}

