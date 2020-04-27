package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
/**
 *  인터넷 상의 이미지 보여주기
 *      1. 권한을 획득한다 (인터넷에 접근할수 있는 권한을 획득한다)  - 메니페스트 파일
 *      2. 웹에 접근하려면  Thread 가 필요 : Thread 에서 웹의 이미지를 받아온다 - honeycomb(3.0) 버젼 부터 바뀜
 *      3. 외부쓰레드에서 메인 UI에 접근하려면 Handler 를 사용해야 한다.
 */
public class Main4Activity extends AppCompatActivity {

    // 이미지 URL , 반드시 https:// 이어야 한다.
    String imgUrl = "https://developer.android.com/studio/images/studio-icon-stable.png";

    ImageView iv1;
    TextView tvUrl;

    //핸들러 객체는 메인에서 선언
    // 외부 쓰레드에서 메인 UI 화면에 그릴때 사용
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        iv1 = findViewById(R.id.iv1);
        tvUrl = findViewById(R.id.tvUrl);

        // url 문자열을 가지고 url 객체를 만들어서 그 안에서 inputSteam 을 뽑기
        // 안드로이드에서는 inputStream 까지 뽑고 이를 Bitmap 객채로 만든다.

        // Thread t = new Thread(Runnable);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Thread 없이 사용하면 에러 발생
                    // android.os.NetworkOnMainThreadException 발생
                    URL url = new URL(imgUrl);
                    InputStream in = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(in);

                    //안드로이드에서는 Main 에서 네트워크 통신하려하면 에러 발생
                    //iv1.setImageBitmap(bm);
                    //CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                    // 뷰를 생성한 그 쓰레드만이 그 뷰의 요소들을 건들일 수 있다.
                    // iv1은 메인 쓰레드에서 선언되어있는데 지금 별도의 쓰레드를 만들어서 사용하려고 했기 때문에 에러
                    // 중간에 핸들러가 있어야 사용이 가능하다!!(사진참고!!)

                    // 핸들러 객체 선언( 핸들러 객체는 메인에서 선언)
                    handler.post(new Runnable() { // Runnable
                        @Override
                        public void run() {
                        iv1.setImageBitmap(bm);
                        tvUrl.setText(imgUrl);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();


    } // end onCreate()
} // end Activity
