package com.example.ian.a24game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    protected void play(View view){
        Intent intent = new Intent(Home.this, GameActivity.class);
        startActivity(intent);
    }
}
