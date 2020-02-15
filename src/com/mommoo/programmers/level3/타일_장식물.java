package com.mommoo.programmers.level3;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class 타일_장식물 {
    public static void main(String[] args) {
        System.out.println(new 타일_장식물().solution(5));
        System.out.println(new 타일_장식물().solution(6));
    }

    public long solution(int N) {
        if (N == 1) {
            return 4;
        }
        List<Long> tiles = createTiles(N);
        Collections.sort(tiles, Comparator.reverseOrder());
        Long firstMax = tiles.get(0);
        Long secondMax = tiles.get(1);

        return (firstMax * 2) + ((firstMax + secondMax) * 2);
    }

    private static List<Long> createTiles(int N) {
        List<Long> tiles = new LinkedList<>();
        tiles.add(1L);
        tiles.add(1L);
        N -= 2;

        while (N-- > 0) {
            int size = tiles.size();
            Long previous1 = tiles.get(size - 2);
            Long previous2 = tiles.get(size - 1);
            tiles.add(previous1 + previous2);
        }

        return tiles;
    }
}
