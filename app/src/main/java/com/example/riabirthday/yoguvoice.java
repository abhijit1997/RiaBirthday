package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class yoguvoice extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoguvoice);
        ourSong= MediaPlayer.create(yoguvoice.this,R.raw.yogu);
        ourSong.start();



    }
    public void yoguvid(View view) {

        Intent intent = new Intent(this, bhabhi1.class);
        startActivity(intent);


    }
    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }


}
