package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dhruvivoice extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhruvivoice);
        ourSong= MediaPlayer.create(dhruvivoice.this,R.raw.dhruvi);
        ourSong.start();



    }
    public void dhruvivid(View view){
        Intent intent=new Intent(this,dhruvivid.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}


