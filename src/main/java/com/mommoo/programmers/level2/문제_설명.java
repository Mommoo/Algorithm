package com.mommoo.programmers.level2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class 문제_설명 {
    public static void main(String[] args) {
        String[][] clothData1 = new String[][]{
                new String[]{"yellow_hat", "headgear"},
                new String[]{"blue_sunglasses", "eyewear"},
                new String[]{"green_turban", "headgear"}
        };
        System.out.println(new 문제_설명().solution(clothData1));

        String[][] clothData2 = new String[][]{
                new String[]{"crow_mask", "face"},
                new String[]{"blue_sunglasses", "face"},
                new String[]{"smoky_makeup", "face"}
        };
        System.out.println(new 문제_설명().solution(clothData2));
    }

    public int solution(String[][] clothesData) {
        Clothes clothes = new Clothes();
        for (String[] cloth : clothesData) {
            String name = cloth[0];
            String type = cloth[1];
            clothes.addCloth(type, name);
        }

        return clothes.computeNumberCase();
    }

    private static class Clothes {
        private Map<String, List<String>> clothFinder = new HashMap<>();

        public void addCloth(String type, String clothName) {
            clothFinder.computeIfAbsent(type, key -> new LinkedList<>())
                       .add(clothName);
        }

        public int computeNumberCase() {
            int numberCase = 1;

            for (List<String> clothes : clothFinder.values()) {
                numberCase *= (clothes.size() + 1);
            }

            int allClothNothingNumberCase = 1;
            return numberCase - allClothNothingNumberCase;
        }
    }
}
