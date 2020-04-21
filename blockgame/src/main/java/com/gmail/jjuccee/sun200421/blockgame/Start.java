package com.gmail.jjuccee.sun200421.blockgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {

    TextView tvTime; //시간표시
    TextView tvPoint; //점수표시

    // 멤버 변수
    int time = 30; // 스테이지가 시작하면 주어지는 시간 : 조건 - 30초
    int point = 0; // 점수값

    // 블록은 3가지 색 - img resource 로 가지고 있음
    // 블록 이미지 배열
    int [] img = {R.drawable.block_red, R.drawable.block_green, R.drawable.block_blue};

    // 이미지 뷰를 관리할 배열이 필요 (총8개로 설정했음 - start.xml)
    ImageView[] iv = new ImageView[8];  // iv[0] --- ?  null 이다 초기화되어있기 때문에 처음 시작에 가지고 있는 값은 null

    private Vibrator vibrator;  //진동 객체
    private SoundPool soundPool;    // 음향

    private int soundID_OK; // 음향효과 : 블럭 맞췄을때
    private int soundID_Error; // 음향효과 : 블럭 못맞췄을때

    private MediaPlayer mp;     // 배경음악

    final int DIALOG_TIMEOVER = 1;  // 다이얼로그 ID

    Handler handler = new Handler(); // 시간,

    // 게임진행 쓰레드 만들기
    class GameThread extends Thread {
        @Override
        public void run() {
            // 시간은 1초마다 다시 표시 (업데이트)
            // Handler 사용하여 화면 UI 업데이트

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 시간이 남아있는 동안만 흘러가야 한다.
                    if(time >= 0) {
                        tvTime.setText("시간:" + time); // 처음 시간 값 30 으로 이전에 셋팅 완료

                        if(time > 0){
                            time--; // 1초 감소 , 1초 후에 다시 run() 수행
                            handler.postDelayed(this, 1000); // 자기자신에게 다시
                        } else { // time == 0 이 된 경우 - 게임 시간이 모두 끝난 경우
                            // popup 띄우기
                            AlertDialog.Builder builder
                                    = new AlertDialog.Builder(Start.this);
                            builder.setTitle("타임아웃")
                                    .setMessage("점수 : " + point)
                                    .setNegativeButton("그만하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish(); // 현재화면 종료하고 이전화면으로 돌아가기
                                        }
                                    })
                                    .setPositiveButton("다시하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 게임 리셋 (초기화) 하고 새로운 게임 시작
                                            time = 30;
                                            point = 0;
                                            tvTime.setText("시간:" + time);
                                            tvPoint.setText("점수:" + point);
                                            new GameThread().start(); //  스레드 다시 시작하기 (새로운 게임 시작)
                                        }
                                    });
                            builder.show();
                        } // end else
                    } // end if
                } // end run()
            }, 1000); // 1초 후에 run() 안에서 실행 (1초마다 실행)

        } // end run()
    } // end GameThread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상태가 없애기 반드시  setContentView() 앞에서 처리
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start);

        //레이아웃 객체(뷰) 초기화
        tvTime = findViewById(R.id.tvTime);
        tvPoint = findViewById(R.id.tvPoint);

        ImageView ivRed = findViewById(R.id.ivRed);
        ImageView ivGreen = findViewById(R.id.ivGreen);
        ImageView ivBlue = findViewById(R.id.ivBlue);

        iv[0] = findViewById(R.id.ivBlock1);
        iv[1] = findViewById(R.id.ivBlock2);
        iv[2] = findViewById(R.id.ivBlock3);
        iv[3] = findViewById(R.id.ivBlock4);
        iv[4] = findViewById(R.id.ivBlock5);
        iv[5] = findViewById(R.id.ivBlock6);
        iv[6] = findViewById(R.id.ivBlock7);
        iv[7] = findViewById(R.id.ivBlock8);

        // 최초에 게임이 시작되면 ----> 초기화
        // 8개의 블럭이 랜덤으로 나와야 한다. ---> 랜덤으로 색상 지정

        for(int i = 0; i < iv.length; i++){

            // 0, 1, 2 < - red, green, blue 로 지정
            int num = new Random().nextInt(3); // 0,1,2 중에 랜덤 정수 뽑아준다.
            iv[i].setImageResource(img[num]);
            // 눈에 보이지 않지만 존재하는 tag 값
            iv[i].setTag(num + ""); // 태그를 버튼 색상 판단하기 위한 값으로 활용
        }

        // 점수 초기화
        point = 0;
        tvPoint.setText("점수:" + point);
        // red, green, blue 버튼의 리스너 등록
        ivRed.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivBlue.setOnClickListener(this);

        // 시간표시, 게임진행 쓰레드 시작하기 (시간이 흘러가기 시작한다!!!)
        new GameThread().start();

    } // end onCreate

    @Override
    public void onClick(View v) {
        //버튼을 눌렀을때 호출되는 콜백
        //블럭과 같은 색깔의 버튼이 눌렸는지 판별해주기 !!!
        //같은 블럭이면 이미지 블럭 한칸씩 내려오기 , 맨위에 새로운 블럭 생성 !

        boolean isOk = false; // 색깔이 같은지 판별 결과
        ImageView imageView = (ImageView) v; //어떤 색상이든지View type 의  v 로 받는다.
        // 맨 아래 블럭 iv[7]의 ImageView 의 색상과 일치하는 버튼인지 판정
        switch (imageView.getId()){
            case R.id.ivRed: //빨간 버튼 클릭시

                if("0".equals(iv[7].getTag().toString())) isOk = true;  // 빨간블럭의 tag값은 "0"
                break;
            case R.id.ivGreen: //초록 버튼 클릭시
                if("1".equals(iv[7].getTag().toString())) isOk = true;  // 초록블럭의 tag값은 "1"
                break;
            case R.id.ivBlue: //파란 버튼 클릭시
                if("2".equals(iv[7].getTag().toString())) isOk = true;  // 파란블럭의 tag값은 "2"
                break;
        } // end switch

        if(isOk) {  // 버튼 색깔을 맞추었다면!

            // 가장 위의 블럭 ImageView 는 랜덤으로 생성
            int num = new Random().nextInt(3); // 0, 1,  2 중 랜덤
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num + "");

            // 위의 쌓여있는 7개 블럭을 한칸씩 아래로 이동, iv[i] > iv[i +1]
            for(int i = iv.length - 2 ; i >= 0; i--){
                num = Integer.parseInt(iv[i].getTag().toString()); // "0", "1", "2"
                iv[i +1].setImageResource(img[num]);    // i  아래쪽 블럭 이미지 업데이트
                iv[i +1].setTag(num + ""); // i 아래쪽 블럭 tag 값 업데이트
            } // end for

            //진동과 음향 넣기
            vibrator.vibrate(200); // 0.2초
            soundPool.play(soundID_OK, 1, 1, 0, 0, 1);

            // 점수 올리기
            point++;
            tvPoint.setText("점수:" + point);

        } else {   // 버튼 색깔을 못맞추었다면!

            //진동과 음향 넣기
            vibrator.vibrate(new long[] {20,80,20,80,20,80}, -1); // 불규칙한
            soundPool.play(soundID_Error, 1, 1, 0, 0, 1);

            // 점수 내리기
            point--;
            tvPoint.setText("점수:" + point);

        }
    } // end onClick()

    @Override
    protected void onResume() {
        super.onResume();
        // 자원 획득

        // Vibrator 객체 얻어오기
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        // SoundPool 객체 가져오기
        soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK = soundPool.load(Start.this, R.raw.gun3, 1);
        soundID_Error = soundPool.load(Start.this, R.raw.error, 1);

        // MediaPlayer 객체 , 연주시작
        mp = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        mp.setLooping(false); // 반복재생 안함
        mp.start(); // 재생 시작

    }

    @Override
   protected void onPause() {
        super.onPause();
        // 자원해제
        if(mp != null){
            mp.stop();
            mp.release();
        }
    }
} // end Activity
