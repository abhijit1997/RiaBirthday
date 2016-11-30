package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ishavoice extends AppCompatActivity {

    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishavoice);
        ourSong= MediaPlayer.create(ishavoice.this,R.raw.isha);
        ourSong.start();



    }

    public void ishavid(View view) {
        Intent intent = new Intent(this, ishavid.class);
        startActivity(intent);

    }


    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
