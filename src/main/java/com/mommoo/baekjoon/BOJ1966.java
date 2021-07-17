package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(reader.readLine());

        while (N-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int documentCount = Integer.parseInt(tokenizer.nextToken());
            int targetPosition = Integer.parseInt(tokenizer.nextToken());

            PrintDocumentQueue queue = PrintDocumentQueue.from(reader.readLine());
            PrintDocument pollDocument = queue.poll();
            while (pollDocument.doesNotEqualPosition(targetPosition)) {
                pollDocument = queue.poll();
            }

            writer.write(String.valueOf(queue.processCount));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    private static class PrintDocument {
        private final int position;
        private final int priority;

        public PrintDocument(int position, int priority) {
            this.position = position;
            this.priority = priority;
        }

        public boolean doseNotEqualPriority(int priority) {
            return this.priority != priority;
        }

        public boolean doesNotEqualPosition(int position) {
            return this.position != position;
        }
    }

    private static class PrintDocumentQueue {
        private final LinkedList<Integer> sortedPriorities;
        private final LinkedList<PrintDocument> documents;
        private int processCount;

        public PrintDocumentQueue(LinkedList<PrintDocument> documents) {
            this.sortedPriorities = documents.stream()
                                             .map(document -> document.priority)
                                             .sorted()
                                             .collect(Collectors.toCollection(LinkedList::new));
            this.documents = documents;
        }

        private static PrintDocumentQueue from(String printPriority) {
            String[] priorities = printPriority.split(" ");
            return new PrintDocumentQueue(IntStream.range(0, priorities.length)
                                                   .mapToObj(idx -> new PrintDocument(idx, Integer.parseInt(priorities[idx])))
                                                   .collect(Collectors.toCollection(LinkedList::new)));
        }

        public PrintDocument poll() {
            int maxPriority = sortedPriorities.removeLast();
            while (documents.getFirst().doseNotEqualPriority(maxPriority)) {
                documents.addLast(documents.removeFirst());
            }

            processCount++;
            return documents.removeFirst();
        }
    }
}
