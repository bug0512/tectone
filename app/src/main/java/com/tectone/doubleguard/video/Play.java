package com.tectone.doubleguard.video;


import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lx520sdk.RtspInfo;
import com.example.lx520sdk.VideoPlay;
import com.tectone.doubleguard.MainActivity;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.util.NetworkManager;

public class Play extends Activity {
    Context context;
    //�������̹�����
    KeyguardManager mKeyguardManager = null;
    String get_username;
    String get_userpsk;
    String get_ip;
    String get_deviceid;
    int get_port;
    boolean get_voice;
    String high_video_quality = "/cam1/h264";
    String low_video_quality = "/cam1/h264-1";
    RelativeLayout play_layout;
    LinearLayout play_head;
    ImageView play_back;
    TextView Play_name;
    TextView Play_type;
    TextView Play_size;
    TextView Play_fps;
    ProgressBar Play_connect;
    VideoPlay _videoPlay = null;
    boolean Is_show = true;
    /**
     * ******************************************************************************************************
     * * ����˵�������
     * * ���������
     * * �õ�������
     * *******************************************************************************************************
     */
    OnClickListener play_layout_click = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Is_show = !Is_show;
            if (Is_show) {
                play_head.setVisibility(View.VISIBLE);
            } else {
                play_head.setVisibility(View.GONE);
            }
        }
    };
    /**
     * ******************************************************************************************************
     * * ����˵�����˳�����
     * * ���������
     * * �õ�������
     * *******************************************************************************************************
     */
    OnClickListener play_back_click = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            MainActivity._remoteTunnel.closeTunnels();
            _videoPlay.stop();
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    };
    //����������
    private KeyguardLock mKeyguardLock = null;
    //������Դ������
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.video_play);

        wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        wakeLock.acquire();
        mKeyguardLock = mKeyguardManager.newKeyguardLock("");
        mKeyguardLock.disableKeyguard();

        Play_connect = (ProgressBar) findViewById(R.id.playprogressscan);
        Play_connect.setVisibility(View.GONE);
        play_back = (ImageView) findViewById(R.id.play_back);
        play_back.setOnClickListener(play_back_click);
        play_head = (LinearLayout) findViewById(R.id.play_head);
        play_layout = (RelativeLayout) findViewById(R.id.lin);
        play_layout.setOnClickListener(play_layout_click);
        Play_name = (TextView) findViewById(R.id.play_name);
        Play_type = (TextView) findViewById(R.id.play_type);
        Play_size = (TextView) findViewById(R.id.play_size);
        Play_fps = (TextView) findViewById(R.id.play_fps);

        Intent intent1 = getIntent();
        get_username = intent1.getStringExtra("name");
        get_userpsk = intent1.getStringExtra("psk");
        get_deviceid = intent1.getStringExtra("id");
        get_ip = intent1.getStringExtra("ip");
        get_port = intent1.getIntExtra("port", 0);

        get_voice = intent1.getBooleanExtra("voice", false);

        _videoPlay = new VideoPlay(this);
        _videoPlay.setBackgroundColor(Color.BLACK);
        _videoPlay.setLayoutParams(
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT));
        play_layout.addView(_videoPlay, 0);

        //네트워크 연결 체크
        NetworkManager nmanager = new NetworkManager(context);
        if (nmanager.isNetworkStat(2)) {
            if ("LTH_E09C9E".equals(nmanager.getExtraInfo())) {
                PlayVideo();
            } else {
                //추후 String value에서 가지고 오게 할것
                nmanager.showWifiAlert("CAMERA 연결이 안되었습니다. 카메라 WIFI셋팅을 하세요.");
            }

        } else {
            nmanager.showWifiAlert();
        }


    }

    /**
     * ******************************************************************************************************
     * * ����˵��������
     * * ���������
     * * �õ�������
     * *******************************************************************************************************
     */
    public void PlayVideo() {
        if (MainActivity.remote == false)//Local
        {
            RtspInfo info = new RtspInfo();
            info.username = get_username;
            info.password = get_userpsk;
            info.ip = get_ip;
            info.port = get_port;
            info.pipe = high_video_quality;
            info.audio = get_voice;
            info.type = RtspInfo.UDP;
            _videoPlay.setRtspInfo(info);
            _videoPlay.play();
        } else//Remote
        {
            RtspInfo info = new RtspInfo();
            info.username = get_username;
            info.password = get_userpsk;
            info.ip = get_ip;
            info.port = get_port;
            info.pipe = low_video_quality;
            info.audio = get_voice;
            info.type = RtspInfo.TCP;
            _videoPlay.setRtspInfo(info);
            _videoPlay.play();
        }
    }

    /**
     * ******************************************************************************************************
     * * ����˵�������ؼ��˳�����
     * * ���������
     * * �õ�������
     * *******************************************************************************************************
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainActivity._remoteTunnel.closeTunnels();
            _videoPlay.stop();
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        return false;
    }

    /**
     * ******************************************************************************************************
     * * ����˵����UI������Ϣ��ʾ
     * ******************************************************************************************************
     */
    public void DisplayToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}

