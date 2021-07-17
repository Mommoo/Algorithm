package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ16234 {
    private static int N, L, R;
    private static int[][] lands;
    private static boolean[][] visits;
    private static final int[] RAW_DIRECTIONS = {-1, 1, 0, 0};
    private static final int[] COL_DIRECTIONS = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = parseInt(tokenizer);
        L = parseInt(tokenizer);
        R = parseInt(tokenizer);

        lands = createLands(reader);
        visits = new boolean[N * N][N * N];

        int count = 0;
        while (movePeople()) {
            count++;
        }

        System.out.println(count);
    }

    private static int parseInt(StringTokenizer tokenizer) {
        return Integer.parseInt(tokenizer.nextToken());
    }

    private static int[][] createLands(BufferedReader reader) throws IOException {
        int[][] lands = new int[N][N];

        for (int raw = 0; raw < N; raw++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                lands[raw][col] = parseInt(tokenizer);
            }
        }

        return lands;
    }

    private static boolean movePeople() {
        initVisits();
        boolean isNeedToUnionAtOnce = false;
        PositionUnionFinder positionUnionFinder = new PositionUnionFinder();

        Stack<Integer> positionStack = new Stack<>();
        positionStack.push(0);

        while (!positionStack.isEmpty()) {
            int position = positionStack.pop();
            int raw = position / N;
            int col = position % N;

            for (int i = 0; i < 4; i++) {
                int nextRaw = raw + RAW_DIRECTIONS[i];
                int nextCol = col + COL_DIRECTIONS[i];

                if (canVisit(position, nextRaw, nextCol)) {
                    int nextPosition = nextRaw * N + nextCol;

                    visits[position][nextPosition] = true;
                    positionStack.push(nextPosition);

                    int subPeopleCount = Math.abs(lands[raw][col] - lands[nextRaw][nextCol]);
                    boolean isCanShared = L <= subPeopleCount && subPeopleCount <= R;

                    if (isCanShared) {
                        isNeedToUnionAtOnce = true;
                        positionUnionFinder.union(position, nextPosition);
                    }
                }
            }
        }

        movePeople(positionUnionFinder);

        return isNeedToUnionAtOnce;
    }

    private static void initVisits() {
        for (boolean[] visitLine: visits) {
            Arrays.fill(visitLine, false);
        }
    }

    private static boolean canVisit(int fromPosition, int raw, int col) {
        boolean isValidRawAndCol = 0 <= raw && raw < N && 0 <= col && col < N;
        int toPosition = raw * N + col;
        return isValidRawAndCol && !visits[fromPosition][toPosition];
    }

    private static void movePeople(PositionUnionFinder positionUnionFinder) {
        Map<Integer, List<Integer>> positionLandSum = new HashMap<>();
        for (int raw = 0 ; raw < N ; raw++) {
            for (int col = 0; col < N; col++) {
                int position = N * raw + col;
                int rootPosition = positionUnionFinder.find(position);
                positionLandSum.computeIfAbsent(rootPosition, key -> new LinkedList<>());
                positionLandSum.get(rootPosition).add(lands[raw][col]);
            }
        }

        Map<Integer, Integer> positionLandCount = new HashMap<>();
        for (Integer rootPosition: positionLandSum.keySet()) {
            int peopleCountAverage = (int)positionLandSum.get(rootPosition)
                                                         .stream()
                                                         .mapToInt(Integer::intValue)
                                                         .average()
                                                         .orElseThrow(RuntimeException::new);

            positionLandCount.put(rootPosition, peopleCountAverage);
        }

        for (int raw = 0 ; raw < N ; raw++) {
            for (int col = 0; col < N; col++) {
                int position = N * raw + col;
                int rootPosition = positionUnionFinder.find(position);
                lands[raw][col] = positionLandCount.get(rootPosition);
            }
        }
    }

    private static class PositionUnionFinder {
        private final Map<Integer, Integer> groups = new HashMap<>();

        public int find(int position) {
            if (!groups.containsKey(position)) {
                groups.put(position, position);
            }

            int nextPosition = groups.get(position);
            if (nextPosition == position) {
                return position;
            }

            int rootPosition = find(nextPosition);
            groups.put(position, rootPosition);
            return find(rootPosition);
        }

        public void union(int position1, int position2) {
            int rootPosition1 = find(position1);
            int rootPosition2 = find(position2);

            if (rootPosition1 > rootPosition2) {
                groups.put(position1, rootPosition2);
                groups.put(rootPosition1, rootPosition2);
            } else {
                groups.put(position2, rootPosition1);
                groups.put(rootPosition2, rootPosition1);
            }
        }
    }
}
