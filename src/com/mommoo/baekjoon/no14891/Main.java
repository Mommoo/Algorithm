package com.mommoo.baekjoon.no14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Gear[] gears = createGears(reader);
        Input[] inputs = createInputs(reader);
        reader.close();

        for (Input input : inputs) {
            compute(gears, input);
        }

        System.out.println(computeTotalScore(gears));
    }

    private static Gear[] createGears(BufferedReader reader) throws Exception {
        return new Gear[]{
            Gear.of(reader.readLine(), 1),
            Gear.of(reader.readLine(), 2),
            Gear.of(reader.readLine(), 4),
            Gear.of(reader.readLine(), 8)
        };
    }

    private static Input[] createInputs(BufferedReader reader) throws Exception {
        int count = Integer.parseInt(reader.readLine());
        Input[] inputs = new Input[count];

        for (int i = 0; i < count; i++) {
            inputs[i] = Input.from(reader.readLine());
        }

        return inputs;
    }

    private static void compute(Gear[] gears, Input input) {
        int direction = input.direction;
        gears[input.gearNumber-1].reservationRotate(direction);
        // right
        for (int i = input.gearNumber; i < 4; i++) {
            Gear leftGear = gears[i - 1];
            Gear rightGear = gears[i];
            if (rightGear.isNeedToRotate(leftGear)) {
                direction *= -1;
                rightGear.reservationRotate(direction);
            } else {
                break;
            }
        }

        //left
        direction = input.direction;
        for (int i = input.gearNumber - 1; i >= 1; i--) {
            Gear leftGear = gears[i - 1];
            Gear rightGear = gears[i];
            if (rightGear.isNeedToRotate(leftGear)) {
                direction *= -1;
                leftGear.reservationRotate(direction);
            } else {
                break;
            }
        }

        for (Gear gear: gears) {
            gear.rotate();
        }
    }

    private static int computeTotalScore(Gear[] gears) {
        return gears[0].computeScore() + gears[1].computeScore() + gears[2].computeScore() + gears[3].computeScore();
    }

    private static class Gear {
        private final int[] values;
        private final int score;
        private int reserveRotate = -99;

        public Gear(int[] values, int score) {
            this.values = values;
            this.score = score;
        }

        public static Gear of(String valueString, int score) {
            int[] values = new int[8];
            for (int i = 0 ; i < 8; i++) {
                values[i] = valueString.charAt(i) - '0';
            }

            return new Gear(values, score);
        }

        public int computeScore() {
            return values[0] == 0 ? 0 : score;
        }

        private void rotate(int direction) {
            if (direction == 1) {
                int lastValue = values[7];
                System.arraycopy(values, 0, values, 1, 7);
                values[0] = lastValue;
            } else {
                int firstValue = values[0];
                System.arraycopy(values, 1, values, 0, 7);
                values[7] = firstValue;
            }
        }

        public void rotate() {
            if (reserveRotate != -99) {
                rotate(reserveRotate);
                reserveRotate = -99;
            }
        }

        public void reservationRotate(int direction) {
            this.reserveRotate = direction;
        }

        public boolean isNeedToRotate(Gear leftGear) {
            return values[6] != leftGear.values[2];
        }
    }

    private static class Input {
        private final int gearNumber;
        private final int direction;

        public Input(int gearNumber, int direction) {
            this.gearNumber = gearNumber;
            this.direction = direction;
        }

        public static Input from(String string) {
            return new Input(string.charAt(0) - '0', Integer.parseInt(string.substring(2)));
        }
    }
}
