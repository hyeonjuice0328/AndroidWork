package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Information adapter; // Adapter 객체
    RecyclerView rv;
    EditText etName, etAge, etAdd;

    private final int REQUEST_CODE_I = 101; //값을 돌려받기 위해 필요한 코드값.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       rv = findViewById(R.id.rv);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       rv.setLayoutManager(layoutManager);

       etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAdd = findViewById(R.id.etAdd);

       adapter = new Information();

       //initAdapter(adapter); //  초기화??

        rv.setAdapter(adapter);

        Button btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        I.class
                );
            startActivityForResult(intent, REQUEST_CODE_I);}
        });


    } // end onCreate()

    //데이터 가져오기
    protected void initAdapter(InfoAdapter adapter) {
        // 생성되는만큼 가져오기

    }

    protected void insertDate(View v){
        int idx = I.next();
        adapter.addItem
    }
} // end Activity
