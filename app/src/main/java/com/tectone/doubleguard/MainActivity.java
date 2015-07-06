package com.tectone.doubleguard;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nabto.api.RemoteTunnel;
import com.tectone.doubleguard.db.SQLiteDbManager;
import com.tectone.doubleguard.frg.AudioMuteSettingFrg;
import com.tectone.doubleguard.frg.AudioSettingFrg;
import com.tectone.doubleguard.frg.CameraSettingFrg;
import com.tectone.doubleguard.frg.IlluminanceFrg;
import com.tectone.doubleguard.frg.LocationSettingFrg;
import com.tectone.doubleguard.frg.MainFrg;
import com.tectone.doubleguard.frg.MovingSettingFrg;
import com.tectone.doubleguard.frg.ReservationFrg;
import com.tectone.doubleguard.frg.SaveSettingFrg;
import com.tectone.doubleguard.frg.SearchSettingFrg;
import com.tectone.doubleguard.frg.SystemFrg;
import com.tectone.doubleguard.frg.UserSettingFrg;
import com.tectone.doubleguard.model.NotiBean;
import com.tectone.doubleguard.util.Preference;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    static final String KEY_FRAGMENT_ID = "fragment_id";
    static final int FRAGMENT_MAIN = 0;
    static final int FRAGMENT_SYSTEM = 1;
    static final int FRAGMENT_RESERVATION = 2;
    static final int FRAGMENT_CAMERA = 3;
    static final int FRAGMENT_AUDIO = 4;
    static final int FRAGMENT_ILLUMINANCE = 5;
    static final int FRAGMENT_MOVING = 6;
    static final int FRAGMENT_LOCATION = 7;
    static final int FRAGMENT_SAVE = 8;
    static final int FRAGMENT_USER = 9;
    static final int FRAGMENT_MUTE = 10;
    static final int FRAGMENT_SEARCH = 11;

    public static Context m_Context;
    public static RemoteTunnel _remoteTunnel;
    public static boolean remote = false;     //false L local, true L remote
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ListView lvDrawerList;
    /*
     * TODO. 설정팝업 FLG 재정의 BY LEE.
     */
    MainFrg mainFrg;
    SystemFrg systemFrg;
    ReservationFrg reservationFrg;
    CameraSettingFrg cameraSettingFrg;
    AudioSettingFrg audioSettingFrg;
    IlluminanceFrg illuminanceFrg;
    MovingSettingFrg movingSettingFrg;
    LocationSettingFrg locationSettingFrg;
    SaveSettingFrg saveSettingFrg;
    UserSettingFrg userSettingFrg;
    AudioMuteSettingFrg audioMuteSettingFrg;
    SearchSettingFrg searchSettingFrg;
    ArrayList<CustomListData> arrayData;
    CustomListData data;
    CustomListAdapt adapter;
    ArrayList<NotiBean> n_arrayData;
    NotiBean n_data;
    int lastFragmentId = FRAGMENT_SYSTEM;
    boolean m_final_url = false;
    int exit_cnt = 0;
    private NotificationManager nm;

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        return dialog;
    }

    private void selectFragment(int fragmentId) {
        exit_cnt = 0;
        switch (fragmentId) {
            case FRAGMENT_MAIN:
                exit_cnt++;
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, mainFrg).commit();
                break;
            case FRAGMENT_SYSTEM:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, systemFrg).commit();
                break;

            case FRAGMENT_RESERVATION:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, reservationFrg).commit();
                break;
            case FRAGMENT_CAMERA:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, cameraSettingFrg).commit();
                break;
            case FRAGMENT_AUDIO:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, audioSettingFrg).commit();
                break;
            case FRAGMENT_ILLUMINANCE:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, illuminanceFrg).commit();
                break;
            case FRAGMENT_MOVING:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, movingSettingFrg).commit();
                break;
            case FRAGMENT_LOCATION:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, locationSettingFrg).commit();
                break;
            case FRAGMENT_SAVE:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, saveSettingFrg).commit();
                break;
            case FRAGMENT_USER:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, userSettingFrg).commit();
                break;
            case FRAGMENT_MUTE:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, audioMuteSettingFrg).commit();
                break;
            case FRAGMENT_SEARCH:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, searchSettingFrg).commit();
                break;

        }
        lastFragmentId = fragmentId;
    }

    public void toggleDrawerMenu() {
        if (dlDrawer.isDrawerOpen(Gravity.LEFT)) {
            dlDrawer.closeDrawer(Gravity.LEFT);
        } else {
            dlDrawer.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Context = this;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /*
         * TODO. 보호자와 사용자의 상태 저장으로 변경
         * 2015.06.04 BY LEE.
         */
        Preference pf = new Preference(this);
        String user_type = pf.getValue("USER_TYPE", "");

        if (user_type.equals("") || user_type == null) {
            //Activity 생성
//            Intent it = new Intent(this, URLInput.class);
            Intent it = new Intent(this, UserTypeChoiceActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            finish();
        } else if ("1".equals(user_type)) {   //사용자
            m_final_url = true;

            arrayData = new ArrayList<CustomListData>();

            data = new CustomListData("시스템 설정");
            arrayData.add(data);
            data = new CustomListData("예약 녹화 설정");
            arrayData.add(data);
            data = new CustomListData("카메라 설정");
            arrayData.add(data);
            data = new CustomListData("음성 알람 설정");
            arrayData.add(data);
            data = new CustomListData("저조도 알람 설정");
            arrayData.add(data);
            data = new CustomListData("움직임 감지 설정");
            arrayData.add(data);
            data = new CustomListData("위치 알람 설정");
            arrayData.add(data);
            data = new CustomListData("저장");
            arrayData.add(data);
            data = new CustomListData("모드설정 및 사용자");
            arrayData.add(data);
            data = new CustomListData("Audio Mute");
            arrayData.add(data);
            data = new CustomListData("Search");
            arrayData.add(data);

        } else { //보호자
//            new CommonConst(pf.getValue("SERVER_URL", ""));
            m_final_url = true;

            arrayData = new ArrayList<CustomListData>();

            data = new CustomListData("모드설정 및 사용자");
            arrayData.add(data);
        }

        if (m_final_url) {
            //slide menu

            // Fragment main
            mainFrg = new MainFrg().newInstance();
            systemFrg = new SystemFrg().newInstance();
            reservationFrg = new ReservationFrg().newInstance();
            cameraSettingFrg = new CameraSettingFrg().newInstance();
            audioSettingFrg = new AudioSettingFrg().newInstance();
            illuminanceFrg = new IlluminanceFrg().newInstance();
            movingSettingFrg = new MovingSettingFrg().newInstance();
            locationSettingFrg = new LocationSettingFrg().newInstance();
            saveSettingFrg = new SaveSettingFrg().newInstance();
            userSettingFrg = new UserSettingFrg().newInstance();
            audioMuteSettingFrg = new AudioMuteSettingFrg().newInstance();
            searchSettingFrg = new SearchSettingFrg().newInstance();

            if (savedInstanceState == null) {
                //selectFragment(FRAGMENT_SYSTEM);
                selectFragment(FRAGMENT_MAIN);
            } else {
                selectFragment(savedInstanceState.getInt(KEY_FRAGMENT_ID));
            }

            //list view header
            View header = getLayoutInflater().inflate(R.layout.list_header, null, false);

            //getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, systemFrg).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, mainFrg).commit();

            // Navigation drawer : menu lists
            lvDrawerList = (ListView) findViewById(R.id.lv_activity_main);
            lvDrawerList.addHeaderView(header); //header 추가

            adapter = new CustomListAdapt(this, android.R.layout.simple_list_item_1, arrayData);

            lvDrawerList.setAdapter(adapter);
            lvDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectFragment(position);
                    dlDrawer.closeDrawer(lvDrawerList);
                }
            });

            // Navigation drawer : ActionBar Toggle
            dlDrawer = (DrawerLayout) findViewById(R.id.dl_activity_main);
            dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);

                    // Hide action items
                    systemFrg.showActionItems(false);

                    // Refresh action items
                    supportInvalidateOptionsMenu();
                    //invalidateOptionsMenu();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);

                    // Show action items
                    systemFrg.showActionItems(true);

                    // Refresh action items
                    supportInvalidateOptionsMenu();
                    //invalidateOptionsMenu();
                }
            };

            dlDrawer.setDrawerListener(dtToggle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //actionbar hide
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.hide();

        }

        _remoteTunnel = new RemoteTunnel(m_Context);

    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {

        if (keycode == KeyEvent.KEYCODE_BACK) {

            if (exit_cnt == 1) {
                Toast.makeText(getApplicationContext(), "한번더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show();
                exit_cnt++;
            } else if (exit_cnt >= 2) {
                this.finish();
            } else {
                selectFragment(FRAGMENT_MAIN);
            }

        }

        return true;//super.onKeyDown(keycode, event);
    }

    /**
     * sqlite db 테스트
     */
    public void dataBAsetest() {
        //        SQLiteDbManager test =   new SQLiteDbManager(this);
//        test.update( "drop table "+test.getTableContacts());


        SQLiteDbManager sqLiteDbManager = new SQLiteDbManager(this);

        int table_cnt = sqLiteDbManager.getDataCount(" select count(*) from sqlite_master Where Name = '" + sqLiteDbManager.getTableCode() + "' ");


        //0 noTable, 1: yesTable
        if (table_cnt <= 0) {
            sqLiteDbManager.update(sqLiteDbManager.getCreateCodeTable());
        } else {
//            sqLiteDbManager.update( " drop table "+sqLiteDbManager.getTableCode()+"; ");
//            sqLiteDbManager.update(sqLiteDbManager.getCreateCodeTable());
        }

        String inser_sql = "insert into " + sqLiteDbManager.getTableCode() + " (" + sqLiteDbManager.getKeyName() + ", " + sqLiteDbManager.getKeyValue() + ") " +
                " values ('" + System.currentTimeMillis() + "', '" + System.currentTimeMillis() + "'); ";
//                " values ('NAME11', 'VALUE111'); " ;

        sqLiteDbManager.insert(inser_sql);
        System.out.println("start #########################");
        System.out.println(sqLiteDbManager.PrintData());

        System.out.println(sqLiteDbManager.getDataValue(" select " + sqLiteDbManager.getKeyValue() + " from " + sqLiteDbManager.getTableCode() + " where " + sqLiteDbManager.getKeyName() + "= '1434625403143' "));
        System.out.println("end #########################");
    }
}
