package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    RadioGroup rg;
    Button btnResult;
    TextView tvResult;

    // 라디오 그룹에 리스너가 붙는다.
    // 체크박스는 각각의 체크박스에 리스너가 붙는다. (라디오버튼과 체크박스의 차이점1.)
    //(라디오버튼과 체크박스의 차이점2.)
    // 체크박스는 중복선택이 가능하지만 라디오버튼은 라디오 그룹 내의 하나만 선택이 가능하다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // 아이디 값 이름 변경 가능 Shift + F6
        rg = findViewById(R.id.rg);
        btnResult = findViewById(R.id.btnResult);
        tvResult = findViewById(R.id.tvResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 선택된 라디오 버튼의 아이디 값 가져오기
                int id = rg.getCheckedRadioButtonId();
                // 2. 위 아이디값을 통해 라디오 버튼 객체 가져오기
                RadioButton rb = findViewById(id);
                tvResult.setText("결과:" + rb.getText());
            }
        });

        //라디오버튼만을 선택했을때도 위와 같은 동작을 하게 하기

//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            // 매개변수 : int checkedId - 선택된 라디오 버튼의 아이디
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rb = findViewById(checkedId);
//                tvResult.setText("결과:" + rb.getText());
//            }
//        });
    } // onCreate()
}
