package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class bhabhivoice extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhabhivoice);
        ourSong= MediaPlayer.create(bhabhivoice.this,R.raw.bhabhi);
        ourSong.start();



    }
    public void ashrayvid123(View view){
        Intent intent=new Intent(this,isha1.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}