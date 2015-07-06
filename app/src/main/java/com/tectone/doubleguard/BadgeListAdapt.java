package com.tectone.doubleguard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JamesLee on 2014-10-17.
 */
public class BadgeListAdapt extends ArrayAdapter<RecommandListData> {

    private ArrayList<RecommandListData> items;

    public BadgeListAdapt(Context context, int textViewResourceId, ArrayList<RecommandListData> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_suggest_layout, null);
        }

        RecommandListData badgeListData = items.get(position);

        if (badgeListData != null) {
/*            ImageView iv = (ImageView)v.findViewById(R.id.badge_icon);
            TextView tv = (TextView)v.findViewById(R.id.badgeTitle);
            TextView tv2 = (TextView)v.findViewById(R.id.badgeContent);

            iv.setBackgroundResource(badgeListData.getBadgeImage());
            tv.setText(badgeListData.getBadgeTitle());
            tv.setText(badgeListData.getBadgeContent());*/


            TextView tv = (TextView) v.findViewById(R.id.badgeTitle);
            tv.setText(badgeListData.getBadgeTitle());
            TextView tv2 = (TextView) v.findViewById(R.id.badgeContent);
            tv2.setText(badgeListData.getBadgeContent());
        }

        return v;

    }
}
