package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class khatervoicenote extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatervoicenote);
        ourSong= MediaPlayer.create(khatervoicenote.this,R.raw.khaterv);
        ourSong.start();



    }
    public void next823(View view){
        Intent intent=new Intent(this,khatervideo.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}


