package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BOJ2931 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = reader.readLine().split(" ");
        int rowSize = Integer.parseInt(sizes[0]);
        int colSize = Integer.parseInt(sizes[1]);

        Blocks blocks = new Blocks(rowSize, colSize);

        for (int row = 0; row < rowSize; row++) {
            blocks.addNodes(row, reader.readLine());
        }

        blocks.printErasedBlockType();
        reader.close();
    }

    private enum Block {
        NONE('.', false, false, false, false),
        SOURCE('M', false, false, false, false),
        DESTINATION('Z', false, false, false, false),
        UP_DOWN('|', true, true, false, false),
        LEFT_RIGHT('-', false, false, true, true),
        UP_DOWN_LEFT_RIGHT('+', true, true, true, true),
        RIGHT_TO_DOWN('1', false, true, false, true),
        UP_TO_RIGHT('2', true, false, false, true),
        UP_TO_LEFT('3', true, false, true, false),
        LEFT_TO_DOWN('4', false, true, true, false);

        private static final Map<Character, Block> finder = Stream.of(values())
                                                                  .collect(Collectors.toMap(type -> type.charName, Function.identity()));
        public final boolean canPassUp;
        public final boolean canPassDown;
        public final boolean canPassLeft;
        public final boolean canPassRight;
        private final char charName;

        private Block(char charName, boolean canPassUp, boolean canPassDown, boolean canPassLeft, boolean canPassRight) {
            this.charName = charName;
            this.canPassUp = canPassUp;
            this.canPassDown = canPassDown;
            this.canPassLeft = canPassLeft;
            this.canPassRight = canPassRight;
        }

        private static Block valueOf(char charName) {
            return finder.getOrDefault(charName, NONE);
        }

        private static Block valueOf(boolean canPassUp, boolean canPassDown, boolean canPassLeft, boolean canPassRight) {
            return Stream.of(values())
                         .filter(block -> block.canPassUp == canPassUp &&
                                          block.canPassDown == canPassDown &&
                                          block.canPassLeft == canPassLeft &&
                                          block.canPassRight == canPassRight)
                         .findFirst()
                         .orElse(null);
        }

        private boolean isPipe() {
            return this != NONE && this != SOURCE && this != DESTINATION;
        }

        private boolean isSourceOrDestination() {
            return this == Block.SOURCE || this == Block.DESTINATION;
        }
    }

    private static class RowAndCol {
        private static final RowAndCol INVALID = new RowAndCol(-1, -1);

        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public RowAndCol moveToUp() {
            return new RowAndCol(row - 1, col);
        }

        public RowAndCol moveToLeft() {
            return new RowAndCol(row, col - 1);
        }

        public RowAndCol moveToDown() {
            return new RowAndCol(row + 1, col);
        }

        public RowAndCol moveToRight() {
            return new RowAndCol(row, col + 1);
        }
    }

    private static class Blocks {
        private static final int UP = 0;
        private static final int DOWN = 1;
        private static final int LEFT = 2;
        private static final int RIGHT = 3;

        private final Block[][] nodes;
        private final boolean[][] visit;
        private RowAndCol beginRowAndCol;

        public Blocks(int rowSize, int colSize) {
            nodes = new Block[rowSize][colSize];
            visit = new boolean[rowSize][colSize];
        }

        private void addNodes(int level, String nodes) {
            IntStream.range(0, nodes.length())
                     .mapToObj(col -> new RowAndCol(level, col))
                     .forEach(rowAndCol -> addNode(rowAndCol, nodes.charAt(rowAndCol.col)));
        }

        private void addNode(RowAndCol rowAndCol, char c) {
            Block block = Block.valueOf(c);
            this.nodes[rowAndCol.row][rowAndCol.col] = block;
            if (block.isPipe()) {
                beginRowAndCol = rowAndCol;
            }
        }

        private RowAndCol[] findNextPipeRowAndColNearBy(RowAndCol current) {
            RowAndCol[] pipes = new RowAndCol[4];

            Block currentBlock = getBlock(current);

            if (currentBlock.canPassUp && isValid(current.moveToUp())) {
                pipes[UP] = current.moveToUp();
            }

            if (currentBlock.canPassDown && isValid(current.moveToDown())) {
                pipes[DOWN] = current.moveToDown();
            }

            if (currentBlock.canPassLeft && isValid(current.moveToLeft())) {
                pipes[LEFT] = current.moveToLeft();
            }

            if (currentBlock.canPassRight && isValid(current.moveToRight())) {
                pipes[RIGHT] = current.moveToRight();
            }

            return pipes;
        }

        private boolean canVisit(RowAndCol rowAndCol) {
            return isValid(rowAndCol) && !visit[rowAndCol.row][rowAndCol.col];
        }

        private void visit(RowAndCol rowAndCol) {
            visit[rowAndCol.row][rowAndCol.col] = true;
        }

        private boolean isValid(RowAndCol rowAndCol) {
            int row = rowAndCol.row;
            int col = rowAndCol.col;
            return 0 <= row && row < nodes.length && 0 <= col && col < nodes[0].length;
        }

        private Block getBlock(RowAndCol rowAndCol) {
            int row = rowAndCol.row;
            int col = rowAndCol.col;
            return nodes[row][col];
        }

        private void offerAndVisit(Queue<RowAndCol> queue, RowAndCol rowAndCol) {
            visit(rowAndCol);
            queue.offer(rowAndCol);
        }

        private RowAndCol findSourceOrDestination() {
            for (int row = 0 ; row < nodes.length ; row++){
                for (int col = 0 ; col < nodes[0].length; col++) {
                    if (nodes[row][col].isSourceOrDestination()) {
                        return new RowAndCol(row, col);
                    }
                }
            }

            return RowAndCol.INVALID;
        }

        private boolean isSourceOrDestNode(RowAndCol rowAndCol) {
            return getBlock(rowAndCol).isSourceOrDestination();
        }

        private RowAndCol findNodeBetweenSourceAndDest() {
            RowAndCol sourceOrDestination = findSourceOrDestination();
            RowAndCol up = sourceOrDestination.moveToUp();
            RowAndCol down = sourceOrDestination.moveToDown();
            RowAndCol left = sourceOrDestination.moveToLeft();
            RowAndCol right = sourceOrDestination.moveToRight();

            if (isValid(up.moveToUp()) && isSourceOrDestNode(up.moveToUp())) {
                return up;
            }

            if (isValid(down.moveToDown()) && isSourceOrDestNode(down.moveToDown())) {
                return down;
            }

            if (isValid(left.moveToLeft()) && isSourceOrDestNode(left.moveToLeft())) {
                return left;
            }

            if (isValid(right.moveToRight()) && isSourceOrDestNode(right.moveToRight())) {
                return right;
            }

            return RowAndCol.INVALID;
        }

        private RowAndCol findAbsentNode() {
            boolean isAbsentPipe = beginRowAndCol == null;
            if (isAbsentPipe) {
                return findNodeBetweenSourceAndDest();
            }

            Queue<RowAndCol> queue = new LinkedList<>();
            offerAndVisit(queue, beginRowAndCol);

            while (!queue.isEmpty()) {
                RowAndCol rowAndCol = queue.poll();

                RowAndCol[] nextPipes = findNextPipeRowAndColNearBy(rowAndCol);
                List<RowAndCol> canVisitPipes = Stream.of(nextPipes)
                                                      .filter(Objects::nonNull)
                                                      .filter(this::canVisit)
                                                      .collect(Collectors.toList());

                for (RowAndCol next : canVisitPipes) {
                    if (getBlock(next) == Block.NONE) {
                        return next;
                    }

                    offerAndVisit(queue, next);
                }
            }

            return RowAndCol.INVALID;
        }

        private void printErasedBlockType() {
            RowAndCol erasedNode = findAbsentNode();
            RowAndCol up = erasedNode.moveToUp();
            RowAndCol down = erasedNode.moveToDown();
            RowAndCol left = erasedNode.moveToLeft();
            RowAndCol right = erasedNode.moveToRight();

            boolean canPassUp = isValid(up) && getBlock(up).canPassDown;
            boolean canPassDown = isValid(down) && getBlock(down).canPassUp;
            boolean canPassRight = isValid(right) && getBlock(right).canPassLeft;
            boolean canPassLeft = isValid(left) && getBlock(left).canPassRight;

            Block block = Block.valueOf(canPassUp, canPassDown, canPassLeft, canPassRight);
            System.out.println((erasedNode.row + 1) + " " + (erasedNode.col + 1) + " " + block.charName);
        }
    }
}
