package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class aayushvoicenote extends AppCompatActivity {
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aayushvoicenote);
        ourSong= MediaPlayer.create(aayushvoicenote.this,R.raw.aayush);
        ourSong.start();



    }
    public void aayuvid(View view){
        Intent intent=new Intent(this,aayushvideo.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}



