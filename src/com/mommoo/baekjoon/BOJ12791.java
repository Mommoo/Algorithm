package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ12791 {
    public static void main(String[] args) throws Exception {
        TreeMap<Integer, List<String>> albumFinder = createAlbumFinder();
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());

        while (TC -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int beginYear = Integer.parseInt(tokenizer.nextToken());
            int endYear = Integer.parseInt(tokenizer.nextToken());

            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (Integer year: albumFinder.keySet()) {
                if (year < beginYear) {
                    continue;
                }

                if (endYear < year) {
                    break;
                }

                List<String> albums = albumFinder.get(year);
                count += albums.size();
                albums.forEach(album -> sb.append(year).append(" ").append(album).append("\n"));
            }
            builder.append(count).append("\n");
            builder.append(sb);
        }

        System.out.println(builder);
    }

    private static TreeMap<Integer, List<String>> createAlbumFinder() {
        TreeMap<Integer, List<String>> albums = new TreeMap<>();
        StringTokenizer tokenizer = new StringTokenizer("1967 DavidBowie\n"
                                                        + "1969 SpaceOddity\n"
                                                        + "1970 TheManWhoSoldTheWorld\n"
                                                        + "1971 HunkyDory\n"
                                                        + "1972 TheRiseAndFallOfZiggyStardustAndTheSpidersFromMars\n"
                                                        + "1973 AladdinSane\n"
                                                        + "1973 PinUps\n"
                                                        + "1974 DiamondDogs\n"
                                                        + "1975 YoungAmericans\n"
                                                        + "1976 StationToStation\n"
                                                        + "1977 Low\n"
                                                        + "1977 Heroes\n"
                                                        + "1979 Lodger\n"
                                                        + "1980 ScaryMonstersAndSuperCreeps\n"
                                                        + "1983 LetsDance\n"
                                                        + "1984 Tonight\n"
                                                        + "1987 NeverLetMeDown\n"
                                                        + "1993 BlackTieWhiteNoise\n"
                                                        + "1995 1.Outside\n"
                                                        + "1997 Earthling\n"
                                                        + "1999 Hours\n"
                                                        + "2002 Heathen\n"
                                                        + "2003 Reality\n"
                                                        + "2013 TheNextDay\n"
                                                        + "2016 BlackStar", "\n");

        while (tokenizer.hasMoreTokens()) {
            String yearAndAlbum = tokenizer.nextToken();
            Integer year = Integer.parseInt(yearAndAlbum.substring(0, 4));
            albums.computeIfAbsent(year, _year -> new LinkedList<>());
            albums.get(year).add(yearAndAlbum.substring(5));
        }

        return albums;
    }
}
