package com.aoc.aoc05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static class Card {
        long sourceStart;
        long destinationStart;
        long length;
    }
    static List<Card> seedToSoilMap = new ArrayList<>();
    static List<Card> soilToFertilizerMap = new ArrayList<>();
    static List<Card> fertilizerToWaterMap = new ArrayList<>();
    static List<Card> waterToLightMap = new ArrayList<>();
    static List<Card> lightToTemperatureMap = new ArrayList<>();
    static List<Card> temperatureToHumidity = new ArrayList<>();
    static List<Card> humidityToLocation = new ArrayList<>();
    static List<Long> seeds = new ArrayList<>();
    static List<Seed> seedPairs = new ArrayList<>();
    public static void main(String[] args) {
        getFromFile("05.txt");
        solvePart1();
        solvePart2();
    }

   static class Seed {
        long startNumber;
        long length;
    }


    private static void solvePart1() {
        long lowest = 999999999999999999L;
        for (Long seed : seeds) {
            long soil = getDestination(seedToSoilMap, seed);

            long fertilizer = getDestination(soilToFertilizerMap, soil);

            long water = getDestination(fertilizerToWaterMap, fertilizer);

            long light = getDestination(waterToLightMap, water);

            long temp = getDestination(lightToTemperatureMap, light);

            long humidity = getDestination(temperatureToHumidity, temp);

            long location = getDestination(humidityToLocation, humidity);

            if (location < lowest) lowest = location;
        }
        System.out.println("Part 1 lowest = " + lowest);
    }

    private static void solvePart2() {
        long lowest = 999999999999999999L;

        for (Seed seedPair : seedPairs) {
            for (long seed = seedPair.startNumber; seed < seedPair.startNumber + seedPair.length; seed++) {


                long soil = getDestination(seedToSoilMap, seed);

                long fertilizer = getDestination(soilToFertilizerMap, soil);

                long water = getDestination(fertilizerToWaterMap, fertilizer);

                long light = getDestination(waterToLightMap, water);

                long temp = getDestination(lightToTemperatureMap, light);

                long humidity = getDestination(temperatureToHumidity, temp);

                long location = getDestination(humidityToLocation, humidity);

                if (location < lowest) lowest = location;
            }
        }
        System.out.println("Part 2 lowest = " + lowest);
    }

    private static long getDestination(List<Card> thisList, long source) {
        for (Card card : thisList) {
            if (source>=card.sourceStart && source<(card.sourceStart + card.length)) {
                long diff = source - card.sourceStart;
                return card.destinationStart + diff;
            }
        }
        // destination is same as source
        return source;
    }

    private static void getFromFile(String fileName) {
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            int ix = line.indexOf(":");
            String numbers = line.substring(ix+2);
            String[] seedNum = numbers.split(" ");
            boolean first = true;
            Seed seed = null;
            for (String s : seedNum) {
                seeds.add(Long.parseLong(s));
                if (first) {
                    seed = new Seed();
                    seed.startNumber = Long.parseLong(s);
                    first = false;
                } else {
                    seed.length = Long.parseLong(s);
                    seedPairs.add(seed);
                    first = true;
                }
            }
            reader.readLine();
            reader.readLine();

            line = reader.readLine();
            addToMap(line, reader, seedToSoilMap);

            reader.readLine();
            line = reader.readLine();
            addToMap(line, reader, soilToFertilizerMap);

            reader.readLine();
            line = reader.readLine();

            addToMap(line, reader, fertilizerToWaterMap);

            reader.readLine();
            line = reader.readLine();

            addToMap(line, reader, waterToLightMap);

            reader.readLine();
            line = reader.readLine();

            addToMap(line, reader, lightToTemperatureMap);

            reader.readLine();
            line = reader.readLine();

            addToMap(line, reader, temperatureToHumidity);

            reader.readLine();
            line = reader.readLine();

            addToMap(line, reader, humidityToLocation);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addToMap(String line, BufferedReader reader, List<Card> thisMap) {
        while (line !=null && !line.isEmpty()) {
            String[] split = line.split(" ");
            long dest = Long.parseLong(split[0]);
            long source = Long.parseLong(split[1]);
            long num = Long.parseLong(split[2]);
            Card card = new Card();
            card.sourceStart = source;
            card.destinationStart = dest;
            card.length = num;
            thisMap.add(card);

            try {
                line = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = com.aoc.aoc01.Main.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) return null;
        String file1 = url.getFile();
        return new File(file1);
    }
}
