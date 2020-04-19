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

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnC, btnResult, btnPlus, btnMinus, btnMulti, btnDiv;
    EditText etResult;
    String cal1 = ""; // 첫번째 값
    String cal2 = ""; // 두번째 값
    String symbol = ""; // 연산자 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

        etResult = findViewById(R.id.etResult);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnPlus = findViewById(R.id.buttonPlus);
        btnMinus = findViewById(R.id.buttonMinus);
        btnMulti = findViewById(R.id.buttonMulti);
        btnDiv = findViewById(R.id.buttonDiv);
        btnResult = findViewById(R.id.buttonResult);
        btnC = findViewById(R.id.buttonC);


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal2 = etResult.getText().toString();
                if(symbol.equals("") || cal1.equals("") || cal2.equals(""));
                int result = 0;

                try{
                    if(symbol.equals("+")){
                        result = Integer.parseInt(cal1) + Integer.parseInt(cal2);
                        etResult.setText(String.valueOf(result));
                    } else if(symbol.equals("─")){
                        result = Integer.parseInt(cal1) - Integer.parseInt(cal2);
                        etResult.setText(String.valueOf(result));
                    } else if(symbol.equals("X")){
                        result = Integer.parseInt(cal1) * Integer.parseInt(cal2);
                        etResult.setText(String.valueOf(result));
                    } else if(symbol.equals("÷")) {
                        result = Integer.parseInt(cal1) / Integer.parseInt(cal2);
                        etResult.setText(String.valueOf(result));
                    }

                } catch (Exception e){
                    etResult.setText("");

                }
                finally {
                    cal1 = "";
                    cal2 = "";
                    symbol = "";
                }
            }
        });

        // ButtonC
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etResult.setText(""); }
        });

        class NumberListener implements View.OnClickListener{
             @Override
             public void onClick(View v) {
                    String text = (String)((Button)v).getText();
                    etResult.setText(etResult.getText().append(text)); }
             }

        class calListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                cal1 = etResult.getText().toString();
                cal2 = etResult.getText().toString();
                symbol = (String)((Button)v).getText();
                etResult.setText(""); }
        }
        btn0.setOnClickListener(new NumberListener());
        btn1.setOnClickListener(new NumberListener());
        btn2.setOnClickListener(new NumberListener());
        btn3.setOnClickListener(new NumberListener());
        btn4.setOnClickListener(new NumberListener());
        btn5.setOnClickListener(new NumberListener());
        btn6.setOnClickListener(new NumberListener());
        btn7.setOnClickListener(new NumberListener());
        btn8.setOnClickListener(new NumberListener());
        btn9.setOnClickListener(new NumberListener());
        btnPlus.setOnClickListener(new calListener());
        btnMinus.setOnClickListener(new calListener());
        btnMulti.setOnClickListener(new calListener());
        btnDiv.setOnClickListener(new calListener());

    } //  end onCreate()
} // end Activity
