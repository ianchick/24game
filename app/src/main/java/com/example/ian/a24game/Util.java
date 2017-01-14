package com.example.ian.a24game;

import java.util.Random;

import static android.R.attr.max;

/**
 * Created by Ian on 1/13/2017.
 */

public class Util {

    static int getRandomDigit(){
        int max = 9;
        int min = 1;
        Random random = new Random();
        int num = random.nextInt(max - min + 1) + min;
        return num;
    }

    static int[] getDigits(){
        int[] values = new int[4];
        values[0] = getRandomDigit();
        values[1] = getRandomDigit();
        values[2] = getRandomDigit();
        values[3] = getRandomDigit();

        return values;
    }

    static boolean calculateTwentyFour(int a, int b, int c, int d, int total){

        return true;
    }
}
