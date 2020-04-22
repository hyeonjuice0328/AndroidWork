package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

/**
// SQLite3 : 안드로이드에 기본탑재된 모바일 용으로 제작된 경량화 DB
//          C언어로 엔진이 제작되어 가볍다
//          안드로이드에서 sqLite3 를 쉽게 사용할 수 있도록 SQLiteOpenHelper클래스제공

// SQLite 를 사용한 데이터 저장
//   1. SQLiteOpenHelper 를 상속받은 클래스 정의
//      onCreate(), onUpgrade() 작성
//   2. 위 Helper 로부터 SQLiteDatabase  DB 객체 추출
//   3. DML 명령은 : execSQL()
//      SELECT 명령은 : rawQuery() --> 결과는 Cursor 객체 사용하여 다룸
 **/
public class Main3Activity extends AppCompatActivity {

    // SQL 클래스 선언
    MySQLiteOpenHelper3 helper;
    String dbName = "st_file.db";   // 파일 형태로 DB가 저장된다.
    int dbVersion = 2;      // 데이터베이스는 버전관리를 꼭 해야한다.
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        helper = new MySQLiteOpenHelper3(
                this, // 현재 화면 제어권자
                dbName,       // DB 이름
                null, // 커서팩토리-null : 표준커서가 사용됨. ???
                dbVersion     // DB 버전
        );

        try{
            db = helper.getWritableDatabase();   // 읽고 쓰기 가능한 DB
            //db = helper.getReadableDatabase();   // 읽기 전용 DB (ex. SELECT 만 사용하는 경우)
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("myapp", "데이터베이스를 얻어올 수 없음");
            finish(); //액티비티 종료
        }
        insert();
        select();
        update();
        select();
        delete();
        select();

    } // end onCreate

    void delete() {

        db.execSQL("DELETE FROM mytable WHERE id = 2");
        Log.d("myapp", "DELETE 완료");
    }

    void update() {

        db.execSQL("UPDATE mytable SET name = '홍성용' WHERE id = 5");
        Log.d("myapp", "UPDATE 완료");
   }

    void select() {
        // select 문은 cursor 객체로 받는다 !!
        Cursor c = db.rawQuery("SELECT * FROM mytable", null);
        while(c.moveToNext()) {
            int id = c.getInt(0); // 컬럼 인덱스 0 부터 시작 !
            String name = c.getString(1);
            Log.d("myapp", "id:" + id + ", name:" + name);
        }
    }

    void insert() {
        db.execSQL("INSERT INTO mytable (name) values ('김민호')");
        db.execSQL("INSERT INTO mytable (name) values ('이승환')");
        db.execSQL("INSERT INTO mytable (name) values ('남윤주')");
        db.execSQL("INSERT INTO mytable (name) values ('이성은')");
        db.execSQL("INSERT INTO mytable (name) values ('윤종섭')");
        Log.d("myapp", "INSERT 성공");
    }

} // end Activity
