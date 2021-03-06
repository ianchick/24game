package com.myapp.ian.a24game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.myapp.ian.a24game.R.id.button4;


public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    int current_int;
    String current_operation;
    boolean lastClickInt;
    ArrayList<Integer> active_numbers;
    int numbersUsed;

    long time;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        long bestScore = prefs.getLong("bestScore", 0);
        int seconds = (int) (bestScore / 1000) % 60 ;
        int minutes = (int) ((bestScore / (1000*60)) % 60);

        TextView scoreView = (TextView)findViewById(R.id.best_score);

        String timeString = String.format("%02d:%02d", minutes, seconds);
        scoreView.setText(timeString);

        current_int = 9999;
        current_operation = "";
        numbersUsed = 0;
        lastClickInt = false;
        setActive_numbers();

        ImageButton b1 = (ImageButton) findViewById(R.id.button1);
        ImageButton b2 = (ImageButton) findViewById(R.id.button2);
        ImageButton b3 = (ImageButton) findViewById(R.id.button3);
        ImageButton b4 = (ImageButton) findViewById(button4);
        ImageButton plus = (ImageButton) findViewById(R.id.plus);
        ImageButton minus = (ImageButton) findViewById(R.id.minus);
        ImageButton multiply = (ImageButton) findViewById(R.id.multiply);
        ImageButton divide = (ImageButton) findViewById(R.id.divide);
        ImageButton clear = (ImageButton) findViewById(R.id.clear);
        ImageButton skip = (ImageButton) findViewById(R.id.skip);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        clear.setOnClickListener(this);
        skip.setOnClickListener(this);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.start();
    }

    @Override
    public void onClick(View view){
        TextView inputView = (TextView) findViewById(R.id.input);
        switch (view.getId()){
            case R.id.button1:
                ImageButton button1 = (ImageButton) findViewById(R.id.button1);
                clickIntegerButton(active_numbers.get(0), button1);
                break;
            case R.id.button2:
                ImageButton button2 = (ImageButton) findViewById(R.id.button2);
                clickIntegerButton(active_numbers.get(1), button2);
                break;
            case R.id.button3:
                ImageButton button3 = (ImageButton) findViewById(R.id.button3);
                clickIntegerButton(active_numbers.get(2), button3);
                break;
            case R.id.button4:
                ImageButton button4 = (ImageButton) findViewById(R.id.button4);
                clickIntegerButton(active_numbers.get(3), button4);
                break;
            case R.id.plus:
                ImageButton button5 = (ImageButton) findViewById(R.id.plus);
                clickOperator("+", button5);
                break;
            case R.id.minus:
                ImageButton button6 = (ImageButton) findViewById(R.id.minus);
                clickOperator("-", button6);
                break;
            case R.id.multiply:
                ImageButton button7 = (ImageButton) findViewById(R.id.multiply);
                clickOperator("x", button7);
                break;
            case R.id.divide:
                ImageButton button8 = (ImageButton) findViewById(R.id.divide);
                clickOperator("/", button8);
                break;
            case R.id.clear:
                clear();
                break;
            case R.id.skip:
                resetFilters();
                setActive_numbers();
                current_operation = "";
                current_int = 9999;
                numbersUsed = 0;
                inputView.setText("");
                lastClickInt = false;
                enableButtons();
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
        }
    }

    protected void clickIntegerButton(int i, View button){
        if (!lastClickInt){
            if (current_int == 9999){
                current_int = i;
            }
            switch (current_operation) {
                case "+":
                    current_int = current_int + i;
                    break;
                case "-":
                    current_int = current_int - i;
                    break;
                case "x":
                    current_int = current_int * i;
                    break;
                case "/":
                    current_int = current_int / i;
                    break;
            }
            current_operation = "";
            lastClickInt = true;
            numbersUsed++;

            ((ImageButton)button).setColorFilter(Color.argb(150,200,200,200));
            button.setEnabled(false);
            setFormula();
            gameOver();
        }
    }

    protected void clickOperator(String s, View button){
        if (lastClickInt) {
            current_operation = s;
            lastClickInt = false;
            setFormula();
            ((ImageButton)button).setColorFilter(Color.argb(150,200,200,200));
            button.setEnabled(false);
        }
    }

    protected void setActive_numbers() {
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        active_numbers = Util.getValidDigits();
        setButton(button1, active_numbers.get(0));
        setButton(button2, active_numbers.get(1));
        setButton(button3, active_numbers.get(2));
        setButton(button4, active_numbers.get(3));
    }

    protected void setButton(View button, int i) {
        switch (i) {
            case 1:
                ((ImageButton) button).setImageResource(R.drawable.one);
                break;
            case 2:
                ((ImageButton) button).setImageResource(R.drawable.two);
                break;
            case 3:
                ((ImageButton) button).setImageResource(R.drawable.three);
                break;
            case 4:
                ((ImageButton) button).setImageResource(R.drawable.four);
                break;
            case 5:
                ((ImageButton) button).setImageResource(R.drawable.five);
                break;
            case 6:
                ((ImageButton) button).setImageResource(R.drawable.six);
                break;
            case 7:
                ((ImageButton) button).setImageResource(R.drawable.seven);
                break;
            case 8:
                ((ImageButton) button).setImageResource(R.drawable.eight);
                break;
            case 9:
                ((ImageButton) button).setImageResource(R.drawable.nine);
                break;
        }
    }

    protected void setFormula() {
        String text = current_int + " " + current_operation;
        TextView inputView = (TextView) findViewById(R.id.input);
        inputView.setText(text);
    }

    protected void clear(){
        TextView inputView = (TextView) findViewById(R.id.input);
        current_int = 9999;
        numbersUsed = 0;
        current_operation = "";
        lastClickInt = false;
        inputView.setText("");
        resetFilters();
        enableButtons();
    }

    protected void gameOver(){
        if (numbersUsed == 4) {
            if (current_int == 24) {
                time = SystemClock.elapsedRealtime() - chronometer.getBase();
                Intent intent = new Intent(this, GameOverActivity.class);
                intent.putExtra("TIME", time);
                startActivity(intent);
                finish();
            } else {
                clear();
            }
        }
    }

    protected void enableButtons(){
        findViewById(R.id.button1).setEnabled(true);
        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(button4).setEnabled(true);
        findViewById(R.id.plus).setEnabled(true);
        findViewById(R.id.minus).setEnabled(true);
        findViewById(R.id.multiply).setEnabled(true);
        findViewById(R.id.divide).setEnabled(true);
    }

    protected void resetFilters(){
        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) findViewById(R.id.plus);
        ImageButton button6 = (ImageButton) findViewById(R.id.minus);
        ImageButton button7 = (ImageButton) findViewById(R.id.multiply);
        ImageButton button8 = (ImageButton) findViewById(R.id.divide);

        button1.clearColorFilter();
        button2.clearColorFilter();
        button3.clearColorFilter();
        button4.clearColorFilter();
        button5.clearColorFilter();
        button6.clearColorFilter();
        button7.clearColorFilter();
        button8.clearColorFilter();

    }
}
