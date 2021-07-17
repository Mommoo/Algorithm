package com.mommoo.programmers.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 영어_끝말잇기 {
    public static void main(String[] args) {
        String[] words1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println(Arrays.toString(new 영어_끝말잇기().solution(3, words1)));
        System.out.println(Arrays.toString(new 영어_끝말잇기().solution(5, words2)));
        System.out.println(Arrays.toString(new 영어_끝말잇기().solution(2, words3)));

    }

    public int[] solution(int n, String[] words) {
        WordDuplicateDetector wordDuplicateDetector = new WordDuplicateDetector();
        Persons persons = Persons.from(n, words);

        String previousWord = persons.speakWord();
        wordDuplicateDetector.addWord(previousWord);

        while (persons.moreSpeak()) {
            String currentWord = persons.speakWord();

            if (wordDuplicateDetector.isDuplicated(currentWord) || isInValidRelayWord(previousWord, currentWord)) {
                Person person = persons.getLastPerson();
                return new int[]{person.position, person.wordOrder};
            }

            wordDuplicateDetector.addWord(currentWord);
            previousWord = currentWord;
        }
        return new int[] {0, 0};
    }

    private static boolean isInValidRelayWord(String previous, String current) {
        int previousLastCharIndex = previous.length() - 1;
        return current.charAt(0) != previous.charAt(previousLastCharIndex);
    }

    private static class Persons {
        private final LinkedList<Person> people;
        private final int wordCount;
        private int gameCount;

        private Persons(LinkedList<Person> people, int wordCount) {
            this.people = people;
            this.wordCount = wordCount;
        }

        public static Persons from(int personCount, String[] words) {
            LinkedList<Person> people = IntStream.range(0, personCount)
                                                 .mapToObj(idx -> new Person(idx + 1))
                                                 .collect(Collectors.toCollection(LinkedList::new));

            addWords(people, words);
            initOrder(people);

            return new Persons(people, words.length);
        }

        private static void addWords(LinkedList<Person> people, String[] words) {
            Arrays.stream(words).forEach(word -> {
                Person person = people.poll();
                person.addWord(word);
                people.offer(person);
            });
        }

        private static void initOrder(LinkedList<Person> people) {
            while (people.getFirst().position != 1) {
                Person p = people.poll();
                people.offer(p);
            }
        }

        public String speakWord() {
            Person person = people.poll();
            String word = person.speakWord();
            people.offer(person);

            gameCount++;

            return word;
        }

        public boolean moreSpeak() {
            return gameCount < wordCount;
        }

        public Person getLastPerson() {
            return people.getLast();
        }
    }

    private static class Person {
        private final Queue<String> words = new LinkedList<>();
        private final int position;
        private int wordOrder = 0;

        public Person(int position) {
            this.position = position;
        }

        private void addWord(String word) {
            words.add(word);
        }

        private String speakWord() {
            wordOrder++;
            return words.poll();
        }
    }

    private static class WordDuplicateDetector {
        private final Set<String> words = new HashSet<>();

        public boolean isDuplicated(String word) {
            return words.contains(word);
        }

        public void addWord(String word) {
            this.words.add(word);
        }
    }
}
