package com.lec.android.a013_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;


// SubMenu 와 PopupMenu
public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button btnPopup = findViewById(R.id.btnPopup);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // popup menu 나오게 하기
                // popup menu 는 API 11부터 제공
                PopupMenu p= new PopupMenu(
                        getApplicationContext(),
                        v );    // anchor :  팝업을 띄울 기준이 될 뷰
                getMenuInflater().inflate(R.menu.menu_main5, p.getMenu());
                //팝업 메뉴 이벤트 처리
                //옵션메뉴와 다르다. 따로 처리해주어야 한다.
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showInfo(item);
                        return false;
                    }
                });
                p.show(); // popup menu 띄우기 
            }
        });


    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 장착
        getMenuInflater().inflate(R.menu.menu_main5, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        showInfo(item);

        return super.onOptionsItemSelected(item);
    }

    public void showInfo(MenuItem item){
        int id = item.getItemId();   // 옵션메뉴 아이템의 id 값
        String title = item.getTitle().toString();   // 옵션 메뉴의 title
        int groupId = item.getGroupId();   // 옵션 메뉴의 그룹아이디
        int order = item.getOrder();

        String msg = "id:" + id + " title:" + title + " groupid:" + groupId + " order:" + order;
        Log.d("myapp", msg);
        Toast.makeText(getApplicationContext(), title + " 메뉴 클릭", Toast.LENGTH_SHORT).show();
    }

} // end Activity