package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class chachivoicenote extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chachivoicenote);
        ourSong= MediaPlayer.create(chachivoicenote.this,R.raw.chachi);
        ourSong.start();



    }
    public void shail (View view){
        Intent intent=new Intent(this,chachivideo.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
