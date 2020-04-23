package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/7848716d426a6a7539307444475842/xml/stationInfo/1/5/서울

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/7848716d426a6a7539307444475842/json/stationInfo/1/5/서울
*/
public class Main3Activity extends AppCompatActivity {

    TextView tvResult;
    EditText editText;
    Button btnJSON, btnXML, btnParse;

    Handler handler = new Handler();
    String urlStr;

    public static final String REQ_SERVICE = "stationList"; // 서비스 이름
    public static final String API_KEY = "7848716d426a6a7539307444475842"; // 내 키값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        editText = findViewById(R.id.editText);
        btnJSON = findViewById(R.id.btnJSON);
        btnXML = findViewById(R.id.btnXML);
        btnParse = findViewById(R.id.btnParse);

        urlStr = "";

        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  urlStr = ;
                // HTTP request 는 별도의 Thread 로 진행해야한다.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();

            }
        });





    } // end onCreate()

    public static String buildUrlAddress(String statnNM)
            throws IOException {
        String urlStr = String.format("http://swopenAPI.seoul.go.kr/api/subway/7848716d426a6a7539307444475842/xml/stationInfo/1/5/%s",
                statnNM);

        return urlStr;
    }



    public void request(String urlStr) {
        final StringBuilder sb = new StringBuilder();

        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(5000); // timeout 시간 설정.
                conn.setUseCaches(false);     // 캐시 사용 안함.
                conn.setRequestMethod("GET"); // GET 방식 request

                conn.setDoInput(true);        // URLConnection 을 입력으로 사용(true) , (false) > 출력용

                int responseCode = conn.getResponseCode();       // response code 값 : 성공이면 200

                if(responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if(line == null) break;
                        sb.append(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
                if(conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(sb.toString());
            }
        });

    } // end request


} // end Activity
