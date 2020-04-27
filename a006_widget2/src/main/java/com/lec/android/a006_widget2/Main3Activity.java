package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;
    SeekBar seekBar;

    int value = 0;
    int add = 1;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        seekBar = findViewById(R.id.seekBar);

        //리스너 달기
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // progress : 진행값 0 - max
            // fromUser : 사용자에 의한 진행값의 변화(true) 와 그렇지 않은 경우(false) 를 구분
            @Override// 값의 변화가 생겼을 때 콜백
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvResult.setText("onProgressChanged: " + progress + " (" + fromUser + ")");
            }

            @Override // tracking 시작 할 때 콜백
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Tracking Start!", Toast.LENGTH_SHORT).show();
            }

            @Override // tracking 이 끝났을 때 손을 뗐을 때 콜백
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Tracking End", Toast.LENGTH_SHORT).show();

            }
        });

        // 앱 시작시 Thread .. SeekBar 자동으로 증가 시키기
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = seekBar.getMax(); // max 값 가져오기 (설정 200으로 했음)

                while(true) {
                    value = seekBar.getProgress()  + add;
                    if(value > max || value < 0) {
                        value = -add;
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(value);
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}