package com.aoc.aoc07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int FIVE_OF_A_KIND = 7;
    private static final int FOUR_OF_A_KIND = 6;
    private static final int FULL_HOUSE = 5;
    private static final int THREE_OF_A_KIND = 4;
    private static final int TWO_PAIR = 3;
    private static final int ONE_PAIR = 2;
    private static final int HIGH_CARD = 1;


    public static void main(String[] args) {
        List<Hand> hands = getFromFile("07.txt");
        solvePart1(hands);
        solvePart2(hands);
    }

    static class Hand {
        List<String> cardsInHand = new ArrayList<>();
        long bid = 0;
        int rank = 0;
        long value = 0;
        long valueWithJoker = 0;
        long winning = 0;
        Map<String, Integer> cardCount;
    }


    private static int getValueOfCard(String card) {
        return switch (card) {
            case "A" -> 14;
            case "K" -> 13;
            case "Q" -> 12;
            case "J" -> 11;
            case "T" -> 10;
            case "9" -> 9;
            case "8" -> 8;
            case "7" -> 7;
            case "6" -> 6;
            case "5" -> 5;
            case "4" -> 4;
            case "3" -> 3;
            case "2" -> 2;
            default -> 0;
        };
    }

    private static int getValueOfCardWithJoker(String card) {
        return switch (card) {
            case "A" -> 14;
            case "K" -> 13;
            case "Q" -> 12;
            case "T" -> 10;
            case "9" -> 9;
            case "8" -> 8;
            case "7" -> 7;
            case "6" -> 6;
            case "5" -> 5;
            case "4" -> 4;
            case "3" -> 3;
            case "2" -> 2;
            case "J" -> 1;
            default -> 0;
        };
    }

    private static void solvePart1(List<Hand> hands) {
        hands.sort(new CardComparator());
        int rank = 1;
        long winning = 0;
        for (Hand hand : hands) {
            hand.rank = rank;
            hand.winning = hand.rank * hand.bid;
            winning += hand.winning;
            rank++;
            //System.out.println("Hand: " + hand.cardsInHand.toString() + " Rank: " + hand.rank + ", Value: " + hand.value);
        }
        System.out.println("Part 1: Winning: " + winning);
    }

    private static void solvePart2(List<Hand> hands) {
        hands.sort(new CardComparatorJoker());
        int rank = 1;
        long winning = 0;
        for (Hand hand : hands) {
            hand.rank = rank;
            hand.winning = hand.rank * hand.bid;
            winning += hand.winning;
            rank++;
            System.out.println("Hand: " + hand.cardsInHand.toString() + " Rank: " + hand.rank + ", Value: " + hand.valueWithJoker);
        }
        System.out.println("Part 2: Winning: " + winning);
        // 249579731 low
        // 249632712 low

        // 249648708 njaaaa
        // 249796597 fel
        // 249763554 fel
        // 250087440
        // 250498297 fel
        // 251065455 fel
        // 250827450 high
        // 250179202 high
    }


    static class CardComparator implements Comparator<Hand>
    {
        public int compare(Hand c1, Hand c2)
        {
            if (c1.value > c2.value) return 1;
            if (c2.value > c1.value) return -1;
            for (int i = 0; i<c1.cardsInHand.size(); i++) {
                if (getValueOfCardWithJoker(c1.cardsInHand.get(i)) > getValueOfCardWithJoker(c2.cardsInHand.get(i))) return 1;
                if (getValueOfCardWithJoker(c1.cardsInHand.get(i)) < getValueOfCardWithJoker(c2.cardsInHand.get(i))) return -1;
            }
            return 0;
        }
    }

    static class CardComparatorJoker implements Comparator<Hand>
    {
        public int compare(Hand c1, Hand c2)
        {
            if (c1.valueWithJoker > c2.valueWithJoker) return 1;
            if (c2.valueWithJoker > c1.valueWithJoker) return -1;
            for (int i = 0; i<c1.cardsInHand.size(); i++) {
                if (getValueOfCardWithJoker(c1.cardsInHand.get(i)) > getValueOfCardWithJoker(c2.cardsInHand.get(i)))
                    return 1;
                if (getValueOfCardWithJoker(c1.cardsInHand.get(i)) < getValueOfCardWithJoker(c2.cardsInHand.get(i)))
                    return -1;

            }
            return 0;
        }
    }
    private static boolean isFiveOfAKind(Map<String, Integer> cardCount) {
        return cardCount.size() == 1;
    }

    private static boolean isFiveOfAKindWithJoker(Map<String, Integer> cardCount) {
        if (cardCount.size() == 1) return true;
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 5) return true;
            if (!entry.getKey().equals("J") && entry.getValue() + numOfJokers == 5) return true;
        }

        return false;
    }


    private static boolean isFourOfAKind(Map<String, Integer> cardCount) {
        if (cardCount.size() != 2) return false;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 4) return true;
        }
        return false;
    }

    private static boolean isFourOfAKindWithJoker(Map<String, Integer> cardCount) {
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 4) return true;
            if (!entry.getKey().equals("J") && entry.getValue() == 3 && numOfJokers > 0) return true;
            if (!entry.getKey().equals("J") && entry.getValue() == 2 && numOfJokers > 1) return true;
            if (!entry.getKey().equals("J") && entry.getValue() == 1 && numOfJokers > 2) return true;
        }
        return false;
    }

    private static boolean isThreeOfAKind(Map<String, Integer> cardCount) {
        if (cardCount.size() != 2 && cardCount.size() != 3) return false;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 3) return true;
        }
        return false;
    }

    private static boolean isThreeOfAKindWithJoker(Map<String, Integer> cardCount) {
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 3) return true;
            if (!entry.getKey().equals("J") && entry.getValue() == 2 && numOfJokers > 0) return true;
            if (!entry.getKey().equals("J") && entry.getValue() == 1 && numOfJokers > 1) return true;
        }
        return false;
    }

    private static boolean isFullHouse(Map<String, Integer> cardCount) {
        if (cardCount.size() != 2 ) return false;
        boolean twoFound = false;
        boolean threeFound = false;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 3) threeFound = true;
            if (entry.getValue() == 2) twoFound = true;
        }
        return  (threeFound && twoFound);
    }

    private static boolean isFullHouseWithJoker(Map<String, Integer> cardCount) {
        boolean twoFound = false;
        boolean threeFound = false;
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        String usedCard = "";
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (!entry.getKey().equals("J") && entry.getValue() == 3) {
                threeFound = true;
                usedCard = entry.getKey();
                break;
            }

        }
        if (!threeFound) {
            for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
                if (entry.getKey().equals("J") && numOfJokers > 2) {
                    threeFound = true;
                    numOfJokers = numOfJokers -3;
                    break;
                }

                if (!entry.getKey().equals("J") && !entry.getKey().equals(usedCard) && entry.getValue() == 2 && numOfJokers > 0) {
                    threeFound = true;
                    usedCard = entry.getKey();
                    numOfJokers--;
                    break;
                }
                if (!entry.getKey().equals("J") && !entry.getKey().equals(usedCard) && entry.getValue() == 1 && numOfJokers > 1) {
                    threeFound = true;
                    usedCard = entry.getKey();
                    numOfJokers = numOfJokers - 2;
                    break;
                }
                if (numOfJokers > 2) {
                    threeFound = true;
                    numOfJokers = numOfJokers - 3;
                    break;
                }
            }
        }
        if (!threeFound) return false;

        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (!entry.getKey().equals("J") && !entry.getKey().equals(usedCard) && entry.getValue() == 2) {
                return true;
            }
        }
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getKey().equals("J") && numOfJokers >1) return true;
            if (!entry.getKey().equals("J") && !entry.getKey().equals(usedCard) && entry.getValue() == 1 && numOfJokers > 0) {
                return true;
            }
            if (numOfJokers > 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTwoPair(Map<String, Integer> cardCount) {
        if (cardCount.size() != 3) return false;
        boolean twoFound1 = false;
        boolean twoFound2 = false;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 2) {
                if (!twoFound1) {
                    twoFound1 = true;
                } else {
                    twoFound2 = true;
                }
            }
        }
        return  (twoFound1 && twoFound2);
    }

    private static boolean isTwoPairWithJoker(Map<String, Integer> cardCount) {
        boolean twoFound1 = false;
        boolean twoFound2 = false;
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (!entry.getKey().equals("J") && entry.getValue() == 2) {
                if (!twoFound1) {
                    twoFound1 = true;
                } else {
                    twoFound2 = true;
                }
            }
            if (!entry.getKey().equals("J") && entry.getValue()==1 && numOfJokers>0) {
                if (!twoFound1) {
                    twoFound1 = true;
                } else {
                    twoFound2 = true;
                }
                numOfJokers--;
            }
            if (numOfJokers>1) {
                if (!twoFound1) {
                    twoFound1 = true;
                } else {
                    twoFound2 = true;
                }
                numOfJokers = numOfJokers -2;
            }

        }
        return  (twoFound1 && twoFound2);
    }

    private static boolean isOnePair(Map<String, Integer> cardCount) {
        return (cardCount.size() == 4);
    }

    private static boolean isOnePairWithJoker(Map<String, Integer> cardCount) {
        Integer numOfJokers = cardCount.get("J");
        if (numOfJokers == null) numOfJokers = 0;
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 2) return true;
            if (!entry.getKey().equals("J") && entry.getValue()==1 && numOfJokers>0) return true;
            if (numOfJokers>1) return true;
        }
        return false;
    }

    private static Map<String, Integer> getCardCount(List<String> cards) {
        Map<String, Integer> cardCount = new HashMap<>();
        for (String s : cards) {
            cardCount.merge(s, 1, Integer::sum);
        }
        return cardCount;
    }

    private static int getHandValue(Map<String, Integer> cardCount) {
        if (isFiveOfAKind(cardCount)) return FIVE_OF_A_KIND;
        if (isFourOfAKind(cardCount)) return FOUR_OF_A_KIND;
        if (isFullHouse(cardCount)) return FULL_HOUSE;
        if (isThreeOfAKind(cardCount)) return THREE_OF_A_KIND;
        if (isTwoPair(cardCount)) return TWO_PAIR;
        if (isOnePair(cardCount)) return ONE_PAIR;
        return HIGH_CARD;
    }

    private static int getHandValueWithJoker(Map<String, Integer> cardCount) {
        if (isFiveOfAKindWithJoker(cardCount)) return FIVE_OF_A_KIND;
        if (isFourOfAKindWithJoker(cardCount)) return FOUR_OF_A_KIND;
        if (isFullHouseWithJoker(cardCount)) return FULL_HOUSE;
        if (isThreeOfAKindWithJoker(cardCount)) return THREE_OF_A_KIND;
        if (isTwoPairWithJoker(cardCount)) return TWO_PAIR;
        if (isOnePairWithJoker(cardCount)) return ONE_PAIR;
        return HIGH_CARD;
    }

    private static List<Hand> getFromFile(String fileName) {
        BufferedReader reader;
        List<Hand> hands = new ArrayList<>();
        try {
            File file = getFile(fileName);
            if (file == null) return null;
            reader = new BufferedReader(new FileReader(getFile(fileName)));
            String line = reader.readLine();
            while (line != null) {
                Hand hand = new Hand();
                String[] split = line.split(" ");
                String myCards = split[0];
                long bid = Long.parseLong(split[1]);
                for (int i=0; i<myCards.length();i++) {
                    hand.cardsInHand.add(myCards.substring(i, i+1));
                }
                hand.bid = bid;
                hand.cardCount = getCardCount(hand.cardsInHand);
                hand.value = getHandValue(hand.cardCount);
                hand.valueWithJoker = getHandValueWithJoker(hand.cardCount);
                hands.add(hand);
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hands;
    }


    private static File getFile(String fileName) {
        ClassLoader classLoader = com.aoc.aoc01.Main.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) return null;
        String file1 = url.getFile();
        return new File(file1);
    }


}
