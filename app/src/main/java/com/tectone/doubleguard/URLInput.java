package com.tectone.doubleguard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tectone.doubleguard.util.CommonConst;
import com.tectone.doubleguard.util.Preference;

/**
 * Created by JamesLee on 2014-10-23.
 */
public class URLInput extends Activity {
    Context m_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_get_form);
        m_context = this;

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에

            }
        });

        Button button = (Button) findViewById(R.id.buttonSS);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Preference pf = new Preference(m_context);
                pf.put("SERVER_URL", editText.getText().toString());
                new CommonConst("SERVER_URL");

                Intent intent = new Intent(m_context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
