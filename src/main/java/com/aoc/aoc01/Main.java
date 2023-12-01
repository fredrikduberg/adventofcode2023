package com.aoc.aoc01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> data = getFromFile("01.txt");
        getResult(data);
    }

    private static void getResult(List<Integer> data) {
        Integer last = null;
        int numOfIncreases = 0;
        for (Integer point : data) {
            if (last != null) {
                if (point > last) {
                    numOfIncreases++;
                }
            }
            last = point;
        }
        System.out.println("Part 1, Number of increases for: " + numOfIncreases);
    }

    private static List<Integer> getFromFile(String fileName) {
        List<Integer> data = new ArrayList<>();
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                try {
                    data.add(Integer.parseInt(line));
                } catch (NumberFormatException nf) {
                    nf.printStackTrace();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) return null;
        String file1 = url.getFile();
        return new File(file1);
    }
}
