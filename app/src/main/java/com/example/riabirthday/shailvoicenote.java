package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class shailvoicenote extends AppCompatActivity {
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shailvoicenote);
        ourSong= MediaPlayer.create(shailvoicenote.this,R.raw.shail);
        ourSong.start();



    }
    public void shailvid(View view){
        Intent intent=new Intent(this,shailvideo.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}