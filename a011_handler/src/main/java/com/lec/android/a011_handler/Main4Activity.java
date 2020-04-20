package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// TODO
//Value1
//1~10까지 1초 단위로 증가
//10초에 도달하면 멈추어서 Toast
public class Main4Activity extends AppCompatActivity {

    int backValue1 = 0;
    int backValue2 = 0;
    TextView tvResult1, tvResult2, tvResult3, tvResult4, tvResult5;
    Handler handler2, mHandler4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
        tvResult5 = findViewById(R.id.tvResult5);

        // 방법1. 핸들러 객체들 외부에서 생성
        handler1.sendEmptyMessage(0); // -> 시작과 동시에 핸들러에 메세지 전달

        // 방법2. handler.postDelayed() 사용
        handler2 = new Handler();


    } // end onCreate

    // Main Thread
    public void mOnClick(View v) {

    } // end main Thread

    // 작업 스레드
    class BackThread1 extends Thread{
        @Override
        public void run() {
            while(true) {
                // 방법 1. 메인에서 생성된 handler 객체의 sendEmptyMessage 를 통해 Message 전달
                backValue1++;
                handler1.sendEmptyMessage(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    } // end BackThread1

    Handler handler1 = new Handler() { // 익명클래스 안에서 handleMessage 를 override
        @Override
        public void handleMessage(@NonNull Message msg) { //매개변수 msg
            backValue1++;

            tvResult1.setText("value = " + backValue1);

            if(backValue1 < 10) {
                handler1.sendEmptyMessageDelayed(0, 1000);
            } else {
                Toast.makeText(getApplicationContext(), "val1종료", Toast.LENGTH_LONG).show();
            }
        }

    };

} // end Activity
