package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 대화상자 객체
    Dialog dlg1;
    ImageView ivDlgBanner;
    Button btnDlgEvent;

    Dialog dlg2;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Dialog 클래스로 다이얼로그 객체 생성및 세팅
        dlg1 = new Dialog(this); // 다이얼로그 객체 생성
        dlg1.setContentView(R.layout.dialog_layout11); // 다이얼로그 화면 등록

        // Dialog 안에 View 객체들 얻어 오기
        ivDlgBanner = dlg1.findViewById(R.id.ivDlgBanner);
        btnDlgEvent = dlg1.findViewById(R.id.btnDlgEvent);

        btnDlgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDlgBanner.setImageResource(R.drawable.face09);
                Toast.makeText(getApplicationContext(), "다이얼로그 버튼을 눌렀어요", Toast.LENGTH_LONG).show();
            }
        });

        //Activity 에 Dialog 등록하기  / 다이얼로그 단독으로 사용 안됨
        dlg1.setOwnerActivity(MainActivity.this); // 누구의 것인지 명시 중요
        dlg1.setCanceledOnTouchOutside(true); // 다이얼로그 바깥 영역 클릭시 또는 back 버튼 클릭시 - hide 상태
                    // 종료의 여부를 확인한다., -> true 이면 종료, false 이면 종료 안된다.

    }

    public void showDialog1(View v) {
        dlg1.show(); // 다이얼로그 띄우기
    }

    public void showDialog2(View v) {
        //TODO
    }

}
