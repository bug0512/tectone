package com.tectone.doubleguard.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tectone.doubleguard.R;


/**
 * Created by JamesLee on 2014-10-28.
 */
public class CustomToast extends Activity {
    Context mContext;

    public CustomToast(Context context) {
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_layout);
    }


    public void showToast(String body, int duration) {
        // http://developer.android.com/guide/topics/ui/notifiers/toasts.html
        LayoutInflater inflater;

        View v;
        if (false) {
            Activity act = (Activity) mContext;
            inflater = act.getLayoutInflater();
            v = inflater.inflate(R.layout.toast_layout, null);
        } else {  // same
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.toast_layout, null);
        }
        /*
        Activity act = (Activity) mContext;
        inflater = act.getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_layout, null);
        */
        TextView text = (TextView) v.findViewById(R.id.text);
        text.setText(body);

        Toast toast = new Toast(getBaseContext());
        show(toast, v, duration);
    }


    private void show(Toast toast, View v, int duration) {
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(v);
        toast.show();
    }
}
