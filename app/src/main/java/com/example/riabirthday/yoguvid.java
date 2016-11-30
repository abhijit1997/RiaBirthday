package com.example.riabirthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class yoguvid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoguvid);
    }
    public void bhabhi1(View view) {

        Intent intent = new Intent(this, bhabhi1.class);
        startActivity(intent);


    }
}
