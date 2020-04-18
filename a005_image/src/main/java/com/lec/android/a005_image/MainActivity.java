package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// 안드로이드의 모든 리소스로 사용하는 파일들은
// 파일명 규칙
// 대문자 불가 , 숫자 시작 불가, 공백과 특수문자 사용 불가
// 특수문자는 _ 언더바 만 사용가능 !!!

// 여섯개의 이미지 로테이션으로 보여주기 / id 는 모두 정수값! 기억하기
public class MainActivity extends AppCompatActivity {

    // 이미지의 아이디값(정수)를 담을 배열을 생성하기
    int [] imageId = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
            R.drawable.a5, R.drawable.a6};
    ImageView iv;
    // 이미지를 클릭 할 때 동작하는 이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv1);

        // 리소스의 drawable 폴더에 있는 이미지로 세팅하기
        iv.setImageResource(R.drawable.a1);

        // 이미지 뷰에 리스너 장착
        iv.setOnClickListener(new MyListener());

    } // onCreate() : activity 가 생성될 때 실행 (초기화)

    //inner class 만들기
    class MyListener implements View.OnClickListener{
        int i = 0;
        TextView tvResult = findViewById(R.id.tvResult);

        @Override
        public void onClick(View v) {
            // 배열의 인덱스 0번부터 5번까지 있고 (0 - a1 이미지)
            // 인덱스의 번호가 5번까지 갔으면 다시 0으로 돌아오게 만드는 메소드
            i++;
            if( i == imageId.length) i = 0;

            iv.setImageResource(imageId[i]);
            tvResult.setText("이미지뷰: " + i);
            //리스너 객체 완성  ---> Oncreate() 에 가서 이미뷰에 장착
        }
    }
} // end Activity
