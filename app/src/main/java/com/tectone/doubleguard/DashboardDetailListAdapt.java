package com.tectone.doubleguard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tectone.doubleguard.util.BadgeSetting;
import com.tectone.doubleguard.util.BadgeTitle;
import com.tectone.doubleguard.util.CommonConst;
import com.tectone.doubleguard.util.CommonUtil;
import com.tectone.doubleguard.wheel.OnWheelChangedListener;
import com.tectone.doubleguard.wheel.WheelView;
import com.tectone.doubleguard.wheel.adapter.NumericWheelAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by JamesLee on 2014-10-17.
 */
public class DashboardDetailListAdapt extends ArrayAdapter<BadgeListData> {
    Context m_Context;
    String m_TimeValue = "000000";
    String m_SeekValue = "100";
    String m_OuterType = "0";
    int m_Index = 0;
    String s_BadgeType;
    String s_SencerType;
    String s_WhatType;
    String m_Output;
    String s_SencerListName;
    boolean popupYn = true;
    boolean m_Capture = false;
    private ArrayList<BadgeListData> items;
    private int seekbarSencitivity = 100;
    private int hourIndex;
    private int minIndex;
    private int secIndex;
    private int dialog_titles[] = {R.string.dialog_title_proximity, R.string.dialog_title_near, R.string.dialog_title_speaker};
    private int dialog_sub_titles[] = {R.string.dialog_sub_sensor, R.string.dialog_sub_proximity_activator, R.string.dialog_sub_action};

    public DashboardDetailListAdapt(Context context, int textViewResourceId, ArrayList<BadgeListData> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        m_Context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final BadgeListData badgeListData = items.get(position);

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_dashboard_detail_layout, null);

            String[] badgeListId = badgeListData.getBadge_list_id();
            //ImageView Add
            Integer[][] badgeImages = new Integer[badgeListId.length][3];

            for (int i = 0; i < badgeListId.length; i++) {
                int[] divideImag = CommonUtil.getBigIndivisualImg(badgeListId[i]);
                for (int j = 0; j < 3; j++) {
                    badgeImages[i][j] = divideImag[j];
                }
                Log.d("DEBUG", "divideImag.length :" + divideImag.length);
            }

            LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.badgeDetailList);
            ImageView imageView[][] = new ImageView[badgeImages.length][3];

            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (badgeImages.length > 1) {
                lparams.gravity = Gravity.LEFT;
            } else {
                lparams.gravity = Gravity.CENTER;
            }

            int j = 1;
            for (int i = 0; i < badgeImages.length; i++) {

                View imgSetItem = View.inflate(getContext(), R.layout.detail_item, null);

                imageView[i][2] = (ImageView) imgSetItem.findViewById(R.id.img_3);
                imageView[i][2].setImageResource(badgeImages[i][2]);

                imageView[i][1] = (ImageView) imgSetItem.findViewById(R.id.img_2);
                imageView[i][1].setImageResource(badgeImages[i][1]);

                imageView[i][0] = (ImageView) imgSetItem.findViewById(R.id.img_1);
                imageView[i][0].setImageResource(badgeImages[i][0]);

                imgSetItem.setLayoutParams(lparams);
                linearLayout.addView(imgSetItem);
                //PLUS IMG
                if (i < badgeImages.length - 1) {
                    addPlus(linearLayout);
                }
            }

            //BADGE CLICK EVENT
            for (int i = 0; i < badgeImages.length; i++) {
                final int m = i;
                for (int k = 0; k < 3; k++) {
                    final int index = k;
                    m_Index = k;
                    imageView[i][k].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            //badge click event.
                            int id = v.getId();

                            s_SencerType = badgeListData.getBadge_list_id()[m].substring(2, 3);
                            s_WhatType = badgeListData.getBadge_list_id()[m].substring(0, 1);
                            s_BadgeType = badgeListData.getBadgeTitle();
                            s_SencerListName = badgeListData.getBadge_list_id()[m];

                            m_OuterType = badgeListData.getBadge_list_id()[m].substring(4); //OUTER VALUE

                            if (getAttribute(s_BadgeType, s_SencerType)) { //해당 함수가 DB에서 가져오는 부분
                                if (popupYn) {
                                    onCoachMark(index, s_BadgeType, s_SencerType, m_OuterType, m_TimeValue, m_SeekValue, s_SencerListName);
                                }
                            }
                        }
                    });
                }
            }


            if (badgeListData != null) {
                TextView tv = (TextView) v.findViewById(R.id.badgeTitle);
                TextView tv2 = (TextView) v.findViewById(R.id.badgeContent);

                //iv.setBackgroundResource(badgeListData.getBadgeImages());

                // FLAG 처리
                String notBagdeConbine = badgeListData.getFlag();
                if (notBagdeConbine.equals("1")) {
                    tv2.setTextColor(Color.RED);
                }
                tv.setText(badgeListData.getBadgeTitle());
                tv2.setText(badgeListData.getBadgeContent());
            }

        }
        return v;
    }

    /*
     * 상세보기
     */
    public void onCoachMark(final int index, final String sencerType, final String badgeType, final String m_OuterType, final String set_TimeValue, final String set_SeekValue, final String sencerListName) {
        popupYn = false;
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.badge_update);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                popupYn = true;
            }
        });


        TextView title = (TextView) dialog.findViewById(R.id.title);
        //TextView subTitle = (TextView) dialog.findViewById(R.id.sub_title);
        TextView badgeDesc = (TextView) dialog.findViewById(R.id.badgeDesc);

        View durationLayout = dialog.findViewById(R.id.duration_layout);
        View seekbarLayout = dialog.findViewById(R.id.seekbar_layout);
        View spinnerLayout = dialog.findViewById(R.id.spinner_layout);

        //초기값설정
        final TextView time = (TextView) dialog.findViewById(R.id.Text2);
        final TextView seek = (TextView) dialog.findViewById(R.id.Text22);

        final TextView selAudio = (TextView) dialog.findViewById(R.id.sel_audio);

        //OUTPUT 초기값을 가져온다.
        getAttributeOutput(s_BadgeType, s_SencerListName);
        //OUTPUT 초기값세팅
        selAudio.setText(m_Output);

        //seek bar 처리
        final SeekBar seekBar = (SeekBar) dialog.findViewById(R.id.seekBar);

        //2014.11.13 CAPTURE
        //TODO. TESTING
        final LinearLayout captureLayout = (LinearLayout) dialog.findViewById(R.id.capture_layout);
        final ImageView captureImg = (ImageView) dialog.findViewById(R.id.capture_img);
        final TextView capture = (TextView) dialog.findViewById(R.id.capture);
        captureLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!m_Capture) {
                    //Capture 시작
                    m_Capture = true;
                    captureLayout.setBackgroundColor(Color.rgb(121, 121, 121));
                    captureImg.setBackgroundResource(R.drawable.popup_btn_ic_stop);
                    capture.setText("Stop");

                    SendByHttpCapture(sencerType, s_SencerListName, "Y");

                } else {
                    //Capture 중단
                    m_Capture = false;
                    captureLayout.setBackgroundColor(Color.rgb(237, 92, 119));
                    captureImg.setBackgroundResource(R.drawable.popup_btn_ic_capture);
                    capture.setText("Capture");

                    SendByHttpCapture(sencerType, s_SencerListName, "N");
                    //Capture 중단 후 값 갱신
                    if (getAttribute(s_BadgeType, s_SencerType)) {
                        CommonUtil.showAlertDialog(m_Context, "The Badge captured successfully.", 2000);
                        //다시 셋업
                        seek.setText(m_SeekValue);
                        seekbarSencitivity = Integer.parseInt(m_SeekValue);
                        seekBar.setProgress(seekbarSencitivity);
                    } else {
                        CommonUtil.showAlertDialog(m_Context, "The Badge captured unsuccessfully.", 2000);
                    }

                }
            }
        });

        //DESC Setting
        BadgeTitle badgeTitle = new BadgeTitle();
        String rtnDesc = badgeTitle.getBadgeTitle(index, sencerListName);
        badgeDesc.setText(rtnDesc);

        LinearLayout bgImg = (LinearLayout) dialog.findViewById(R.id.imageView1);
        //2014.11.14 기능변경

        int popupTtitleImg = R.drawable.popup_dummy_img_04;
        if (index == 0) {
            popupTtitleImg = CommonUtil.getPopupTitleFirst(s_WhatType);
        } else if (index == 1) {
            popupTtitleImg = CommonUtil.getPopupTitleSecond(badgeType);
        } else {
            popupTtitleImg = CommonUtil.getPopupTitleThird(m_OuterType);
        }
        bgImg.setBackgroundResource(popupTtitleImg);


        time.setText(set_TimeValue.substring(0, 2).format("%02d", hourIndex) + ":" + set_TimeValue.substring(2, 4).format("%02d", minIndex) + ":" + set_TimeValue.substring(4).format("%02d", secIndex));
        seek.setText(set_SeekValue);
        seekbarSencitivity = Integer.parseInt(set_SeekValue);

        if (index == 0) {
            durationLayout.setVisibility(View.GONE);
            seekbarLayout.setVisibility(View.GONE);
            spinnerLayout.setVisibility(View.GONE);
        } else if (index == 1) {
            spinnerLayout.setVisibility(View.GONE);
            final View pickerLayout = dialog.findViewById(R.id.time_layout);

            // DB에서 읽어온 초기값 세팅
            hourIndex = Integer.parseInt(set_TimeValue.substring(0, 2));
            minIndex = Integer.parseInt(set_TimeValue.substring(2, 4));
            secIndex = Integer.parseInt(set_TimeValue.substring(4));

            String timeStr = set_TimeValue.substring(0, 2).format("%02d", hourIndex) + ":" + set_TimeValue.substring(2, 4).format("%02d", minIndex) + ":" + set_TimeValue.substring(4).format("%02d", secIndex);
            time.setText(timeStr);
            time.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    pickerLayout.setVisibility(pickerLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                }
            });

            //시나리오별로 값을 조절해야함
            seekBar.setProgress(seekbarSencitivity);

            //TODO. 초기값 세팅은 서버에서 받아오는것이 맞음.
            //11.3 Max값은 App에 세팅하는걸로 협의

            BadgeSetting badgeSetting = new BadgeSetting();
            int rtnSencitivitymax = badgeSetting.getSencitivityMax(sencerListName);
            seekBar.setMax(rtnSencitivitymax);

            //Min, Max Value setting
            TextView maxTv = (TextView) dialog.findViewById(R.id.end_value);
            TextView minTv = (TextView) dialog.findViewById(R.id.start_value);

            String m_MaxValue = badgeSetting.getMaxSencitivity(sencerListName);
            String m_MinValue = badgeSetting.getMinSencitivity(sencerListName);
            maxTv.setText(m_MaxValue);
            minTv.setText(m_MinValue);


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seek.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    m_SeekValue = seek.getText().toString();
                }
            });

            OnWheelChangedListener onWheelChangedListener = new OnWheelChangedListener() {

                @Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {

                    if (wheel.getId() == R.id.hour) {
                        hourIndex = newValue;
                    } else if (wheel.getId() == R.id.mins) {
                        minIndex = newValue;
                    } else if (wheel.getId() == R.id.sec) {
                        secIndex = newValue;
                    }
                    String timeStr = String.format("%02d", hourIndex) + ":" + String.format("%02d", minIndex) + ":" + String.format("%02d", secIndex);
                    time.setText(timeStr);
                    m_TimeValue = String.format("%02d", hourIndex) + String.format("%02d", minIndex) + String.format("%02d", secIndex);
                }
            };

            final WheelView hours = (WheelView) dialog.findViewById(R.id.hour);
            NumericWheelAdapter hourAdapter = new NumericWheelAdapter(getContext(), 0, 23, "%02d");
            hourAdapter.setItemResource(R.layout.wheel_text_item);
            hourAdapter.setItemTextResource(R.id.text);
            hours.setViewAdapter(hourAdapter);
            hours.setCyclic(true);
            hours.addChangingListener(onWheelChangedListener);
            hours.setCurrentItem(hourIndex);

            final WheelView mins = (WheelView) dialog.findViewById(R.id.mins);
            NumericWheelAdapter minAdapter = new NumericWheelAdapter(getContext(), 0, 59, "%02d");
            minAdapter.setItemResource(R.layout.wheel_text_item);
            minAdapter.setItemTextResource(R.id.text);
            mins.setViewAdapter(minAdapter);
            mins.setCyclic(true);
            mins.addChangingListener(onWheelChangedListener);
            mins.setCurrentItem(minIndex);

            final WheelView sec = (WheelView) dialog.findViewById(R.id.sec);
            NumericWheelAdapter secAdapter = new NumericWheelAdapter(getContext(), 0, 59, "%02d");
            secAdapter.setItemResource(R.layout.wheel_text_item);
            secAdapter.setItemTextResource(R.id.text);
            sec.setViewAdapter(secAdapter);
            sec.setCyclic(true);
            sec.addChangingListener(onWheelChangedListener);
            sec.setCurrentItem(secIndex);

            pickerLayout.setVisibility(View.GONE);


            // m_SeekValue = seek.getText().toString();
            // m_TimeValue = time.getText().toString().replace(":","");

        } else if (index == 2) {
            durationLayout.setVisibility(View.GONE);
            seekbarLayout.setVisibility(View.GONE);


            final View audioLayout = dialog.findViewById(R.id.audio_layout);
            TextView output_1 = (TextView) dialog.findViewById(R.id.output_1);
            TextView output_2 = (TextView) dialog.findViewById(R.id.output_2);
            TextView output_3 = (TextView) dialog.findViewById(R.id.output_3);
            TextView output_4 = (TextView) dialog.findViewById(R.id.output_4);
            TextView output_5 = (TextView) dialog.findViewById(R.id.output_5);

            if (m_OuterType.equals("1")) {
                if (sencerListName.equals("1,B,1")) {
                    output_1.setText("Red");
                    output_2.setText("Yellow");
                    output_3.setText("Green");
                    output_4.setText("Blue");
                    output_5.setText("White");
                } else if (sencerListName.equals("1,A,1")) {
                    output_1.setText("Red");
                    output_2.setText("Red Blink");
                    output_3.setText("White");
                    output_4.setText("White Blink");
                    output_5.setText("Rainbow");
                } else if (sencerListName.equals("4,L,1")) {
                    output_1.setText("Red Blink");
                    output_2.setText("Yellow Blink");
                    output_3.setText("Green Blink");
                    output_4.setText("Blue Blink");
                    output_5.setText("White Blink");
                } else if (sencerListName.equals("4,E,1")) {
                    output_1.setText("Red Blink");
                    output_2.setText("Yellow Blink");
                    output_3.setText("Green Blink");
                    output_4.setText("Blue Blink");
                    output_5.setText("White Blink");
                } else if (sencerListName.equals("6,G,1")) {
                    output_1.setText("Red");
                    output_2.setText("Green");
                    output_3.setText("Blue");
                    output_4.setText("Yellow");
                    output_5.setText("White");
                } else {
                    output_1.setText("Red");
                    output_2.setText("Yellow");
                    output_3.setText("Green");
                    output_4.setText("Blue");
                    output_5.setText("White");
                }
            } else if (m_OuterType.equals("2")) {
                if (sencerListName.equals("6,N,2")) {
                    output_1.setText("Chine");
                    output_2.setText("Melody");
                    output_3.setText("Alert");
                    output_4.setText("Siren");
                    output_5.setText("Voice");
                } else if (sencerListName.equals("2,C,2")) {
                    output_1.setText("Chime");
                    output_2.setText("Melody");
                    output_3.setText("Alert");
                    output_4.setText("Voice1(Formal)");
                    output_5.setText("Voice2(Fun)");
                } else if (sencerListName.equals("5,F,2")) {
                    output_1.setText("Alert");
                    output_2.setText("Chime");
                    output_3.setText("Melody");
                    output_4.setText("Voice1 (Formal)");
                    output_5.setText("Voice2 (Fun)");
                } else {
                    output_1.setText("Chime");
                    output_2.setText("Melody");
                    output_3.setText("Alert");
                    output_4.setText("Siren");
                    output_5.setText("Voice");
                }
            } else if (m_OuterType.equals("3")) {
                if (sencerListName.equals("3,K,4")) {
                    output_1.setText("Badge1");
                    output_2.setText("Badge2");
                    output_3.setText("Badge3");
                    output_4.setText("Badge4");
                    output_5.setText("Badge5");
                } else {
                    output_1.setText("Badge1");
                    output_2.setText("Badge2");
                    output_3.setText("Badge3");
                    output_4.setText("Badge4");
                    output_5.setText("Badge5");
                }
            } else if (m_OuterType.equals("4")) {
                if (sencerListName.equals("1,I,4")) {
                    output_1.setText("A/C ON/OFF");
                    output_2.setText("Humidifer ON/OFF");
                    output_3.setText("Dehumidifier ON/OFF");
                    output_4.setText("Air Washer ON/OFF");
                    output_5.setText("Air Cleaner ON/OFF");
                } else {
                    output_1.setText("A/C ON/OFF");
                    output_2.setText("Humidifer ON/OFF");
                    output_3.setText("Dehumidifier ON/OFF");
                    output_4.setText("Air Washer ON/OFF");
                    output_5.setText("Air Cleaner ON/OFF");
                }

            } else if (m_OuterType.equals("5")) {
                if (sencerListName.equals("5,F,5")) {
                    output_1.setText("TV ON/OFF");
                    output_2.setText("Mute");
                    output_3.setText("Home");
                    output_4.setText("Volume Up");
                    output_5.setText("Volume Down");
                } else {
                    output_1.setText("TV ON/OFF");
                    output_2.setText("Mute");
                    output_3.setText("Home");
                    output_4.setText("Volume Up");
                    output_5.setText("Volume Down");
                }
            }

            OnClickListener onClickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.sel_audio) {
                        audioLayout.setVisibility(audioLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                        selAudio.setBackgroundResource(audioLayout.getVisibility() == View.VISIBLE ? R.drawable.popup_downmenu_s : R.drawable.popup_downmenu_n);
                    } else {
                        if (m_OuterType.equals("1")) { //LED
                            if (sencerListName.equals("1,B,1")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Yellow");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Green");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Blue");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("White");
                                }
                            } else if (sencerListName.equals("1,A,1")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Red Blink");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("White");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("White Blink");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Rainbow");
                                }
                            } else if (sencerListName.equals("4,L,1")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red Blink");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Yellow Blink");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Green Blink");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Blue Blink");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("White Blink");
                                }
                            } else if (sencerListName.equals("4,E,1")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red Blink");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Yellow Blink");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Green Blink");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Blue Blink");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("White Blink");
                                }
                            } else if (sencerListName.equals("6,G,1")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Green");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Blue");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Yellow");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("White");
                                }
                            } else {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Red");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Yellow");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Green");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Blue");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("White");
                                }
                            }

                        } else if (m_OuterType.equals("2")) { //AUDIO
                            if (sencerListName.equals("6,N,2")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Chime");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Melody");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Alert");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Siren");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Voice");
                                }
                            } else if (sencerListName.equals("2,C,2")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Chime");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Melody");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Alert");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Voice1(Formal)");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Voice2(Fun)");
                                }
                            } else if (sencerListName.equals("5,F,2")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Alert");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Chime");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Melody");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Voice1 (Formal)");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Voice2 (Fun)");
                                }
                            } else {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Chime");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Melody");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Alert");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Siren");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Voice");
                                }
                            }
                        } else if (m_OuterType.equals("3")) { //Badge
                            if (sencerListName.equals("3,K,4")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("Badge 1");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Badge 2");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Badge 3");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Badge 4");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Badge 5");
                                }
                            } else {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("A/C ON/OFF");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Humidifer ON/OFF");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Dehumidifier ON/OFF");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Air Washer ON/OFF");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Air Cleaner ON/OFF");
                                }
                            }
                        } else if (m_OuterType.equals("4")) { //DEVICE
                            if (sencerListName.equals("1,I,4")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("A/C ON/OFF");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Humidifer ON/OFF");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Dehumidifier ON/OFF");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Air Washer ON/OFF");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Air Cleaner ON/OFF");
                                }
                            } else {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("A/C ON/OFF");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Humidifer ON/OFF");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Dehumidifier ON/OFF");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Air Washer ON/OFF");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Air Cleaner ON/OFF");
                                }
                            }
                        } else if (m_OuterType.equals("5")) { //TV
                            if (sencerListName.equals("5,F,5")) {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("TV ON/OFF");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Mute");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Home");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Volume Up");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Volume Down");
                                }
                            } else {
                                if (v.getId() == R.id.output_1) {
                                    selAudio.setText("TV ON/OFF");
                                } else if (v.getId() == R.id.output_2) {
                                    selAudio.setText("Mute");
                                } else if (v.getId() == R.id.output_3) {
                                    selAudio.setText("Home");
                                } else if (v.getId() == R.id.output_4) {
                                    selAudio.setText("Volume Up");
                                } else if (v.getId() == R.id.output_5) {
                                    selAudio.setText("Volume Down");
                                }
                            }
                        }
                        selAudio.setBackgroundResource(R.drawable.popup_downmenu_n);
                        audioLayout.setVisibility(View.GONE);
                        //서버쪽에 OUTPUT ID 전달
                        String outputValue = null;
                        if (v.getId() == R.id.output_1) {
                            outputValue = "1";
                        } else if (v.getId() == R.id.output_2) {
                            outputValue = "2";
                        } else if (v.getId() == R.id.output_3) {
                            outputValue = "3";
                        } else if (v.getId() == R.id.output_4) {
                            outputValue = "4";
                        } else if (v.getId() == R.id.output_5) {
                            outputValue = "5";
                        }
                        if (SendByHttpOutput(sencerType, s_SencerListName, outputValue)) {
                            CommonUtil.showAlertDialog(m_Context, "The output has been applied successfully.", 2000);
                        } else {
                            CommonUtil.showAlertDialog(m_Context, "The output has been applied unsuccessfully.", 2000);
                        }

                    }
                }
            };

            selAudio.setOnClickListener(onClickListener);
            output_1.setOnClickListener(onClickListener);
            output_2.setOnClickListener(onClickListener);
            output_3.setOnClickListener(onClickListener);
            output_4.setOnClickListener(onClickListener);
            output_5.setOnClickListener(onClickListener);

            audioLayout.setVisibility(View.GONE);
        }

        dialog.setCanceledOnTouchOutside(false);
        //for dismissing anywhere you touch
        /*
        2014.11.12 기능변경
        Close 버튼으로
        dialog.findViewById(R.id.done_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서버에 값전달.
                if(index == 1) {
                    showToeast(SendByHttp(sencerType, badgeType, m_TimeValue, m_SeekValue));
                }
                dialog.dismiss();
            }
        });
        */
        dialog.findViewById(R.id.close_cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서버에 값전달.
                if (index == 1) {
                    showToeast(SendByHttp(sencerType, badgeType, m_TimeValue, m_SeekValue));
                }
                dialog.dismiss();
                popupYn = true;
            }
        });
        dialog.show();
    }

    /*
     * JSON 서버 호출
     * BY. LEE
     */
    private boolean SendByHttp(String scenarioName, String badgeName, String duration, String sencitivity) {

        String URL = CommonConst.getHTTP_URL() + "/Service/SetActivator/";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(URL + "/" + URLEncoder.encode(scenarioName + "/" + badgeName + "/" + duration + "/" + sencitivity, "UTF-8"));

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    /*
     *  CAPTURE ACTION
     *  2014.11.15 BY LEE
     */
    private boolean SendByHttpCapture(String scenarioName, String s_SencerListName, String captureYn) {

        String URL = CommonConst.getHTTP_URL() + "/Service/SetCapture/";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(URL + "/" + URLEncoder.encode(scenarioName + "/" + s_SencerListName.replaceAll(",", "") + "/" + captureYn, "UTF-8"));

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    /*
     *  CAPTURE ACTION
     *  2014.11.15 BY LEE
     */
    private boolean SendByHttpCapture(String scenarioName, String s_SencerListName, String captureYn) {

        String URL = CommonConst.getHTTP_URL() + "/Service/SetCapture/";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(URL + "/" + URLEncoder.encode(scenarioName + "/" + s_SencerListName.replaceAll(",", "") + "/" + captureYn, "UTF-8"));

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    private void showToeast(boolean result) {
        if (result) {
            CommonUtil.showAlertDialog(m_Context, "The status has been applied successfully.", 2000);
        } else {
            CommonUtil.showAlertDialog(m_Context, "The status has been applied unsuccessful.", 2000);
        }
    }

    /*
     * PLUS MARK
     */
    public void addPlus(LinearLayout relativeLayout) {
        //Badge 구분자
        ImageView plus = new ImageView(this.getContext());
        plus.setBackgroundResource(R.drawable.img_plus);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(0, 25, 3, 25);
        lparams.gravity = Gravity.CENTER;

        plus.setLayoutParams(lparams);
        relativeLayout.addView(plus);
    }

    public boolean getAttribute(String scenarioName, String badgeName) {
        String url = CommonConst.getHTTP_URL() + "/Service/GetActivator";
        boolean rtnResult = false;

        try {
            url = url + "/" + URLEncoder.encode(scenarioName + "/" + badgeName, "UTF-8");
            //JSON URL 세팅
            String line = getStringFromUrl(url);
            JSONObject object = new JSONObject(line);

            JSONArray Array = new JSONArray(object.getString("activate_values"));

            for (int i = 0; i < Array.length(); i++) {
                JSONObject insideObject = Array.getJSONObject(i);
                m_TimeValue = insideObject.getString("activator_value");
                m_SeekValue = insideObject.getString("sensitivity_value");
            }

            rtnResult = true;

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtnResult;
    }

    /*
   * OUTPUT SETTING
   */
    private boolean SendByHttpOutput(String scenarioName, String s_SencerListName, String index) {

        String URL = CommonConst.getHTTP_URL() + "/Service/SetOutput/";
        DefaultHttpClient client = new DefaultHttpClient();

        String resultString = null;
        boolean resultStatus = false;
        try {
            HttpGet get = null;
            get = new HttpGet(URL + "/" + URLEncoder.encode(scenarioName + "/" + s_SencerListName.replaceAll(",", ""), "UTF-8") + "/" + index);

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);

            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            String line = null;

            while ((line = bufreader.readLine()) != null) {
                resultString += line;
            }
            if (resultString.contains("true")) {
                resultStatus = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            CommonUtil.showAlertDialog(m_Context, "Server does not response.", 2000);
        }
        return resultStatus;
    }

    public boolean getAttributeOutput(String scenarioName, String s_SencerListName) {
        String url = CommonConst.getHTTP_URL() + "/Service/GetOutput";
        boolean rtnResult = false;

        try {
            url = url + "/" + URLEncoder.encode(scenarioName + "/" + s_SencerListName.replaceAll(",", ""), "UTF-8");
            //JSON URL 세팅
            String line = getStringFromUrl(url);
            JSONObject object = new JSONObject(line);

            JSONArray Array = new JSONArray(object.getString("output_values"));

            for (int i = 0; i < Array.length(); i++) {
                JSONObject insideObject = Array.getJSONObject(i);
                m_Output = insideObject.getString("output");
            }

            rtnResult = true;

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtnResult;
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

}
