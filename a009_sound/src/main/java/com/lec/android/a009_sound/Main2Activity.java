package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnResume;
    private ImageView btnStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바

    MediaPlayer mp; // 음악재생을 위한 객체

    int pos; // 재생위치 ! 자주 사용할 거기 때문에 이번에 따로 지정
    boolean isTracking = false; // 트래킹

    // 쓰레드 클래스 생성
    class MyThread extends Thread {
        @Override // run 메소드 오버라이딩
        public void run() {
            // SeekBar 가 음악 재생 시 , 움직이게 하기
            while(mp.isPlaying()) { // 현재 재생중 이라면 !!
                pos = mp.getCurrentPosition();    // 현재 재생중인 위치 ms (int)
                if(!isTracking) //  트래킹중일때는 플레이가 계속 되면 안된다.
                                // Sb 값이 변하니까  onProgressChanged 를 호출 한다.
                    sb.setProgress(pos); }
            super.run(); }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);
        sb = findViewById(R.id.sb);

        //음악 재생 전에는 다른 버튼들 활성화 되지 않게 안보이게 끔 가려주기
        btnPlay.setVisibility(View.VISIBLE); // 플레이 버튼만 활성화
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);

        // 시크바 활성화 하기
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override   // sb 값이 변결 될 때마다 호출
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 음악이 끝까지 연주 완료 되었다면
                // seekBar.getMax() 즉 곡의 길이가 progress 진행값이라면 그리고 그것이 사용자로부터 진행된게 아니라면
                if(seekBar.getMax() == progress && !fromUser ){
                    btnPlay.setVisibility(View.VISIBLE); // 플레이 버튼만 활성화
                    btnPause.setVisibility(View.INVISIBLE);
                    btnResume.setVisibility(View.INVISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                    if(mp != null ) mp.stop(); }
            }

            @Override   // 드래그 시작(트레킹) 하면 호출
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;
            }
            @Override   // 드래그 마치면 (트레킹 종료) 호출
            public void onStopTrackingTouch(SeekBar seekBar) {
                pos = seekBar.getProgress();
                if(mp != null) mp.seekTo(pos);
                isTracking = false; }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 미디어 플레이어 객체 초기화 + 재생까지 실행
                mp = MediaPlayer.create(
                        getApplicationContext(), // 현재 화면의 제어권자
                        R.raw.chacha);           // 실행할 음악 파일 리소스
                mp.setLooping(false);            // true: 무한반복

                //재생이 끝나면 호출되는 콜백 메소드
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {  // 현재 재생중인 위치        // 음악의 전체 길이
                        Log.d("Myapp", "연주종료 " + mp.getCurrentPosition() + "/" + mp.getDuration());
                        btnPlay.setVisibility(View.VISIBLE); // 플레이 버튼만 활성화
                        btnPause.setVisibility(View.INVISIBLE);
                        btnResume.setVisibility(View.INVISIBLE);
                        btnStop.setVisibility(View.INVISIBLE); }
                });

                mp.start(); // 노래 재생 시작
                int duration = mp.getDuration();   // 음악의 재생시간 (ms - 밀리세컨드)
                sb.setMax(duration);    // SeekBar 의 범위를 음악의 재생시간으로 설정
                new MyThread().start();     // SeekBar 쓰레드 시작.

                // 음악이 재생 된 후 stop 과 Pause 버튼 보이게 하기
                btnPlay.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 음악 종료
                pos = 0;
                if(mp != null) {
                    mp.stop();      // 재생멈춤
                    mp.seekTo(0); // 처음으로 이동
                    mp.release();   //자원해제
                    mp = null; }
                sb.setProgress(0);
                btnPlay.setVisibility(View.VISIBLE); // 플레이 버튼만 활성화
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE); }
        });

        // 일시중지
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = mp.getCurrentPosition();      // 현재 재생중이던 위치 를 pos 라는 변수에 저장.
                mp.pause();         // 일시 정지 ------> 재생중이 아니기 때문애 Thread 가 끝난다.
                // Pause 버튼 가리고 리줌 버튼 활성화
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE); }
        });

        // 멈춘 시점부터 다시 재시작(RESUME)
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(pos);         // 재생중이던 위치 넣기 - 일시정지 시 위치로 이동.
                mp.start();         // 재생 시작
                new MyThread().start();     // SeekBar Thread 다시 시작
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.INVISIBLE); }
        });
    } // end onCreate

    @Override
    protected void onPause() {
        super.onPause();
        if(mp != null) {
            mp.release();  } // 자원 해
        btnPlay.setVisibility(View.VISIBLE); // 플레이 버튼만 활성화
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);
    }
} // end Activity
