package com.gildedrose;

import java.util.stream.IntStream;

public class GildedRoseAbstractTest {
    public static final int ONCE = 1;
    public static final int TWICE = 2;
    public static final int THRICE = 3;


    public static final int MANY_TIMES = 100;

    public static void repeat(int count, Runnable action) {
        IntStream.range(0, count).forEach(i -> action.run());
    }

    public static void repeatManyTimes(Runnable action) {
        IntStream.range(0, MANY_TIMES).forEach(i -> action.run());
    }

}
