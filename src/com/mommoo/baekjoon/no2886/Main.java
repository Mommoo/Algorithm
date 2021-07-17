package com.mommoo.baekjoon.no2886;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final char PERSON = 'X';
    private static final char SEAT = 'L';

    private static int R;
    private static int C;
    private static Set<Integer> SEAT_POSITIONS = new HashSet<>();
    private static Set<Integer> PERSON_POSITIONS = new HashSet<>();

    public static void main(String[] args) throws Exception {
        initData();
        Map<Integer, Seats> seatsFinder = findAllSeatsPerPerson();
        System.out.println(countFightSeat(seatsFinder));
    }

    private static void initData() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        for (int row = 0; row < R; row++) {
            String nodeLine = reader.readLine();
            for (int col = 0; col < C; col++) {
                char node = nodeLine.charAt(col);

                int position = computePosition(row, col);
                if (node == SEAT) {
                    SEAT_POSITIONS.add(position);
                }

                if (node == PERSON) {
                    PERSON_POSITIONS.add(position);
                }
            }
        }

        reader.close();
    }

    private static int computePosition(int row, int col) {
        return row * C + col;
    }

    private static Map<Integer, Seats> findAllSeatsPerPerson() {
        Map<Integer, Seats> seatsFinder = new HashMap<>();

        for (int personPosition: PERSON_POSITIONS) {
            Seats findSeats = findAllSeats(personPosition);
            seatsFinder.put(personPosition, findSeats);
        }

        return seatsFinder;
    }

    private static Seats findAllSeats(int personPosition) {
        Seats seats = new Seats();
        for (int seatPosition: SEAT_POSITIONS) {
            seats.addSeat(seatPosition, computeDistance(personPosition, seatPosition));
        }
        return seats;
    }

    private static int computeDistance(int beginPosition, int endPosition) {
        int beginRow = beginPosition / C;
        int beginCol = beginPosition % C;
        int endRow = endPosition / C;
        int endCol = endPosition % C;
        return (int) (Math.pow(beginRow - endRow, 2) + Math.pow(beginCol - endCol, 2));
    }

    private static int countFightSeat(Map<Integer, Seats> seatsFinder) {
        int count = 0;

        while (!PERSON_POSITIONS.isEmpty() && !SEAT_POSITIONS.isEmpty()) {
            List<Integer> personsHaveMinDistance = null;
            int curSeatPosition = -1;
            int minDistance = -1;
            for (int seatPosition: SEAT_POSITIONS) {
                List<Integer> foundPersonsOccupiedSeat = findPersonsOccupiedSeat(seatPosition, seatsFinder);

                int distance = foundPersonsOccupiedSeat.remove(foundPersonsOccupiedSeat.size() - 1);

                if (foundPersonsOccupiedSeat.isEmpty()) {
                    continue;
                }

                if (minDistance == -1 || distance < minDistance) {
                    minDistance = distance;
                    curSeatPosition = seatPosition;
                    personsHaveMinDistance = foundPersonsOccupiedSeat;
                }
            }

            if (personsHaveMinDistance.size() > 1) {
                count++;
            }

            if (curSeatPosition != -1) {
                SEAT_POSITIONS.remove(curSeatPosition);
                for (int personPosition: PERSON_POSITIONS) {
                    seatsFinder.get(personPosition).removeSeat(curSeatPosition);
                }

                for (Integer person: personsHaveMinDistance) {
                    PERSON_POSITIONS.remove(person);
                }
            }
        }

        return count;
    }

    private static List<Integer> findPersonsOccupiedSeat(int seatPosition, Map<Integer, Seats> seatsFinder) {
        int minDistance = -1;
        List<Integer> persons = new ArrayList<>();

        for (int personPosition: PERSON_POSITIONS) {
            Seats seats = seatsFinder.get(personPosition);
            int minDistanceSeat = seats.findMinDistanceSeat();

            if (minDistanceSeat != seatPosition) {
                continue;
            }

            int distance = seats.getDistance(seatPosition);
            if (minDistance == -1 || distance < minDistance) {
                minDistance = distance;
                persons.clear();
                persons.add(personPosition);
            } else if (distance == minDistance) {
                persons.add(personPosition);
            }
        }

        persons.add(minDistance);
        return persons;
    }

    private static class Seats {
        private int cacheMinDistanceSeat = -1;
        private final Map<Integer, Integer> values = new HashMap<>();

        public void addSeat(int seatPosition, int distance) {
            values.put(seatPosition, distance);
            cacheMinDistanceSeat = -1;
        }

        public void removeSeat(int seatPosition) {
            cacheMinDistanceSeat = -1;
            values.remove(seatPosition);
        }

        public int findMinDistanceSeat() {
            if (cacheMinDistanceSeat == -1) {
                this.cacheMinDistanceSeat = computeMinDistanceSeat();
            }

            return cacheMinDistanceSeat;
        }

        private int computeMinDistanceSeat() {
            int minSeat = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int seat: values.keySet()) {
                int distance = values.get(seat);
                if (distance < minDistance) {
                    minDistance = distance;
                    minSeat = seat;
                }
            }

            return minSeat;
        }

        public int getDistance(int seatPosition) {
            return values.get(seatPosition);
        }
    }
}
