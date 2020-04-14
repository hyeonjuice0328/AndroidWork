package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Practice extends AppCompatActivity {
    TextView tv;
    EditText etResult; // 결과가 보여지는 곳

    int a;
    int where = 0;


//    Button Calc; // 익명클래스임시객체

//    String num; // 첫번째 값
//    int val; // 연산자 선택 확인(0,1,2,3)

//    int Division = 0; // 나누기
//    int Add = 1;      // 더하기
//    int Multiple = 2; // 곱하기
//    int Sub = 3;      // 빼기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiple = findViewById(R.id.buttonMultiple);
        Button buttonSub = findViewById(R.id.buttonSub);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonResult = findViewById(R.id.buttonResult);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        //계산기
        tv = findViewById(R.id.tV);

        // 결과표시창
        etResult = findViewById(R.id.etResult);

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==button1){
                    etResult.setText(etResult.getText().toString()+1);
                }
                else if(v==button2){
                    etResult.setText(etResult.getText().toString()+2);
                }
                else if(v==button3){
                    etResult.setText(etResult.getText().toString()+3);
                }
                else if(v==buttonAdd){
                    a=Integer.valueOf(etResult.getText().toString().trim());
                    etResult.setText("");
                    where=1;
                }
                else if(v==button4){
                    etResult.setText(etResult.getText().toString()+4);
                }
                else if(v==button5){
                    etResult.setText(etResult.getText().toString()+5);
                }
                else if(v==button6){
                    etResult.setText(etResult.getText().toString()+6);
                }
                else if(v==buttonSub){
                    a=Integer.valueOf(etResult.getText().toString().trim());
                    etResult.setText("");
                    where=2;
                }
                else if(v==button7){
                    etResult.setText(etResult.getText().toString()+7);
                }
                else if(v==button8){
                    etResult.setText(etResult.getText().toString()+8);
                }
                else if(v==button9){
                    etResult.setText(etResult.getText().toString()+9);
                }
                else if(v==buttonDivide){
                    a=Integer.valueOf(etResult.getText().toString().trim());
                    etResult.setText("");
                    where=3;
                }
                else if(v==button0){
                    etResult.setText(etResult.getText().toString()+0);
                }
                else if(v==buttonResult) {
                    if (where == 1) {
                        a = a + Integer.valueOf(etResult.getText().toString().trim());
                        etResult.setText(Integer.toString(a));
                    }
                    else if(where==2){
                        a = a * Integer.valueOf(etResult.getText().toString().trim());
                        etResult.setText(Integer.toString(a));
                    }
                    else if(where==3){
                        a = a / Integer.valueOf(etResult.getText().toString().trim());
                        etResult.setText(Integer.toString(a));
                    }
                    else if(where==4){
                        a = a - Integer.valueOf(etResult.getText().toString().trim());
                        etResult.setText(Integer.toString(a));
                    }
                }
                else if(v==buttonClear){
                    etResult.setText("");
                }
                else if(v==buttonMultiple){
                    a=Integer.valueOf(etResult.getText().toString().trim());
                    etResult.setText("");
                    where=4;
                }
            }

        };
        button1.setOnClickListener(cl);
        button2.setOnClickListener(cl);
        button3.setOnClickListener(cl);
        buttonAdd.setOnClickListener(cl);
        button4.setOnClickListener(cl);
        button5.setOnClickListener(cl);
        button6.setOnClickListener(cl);
        buttonSub.setOnClickListener(cl);
        button7.setOnClickListener(cl);
        button8.setOnClickListener(cl);
        button9.setOnClickListener(cl);
        buttonDivide.setOnClickListener(cl);
        button0.setOnClickListener(cl);
        buttonResult.setOnClickListener(cl);
        buttonClear.setOnClickListener(cl);
        buttonMultiple.setOnClickListener(cl);



    }
}