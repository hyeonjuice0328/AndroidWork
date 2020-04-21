package com.gmail.jjuccee.sun200421.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
// 현재 화면이 가로/세로 변경되지 않도록 지정하기
// AndroidManifest.xml 에 screenOrientation="portrait" 지정

// 액션바 없애기 -> styles.xml 에서 NoActionBar 지정

public class Intro extends AppCompatActivity {
    // 인트로 초기 화면
    // 3초동안 보이고 다음 화면(main) 으로 넘어가도록 작업
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        //지역변수
        Handler mHandler = new Handler(){ // 익명클래스

            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent); // Main 으로 화면 전환
                finish(); // 적층형으로 쌓이기 때문에 intro 화면은 종료시키기
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 3000); // 3초 딜레이

    } // end onCreate
} // end Activity
