package com.tectone.doubleguard;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class ShopFrg extends BaseFragment {

    public ShopFrg() {
        // Required empty public constructor
    }

    public ShopFrg newInstance() {
        ShopFrg fragment = new ShopFrg();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shop, container, false);
        mainView = inflater.inflate(R.layout.fragment_shop, container, false);

        View RootView = inflater.inflate(R.layout.activity_main, container, false);
        final DrawerLayout dl = (DrawerLayout) RootView.findViewById(R.id.dl_activity_main);
        final View lvDrawerList = getActivity().findViewById(R.id.lv_activity_main);

        //shop button 제어
        final ImageButton btnSlideMenu = (ImageButton) mainView.findViewById(R.id.slide_b_button);
        btnSlideMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Slide Menu
                btnSlideMenu.setSelected(true);
                dl.openDrawer(lvDrawerList);
                //((com.tectone.doubleguard.MainActivity)getActivity()).openDrawer();
                //dl.openDrawer(Gravity.LEFT);
            }
        });

        final LinearLayout shopContentLayout = (LinearLayout) mainView.findViewById(R.id.shop_content_view);
        final ImageButton btnKit = (ImageButton) mainView.findViewById(R.id.kit_button);
        final ImageButton btnSensor = (ImageButton) mainView.findViewById(R.id.sensor_button);
        final ImageButton btnAction = (ImageButton) mainView.findViewById(R.id.action_button);
        final ImageButton btnActivator = (ImageButton) mainView.findViewById(R.id.activator_button);

        btnKit.setBackgroundResource(R.drawable.btn_shop_kit_p);
        btnSensor.setBackgroundResource(R.drawable.btn_shop_sensor_n);
        btnAction.setBackgroundResource(R.drawable.btn_shop_action_n);
        btnActivator.setBackgroundResource(R.drawable.btn_shop_activator_n);

        btnKit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnKit.setSelected(true);

                btnKit.setBackgroundResource(R.drawable.btn_shop_kit_p);
                btnSensor.setBackgroundResource(R.drawable.btn_shop_sensor_n);
                btnAction.setBackgroundResource(R.drawable.btn_shop_action_n);
                btnActivator.setBackgroundResource(R.drawable.btn_shop_activator_n);

                shopContentLayout.setBackgroundResource(R.drawable.img_shop_kit);
            }
        });

        btnSensor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSensor.setSelected(true);

                btnKit.setBackgroundResource(R.drawable.btn_shop_kit_n);
                btnSensor.setBackgroundResource(R.drawable.btn_shop_sensor_p);
                btnAction.setBackgroundResource(R.drawable.btn_shop_action_n);
                btnActivator.setBackgroundResource(R.drawable.btn_shop_activator_n);

                shopContentLayout.setBackgroundResource(R.drawable.img_shop_sensor);
            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnAction.setSelected(true);

                btnKit.setBackgroundResource(R.drawable.btn_shop_kit_n);
                btnSensor.setBackgroundResource(R.drawable.btn_shop_sensor_n);
                btnAction.setBackgroundResource(R.drawable.btn_shop_action_p);
                btnActivator.setBackgroundResource(R.drawable.btn_shop_activator_n);

                shopContentLayout.setBackgroundResource(R.drawable.img_shop_action);
            }
        });

        btnActivator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnActivator.setSelected(true);

                btnKit.setBackgroundResource(R.drawable.btn_shop_kit_n);
                btnSensor.setBackgroundResource(R.drawable.btn_shop_sensor_n);
                btnAction.setBackgroundResource(R.drawable.btn_shop_action_n);
                btnActivator.setBackgroundResource(R.drawable.btn_shop_activator_p);

                shopContentLayout.setBackgroundResource(R.drawable.img_shop_activato);
            }
        });

        setSlideBackButton();

        return mainView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putInt("text", "savedText");
    }
}
