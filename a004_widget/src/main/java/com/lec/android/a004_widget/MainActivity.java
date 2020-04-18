package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNumber, etPassword, etEmail;
    TextView tvName, tvNumber, tvPassword, tvEmail;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etNumber = findViewById(R.id.etNumber);
        etEmail = findViewById(R.id.etEmail);

        tvName = findViewById(R.id.tvName);
        tvPassword = findViewById(R.id.tvPassword);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvEmail);

        tvResult = findViewById(R.id.tvResult);

        // edittext 이기때문에 가질 수 있는 이벤트들...
        // 포커스 변화 hasfocus : true 포커스 받은 경우 / false 포커스 잃은 경우
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    // 다시 원래 색으로 돌아오게 하기 위해 > 투명색으로 변경
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        //키보드가 눌릴때마다 호출 - password 누를 때마다 result 에 출력된다.
        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //keyCode: 눌린 키의 코드값
                tvResult.setText(((EditText)v).getText().toString());
                return false;
            }
        });

        // 값의 변화 (입력 완료 후 확인 -> actionId 값 출력)
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                tvResult.setText("입력완료:" + actionId);
                return false;
            }
        });


    } // end onCreate()
} // end Activity





