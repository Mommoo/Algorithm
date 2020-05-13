package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ2823 {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static final char LOAD = '.';
    private static final char VISIT = 'v';

    private static final int NONE = -1;
    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private static final int[] directionTypes = {TOP, RIGHT, BOTTOM, LEFT};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int rawSize = Integer.parseInt(tokenizer.nextToken());
        int colSize = Integer.parseInt(tokenizer.nextToken());

        char[][] map = createMap(rawSize, colSize, reader);

        Node beginNode = findAnyLoadNode(map);

        if (canMoveLoadLessThanTwo(beginNode, map)) {
            System.out.println(1);
            return;
        }

        Stack<Node> nodes = new Stack<>();
        nodes.push(beginNode);

        while (!nodes.isEmpty()) {
            Node curNode = nodes.pop();

            checkVisit(map, curNode);

            boolean notExistAnyCanGo = true;

            for (int i = 0; i < 4; i++) {
                Node nextNode = curNode.add(dy[i], dx[i], directionTypes[i]);
                if (nextNode.isInValid(rawSize, colSize) || nextNode.isBackWard(curNode)) {
                    continue;
                }

                if (canMove(map, nextNode) || isVisit(map, nextNode)) {
                    notExistAnyCanGo = false;
                }

                if (canMove(map, nextNode)) {
                    nodes.add(nextNode);
                }
            }

            if (notExistAnyCanGo) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    private static char[][] createMap(int rawSize, int colSize, BufferedReader reader) throws IOException {
        char[][] map = new char[rawSize][colSize];

        for (int raw = 0; raw < rawSize; raw++) {
            String mapLine = reader.readLine();
            for (int col = 0; col < colSize; col++) {
                char block = mapLine.charAt(col);
                map[raw][col] = block;
            }
        }

        return map;
    }

    private static Node findAnyLoadNode(char[][] map) {
        int rawSize = map.length;
        int colSize = map[0].length;
        for (int raw = 0; raw < rawSize; raw++) {
            for (int col = 0; col < colSize; col++) {
                if (map[raw][col] == LOAD) {
                    return new Node(raw, col);
                }
            }
        }

        return null;
    }

    private static boolean canMoveLoadLessThanTwo(Node node, char[][] map) {
        int maxRaw = map.length;
        int maxCol = map[0].length;
        return IntStream.range(0, 4)
                        .mapToObj(idx -> node.add(dy[idx], dx[idx], directionTypes[idx]))
                        .filter(nextNode -> !nextNode.isInValid(maxRaw, maxCol))
                        .filter(nextNode -> canMove(map, nextNode))
                        .count() < 2;
    }

    private static boolean canMove(char[][] map, Node node) {
        int raw = node.raw;
        int col = node.col;
        return map[raw][col] == LOAD;
    }

    private static boolean isVisit(char[][] map, Node node) {
        int raw = node.raw;
        int col = node.col;
        return map[raw][col] == VISIT;
    }

    private static void checkVisit(char[][] map, Node node) {
        int raw = node.raw;
        int col = node.col;
        map[raw][col] = VISIT;
    }

    private static class Node {
        private final int raw;
        private final int col;
        private final int direction;

        public Node(int raw, int col, int direction) {
            this.raw = raw;
            this.col = col;
            this.direction = direction;
        }

        public Node(int raw, int col) {
            this(raw, col, NONE);
        }

        public boolean isInValid(int maxRaw, int maxCol) {
            return !(0 <= raw && raw < maxRaw && 0 <= col && col < maxCol);
        }

        public Node add(int raw, int col, int nextDirection) {
            return new Node(this.raw + raw, this.col + col, nextDirection);
        }

        public boolean isBackWard(Node from) {
            return (from.direction == TOP && from.raw == this.raw + 1) ||
                   (from.direction == RIGHT && from.col == this.col + 1) ||
                   (from.direction == BOTTOM && from.raw == this.raw - 1) ||
                   (from.direction == LEFT && from.col == this.col - 1);
        }
    }
}
