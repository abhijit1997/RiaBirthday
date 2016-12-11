package com.example.riabirthday;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;


public class VideoActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_VIDEO_PATH = "intent_extra_video_path";

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = (VideoView) findViewById(R.id.video_view);

        String videoPath = "android.resource://" + getPackageName() + "/" + getIntent().getIntExtra(INTENT_EXTRA_VIDEO_PATH, 0);
        mVideoView.setVideoPath(videoPath);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }
}
