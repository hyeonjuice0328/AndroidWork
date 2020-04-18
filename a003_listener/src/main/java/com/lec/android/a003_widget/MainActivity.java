package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.xml.transform.Result;

import static android.graphics.Color.*;

//방법 5. 액티비티가 직접 implement ---> implements View.OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    EditText et;

    // onCrate() : 액티비티가 생성될때 호출되는 메소드로 액티비티 초기화 하는 코드를 작성한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn2);;

        tvResult = findViewById(R.id.tvResult);
        et = findViewById(R.id.et);
        LinearLayout ll = findViewById(R.id.ll);

        //방법2. 익명클래스 사용
        // 버튼2가 클릭되었을 때 사용하는 리스너가 온클릭 리스너
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override //클릭되었을때 호출되는 콜백 메소드:당장은 아니지만 사용하고 다시 제자리에..
            public void onClick(View v) {
                Log.d("myapp", "버튼2가 클릭 되었습니다.");
                tvResult.setText("버튼2가 클릭됨");
                tvResult.setBackgroundColor(YELLOW);
            }
        });
        // 다양한 이벤트 , 다양한 리스터 추가 등록 가능
        btn2.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("myapp", "버튼2가 롱클릭");
                tvResult.setText("버튼2 롱클릭");
                tvResult.setBackgroundColor(CYAN);
                return true; // false 리턴하면 이벤트가 click 까지 간다??
            }                // true 리턴하면 롱클릭으로 소멸된다.
        });

        // 3번째 방법 람다 expresstion 사용하기
        // 안드로이드 스튜디오는 자바7버전와 8버전의 일부 기능만 사용 람다는 X
        btn3.setOnClickListener((v) -> {
            Log.d("myapp", "버튼3가 클릭");
            tvResult.setText("버튼3 클릭");
            ll.setBackgroundColor(Color.rgb(164, 198, 57));
        });

        // 방법4. implement 한 클래스 사용
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        // 6개의 버튼에 대해서 리스너를 설정해줄 때 비슷한 설정일텐데
        // 똑같은 방식으로 여러번 반복하기 번거롭기 때문에
        class MyListener implements View.OnClickListener{

            String name;
            //기본생성자
            public MyListener(String name) {this.name = name;}
            @Override
            public void onClick(View v) {
                String tag = (String)v.getTag();//오브젝트 리턴이라 반드시 형변환
                String text = (String)((Button)v).getText(); //getText() 는 CharSequence 객체 리턴
                String msg = String.format("%s 버튼 %s 이 클릭[%s]", name, text, tag);
                Log.d("myapp",msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name)); //기존 에 있는 텍스트 + A 에 있는 값 ++++....
            }
        }

        btnA.setOnClickListener(new MyListener("안녕1"));
        btnB.setOnClickListener(new MyListener("안녕2"));
        btnC.setOnClickListener(new MyListener("안녕3"));
        btnD.setOnClickListener(new MyListener("안녕4"));
        btnE.setOnClickListener(new MyListener("안녕5"));
        btnF.setOnClickListener(new MyListener("안녕6"));

        //방법 5. 액티비티가 직접 implement
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //연습
//        Button btnInc = findViewById(R.id.btnInc);
//        Button btnDec = findViewById(R.id.btnDec);
//
//
//        btnInc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                float size = tvResult.getTextSize();
//                tvResult.setTextSize(0, size + 10);
//            }
//        });
//
//        btnDec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                float size = tvResult.getTextSize();
//                tvResult.setTextSize(0, size - 10);
//            }
//        });
    } // end onCreate()

   // 방법1. 레이아웃 xml 의 onXXX 속성으로 지정
    public void changeText(View v) {
        //Log.d(tag, message) - log창의 debug 메세지로 출력
        Log.d("myapp", "버튼1이 클릭되었습니다.");
        tvResult.setText("버튼1이 클릭되었습니다.");
    }

    //방법 5. 액티비티가 직접 implement
    @Override
    public void onClick(View v) {
        Log.d("myapp", "Clear 버튼이 클릭");
        tvResult.setText("Clear 버튼이 클릭");
        et.setText("");
    }
}
