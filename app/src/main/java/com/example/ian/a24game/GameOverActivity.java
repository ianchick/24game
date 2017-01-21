package com.example.ian.a24game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.R.attr.value;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    protected void play(){
        Intent myIntent = new Intent(GameOverActivity.this, GameActivity.class);
        GameOverActivity.this.startActivity(myIntent);    }
}
