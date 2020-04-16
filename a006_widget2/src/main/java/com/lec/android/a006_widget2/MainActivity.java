package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1, pb2, pb3;
    // 어떤 작업의 진행량의 진행상황을 표현하는 것이므로 진행량을 넣을 변수 필요
    int value = 0;  // 막대프로그레스 바의 현재 진행값
    int add = 10;   // 증가량

    int value2 = 0; // 자동 막대프로그레스 바의 진행값
    int add2 = 1;   // // 증가량

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);
        pb3 = findViewById(R.id.pb3);

        ToggleButton btn1 = findViewById(R.id.toggleButton);
        Button btn2 = findViewById(R.id.button1);


        // 토글버튼 상태변화시 호출되는 콜백
        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pb1.setVisibility(View.INVISIBLE); // 안보이게 하기 ?
                } else {
                    pb1.setVisibility(View.VISIBLE);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + add; // 프로그래스바는 0~100 까지
                // 프로그래스바가 100까지 도달 했을 때 다시 처음으로 돌아가는 작동을 하게 만들어보자
                if(value > 100 || value < 0) {
                    add = -add; // 거꾸로 감소
                    // value = -add;  처음으로 돌아가는 작동
                }
                pb2.setProgress(value); // 프로그레스바의 진행값 설정
            }
        });

        // 3번째 프로그레스바는 자동으로 진행되도록 만들어보자
        // 앱 시작 시 Thread 를 사용하여 ProgressBar 증가 시키기
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    value2 = value2 + add2; // value2 는 0.1초마다 1씩 증가

                    if(value2 > 100 || value2 < 0) {
                        add2 = -add2; // 거꾸로 감소
                        //value = -add2; //처음으로 돌아간다.
                    }

                    // 별도의 작업 Thread 에서 메인 UI 접근 하려면 반드시 Handler 사용해야한다!
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb3.setProgress(value2);
                        }
                    });

                    try {
                        Thread.sleep(100); // 0.1 초 단위
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }// end onCreate
} // end MainActivity
