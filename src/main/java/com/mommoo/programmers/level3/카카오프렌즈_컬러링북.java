package com.mommoo.programmers.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 카카오프렌즈_컬러링북 {
    public static void main(String[] args) {
        int[][] picture = new int[][]{
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                new int[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                new int[]{0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0},
                new int[]{0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},
                new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                new int[]{0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0},
                new int[]{0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
                new int[]{0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0},
                new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        };
        System.out.println(Arrays.toString(new 카카오프렌즈_컬러링북().solution(picture.length, picture[0].length, picture)));

        picture = new int[][]{
                new int[]{0, 1, 0, 1},
                new int[]{1, 0, 1, 0},
                new int[]{0, 1, 0, 1},
                new int[]{1, 0, 1, 0},
        };

        System.out.println(Arrays.toString(new 카카오프렌즈_컬러링북().solution(picture.length, picture[0].length, picture)));
    }

    private static int computeAreaSize(int row, int col, int maxRow, int maxCol, Map<Integer, Integer> positionFinder, int areaNumber) {
        if (row >= maxRow || col >= maxCol || row < 0 || col < 0) {
            return 0;
        }

        int position = getPosition(row, col, maxCol);

        if (!positionFinder.containsKey(position)) {
            return 0;
        }

        int currentAreaNumber = positionFinder.get(position);
        if (currentAreaNumber != areaNumber) {
            return 0;
        }

        positionFinder.remove(position);

        int areaSize = 1;

        areaSize += computeAreaSize(row, col + 1, maxRow, maxCol, positionFinder, areaNumber);
        areaSize += computeAreaSize(row, col - 1, maxRow, maxCol, positionFinder, areaNumber);
        areaSize += computeAreaSize(row + 1, col, maxRow, maxCol, positionFinder, areaNumber);
        areaSize += computeAreaSize(row - 1, col, maxRow, maxCol, positionFinder, areaNumber);

        return areaSize;
    }

    private static int getPosition(int row, int col, int maxCol) {
        return row * maxCol + col;
    }

    public int[] solution(int m, int n, int[][] picture) {
        Map<Integer, Integer> positionFinder = new HashMap<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int areaNumber = picture[row][col];
                if (areaNumber == 0) {
                    continue;
                }
                positionFinder.put(getPosition(row, col, n), areaNumber);
            }
        }

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        Iterator<Integer> positionIterator = positionFinder.keySet().iterator();
        while (positionIterator.hasNext()) {
            numberOfArea++;
            int positionKey = positionIterator.next();
            int row = positionKey / n;
            int col = positionKey % n;
            int areaNumber = positionFinder.get(positionKey);
            int areaSize = computeAreaSize(row, col, m, n, positionFinder, areaNumber);
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, areaSize);
            positionIterator = positionFinder.keySet().iterator();
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
