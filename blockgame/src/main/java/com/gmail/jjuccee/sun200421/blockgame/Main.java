package com.gmail.jjuccee.sun200421.blockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 버튼 만들기
        Button btnStart = findViewById(R.id.btnStart);
        Button btnInfo = findViewById(R.id.btnInfo);
        Button btnHowto = findViewById(R.id.btnHowto);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override //화면 전환 --> 게임시작
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Start.class);
                startActivity(intent);
            }
        });

        btnHowto.setOnClickListener(new View.OnClickListener() {
            @Override // 화면 전환 --> 게임방법보기
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HowToPlay.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override // 게임 정보 보기
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Info.class);
                startActivity(intent);
            }
        });

    } // end onCreate
} // end Activity
