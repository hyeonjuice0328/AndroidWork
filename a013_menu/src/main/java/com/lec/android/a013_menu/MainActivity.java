package com.lec.android.a013_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.LinearGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

/**  안드로이드의 메뉴
 1. Option Menu :  '메뉴' 버튼을 눌렀을때 나타나는 메뉴
                    각각의 '화면' (액티비티) 마다 설정
 2. Context Menu :  길게 눌렀을때 나타나는 메뉴 // 안드로이드는 우클릭이 없기 때문에 롱클릭이 이를 대체
                    각각의 '뷰' 마다 설정  (화면도 가능)
 **/
public class MainActivity extends AppCompatActivity {

    boolean blog = false; //  로그인 상태
    LinearLayout ll;

    // #2
    static final int MENUITEM_YELLOW = 1;
    static final int MENUITEM_ORANGE = 2;
    static final int MENUITEM_CYAN = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.ll);

    } // end onCreate()

    // onCreateOptionsMenu()
    // '옵션메뉴'버튼이 '처음' 눌러졌을 때 실행되는 메소드
    // 메뉴버튼을 눌렀을 때 보여줄 menu 에 대해서 정의

    @Override // boolean type return
    public boolean onCreateOptionsMenu(Menu menu) { // activity 의 menu 객체
        getMenuInflater().inflate(R.menu.menu_main1, menu);
                //Inflater 는 xml 파일로부터 무언가를 만들어내는 장치
        Log.d("myapp", "onCreateOptionMenu - 최초 메뉴키를 눌렀을대 호출됨");

        // 2# 메뉴 아이템을 동적으로 추가 가능하다.
        // add() 라는 메소드를 사용하여 가능 . MenuItem 을 리턴한다.
        MenuItem item1 = menu.add(1,MENUITEM_YELLOW, 100, "노랑");
        MenuItem item2 = menu.add(1,MENUITEM_ORANGE, 100, "오렌지");
        MenuItem item3 = menu.add(1,MENUITEM_CYAN, 100, "파란");


        return true; // false 리턴하면 메뉴가 보이지 않는다.
    }


    // onPrepareOptionsMenu()
    // '옵션메뉴'가 화면에 보여질때마다 호출되는 메소드
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //호출되는 순서 확인
        Log.d("myapp", "onPrepareOptionMenu - 옵션메뉴가 화면에 보여질때 마다 호출됨");

        //메뉴가 보여질 때마다 로그인과 로그아웃이 enable/disable 로 토글되게 보여주기.

        if(blog){ //로그인
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(false);
        }else { //로그아웃
            menu.getItem(1).setEnabled(true);
            menu.getItem(0).setEnabled(false);

        }
        blog = !blog;   // 값을 바꿈
        return true; // false 리턴하면 메뉴가 보이지 않는다.
    }



    // onOptionsItemSelected()
    // '옵션메뉴의 아이템'이 선택(클릭) 되었을때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("myapp", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        // 아이템에 대한 정보 뽑았다.
        showInfo(item);
        //특정 메뉴아이템에 대한 동작
        //각 색상이 눌렸을 때 실행할 동작
        switch (item.getItemId()){
            case MENUITEM_YELLOW:
                ll.setBackgroundResource(R.color.bgColorYellow);
                break;
            case MENUITEM_ORANGE:
                ll.setBackgroundResource(R.color.bgColorOrange);
                break;
            case MENUITEM_CYAN:
                ll.setBackgroundResource(R.color.bgColorCyan);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    // 어이스트 메뉴
    public void showInfo(MenuItem item) {
        int id = item.getItemId(); // 옵션메뉴 아이템의 id 값(정수)
        String title = item.getTitle().toString(); // 옵션 메뉴 아이템의 title 값
        int groupId = item.getGroupId(); // 옵션메뉴 아이템의 그룹 id 값(정수)
        int order = item.getOrder(); // 아이템의 순번

        String msg = "id:" + id + " title:" + title + "groupId:" + groupId + " order:" + order;
        Log.d("myapp", msg);
        Toast.makeText(getApplicationContext(), title + "메뉴 클릭", Toast.LENGTH_SHORT).show();
    }
} // end Activity
