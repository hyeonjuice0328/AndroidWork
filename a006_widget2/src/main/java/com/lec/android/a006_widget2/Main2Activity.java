package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView tvResult;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult = findViewById(R.id.tvResult);
        spinner1 = findViewById(R.id.spinner1);

        // 아이템을 선택했을 때 호출되는 콜백
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // position 값이 중요 : 몇번 째 아이템인지 0부터 ~~
            @Override// 아이템이 선택 되었을 때
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvResult.setText("position: " + position + parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                // static 메소드 / 매개변수1: 이 토스트를 보여줄 컨텍스트가 누구냐 ,매게변수2: 보여줄 텍스트 ,매게변수3: 짧게 보여줄건지 길게 보여줄 건지 /show();
            }

            @Override // 아이템이 선택 된 것이 없이 화면에서 사라질 때
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "선택 안했어요", Toast.LENGTH_LONG).show();

            }
        });
    }
}
