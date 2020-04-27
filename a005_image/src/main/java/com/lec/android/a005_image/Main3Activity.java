package com.lec.android.a005_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

/**
 * 폰의 저장장치에 있는 사진을 ImageView 에 띄우기
 *    1. 권한 획득
 *        - 메니페스트 (AndroidManifest.xml)
 *         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 *         <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 *        - '위험권한'요청  (Android 6.0 / API23 이상)
 *         https://developer.android.com/guide/topics/security/permissions?hl=ko  (위험권한및 위험권한 목록들)
 *            https://developer.android.com/training/permissions/requesting?hl=ko#java
 *
 *            설치 앱별 '권한' 확인 가능하다 :
 *                픽셀2 폰의 경우 '앱' 아이콘 롱클릭후 App Info 확인
 *
 *    2. 이미지 경로
 *    3. Bitmap 사용하여 ImageView 에 띄우기
 */
public class Main3Activity extends AppCompatActivity {

    ImageView ivPhoto;
    TextView tvPath;
    Button btnAction;

    //앨범에 있는 이미지의 경로를 아는 경우 이미지뷰에서 볼 수 있게 하는 방법
    //픽셀폰 : 이미지보기 -> 하단의 i 버튼 누르면 경로를 찾을 수 있다.
    //삼성폰 : 갤러리 이미지 -> 롱클릭 후 상세정보에서 경로를 찾을 수 있다.
    //이미지 경로
    private String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20200425_050156.jpg";
            //= "/sdcard/DCIM/Camera/france.png";
    private final int PERMISSION_REQUEST_CODE = 101; // 권한 요청 코드값 (int) : 정수타입의 아무 숫자가 상관없음
    private final String[] PERMISSION = {   // 요청할 권한들을 문자열 배열로 준비해놓기 String{}
            Manifest.permission.READ_EXTERNAL_STORAGE, // 요청할 권한 1
            Manifest.permission.WRITE_EXTERNAL_STORAGE    // 요청할 권한2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ivPhoto = findViewById(R.id.ivPhoto);
        btnAction = findViewById(R.id.btnAction);
        tvPath = findViewById(R.id.tvPath);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawPhoto();// 위험권한 획득 없이 실행하면?  아래 Exception 발생
                // E/BitmapFactory: Unable to decode stream: java.io.FileNotFoundException:이미지경로..
            }
        });

        // 위험권한 획득 : API 23(코드명: 마시멜로) 이상에서만 권한 요청
        // 권한을 획득하지 않은 상태에서만 요청 최초에 한번 권한획득 여부를 확인
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 마시멜로 이상의 버전이어야 하는 조건문
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){ // 권한승인을 받지 않았을 때의 조건문

                //권한 요청 : 비동기로 진행된다.
                ActivityCompat.requestPermissions(this,
                        PERMISSION, // 요청할 권한들
                        PERMISSION_REQUEST_CODE // 권한 요청 코드
                );
                // 이미 권한을 획득한 상태
            } else { drawPhoto(); }
        }
    } // end onCreate()

    public  void drawPhoto() {
        // ImageView < Bitmap < 경로
        Bitmap bm = BitmapFactory.decodeFile(imgPath);
        ivPhoto.setImageBitmap(bm);
        tvPath.setText(imgPath);
    } // end drawPhoto()

    //권한 획득에 대한 결과처리 하는 콜백 메소드(권한을 획득 또는 획득하지 않았을 결과)
    //request code : 권한 요청 코드값
    //permissions : return type String[] / 권한 요청
    //grantResults: 승인내역들
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //101번 코드인지 ..
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
                    // 배열의 길이가 1 이상이어야 하고 0번째 배열이 PERMISSION_GRANTED 이면 ==> 즉 사용자가 권한을 승인했다면
                    drawPhoto();

                    Log.d("myapp", "권한획득결과\n\t" + Arrays.toString(permissions) + " : " + Arrays.toString(grantResults));
                } else  {
                    //사용자가 권한 승인 안했을 경우
                }
                break;
                }
    }


} // end Activity
