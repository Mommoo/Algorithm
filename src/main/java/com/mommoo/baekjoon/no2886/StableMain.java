package com.mommoo.baekjoon.no2886;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class StableMain {
    private static final char PERSON = 'X';
    private static final char SEAT = 'L';

    private static final Set<RowAndCol> SEATS = new HashSet<>();
    private static final Set<RowAndCol> PERSONS = new HashSet<>();

    private static int R;
    private static int C;

    public static void main(String[] args) throws Exception {
        initData();
        TreeMap<Integer, List<PersonAndSeat>> personAndSeatsGroupByDistance = createPersonAndSeatGroupByDistance();
        Map<RowAndCol, Integer> occupiedSeats = new HashMap<>();
        Set<RowAndCol> sitPersons = new HashSet<>();
        int competitionCount = 0;

        for (Integer distance: personAndSeatsGroupByDistance.keySet()) {
            List<PersonAndSeat> personAndSeats = personAndSeatsGroupByDistance.get(distance);
            computeSeatCompetitions(personAndSeats, occupiedSeats, sitPersons);

            for (int wantCount: occupiedSeats.values()) {
                boolean existCompetition = wantCount >= 2;
                competitionCount += existCompetition ? 1 : 0;
            }

            SEATS.removeAll(occupiedSeats.keySet());
            PERSONS.removeAll(sitPersons);
            occupiedSeats.clear();
            sitPersons.clear();
        }

        System.out.println(competitionCount);
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

                if (node == SEAT) {
                    SEATS.add(new RowAndCol(row, col));
                }

                if (node == PERSON) {
                    PERSONS.add(new RowAndCol(row, col));
                }
            }
        }

        reader.close();
    }

    private static TreeMap<Integer, List<PersonAndSeat>> createPersonAndSeatGroupByDistance() {
        TreeMap<Integer, List<PersonAndSeat>> personAndSeatGroupByDistance = new TreeMap<>();
        for (RowAndCol person: PERSONS) {
            for (RowAndCol seat: SEATS) {
                int distance = person.computeDistance(seat);
                PersonAndSeat personAndSeat = new PersonAndSeat(person, seat);
                personAndSeatGroupByDistance.computeIfAbsent(distance, d -> new ArrayList<>())
                                            .add(personAndSeat);
            }
        }
        return personAndSeatGroupByDistance;
    }

    private static void computeSeatCompetitions(List<PersonAndSeat> personAndSeats, Map<RowAndCol, Integer> occupiedSeats, Set<RowAndCol> sitPersons) {
        for (PersonAndSeat personAndSeat: personAndSeats) {
            if (doesNotHasChance(personAndSeat)) {
                continue;
            }

            RowAndCol seat = personAndSeat.seat;
            RowAndCol person = personAndSeat.person;

            occupiedSeats.put(seat, occupiedSeats.getOrDefault(seat, 0) + 1);
            sitPersons.add(person);
        }
    }

    private static boolean doesNotHasChance(PersonAndSeat personAndSeat) {
        return !PERSONS.contains(personAndSeat.person) || !SEATS.contains(personAndSeat.seat);
    }

    private static class PersonAndSeat {
        private final RowAndCol person;
        private final RowAndCol seat;

        public PersonAndSeat(RowAndCol person, RowAndCol seat) {
            this.person = person;
            this.seat = seat;
        }
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int computeDistance(RowAndCol rowAndCol) {
            return (int)(Math.pow(row - rowAndCol.row, 2) + Math.pow(col - rowAndCol.col, 2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof RowAndCol))
                return false;
            RowAndCol rowAndCol = (RowAndCol) o;
            return row == rowAndCol.row && col == rowAndCol.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
