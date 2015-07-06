package com.tectone.doubleguard.frg;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tectone.doubleguard.BadgeListAdapt;
import com.tectone.doubleguard.BaseFragment;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.RecommandListData;
import com.tectone.doubleguard.StyledTextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFrg extends BaseFragment implements View.OnClickListener {
    public static final String className = "SystemFrg";

    boolean checkData = false;

    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;
    FragmentActivity frag_activity = null;
    LayoutInflater inflater = null;
    boolean showActionItems = true;

    public SystemFrg() {
        // Required empty public constructor
    }

    public SystemFrg newInstance() {
        SystemFrg fragment = new SystemFrg();
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
        this.inflater = inflater;
//        mainView = inflater.inflate(R.layout.fragment_system, container, false);
//        browse_list = (ListView) mainView.findViewById(R.id.system_list);
        //ADD HEADER
        //header = inflater.inflate(R.layout.list_suggest_header, null, false);
        //browse_list.addHeaderView(header);

        mainView = inflater.inflate(R.layout.fragment_layout, container, false);
        StyledTextView title_text = (StyledTextView) mainView.findViewById(R.id.fragment_title);
        title_text.setText("시스템설정");

        browse_list = (ListView) mainView.findViewById(R.id.frage_submenu_list);
        /*
        //Browse 버튼제어
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

    }

    public void setSuggestData() {
        //Suggest List 생성

        //TEST DATA INSERT
        arrayData = new ArrayList<RecommandListData>();
        data = new RecommandListData("절전기능 설정", "ON", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("절전 타이머 설정", "1분", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("절전 시간대 설정", "ON", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("자이로 센서 감지", "ON", 0, "0");
        arrayData.add(data);
//        data = new RecommandListData("전원 주파수", "자동", 0, "0");
//        arrayData.add(data);

        adapter = new BadgeListAdapt(this.getActivity(), android.R.layout.simple_list_item_1, arrayData);
        browse_list.setAdapter(adapter);

        browse_list.setVisibility(View.VISIBLE);

        final LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // ListView 아이템 터치 시 이벤트 추가
        browse_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v(" onItemClick1111 : ", "=================>" + i + " : " + l);
                switch (i) {
                    case 0:
                        drawDialogEconomyInPower();
                        break;
                    case 1:
                        drawDialogEconomyInPowerTimer();
                        break;
                    case 2:
                        drawDialogEconomyInPowerHour();
                        break;
                    case 3:
                        drawDialogGyroOnOff();
                        break;
                    case 4:
                        //drawDialogPowerFrequency();
                        //mostrar_alertdialog_spinners();
                        break;
                    default:
                        break;
                }


            }


        });
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

    public void showActionItems(boolean show) {
        this.showActionItems = show;

    }

    /**
     * 절전기능 설정
     */
    private void drawDialogEconomyInPower() {

        final CharSequence[] choice_items = {"ON", "OFF"};
        AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
        ab.setTitle("절전기능 설정");
        //대화상자가  back버튼으로 닫히지 않도록 설정
        ab.setCancelable(false);

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
    }

    /**
     * 절전 타이머 설정
     */
    private void drawDialogEconomyInPowerTimer() {

        final CharSequence[] choice_items = {"1분", "5분", "10분"};
        AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
        ab.setTitle("절전 타이머 설정");
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
    }

    /**
     * 절전 시간대 설정
     */
    private void drawDialogEconomyInPowerHour() {

        final CharSequence[] choice_items = {"1분", "5분", "10분"};
        AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
        ab.setTitle("절전 시간대 설정");
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
    }

    /**
     * 활성화를 위한 자이로센서 감지
     */
    private void drawDialogGyroOnOff() {

        final CharSequence[] choice_items = {"ON", "OFF"};
        AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
        ab.setTitle("활성화를 위한 자이로센서 감지");
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
    }

    /**
     * 전원 주파수
     */
    private void drawDialogPowerFrequency() {

/*        final CharSequence[] choice_items = { "자동", "50Hz","60Hz" };
        AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
        ab.setTitle("전원주파수");
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
        ab.show();*/

        final Spinner s = (Spinner) mainView.findViewById(R.id.dynamicSpinner);
        ArrayList<String> items = new ArrayList<String>();
        items.add("KAI");
        items.add("ENF");
        items.add("KCNA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(frag_activity, android.R.layout.simple_spinner_item, items);
        adapter.add("List Item C");
        adapter.notifyDataSetChanged();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        //s.setOnItemSelectedListener(frag_activity.addContentView());
/*        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(frag_activity.getApplication(),
                        s.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });*/


/*
        Spinner spinner = (Spinner) mainView.findViewById(R.id.dynamicSpinner);

        spinner.setPrompt("시/도 를 선택하세요.");


        final ArrayAdapter<?>  adspin = ArrayAdapter.createFromResource(frag_activity, R.array.selected, android.R.layout.simple_spinner_item);
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adspin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(frag_activity.getApplication(),
                        adspin.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });*/
    }


    private void mostrar_alertdialog_spinners() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(frag_activity);
        final TextView title = new TextView(frag_activity);
        title.setText("Selecciona un archivo:");
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
        lista_k.add("Path A");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path B");
        lista_k.add("Path c");

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
}
