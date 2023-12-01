package com.aoc.aoc01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> data = getFromFile("01.txt");
        getResult(data);
        List<String> data2 = getFromFile("01.txt");
        getResult2(data2);
    }

    private static void getResult(List<String> data) {
        int total=0;
        for (String point : data) {
            String number1 = findFirst(point);
            String number2 = findLast(point);
            String combined = number1.concat(number2);
            total += Integer.parseInt(combined);
        }
        System.out.println("Total task 1 =" + total);
    }

    private static void getResult2(List<String> data) {
        int total=0;
        for (String point : data) {
            String number1 = findFirst(point);
            String number2 = findLast(point);
            String combined = number1.concat(number2);
            total += Integer.parseInt(combined);
        }
        System.out.println("Total task 2=" + total);
    }

    private static String findFirst(String line) {
        int ix0 = line.indexOf("0");
        int ix1 = line.indexOf("1");
        int ix2 = line.indexOf("2");
        int ix3 = line.indexOf("3");
        int ix4 = line.indexOf("4");
        int ix5 = line.indexOf("5");
        int ix6 = line.indexOf("6");
        int ix7 = line.indexOf("7");
        int ix8 = line.indexOf("8");
        int ix9 = line.indexOf("9");
        int ixOne = line.indexOf("one");
        int ixTwo = line.indexOf("two");
        int ixThree = line.indexOf("three");
        int ixFour = line.indexOf("four");
        int ixFive = line.indexOf("five");
        int ixSix = line.indexOf("six");
        int ixSeven = line.indexOf("seven");
        int ixEight = line.indexOf("eight");
        int ixNine = line.indexOf("nine");
        int lastIndex = 999999999;
        int finalNumber = -1;
        if (ix0 > -1 && (ix0 < lastIndex)) {
            finalNumber = 0;
            lastIndex = ix0;
        }
        if (ix1 > -1 && (ix1 < lastIndex)) {
            finalNumber = 1;
            lastIndex = ix1;
        }
        if (ix2 > -1 && (ix2 < lastIndex)) {
            finalNumber = 2;
            lastIndex = ix2;
        }
        if (ix3 > -1 && (ix3 < lastIndex)) {
            finalNumber = 3;
            lastIndex = ix3;
        }
        if (ix4 > -1 && (ix4 < lastIndex)) {
            finalNumber = 4;
            lastIndex = ix4;
        }
        if (ix5 > -1 && (ix5 < lastIndex)) {
            finalNumber = 5;
            lastIndex = ix5;
        }
        if (ix6 > -1 && (ix6 < lastIndex)) {
            finalNumber = 6;
            lastIndex = ix6;
        }
        if (ix7 > -1 && (ix7 < lastIndex)) {
            finalNumber = 7;
            lastIndex = ix7;
        }
        if (ix8 > -1 && (ix8 < lastIndex)) {
            finalNumber = 8;
            lastIndex = ix8;
        }
        if (ix9 > -1 && (ix9 < lastIndex)) {
            finalNumber = 9;
            lastIndex = ix9;
        }
        if (ixOne > -1 && (ixOne < lastIndex)) {
            finalNumber = 1;
            lastIndex = ixOne;
        }
        if (ixTwo > -1 && (ixTwo < lastIndex)) {
            finalNumber = 2;
            lastIndex = ixTwo;
        }
        if (ixThree > -1 && (ixThree < lastIndex)) {
            finalNumber = 3;
            lastIndex = ixThree;
        }
        if (ixFour > -1 && (ixFour < lastIndex)) {
            finalNumber = 4;
            lastIndex = ixFour;
        }
        if (ixFive > -1 && (ixFive < lastIndex)) {
            finalNumber = 5;
            lastIndex = ixFive;
        }
        if (ixSix > -1 && (ixSix < lastIndex)) {
            finalNumber = 6;
            lastIndex = ixSix;
        }
        if (ixSeven > -1 && (ixSeven < lastIndex)) {
            finalNumber = 7;
            lastIndex = ixSeven;
        }
        if (ixEight > -1 && (ixEight < lastIndex)) {
            finalNumber = 8;
            lastIndex = ixEight;
        }
        if (ixNine > -1 && (ixNine < lastIndex)) {
            finalNumber = 9;
        }
        return String.valueOf(finalNumber);
    }

    private static String findLast(String line) {
        int ix0 = line.lastIndexOf("0");
        int ix1 = line.lastIndexOf("1");
        int ix2 = line.lastIndexOf("2");
        int ix3 = line.lastIndexOf("3");
        int ix4 = line.lastIndexOf("4");
        int ix5 = line.lastIndexOf("5");
        int ix6 = line.lastIndexOf("6");
        int ix7 = line.lastIndexOf("7");
        int ix8 = line.lastIndexOf("8");
        int ix9 = line.lastIndexOf("9");
        int ixOne = line.lastIndexOf("one");
        int ixTwo = line.lastIndexOf("two");
        int ixThree = line.lastIndexOf("three");
        int ixFour = line.lastIndexOf("four");
        int ixFive = line.lastIndexOf("five");
        int ixSix = line.lastIndexOf("six");
        int ixSeven = line.lastIndexOf("seven");
        int ixEight = line.lastIndexOf("eight");
        int ixNine = line.lastIndexOf("nine");
        int lastIndex = -1;
        int finalNumber = -1;
        if (ix0 > -1 && (ix0 > lastIndex)) {
            finalNumber = 0;
            lastIndex = ix0;
        }
        if (ix1 > -1 && (ix1 > lastIndex)) {
            finalNumber = 1;
            lastIndex = ix1;
        }
        if (ix2 > -1 && (ix2 > lastIndex)) {
            finalNumber = 2;
            lastIndex = ix2;
        }
        if (ix3 > -1 && (ix3 > lastIndex)) {
            finalNumber = 3;
            lastIndex = ix3;
        }
        if (ix4 > -1 && (ix4 > lastIndex)) {
            finalNumber = 4;
            lastIndex = ix4;
        }
        if (ix5 > -1 && (ix5 > lastIndex)) {
            finalNumber = 5;
            lastIndex = ix5;
        }
        if (ix6 > -1 && (ix6 > lastIndex)) {
            finalNumber = 6;
            lastIndex = ix6;
        }
        if (ix7 > -1 && (ix7 > lastIndex)) {
            finalNumber = 7;
            lastIndex = ix7;
        }
        if (ix8 > -1 && (ix8 > lastIndex)) {
            finalNumber = 8;
            lastIndex = ix8;
        }
        if (ix9 > -1 && (ix9 > lastIndex)) {
            finalNumber = 9;
            lastIndex = ix9;
        }
        if (ixOne > -1 && (ixOne > lastIndex)) {
            finalNumber = 1;
            lastIndex = ixOne;
        }
        if (ixTwo > -1 && (ixTwo > lastIndex)) {
            finalNumber = 2;
            lastIndex = ixTwo;
        }
        if (ixThree > -1 && (ixThree > lastIndex)) {
            finalNumber = 3;
            lastIndex = ixThree;
        }
        if (ixFour > -1 && (ixFour > lastIndex)) {
            finalNumber = 4;
            lastIndex = ixFour;
        }
        if (ixFive > -1 && (ixFive > lastIndex)) {
            finalNumber = 5;
            lastIndex = ixFive;
        }
        if (ixSix > -1 && (ixSix > lastIndex)) {
            finalNumber = 6;
            lastIndex = ixSix;
        }
        if (ixSeven > -1 && (ixSeven > lastIndex)) {
            finalNumber = 7;
            lastIndex = ixSeven;
        }
        if (ixEight > -1 && (ixEight > lastIndex)) {
            finalNumber = 8;
            lastIndex = ixEight;
        }
        if (ixNine > -1 && (ixNine > lastIndex)) {
            finalNumber = 9;
        }
        return String.valueOf(finalNumber);
    }

    private static List<String> getFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                try {
                    data.add(line);
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
