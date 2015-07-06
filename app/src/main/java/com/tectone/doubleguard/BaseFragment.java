package com.tectone.doubleguard;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;


public class BaseFragment extends Fragment {

    protected View mainView;
    protected ImageButton slideBackbutton;

    protected void setSlideBackButton() {

        slideBackbutton = (ImageButton) mainView.findViewById(R.id.slide_b_button);
        slideBackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).toggleDrawerMenu();
            }
        });
    }
}

