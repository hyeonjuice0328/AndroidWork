package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// 화면이 없는 액티비티 생성.
public class I extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setResult(RESULT_OK, intent);

        finish();

    } // end onCreate()

    private static int idx = 0;
    public static int next(){
        idx = idx % 
    }
} // end class I
