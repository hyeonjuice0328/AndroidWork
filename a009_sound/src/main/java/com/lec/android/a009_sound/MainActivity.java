package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** 음향: SoundPool
 *      짧은 음향 리소스(들)을 SoundPool 에 등록(load)하고, 원할때마다 재생(play)
 *
 *  res/raw 폴더 만들고  음향 리소스 추가하기
 */
public class MainActivity extends AppCompatActivity {

    private SoundPool sp;

    // 음향 리소스 id 배열로 준비하기      0          1           2
    private final int[] SOUND_RES = {R.raw.gun, R.raw.gun2, R.raw.gun3};

    // 음향 아이디 값을 담을 배열(INTEGER 형)
    int [] soundId = new int[SOUND_RES.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay1 = findViewById(R.id.btnPlay);
        Button btnPlay2 = findViewById(R.id.btnPlay2);
        Button btnPlay3 = findViewById(R.id.btnPlay3);
        Button btnStop = findViewById(R.id.btnStop);

        // SoundPool 객체 생성하기
        // 롤리팝 이상과 이하의 경우를 차이둬서 생성하는 게 좋다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // APL 21 이상에서는 아래와 같이 SoundPool 생성
            // new SoundPool.Builder() - Builder 생성
            // 클래스 이름으로 곧바로 들어와서 static 빌더를 만들고 난 후 그 빌더를 통해서 SoundPool 접근
            sp = new SoundPool.Builder().setMaxStreams(10).build();
        } else {
            sp = new SoundPool(1,  // 재생 음향 최대 갯수
                    AudioManager.STREAM_MUSIC, // 재생 미디어 타입
                    0);     // 재생품질.. (거의 안쓰임. default : 0)
        };
        // pool 은 수영장 > 물을 담아 놓는 곳
        // SoundPool > 음향을 담아 놓는 곳
        // SoundPool 에 음향 리소스들을 Loading
        for(int i = 0; i < SOUND_RES.length; i++) {
            soundId[i] =  sp.load   //load 는 아이디값을 리턴하기 때문에 음향 아이디 값을 담을 배열을 생성해서 대입하기
                    (this, // 현재 화면의 제어권자
                    SOUND_RES[i], // 음원 파일 리소스
                    1     // 재생 우선 순위
                    );
        }// end for
        // play button 1
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(soundId[0],     //  준비한 sound 리소스 id
                        1,   //  왼쪽볼륨 float 0.0 ~ 1.0
                        1f,  //  오른쪽볼륨 float
                        0,      // 우선 순위 int
                        0,        // 반복 횟수 int - 0: 반복안함, -1: 무한반복
                        1f        //  재생속도 float - 0.5(절반속도) ~ 2.0(2배속)
                        );
            }
        });
        // play button 2
        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(soundId[1],     //  준비한 sound 리소스 id
                        1,   //  왼쪽볼륨 float 0.0 ~ 1.0
                        0,  //  오른쪽볼륨 float
                        0,      // 우선 순위 int
                        2,        // 반복 횟수 int - 0: 반복안함, -1: 무한반복
                        2f        //  재생속도 float - 0.5(절반속도) ~ 2.0(2배속)
                );
            }
        });
        // play button 3
        btnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(soundId[2],     //  준비한 sound 리소스 id
                        0,   //  왼쪽볼륨 float 0.0 ~ 1.0
                        1,  //  오른쪽볼륨 float
                        0,      // 우선 순위 int
                        -1,        // 반복 횟수 int - 0: 반복안함, -1: 무한반복
                        0.5f        //  재생속도 float - 0.5(절반속도) ~ 2.0(2배속)
                );
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 재생중에 있었던 모든 sound stop!!
                for(int i = 0; i < soundId.length; i++) {
                    // 음향 정지
                    sp.stop(soundId[i]);
                }
            }
        });
    }// end onCreate
}// end Activity






















