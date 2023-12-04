package com.aoc.aoc04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Card> game = getFromFile("04.txt");
        solvePart1(game);
        solvePart2(game);
    }


    private static void solvePart1(List<Card> game) {
        int sum = 0;
        for (Card card : game) {
            int points = 0;
            for (int i=0; i<card.myNumbers.size(); i++) {
                if (card.winningNumbers.contains(card.myNumbers.get(i))) {
                    if (points==0) {
                        points = 1;
                    } else {
                        points = points * 2;
                    }
                }
            }
            sum += points;
        }
        System.out.println("Part 1 Sum = " + sum);
    }

    private static void solvePart2(List<Card> game) {
        int cardNum = 0;

        for (Card card : game) {
            cardNum++;
            int points = 0;

            for (int i = 0; i < card.myNumbers.size(); i++) {
                if (card.winningNumbers.contains(card.myNumbers.get(i))) {
                    points++;
                }
            }

            for (int cp=0; cp<card.copies; cp++) {
                for (int c = 0; c < points; c++) {
                    int nextCard = cardNum + c;
                    if (nextCard < game.size()) {
                        game.get(nextCard).copies++;
                    }
                }
            }

        }
        int sum = 0;
        for (Card card : game) {
            sum = sum + card.copies;
        }
        System.out.println("Part 2 number of cards = " + sum);
    }

    static class Card {
        List<Integer> winningNumbers = new ArrayList<>();
        List<Integer> myNumbers = new ArrayList<>();
        int copies = 1;
    }
    private static List<Card> getFromFile(String fileName) {
        List<Card> data = new ArrayList<>();
        BufferedReader reader;
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                try {
                    int colon = line.indexOf(":");
                    String numbers = line.substring(colon+1);
                    String[] split1 = numbers.split("\\|");
                    String[] winnerSplit = split1[0].split(" ");
                    String[] mySplit = split1[1].split(" ");
                    Card card = new Card();
                    for (String s : winnerSplit) {
                        if (!s.trim().isEmpty()) {
                            card.winningNumbers.add(Integer.parseInt(s.trim()));
                        }
                    }
                    for (String s : mySplit) {
                        if (!s.trim().isEmpty()) {
                            card.myNumbers.add(Integer.parseInt(s.trim()));
                        }
                    }
                    data.add(card);

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
