package com.mommoo.baekjoon.no13459;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char WALL = '#';
    private static final char ROAD = '.';
    private static final char HOLE = 'O';
    private static final char RED = 'R';
    private static final char BLUE = 'B';

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static final int[] RED_POSITION = new int[2];
    private static final int[] BLUE_POSITION = new int[2];

    private static int MAX_ROW, MAX_COL;
    private static char[][] NODES;

    public static void main(String[] args) throws IOException {
//        new FileInputStream("src/com/mommoo/baekjoon/data/marble-escape/case"+i)
        for (int i = 1; i <= 12; i++) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/com/mommoo/baekjoon/data/marble-escape/case"+i)));

            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            MAX_ROW = Integer.parseInt(tokenizer.nextToken());
            MAX_COL = Integer.parseInt(tokenizer.nextToken());
            NODES = createNodes(br);
            br.close();

            findBallPositions();
            removeBallAtNodes();

            //                System.out.println("begin: " + RED_POSITION[0] + ", " + RED_POSITION[1] +" :: " + BLUE_POSITION[0] + " , " + BLUE_POSITION[1]);
            System.out.println(dfs(0, RED_POSITION[0], RED_POSITION[1], BLUE_POSITION[0], BLUE_POSITION[1]));
        }
    }

    private static char[][] createNodes(BufferedReader reader) throws IOException {
        char[][] map = new char[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {
            String mapLine = reader.readLine();
            for (int col = 0; col <MAX_COL; col++) {
                map[row][col] = mapLine.charAt(col);
            }
        }

        return map;
    }

    private static void findBallPositions() {
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                char node = NODES[row][col];
                if (node == RED) {
                    RED_POSITION[0] = row;
                    RED_POSITION[1] = col;
                } else if (node == BLUE) {
                    BLUE_POSITION[0] = row;
                    BLUE_POSITION[1] = col;
                }
            }
        }
    }

    private static void removeBallAtNodes() {
        NODES[RED_POSITION[0]][RED_POSITION[1]] = ROAD;
        NODES[BLUE_POSITION[0]][BLUE_POSITION[1]] = ROAD;
    }

    private static int dfs(int count, int redY, int redX, int blueY, int blueX) {
        if (count > 10) {
            return 0;
        }

        if (NODES[blueY][blueX] == HOLE) {
            return 0;
        } else if (NODES[redY][redX] == HOLE) {
            return 1;
        }

        for (int i = 0; i < 4; i++) {
            int nextRedX = movePositionX(redX, redY, blueX, blueY, dx[i]);
            int nextRedY = movePositionY(redX, redY, blueX, blueY, dy[i]);
            int nextBlueX = movePositionX(blueX, blueY, redX, redY, dx[i]);
            int nextBlueY = movePositionY(blueX, blueY, redX, redY, dy[i]);
//            System.out.println(nextRedY+","+nextRedX+" :: "+ nextBlueY+","+nextBlueX);
            if (nextRedX == -1 || nextRedY == -1) {
                nextRedX = nextBlueX - dx[i];
                nextRedY = nextBlueY - dy[i];
//                System.out.println("보정:: " + nextRedY + "," +nextRedX);
            }

            if (nextBlueX == -1 || nextBlueY == -1) {
                nextBlueX = nextRedX - dx[i];
                nextBlueY = nextRedY - dy[i];
//                System.out.println("보정:: " + nextBlueY + "," +nextBlueX);

                if (NODES[nextRedY][nextRedX] == HOLE) {
                    continue;
                }
            }

            int result = dfs(count+1, nextRedY, nextRedX, nextBlueY, nextBlueX);
            if (result==1) {
                return 1;
            }
        }

        return 0;
    }

    private static int movePositionX(int ballX, int ballY, int anotherBallX, int anotherBallY, int dx) {
        if (dx == 0) {
            return ballX;
        }

        int desX = ballX;
        while (true) {
            if (NODES[ballY][desX + dx] == ROAD) {
                desX += dx;

                if (desX == anotherBallX && ballY == anotherBallY) {
                    return -1;
                }

                continue;
            }

            if (NODES[ballY][desX + dx] == HOLE) {
                return desX + dx;
            }

            break;
        }

        return desX;
    }

    private static int movePositionY(int ballX, int ballY, int anotherBallX, int anotherBallY, int dy) {
        if (dy == 0) {
            return ballY;
        }

        int desY = ballY;

        while (true) {
            if (NODES[desY + dy][ballX] == ROAD) {
                desY += dy;

                if (desY == anotherBallY && ballX == anotherBallX) {
                    return -1;
                }

                continue;
            }

            if (NODES[desY + dy][ballX] == HOLE) {
                return desY + dy;
            }

            break;
        }

        return desY;
    }
}
