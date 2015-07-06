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
public class DashboardSuggestListAdapt extends ArrayAdapter<SuggestListData> {

    private ArrayList<SuggestListData> items;

    public DashboardSuggestListAdapt(Context context, int textViewResourceId, ArrayList<SuggestListData> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final SuggestListData suggestListData = items.get(position);

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.custom_suggest_list, null);

            TextView tv = (TextView) v.findViewById(R.id.suggestTitle);

            //iv.setBackgroundResource(badgeListData.getBadgeImages());
            tv.setText(suggestListData.getSuggestTitle());

        }

        return v;

    }


}
