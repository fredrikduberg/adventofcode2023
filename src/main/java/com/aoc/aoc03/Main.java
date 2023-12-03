package com.aoc.aoc03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static long maxX = 0;
    static long maxY = 0;
    public static void main(String[] args) {
        List<String> game = getFromFile("03.txt");
        solvePart1(game);
    }


    static private void solvePart1(List<String> game) {
        int y = 0;
        List<Long> numberList = new ArrayList<>();
        Map<String, List<Long>> stars = new HashMap<>();
        for (String line : game) {
            String aNumber="";
            for (int x=0; x<line.length(); x++) {
                String ss = line.substring(x, x+1);
                if (isNumber(ss)) {
                    aNumber = aNumber + line.charAt(x);
                } else if (!aNumber.isEmpty()){
                    // some number now exists
                    int len = aNumber.length();
                    boolean isPart = getIsPart(game, Long.parseLong(aNumber), x-len, y, len, stars);
                    if (isPart) {
                        numberList.add(Long.parseLong(aNumber));
                    }
                    aNumber="";
                }
                if (!aNumber.isEmpty() && x == (maxX-1)) {
                    int len = aNumber.length();
                    boolean isPart = getIsPart(game, Long.parseLong(aNumber),x-len+1, y, len, stars);
                    if (isPart) {
                        numberList.add(Long.parseLong(aNumber));
                    }
                    aNumber = "";
                }

            }
            y++;
        }

        long sum = 0;
        for (Long l : numberList) {
            sum += l;
        }
        System.out.println("Part 1 Sum is: " + sum); // 531932 rätt

        long sum2 = 0;
        for (Map.Entry<String, List<Long>> entry : stars.entrySet()) {
            List<Long> partList = entry.getValue();
            if (partList.size() == 2) {
                long partSum = partList.get(0) * partList.get(1);
                sum2 += partSum;
            }
        }
        System.out.println("Part 2 Sum is: " + sum2);
    }

    private static boolean getIsPart(List<String> game, long actualNumber, int start, int y, int len, Map<String, List<Long>> stars) {
        // check above
        String thisLine = game.get(y);
        boolean isPart = false;
        if (y>0) {
            String aboveLine = game.get(y-1);
            int scanStartPos = 0;
            if (start>0) {
                scanStartPos = start-1;
            }
            long scanEndPos = start+len;
            if (scanEndPos>=(maxX-1)) {
                scanEndPos = (maxX-1);
            }
            for (int x=scanStartPos; x<scanEndPos+1; x++) {
                if (!isNumberOrDot(aboveLine.substring(x, x+1))) {
                    isPart = true;
                    if (aboveLine.substring(x, x+1).equals("*")) {
                        String xy = getXY(x, y-1);
                        List<Long> partList = stars.get(xy);
                        if (partList == null) {
                            partList = new ArrayList<>();
                            partList.add(actualNumber);
                            stars.put(xy, partList);
                            System.out.println("Found star at: " + xy + ", adding: " + actualNumber);
                        } else {
                            partList.add(actualNumber);
                            System.out.println("Found old star at: " + xy + ", adding: " + actualNumber);
                        }
                    }
                }
            }
        }
        // check below
        if (y<(maxY-1)) {
            String belowLine = game.get(y+1);
            int scanStart = 0;
            if (start>0) {
                scanStart = start-1;
            }
            long scanEndPos = start+len;
            if (scanEndPos>=(maxX-1)) {
                scanEndPos = (maxX-1);
            }
            for (int x=scanStart; x<scanEndPos+1; x++) {
                String c = belowLine.substring(x, x+1);
                if (!isNumberOrDot(c)) {
                    isPart = true;
                    if (belowLine.substring(x, x+1).equals("*")) {
                        String xy = getXY(x, y+1);
                        List<Long> partList = stars.get(xy);
                        if (partList == null) {
                            partList = new ArrayList<>();
                            partList.add(actualNumber);
                            stars.put(xy, partList);
                            System.out.println("Found star at: " + xy + ", adding: " + actualNumber);
                        } else {
                            partList.add(actualNumber);
                            System.out.println("Found old star at: " + xy + ", adding: " + actualNumber);
                        }
                    }
                }
            }
        }
        // check before
        if (start>0) {
            if (!isNumberOrDot(thisLine.substring(start-1, start))) {
                isPart = true;
                int x = start-1;
                if (thisLine.substring(x, x+1).equals("*")) {
                    String xy = getXY(x, y);
                    List<Long> partList = stars.get(xy);
                    if (partList == null) {
                        partList = new ArrayList<>();
                        partList.add(actualNumber);
                        stars.put(xy, partList);
                        System.out.println("Found star at: " + xy + ", adding: " + actualNumber);
                    } else {
                        partList.add(actualNumber);
                        System.out.println("Found old star at: " + xy + ", adding: " + actualNumber);
                    }
                }
            }
        }

        // check after
        if ((start+len)<maxX) {
            String c = thisLine.substring(start+len, start+len+1);
            if (!isNumberOrDot(c)) {
                isPart = true;
                int x = start + len;
                if (thisLine.substring(x, x+1).equals("*")) {
                    String xy = getXY(x, y);
                    List<Long> partList = stars.get(xy);
                    if (partList == null) {
                        partList = new ArrayList<>();
                        partList.add(actualNumber);
                        stars.put(xy, partList);
                        System.out.println("Found star at: " + xy + ", adding: " + actualNumber);
                    } else {
                        partList.add(actualNumber);
                        System.out.println("Found old star at: " + xy + ", adding: " + actualNumber);
                    }
                }
            }
        }
        return isPart;
    }

    private static boolean isNumber(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isNumberOrDot(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return strNum.equals(".");
        }
        return true;
    }
    private static int getX(String xy) {
        return Integer.parseInt(xy.substring(0,1));
    }

    private static int getY(String xy) {
        return Integer.parseInt(xy.substring(1,1));
    }

    private static String getXY(int x, int y) {
        return x + "_" + y;
    }

    private static List<String> getFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            long y=0;
            while (line != null) {
                maxX = line.length();
                try {
                    data.add(line);

                } catch (NumberFormatException nf) {
                    nf.printStackTrace();
                }
                y++;
                line = reader.readLine();
            }

            maxY = y;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = com.aoc.aoc01.Main.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) return null;
        String file1 = url.getFile();
        return new File(file1);
    }
}
