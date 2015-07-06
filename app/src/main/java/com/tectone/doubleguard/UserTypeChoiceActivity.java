package com.tectone.doubleguard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tectone.doubleguard.util.CommonConst;
import com.tectone.doubleguard.util.Preference;


public class UserTypeChoiceActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

    Context m_context;
    Button bt_ok;
    //사용자 1, 보호자 2 선택값
    String user_value = "1";
    RadioButton rb_user;
    RadioButton rb_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_type_choice_layout);
        m_context = this;

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg_userchoice);
        rg1.setOnCheckedChangeListener(this);

        rb_user = (RadioButton) findViewById(R.id.rb_user);
        rb_parent = (RadioButton) findViewById(R.id.rb_parent);

        //user or parent radiobutton choice
        if ("1".equals(user_value)) {
            rb_user.setChecked(true);
        } else {
            rb_parent.setChecked(true);
        }

        bt_ok = (Button) findViewById(R.id.bt_choiceuser);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference pf = new Preference(m_context);
                pf.put("USER_TYPE", user_value);
                new CommonConst("USER_TYPE");

                Intent intent = new Intent(m_context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_type_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int arg1) {
        switch (arg1) {

            case R.id.rb_user:
                user_value = "1";
                break;

            case R.id.rb_parent:
                user_value = "2";
                break;

        }
    }
}
