package com.aoc.aoc02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Map<Integer, List<Dices>>> game = getFromFile("02.txt");
        //solvePart1(game);
        solvePart2(game);
    }

    static private void solvePart2(Map<Integer, Map<Integer, List<Dices>>> game) {
        int gameId = 0;
        long power = 0;
        long totalPower = 0;
        for (Map.Entry<Integer, Map<Integer, List<Dices>>> entry : game.entrySet()) {
            int minGreenInGame = 0;
            int minRedInGame = 0;
            int minBlueInGame = 0;

            Map<Integer, List<Dices>> set = entry.getValue();
            for (Map.Entry<Integer, List<Dices>> setEntries : set.entrySet()) {
                List<Dices> dices = setEntries.getValue();

                for (Dices dices1 : dices) {
                    if (dices1.color == Colors.GREEN) {
                        if (dices1.numberOfColor > minGreenInGame) {
                            minGreenInGame = dices1.numberOfColor;
                        }
                    }
                    if (dices1.color == Colors.BLUE) {
                        if (dices1.numberOfColor > minBlueInGame) {
                            minBlueInGame = dices1.numberOfColor;
                        }
                    }
                    if (dices1.color == Colors.RED) {
                        if (dices1.numberOfColor > minRedInGame) {
                            minRedInGame = dices1.numberOfColor;
                        }
                    }
                }
            }
            System.out.println("Red: " + minRedInGame + ", Green: " + minGreenInGame + ", Blue: " + minBlueInGame);
            power = minBlueInGame * minGreenInGame * minRedInGame;
            System.out.println("Power: " + power);
            totalPower += power;
        }
        System.out.println("Total power: " + totalPower);
    }
    static private void solvePart1(Map<Integer, Map<Integer, List<Dices>>> game) {
        int gameId = 0;
        for (Map.Entry<Integer, Map<Integer, List<Dices>>> entry : game.entrySet()) {
            int numOfGreenInGame = 0;
            int numOfRedInGame = 0;
            int numOfBlueInGame = 0;

            int gameNumber = entry.getKey();
            Map<Integer, List<Dices>> set = entry.getValue();
            boolean gamePossible = true;
            for (Map.Entry<Integer, List<Dices>> setEntries : set.entrySet()) {
                List<Dices> dices = setEntries.getValue();

                for (Dices dices1 : dices) {
                    if (dices1.color == Colors.GREEN) {
                        numOfGreenInGame += dices1.numberOfColor;
                        if (dices1.numberOfColor > 13) {
                            gamePossible = false;
                        }
                    }
                    if (dices1.color == Colors.BLUE) {
                        numOfBlueInGame += dices1.numberOfColor;
                        if (dices1.numberOfColor > 14) {
                            gamePossible = false;
                        }
                    }
                    if (dices1.color == Colors.RED) {
                        numOfRedInGame += dices1.numberOfColor;
                        if (dices1.numberOfColor > 12) {
                            gamePossible = false;
                        }
                    }
                }
            }
            if (gamePossible) {
                System.out.println("Game: " + gameNumber + " (possible)");
                gameId += gameNumber;
            } else {
                System.out.println("Game: " + gameNumber + " (impossible)");
            }
            System.out.println("Red: " + numOfRedInGame + ", Green: " + numOfGreenInGame + ", Blue: " + numOfBlueInGame);
        }
        System.out.println("Game total: " + gameId);
    }

    enum Colors {
        GREEN,
        RED,
        BLUE,

    }
    static class Dices {
        Colors color;
        int numberOfColor;
    }
    private static Map<Integer, Map<Integer, List<Dices>>> getFromFile(String fileName) {
        Map<Integer, Map<Integer, List<Dices>>> data = new HashMap<>();
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                try {
                    int c = line.indexOf(":");
                    int gameNumber = Integer.parseInt(line.substring(5, c));
                    line = line.substring(c+1);
                    String[] splitSet = line.split(";");
                    Map<Integer, List<Dices>> setMap = new HashMap<>();
                    for (int set=0; set<splitSet.length; set++) {
                        String[] splitDice = splitSet[set].split(",");
                        List<Dices> listOfDices = new ArrayList<>();
                        for (int dice=0;dice<splitDice.length;dice++) {
                            String part = splitDice[dice];
                            Dices dices = new Dices();
                            int ixBlue = part.indexOf("blue");
                            if (ixBlue >=0) {
                                int numBlue = Integer.parseInt(part.substring(0, ixBlue).trim());
                                dices.color = Colors.BLUE;
                                dices.numberOfColor = numBlue;
                            }
                            int ixRed = part.indexOf("red");
                            if (ixRed >=0) {
                                int numRed = Integer.parseInt(part.substring(0, ixRed).trim());
                                dices.color = Colors.RED;
                                dices.numberOfColor = numRed;
                            }
                            int ixGreen = part.indexOf("green");
                            if (ixGreen >=0) {
                                int numGreen = Integer.parseInt(part.substring(0, ixGreen).trim());
                                dices.color = Colors.GREEN;
                                dices.numberOfColor = numGreen;
                            }
                            listOfDices.add(dices);
                        }
                        setMap.put(set, listOfDices);
                    }
                    data.put(gameNumber, setMap);
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
        ClassLoader classLoader = com.aoc.aoc01.Main.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) return null;
        String file1 = url.getFile();
        return new File(file1);
    }
}
