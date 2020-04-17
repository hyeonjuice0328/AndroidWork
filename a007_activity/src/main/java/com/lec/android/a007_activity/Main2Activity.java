package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 화면(액티비티)전환 - 인텐트 객체 사용 (인텐트 날린다?)
 *  1. 다음에 넘어갈 액티비티 준비
 *  2. 메니페스트에 액티비티 등록
 *  3. Intent 객체 만들어서 startActivity() 한다
 *      - Intent 로 데이터 주고 받기 :  putExtra() -> getXXXExtra()
 *      - 주고받은 Object 는 Serializable 되어 있어야 한다
 *
 *  안드로이드는 startActivity() 로 새 액티비티를 시작하면
 *  적측형(stack) 구조로 액티비티가 운영된다.
 */
public class Main2Activity extends AppCompatActivity {

    EditText etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        Button btnStartTwo = findViewById(R.id.btnStartTwo);

        btnStartTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면에 제어권자
                        MyTwo.class              // 다음 화면의 엑티비티 클래스 지정
                );

                // 메인에 있는 값을 두번째 화면으로 넘기기 (인텐트에 담아서 보내기)
                // "key와 value" 의 쌍처럼 "name : 데이터형태"  로 보낸다.
                intent.putExtra("num", 3);
                intent.putExtra("num2", 7);
                intent.putExtra("long", 33L);
                intent.putExtra("msg", "안녕하세요");

                // 이름하고 나이를 Person class 에 담은 뒤 Intent 에 실어 보내기
                Person p = new Person(
                        etName.getText().toString(),
                        Integer.parseInt(etAge.getText().toString())
                );

                intent.putExtra("Person", p);

                startActivity(intent);          // 다음 화면으로 넘어간다~
            }
        });

        Button btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 액티비티 종료
            }
        });

    } // end onCreate()
} // end Activity
