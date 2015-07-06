package com.tectone.doubleguard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tectone.doubleguard.util.CommonUtil;

import java.util.ArrayList;

/**
 * Created by JamesLee on 2014-10-17.
 */
public class DashboardListAdapt extends ArrayAdapter<BadgeListData> {
    private final String ClassName = "DashboardListAdapt";
    private ArrayList<BadgeListData> items;

    public DashboardListAdapt(Context context, int textViewResourceId, ArrayList<BadgeListData> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final BadgeListData badgeListData = items.get(position);

        Item item = null;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_dashboard_layout, null);

            item = new Item(v);
            v.setTag(item);
        } else {
            item = (Item) v.getTag();
        }

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

        ImageView imageView[][] = new ImageView[badgeImages.length][3];

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (badgeImages.length > 1) {
            lparams.gravity = Gravity.LEFT;
        } else {
            lparams.gravity = Gravity.CENTER;
        }

        item.linearLayout.removeAllViews();
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
            item.linearLayout.addView(imgSetItem);
            //PLUS IMG
            if (i < badgeImages.length - 1) {
                addPlus(item.linearLayout);
            }
        }

        if (badgeListData != null) {
            // FLAG 처리
            String notBagdeConbine = badgeListData.getFlag();
            Log.d("Badge Flag", "================== :" + notBagdeConbine);
            if (notBagdeConbine.equals("1")) {
                item.tv2.setText(R.string.no_scence);
                item.tv2.setTextColor(Color.RED);
                item.tv2.setTextSize(14);
                badgeListData.setBadgeContent("There is no scene for this Badge yet.");
            } else {
                item.tv2.setTextColor(Color.parseColor("#7f7f7f"));
                item.tv2.setText(badgeListData.getBadgeContent());
            }
            item.tv.setText(badgeListData.getBadgeTitle());
        } else {

        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 터치 시 DASHBOARD DETAIL

                Intent intent = new Intent(getContext(), DashboardDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("badge_id", badgeListData);
                getContext().startActivity(intent);
            }
        });

        return v;

    }

    /*
     * PLUS MARK
     */
    public void addPlus(LinearLayout linearLayout) {
        //Badge 구분자
        ImageView plus = new ImageView(this.getContext());
        plus.setBackgroundResource(R.drawable.img_plus);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.0F);
        lparams.setMargins(0, 25, 3, 25);
        lparams.gravity = Gravity.CENTER;
        plus.setLayoutParams(lparams);
        linearLayout.addView(plus);
    }

    public class Item {
        public LinearLayout linearLayout;
        public TextView tv;
        public TextView tv2;

        public Item(View v) {

            linearLayout = (LinearLayout) v.findViewById(R.id.badgeDetailList);
            tv = (TextView) v.findViewById(R.id.badgeTitle);
            tv2 = (TextView) v.findViewById(R.id.badgeContent);
        }
    }

}
