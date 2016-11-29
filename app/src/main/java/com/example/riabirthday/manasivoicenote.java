package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class manasivoicenote extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manasivoicenote);
        ourSong= MediaPlayer.create(manasivoicenote.this,R.raw.x1);
        ourSong.start();



    }
    public void gomi1 (View view){
        Intent intent=new Intent(this,gomi1.class);
        startActivity(intent);


    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
