package com.simon.datecountdown;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerControler {
    private static MediaPlayer sMediaPlayer;

    private MediaPlayerControler() {
    }


    public static MediaPlayer getInstance(Context context) {
        if (sMediaPlayer == null) {
            sMediaPlayer = MediaPlayer.create(context, R.raw.summer);
        }
        return sMediaPlayer;
    }
}
