package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText op1, op2;
    TextView tvResult;
    Button btnPlus;
    Button btnMinus;
    Button btnMul;
    Button btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);

        tvResult = findViewById(R.id.tvResult);

        op1.setOnFocusChangeListener(new View.OnFocusChangeListener(

        ) {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

//        op1.setOnEditorActionListener();
    } // end onCreate()
} // end  Main2Activity
