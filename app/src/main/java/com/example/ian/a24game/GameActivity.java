package com.example.ian.a24game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    int current_int;
    String current_operation;
    boolean lastClickInt;
    ArrayList<Integer> active_numbers;

    int seconds;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        current_int = 0;
        current_operation = "";
        setActive_numbers();

        ImageButton b1 =(ImageButton)findViewById(R.id.button1);
        ImageButton b2 =(ImageButton)findViewById(R.id.button2);
        ImageButton b3 =(ImageButton)findViewById(R.id.button3);
        ImageButton b4 =(ImageButton)findViewById(R.id.button4);
        ImageButton plus =(ImageButton)findViewById(R.id.plus);
        ImageButton minus =(ImageButton)findViewById(R.id.minus);
        ImageButton multiply =(ImageButton)findViewById(R.id.multiply);
        ImageButton divide =(ImageButton)findViewById(R.id.divide);
        ImageButton clear =(ImageButton)findViewById(R.id.clear);
        ImageButton skip =(ImageButton)findViewById(R.id.skip);

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


        seconds = 0;
        intent = new Intent(this, GameOverActivity.class);
    }

    @Override
     public void onClick(View view){
        TextView inputView = (TextView) findViewById(R.id.input);
        switch (view.getId()){
            case R.id.button1:
                clickIntegerButton(active_numbers.get(0));
                ImageButton button1 = (ImageButton) findViewById(R.id.button1);
                button1.setEnabled(false);
                gameOver();
                break;
            case R.id.button2:
                clickIntegerButton(active_numbers.get(1));
                ImageButton button2 = (ImageButton) findViewById(R.id.button2);
                button2.setEnabled(false);
                gameOver();
                break;
            case R.id.button3:
                clickIntegerButton(active_numbers.get(2));
                ImageButton button3 = (ImageButton) findViewById(R.id.button3);
                button3.setEnabled(false);
                gameOver();
                break;
            case R.id.button4:
                clickIntegerButton(active_numbers.get(3));
                ImageButton button4 = (ImageButton) findViewById(R.id.button4);
                button4.setEnabled(false);
                gameOver();
                break;
            case R.id.plus:
                ImageButton button5 = (ImageButton) findViewById(R.id.plus);
                button5.setEnabled(false);
                clickOperator("+");
                break;
            case R.id.minus:
                ImageButton button6 = (ImageButton) findViewById(R.id.minus);
                button6.setEnabled(false);
                clickOperator("-");
                break;
            case R.id.multiply:
                ImageButton button7 = (ImageButton) findViewById(R.id.multiply);
                button7.setEnabled(false);
                clickOperator("x");
                break;
            case R.id.divide:
                ImageButton button8 = (ImageButton) findViewById(R.id.divide);
                button8.setEnabled(false);
                clickOperator("/");
                break;
            case R.id.clear:
                current_int = 0;
                current_operation = "";
                lastClickInt = false;
                inputView.setText("");
                enableButtons();
                break;
            case R.id.skip:
                setActive_numbers();
                current_operation = "";
                current_int = 0;
                lastClickInt = true;
                inputView.setText("");
                seconds = 0;
                enableButtons();
                break;
        }
    }

    protected void clickIntegerButton(int i){
        if (current_operation==""){
            current_int = i;
            lastClickInt = true;
            setFormula();
        } else if (!lastClickInt){
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
            setFormula();
        }
    }

    protected void clickOperator(String s){
        if (current_int!=0 & lastClickInt) {
            current_operation = s;
            lastClickInt = false;
            setFormula();
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

    protected void gameOver(){
        if(current_int==24){
            startActivity(intent);
        }
    }

    protected void enableButtons(){
        findViewById(R.id.button1).setEnabled(true);
        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(R.id.button4).setEnabled(true);
        findViewById(R.id.plus).setEnabled(true);
        findViewById(R.id.minus).setEnabled(true);
        findViewById(R.id.multiply).setEnabled(true);
        findViewById(R.id.divide).setEnabled(true);
    }
}
