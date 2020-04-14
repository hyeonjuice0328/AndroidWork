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

public class Main2Activity extends AppCompatActivity {

    TextView tv;
    EditText etResult; // 결과가 보여지는 곳

    Button Calc; // 익명클래스임시객체

    String num; // 첫번째 값
    int val; // 연산자 선택 확인(0,1,2,3)

    int Division = 0; // 나누기
    int Add = 1;      // 더하기
    int Multiple = 2; // 곱하기
    int Sub = 3;      // 빼기

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
           num = "";
//           buttonDivide.setOnClickListener(MyListener);
//           buttonAdd.setOnClickListener(MyListener);
//           buttonMultiple.setOnClickListener(MyListener);
//           buttonSub.setOnClickListener(MyListener);
//           buttonResult.setOnClickListener(MyListener);


         class MyListener implements View.OnClickListener{

             @Override
             public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.buttonDivide:
                         num = etResult.getText().toString();
                         etResult.setText("");
                         val = Division;
                         break;
                     case R.id.buttonAdd:
                         num = etResult.getText().toString();
                         etResult.setText("");
                         val = Add;
                         break;
                     case R.id.buttonSub:
                         num = etResult.getText().toString();
                         etResult.setText("");
                         val = Sub;
                         break;
                     case R.id.buttonMultiple:
                         num = etResult.getText().toString();
                         etResult.setText("");
                         val = Multiple;
                         break;
                     case R.id.buttonResult:
                         if(val == Multiple) {
                             etResult.setText("" + (Integer.parseInt(num) * Integer.parseInt(etResult.getText().toString())));
                         } else if(val == Sub) {
                             etResult.setText("" + (Integer.parseInt(num) - Integer.parseInt(etResult.getText().toString())));
                         } else if(val == Add) {
                             etResult.setText("" + (Integer.parseInt(num) + Integer.parseInt(etResult.getText().toString())));
                         } else if(val == Division) {
                             etResult.setText("" + (Integer.parseInt(num) / Integer.parseInt(etResult.getText().toString())));
                         }
                         num = etResult.getText().toString();
                         break;

                     case R.id.button0: etResult.setText(etResult.getText().toString()+0);break;
                     case R.id.button1: etResult.setText(etResult.getText().toString()+1);break;
                     case R.id.button2: etResult.setText(etResult.getText().toString()+2);break;
                     case R.id.button3: etResult.setText(etResult.getText().toString()+3);break;
                     case R.id.button4: etResult.setText(etResult.getText().toString()+4);break;
                     case R.id.button5: etResult.setText(etResult.getText().toString()+5);break;
                     case R.id.button6: etResult.setText(etResult.getText().toString()+6);break;
                     case R.id.button7: etResult.setText(etResult.getText().toString()+7);break;
                     case R.id.button8: etResult.setText(etResult.getText().toString()+8);break;
                     case R.id.button9: etResult.setText(etResult.getText().toString()+9);break;
                 }


             }
         };




           // ButtonClear
            buttonClear.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                        etResult.setText("");
                }
            });

            // et.setText(et.getText().append(name));
            //화면에 0 입력
           button0.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    etResult.setText(button0.getText()); }});
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button1.getText()); }});
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button2.getText()); }});
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button3.getText()); }});
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button4.getText()); }});
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button5.getText()); }});
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button6.getText()); }});
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button7.getText()); }});
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button8.getText()); }});
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(button9.getText()); }});
        buttonMultiple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(buttonMultiple.getText()); }});
        buttonDivide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(buttonDivide.getText()); }});
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(buttonAdd.getText()); }});
        buttonSub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(buttonSub.getText()); }});
        buttonResult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(buttonResult.getText()); }});







    } //  end onCreate()
} // end Activity
