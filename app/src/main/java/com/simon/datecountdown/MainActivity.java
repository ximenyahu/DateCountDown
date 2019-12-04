package com.simon.datecountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private Date mOriginDate;
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
            mOriginDate = mSimpleDateFormat.parse("2019-10-06 14:42:00");
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

        final long originTimeInMIlls = mOriginDate.getTime();
        if (Calendar.getInstance().getTimeInMillis() > originTimeInMIlls) {
            new CountDownTimer(Integer.MAX_VALUE, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long millisUntilFinished) {
                    long durationInMills = (new Date().getTime() - originTimeInMIlls) / 1000;
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
