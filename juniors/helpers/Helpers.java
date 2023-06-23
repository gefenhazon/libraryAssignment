package com.code.java.juniors.helpers;

import java.util.Random;

public class Helpers {

    public static final long MAX_RANGE = 1999999999;

    public static long getRandomNumber(long min, long max) {
        Random random = new Random();
        return random.longs(min, (max + 1)).findFirst().getAsLong();
    }
}
