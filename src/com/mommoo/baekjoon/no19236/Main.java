package com.mommoo.baekjoon.no19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_ROW = 4;
    private static final int MAX_COL = 4;


    public static void main(String[] args) throws Exception {
        FishNodes firstFishNodes = FishNodes.create();
        Shark firstNodeShark = eatFish(Shark.outOfNodes(), firstFishNodes, firstFishNodes.getFish(0, 0));

        PriorityQueue<Integer> sharkScores = new PriorityQueue<>(Comparator.reverseOrder());

        Queue<QueueNode> nodes = new LinkedList<>();
        nodes.offer(new QueueNode(firstNodeShark, firstFishNodes));
        while (!nodes.isEmpty()) {
            QueueNode node = nodes.poll();
            Shark shark = node.shark;
            FishNodes fishNodes = node.fishNodes;

            fishNodes.moveFish(shark);

            for (int i = 1; i < MAX_ROW ; i++) {
                int nextRow = shark.row + shark.direction.dy * i;
                int nextCol = shark.col + shark.direction.dx * i;
                if (isInValid(nextRow, nextCol) || fishNodes.isEmpty(nextRow, nextCol)) {
                    sharkScores.add(shark.eatScore);
                    continue;
                }
                FishNodes newFishNodes = fishNodes.copy();
                Shark nextShark = eatFish(shark, newFishNodes, newFishNodes.getFish(nextRow, nextCol));
                nodes.offer(new QueueNode(nextShark, newFishNodes));
            }
        }

        System.out.println(sharkScores.poll());

    }

    private static boolean isInValid(int row, int col) {
        return !(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL);
    }

    private static Shark eatFish(Shark shark, FishNodes fishNodes, Fish fish) {
        fishNodes.removeFish(fish);
        return shark.eat(fish);
    }

    private static class FishNodes {
        private final Fish[][] nodes;

        public FishNodes(Fish[][] nodes) {
            this.nodes = nodes;
        }

        public static FishNodes create() throws Exception {
            Fish[][] nodes = new Fish[MAX_ROW][MAX_COL];
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (int row = 0 ; row < MAX_ROW ; row ++) {
                StringTokenizer nodeLine = new StringTokenizer(br.readLine());
                for (int col = 0; col < MAX_COL; col++) {
                    int fishValue = Integer.parseInt(nodeLine.nextToken());
                    int directionValue = Integer.parseInt(nodeLine.nextToken());
                    nodes[row][col] = new Fish(fishValue, row, col, Direction.valueOf(directionValue));
                }
            }

            br.close();
            return new FishNodes(nodes);
        }

        public FishNodes copy() {
            Fish[][] newFishNodes = new Fish[MAX_ROW][];
            for (int row = 0 ; row < MAX_ROW ; row ++) {
                newFishNodes[row] = Arrays.copyOf(nodes[row], MAX_COL);
            }

            return new FishNodes(newFishNodes);
        }

        public Fish getFish(int row, int col) {
            return nodes[row][col];
        }

        public Fish findFishOrNull(int value) {
            for (int row = 0 ; row < MAX_ROW; row ++) {
                for (int col = 0; col < MAX_COL; col++) {
                    if (nodes[row][col] == null) {
                        continue;
                    }

                    if (nodes[row][col].value == value) {
                        return nodes[row][col];
                    }
                }
            }

            return null;
        }

        public void removeFish(Fish fish) {
            nodes[fish.row][fish.col] = new Fish(0, fish.row, fish.col, Direction.LEFT);
        }

        public void moveFish(Shark shark) {
            for (int number = 1; number < 17; number++) {
                Fish fish = findFishOrNull(number);
                if (fish == null) {
                    continue;
                }

                moveNextFishNode(fish, shark);
            }
        }

        private void moveNextFishNode(Fish beginFishNode, Shark shark) {
            Direction originDirection = beginFishNode.direction;
            Direction nextDirection = originDirection;
            int nextRow = beginFishNode.nextRow();
            int nextCol = beginFishNode.nextCol();
            while (shark.isIn(nextRow, nextCol) || isInValid(nextRow, nextCol)) {
                beginFishNode = beginFishNode.rotate();
                nextRow = beginFishNode.nextRow();
                nextCol = beginFishNode.nextCol();
                nextDirection = beginFishNode.direction;

                if (originDirection == nextDirection) {
                    return;
                }
            }

            Fish tmp = nodes[nextRow][nextCol];
            nodes[nextRow][nextCol] = beginFishNode.moveTo(tmp);
            nodes[beginFishNode.row][beginFishNode.col] = tmp.moveTo(beginFishNode);
        }

        public boolean isEmpty(int nextRow, int nextCol) {
            return nodes[nextRow][nextCol].value == 0;
        }
    }

    private static class QueueNode {
        private final Shark shark;
        private final FishNodes fishNodes;

        public QueueNode(Shark shark, FishNodes fishNodes) {
            this.shark = shark;
            this.fishNodes = fishNodes;
        }
    }

    private static class DirectionPosition {
        protected final int row;
        protected final int col;
        protected final Direction direction;

        public DirectionPosition(int row, int col, Direction direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int nextRow() {
            return this.row + direction.dy;
        }

        public int nextCol() {
            return this.col + direction.dx;
        }
    }

    private static class Fish extends DirectionPosition {
        private final int value;

        public Fish(int value, int row, int col, Direction direction) {
            super(row, col, direction);
            this.value = value;
        }

        public Fish rotate() {
            return new Fish(value, row, col, this.direction.rotate());
        }

        public Fish moveTo(Fish atFish) {
            return new Fish(value, atFish.row, atFish.col, direction);
        }
    }

    private static class Shark extends DirectionPosition {
        private final int eatScore;

        public Shark(int eatScore, int row, int col, Direction direction) {
            super(row, col, direction);
            this.eatScore = eatScore;
        }

        public static Shark outOfNodes() {
            return new Shark(0, -1, -1, null);
        }

        public Shark eat(Fish fish) {
            return new Shark(eatScore + fish.value, fish.row, fish.col, fish.direction);
        }

        public boolean isIn(int row, int col) {
            return this.row == row && this.col == col;
        }
    }

    private enum Direction {
        UP(1, 0, -1), LEFT_UP(2, -1, -1), LEFT(3, -1 ,0), LEFT_DOWN(4, -1, 1),
        DOWN(5, 0, 1), RIGHT_DOWN(6, 1, 1), RIGHT(7, 1, 0), RIGHT_UP(8, 1, -1);

        private final int value;
        private final int dx;
        private final int dy;

        Direction(int value, int dx, int dy) {
            this.value = value;
            this.dx = dx;
            this.dy = dy;
        }

        private static Direction valueOf(int value) {
            return values()[value - 1];
        }

        public Direction rotate() {
            int newValue = (this.value % 8) + 1;
            return valueOf(newValue);
        }
    }
}
