package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class abhivoice2 extends AppCompatActivity {


    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhivoice2);
        ourSong= MediaPlayer.create(abhivoice2.this,R.raw.abhi2);
        ourSong.start();



    }
    public void abhi3(View view){
        Intent intent=new Intent(this,abhi3.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}