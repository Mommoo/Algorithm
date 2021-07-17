package com.mommoo.baekjoon.no13335;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static Bridge BRIDGE;
    private static final Queue<Integer> CAR_WEIGHTS = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        setUp();
        int time = 0;

        while (!CAR_WEIGHTS.isEmpty()) {
            if (CAR_WEIGHTS.peek() != null && BRIDGE.canWeight(CAR_WEIGHTS.peek())) {
                BRIDGE.onCar(CAR_WEIGHTS.poll());
                time++;
                continue;
            }
            time += BRIDGE.moveToReadyNextCar();
        }
        time += BRIDGE.clear();

        System.out.println(time);
    }

    private static void setUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        Integer.parseInt(tokenizer.nextToken());
        BRIDGE = Bridge.of(tokenizer.nextToken(), tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());
        while (tokenizer.hasMoreTokens()) {
            CAR_WEIGHTS.add(Integer.parseInt(tokenizer.nextToken()));
        }
        br.close();
    }

    private static class Bridge {
        private final int width;
        private final Deque<Car> onCars = new LinkedList<>();
        private int remainWeight;

        public static Bridge of(String width, String weight) {
            return new Bridge(Integer.parseInt(width), Integer.parseInt(weight));
        }

        public Bridge(int width, int weight) {
            this.width = width;
            this.remainWeight = weight;
        }

        public void moveOneBlock() {
            moveAllCars(1);
        }

        public boolean canWeight(int weight) {
            if (existLastOn()) {
                return remainWeight + onCars.getFirst().weight >= weight;
            }
            return remainWeight >= weight;
        }

        public int moveToReadyNextCar() {
            if (onCars.peekFirst() == null) {
                return 0;
            }

            int movePosition =  width - onCars.getFirst().position - 1;

            if (movePosition == 0) {
                moveOneBlock();
                return 1;
            }

            moveAllCars(movePosition);
            return movePosition;
        }

        public void onCar(int carWeight) {
            moveOneBlock();

            remainWeight -= carWeight;
            onCars.offer(new Car(carWeight));
        }

        public int clear() {
            if (onCars.peekLast() == null) {
                return 0;
            }

            int movePosition = width - onCars.getLast().position;
            onCars.clear();

            return movePosition;
        }

        private boolean existLastOn() {
            if (onCars.peekFirst() == null) {
                return false;
            }

            return onCars.getFirst().position == width - 1;
        }

        private void moveAllCars(int movePosition) {
            int size = onCars.size();

            while (size-- > 0) {
                Car onCar = onCars.poll();
                onCar.move(movePosition);
                if (onCar.isPosition(width)) {
                    remainWeight += onCar.weight;
                    continue;
                }

                onCars.offer(onCar);
            }
        }
    }

    private static class Car {
        private final int weight;
        private int position = 0;

        public Car(int weight) {
            this.weight = weight;
        }

        public void move(int pos) {
            position += pos;
        }

        public boolean isPosition(int position) {
            return this.position == position;
        }
    }
}
