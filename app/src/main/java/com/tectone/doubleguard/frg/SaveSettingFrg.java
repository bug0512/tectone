package com.tectone.doubleguard.frg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
public class SaveSettingFrg extends BaseFragment implements View.OnClickListener, View.OnKeyListener {

    ArrayList<RecommandListData> arrayData;
    RecommandListData data;
    BadgeListAdapt adapter;

    ListView browse_list;
    View header;

    Context frag_activity = null;


    public SaveSettingFrg() {
        // Required empty public constructor
    }

    public SaveSettingFrg newInstance() {
        SaveSettingFrg fragment = new SaveSettingFrg();
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
        title_text.setText("저장");

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

        data = new RecommandListData("MicroSD 정보", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("MicroSD Format", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("스마트폰SD Format", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("저장 정책", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("클라우드 설정", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("Camera 덥어쓰기", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("스마트폰 덥어쓰기", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("클라우드 덥어쓰기", "", 0, "0");
        arrayData.add(data);
        data = new RecommandListData("코덱", "", 0, "0");
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
                        dialogMicroSdInfo();
                        break;
                    case 1:
                        dialogCameraSdFormat();
                        break;
                    case 2:
                        dialogPhoneSdFormat();
                        break;
                    case 3:
                        dialogSaveRule();
                        break;
                    case 4:
                        dialogCloudSetting();
                        break;
                    case 5:
                        dialogCameraOverriting();
                        break;
                    case 6:
                        dialogPhoneOverriting();
                        break;
                    case 7:
                        dialogCloudOverriting();
                        break;
                    case 8:
                        dialogCodec();
                        break;
                    default:
                        break;
                }


            }


        });

    }

    private void dialogMicroSdInfo() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(frag_activity);
        alt_bld.setTitle("MicroSD 정보");
        alt_bld.setMessage("정보 무엇을 보여줘야 하는가?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Action for 'NO' Button
                dialog.cancel();
            }
        });

        alt_bld.show();
    }

    private void dialogCameraSdFormat() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(frag_activity);
        alt_bld.setTitle("Format");
        alt_bld.setMessage("Camera의 MicroSD를 Format하시겠습니까?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Action for 'NO' Button
                dialog.cancel();
            }
        });

        alt_bld.show();
    }

    private void dialogPhoneSdFormat() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(frag_activity);
        alt_bld.setTitle("Format");
        alt_bld.setMessage("스마트폰의 MicroSD를 Format하시겠습니까?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Action for 'NO' Button
                dialog.cancel();
            }
        });

        alt_bld.show();
    }

    /**
     * 저장 정책
     */
    private void dialogSaveRule() {

        try {
            final CharSequence[] choice_items = {"저장안함", "상시녹화", "1분전부터 녹화", "5분전부터 녹화", "10분전부터 녹화"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("저장정책");
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

    private void dialogCloudSetting() {

        try {
            final CharSequence[] choice_items = {"화면 그릴것."};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("클라우드 설정");
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
     * 저장 정책
     */
    private void dialogCameraOverriting() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("Carmera 덥어쓰기");
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
     * 폰 덥어 쓰기
     */
    private void dialogPhoneOverriting() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("Phone 덥어쓰기");
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

    private void dialogCloudOverriting() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("Cloud 덥어쓰기");
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

    private void dialogCodec() {

        try {
            final CharSequence[] choice_items = {"ON", "OFF"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(frag_activity);
            ab.setTitle("Cloud 덥어쓰기");
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

