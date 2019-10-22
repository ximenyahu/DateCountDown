package com.simon.datecountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTimeCountDown;
    private Date mFinalDate;
    private SimpleDateFormat mSimpleDateFormat;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTimeCountDown = findViewById(R.id.tv_time_count_down);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //这里设置的时2019-11-29，可以根据需要更改
            mFinalDate = mSimpleDateFormat.parse("2019-11-29 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayerControler.getInstance(this);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        final long finalTimeInMIlls = mFinalDate.getTime();
        if (Calendar.getInstance().getTimeInMillis() < finalTimeInMIlls) {
            new CountDownTimer(finalTimeInMIlls - (new Date().getTime()), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long durationInMills = (finalTimeInMIlls - new Date().getTime()) / 1000;
                    int durationDays = (int) (durationInMills / (60 * 60 * 24));
                    int temp = (int) (durationInMills % (60 * 60 * 24));
                    int durationHours = temp / (60 * 60);
                    int temp2 = temp % (60 * 60);
                    int durationMins = temp2 / 60;
                    int durationMills = temp2 % 60;

                    mTvTimeCountDown.setText(String.format("%02d天%02d小时%02d分钟%02d秒",
                            durationDays, durationHours, durationMins, durationMills));
                }

                @Override
                public void onFinish() {
                    mTvTimeCountDown.setText("解放喽！哈哈");
                }
            }.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
