package com.mommoo.baekjoon.no15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private enum Direction {LEFT, RIGHT, TOP, BOTTOM}
    private static final char EMPTY = '0';
    private static final char WALL = '6';
    private static final char SHOW = '#';

    private static int MAX_ROW;
    private static int MAX_COL;
    private static char[][] MAP;
    private static List<CCTV> CCTVS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        MAP = createMap(reader);
        CCTVS = createCCTVs();
        System.out.println(computeMinimumArea());
    }

    private static char[][] createMap(BufferedReader reader) throws IOException {
        char[][] map = new char[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                map[row][col] = tokenizer.nextToken().charAt(0);

            }
        }

        return map;
    }

    private static List<CCTV> createCCTVs() {
        List<CCTV> cctvs = new ArrayList<>();

        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                char node = MAP[row][col];
                if ('1' <=  node && node <= '5') {
                    cctvs.add(new CCTV(node - '0', row, col));
                }
            }
        }

        return cctvs;
    }

    private static int computeMinimumArea() {
        return dfs(0, MAP);
    }

    private static int dfs(int index, char[][] nextMap) {
        if (CCTVS.size() == index) {
            return countRemainArea(nextMap);
        }

        int minAreaCount = Integer.MAX_VALUE;
        CCTV cctv = CCTVS.get(index);

        if (cctv.type == 1) {
            for (Direction direction: Direction.values()) {
                char[][] newMap = processCCTV(cctv, nextMap, direction);
                minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            }
        }

        if (cctv.type == 2) {
            char[][] newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.RIGHT);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.TOP, Direction.BOTTOM);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
        }

        if (cctv.type == 3) {
            char[][] newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.TOP);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.TOP, Direction.RIGHT);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.RIGHT, Direction.BOTTOM);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
        }

        if (cctv.type == 4) {
            char[][] newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.RIGHT, Direction.TOP);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.BOTTOM, Direction.TOP);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.RIGHT, Direction.BOTTOM);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
            newMap = processCCTV(cctv, nextMap, Direction.TOP, Direction.RIGHT, Direction.BOTTOM);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
        }

        if (cctv.type == 5) {
            char[][] newMap = processCCTV(cctv, nextMap, Direction.LEFT, Direction.RIGHT, Direction.BOTTOM, Direction.TOP);
            minAreaCount = Math.min(minAreaCount, dfs(index + 1, newMap));
        }

        return minAreaCount;
    }

    private static char[][] createNewMap(char[][] map) {
        char[][] newMap = new char[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                newMap[row][col] = map[row][col];
            }
        }

        return newMap;
    }

    private static int countRemainArea(char[][] map) {
        int remainArea = 0;

        for (char[] mapLine: map) {
            for (char node: mapLine) {
                if (node == EMPTY) {
                    remainArea++;
                }
            }
        }

        return remainArea;
    }

    private static char[][] processCCTV(CCTV cctv, char[][] map, Direction... directions) {
        map = createNewMap(map);
        for (Direction direction: directions) {
            if (direction == Direction.LEFT) {
                for (int col = cctv.col + 1; col < MAX_COL; col++) {
                    if (map[cctv.row][col] == EMPTY) {
                        map[cctv.row][col] = SHOW;
                    } else if (map[cctv.row][col] == WALL) {
                        break;
                    }
                }
            } else if (direction == Direction.BOTTOM) {
                for (int row = cctv.row + 1; row < MAX_ROW; row++) {
                    if (map[row][cctv.col] == EMPTY) {
                        map[row][cctv.col] = SHOW;
                    } else if (map[row][cctv.col] == WALL) {
                        break;
                    }
                }
            } else if (direction == Direction.RIGHT) {
                for (int col = cctv.col - 1; col >= 0; col--) {
                    if (map[cctv.row][col] == EMPTY) {
                        map[cctv.row][col] = SHOW;
                    } else if (map[cctv.row][col] == WALL) {
                        break;
                    }
                }
            } else {
                for (int row = cctv.row - 1; row >= 0; row--) {
                    if (map[row][cctv.col] == EMPTY) {
                        map[row][cctv.col] = SHOW;
                    } else if (map[row][cctv.col] == WALL) {
                        break;
                    }
                }
            }
        }

        return map;
    }

    private static class CCTV {
        private final int type;
        private final int row;
        private final int col;

        public CCTV(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }
    }
}
