package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    //등장인물 선언
    CheckBox cb1, cb2, cb3, cb4;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //등장인물 나열
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        tvResult = findViewById(R.id.tvResult);

        Button btnComplete = findViewById(R.id.btnComplete);

        //버튼을 클릭하면 체크된 내용들만 내용 출력 하기
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스의 체크 여부 확인
                // isChecked return type -> boolean (true or false)그래서 if 사용
                String result = "";
                if(cb1.isChecked()) result += cb1.getText();
                if(cb2.isChecked()) result += cb2.getText();
                if(cb3.isChecked()) result += cb3.getText();
                if(cb4.isChecked()) result += cb4.getText();
                //체크 됫는지 모두 확인 후
                tvResult.setText("선택결과: " + result);
            }
        });

        cb1.setOnCheckedChangeListener(new CbListener());
        cb2.setOnCheckedChangeListener(new CbListener());
        cb3.setOnCheckedChangeListener(new CbListener());
        cb4.setOnCheckedChangeListener(new CbListener());

    } // onCreate()
    //내부 클래스 하나 만들기 (체크박스에 체크 되자마자 결과화면에 나오게 하는 방법)
    class  CbListener implements CompoundButton.OnCheckedChangeListener{
        //체크박스의 상태가 변할 때마다 호출되는 메소드
        //매게변수 : boolean isChecked / true 이면 체크 된 상태 false 이면 체크되지 않은 상태
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int count = 0 ;
            if(cb1.isChecked()) count++;
            if(cb2.isChecked()) count++;
            if(cb3.isChecked()) count++;
            if(cb4.isChecked()) count++;
            tvResult.setText(count + "개 선택");
            //위와 같이 실행되는 OnCheckedChangeListener 를 만들었다. 이제 이를 체크박스에 장착!

        }
    }
} // end Main3Activity
