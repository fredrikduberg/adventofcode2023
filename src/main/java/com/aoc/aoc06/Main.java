package com.aoc.aoc06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // Part 1 TEST
    // static List<Integer> times = Arrays.asList(7, 15, 30);
    // static List<Integer> distances = Arrays.asList(9, 40, 200);

    // Part 1 Real
    //static List<Integer> times = Arrays.asList(44, 89, 96, 91);
    //static List<Integer> distances = Arrays.asList(277, 1136, 1890, 1768);

    // Part 2 TEST
    //static List<Integer> times = Arrays.asList(71530);
    //static List<Integer> distances = Arrays.asList(940200);

    // Part 2 Real
    static List<Long> times = Arrays.asList(44899691L);
    static List<Long> distances = Arrays.asList(277113618901768L);
    public static void main(String[] args) {
        solvePart1();
    }


    private static void solvePart1() {
        int ix =0;
        long wins = 1L;
        for (Long time : times) {
            int win = 0;
            for (long i = 1; i<time; i++) {
                long remainingTime = (times.get(ix) - i);
                long thisDistance = remainingTime * i;
                if (thisDistance > distances.get(ix)) {
                    win++;
                }
            }
            wins = wins * win;
            ix++;
        }
        System.out.println("Part 2 margin = " + wins);
        // Part 1 Real : 2344708
    }

}
