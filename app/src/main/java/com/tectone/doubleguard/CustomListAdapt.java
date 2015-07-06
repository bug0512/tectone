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
public class CustomListAdapt extends ArrayAdapter<CustomListData> {

    private ArrayList<CustomListData> items;

    public CustomListAdapt(Context context, int textViewResourceId, ArrayList<CustomListData> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_drawer_layout, null);
        }
        CustomListData custData = items.get(position);

        if (custData != null) {
            //ImageView iv = (ImageView)v.findViewById(R.id.drawer_icon);
            TextView tv = (TextView) v.findViewById(R.id.drawer_itemName);

            //iv.setBackgroundResource(custData.getImageId());
            tv.setText(custData.getMainTitle());
        }

        return v;

    }
}
