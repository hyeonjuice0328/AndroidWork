package com.lec.android.a016_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    // 내 휴대폰에 있는 센서 목록들을 뽑아보기


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView1);

        // 센서 장치 목록 확인하기
        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);
        String str = ":센서목록:\n센서 총개수: " + list.size();

        for(int i = 0; i < list.size(); i++) {
            Sensor s = list.get(i);

            str += "\n\n" + i + ", 센서이름: " + s.getName()
                    + "\n" + "센서전원: " + s.getPower() // 소비하는 전력량
                    + "\n" + "resolution: " + s.getResolution() // 센서값
                    + "\n" + "range: " + s.getMaximumRange() //센서 값의 최대 범위
                    ;
        }
        tv.setText(str);
        Log.d("mtapp", str);

    } // end onCreate()




}
