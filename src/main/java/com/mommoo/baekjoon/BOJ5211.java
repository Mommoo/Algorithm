package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ5211 {
    private static Set<Character> cMajorBasicNotes = new HashSet<>(Arrays.asList('C', 'F', 'G'));
    private static Set<Character> aMinorBasicNotes = new HashSet<>(Arrays.asList('A', 'D', 'E'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] noteGroups = br.readLine().split("\\|");

        int cFirstCount = 0;
        int aFirstCount = 0;
        for (String noteGroup: noteGroups) {
            if (cMajorBasicNotes.contains(noteGroup.charAt(0))) {
                cFirstCount++;
            } else if (aMinorBasicNotes.contains(noteGroup.charAt(0))) {
                aFirstCount++;
            }
        }

        String lastNoteGroup = noteGroups[noteGroups.length - 1];
        boolean isLastNoteMajor = cMajorBasicNotes.contains(lastNoteGroup.charAt(lastNoteGroup.length() - 1));

        if (cFirstCount != aFirstCount) {
            System.out.printf("%s%n", cFirstCount > aFirstCount ? "C-major" : "A-minor");
        } else {
            System.out.printf("%s%n", isLastNoteMajor ? "C-major" : "A-minor");
        }
    }
}
