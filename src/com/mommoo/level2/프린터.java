package com.mommoo.level2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 프린터 {
    public static void main(String[] args) {
        System.out.println(new 프린터().solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(new 프린터().solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public int solution(int[] priorities, int location) {
        LinkedList<Document> documents = createDocuments(priorities);
        int count = 1;
        while (!documents.isEmpty()) {
            int highPriorityIndex = findIndexOfHighPriority(documents);
            locateDocumentAtFirst(highPriorityIndex, documents);
            Document document = documents.pollFirst();
            if (document.isEqualIndex(location)) {
                break;
            }

            count++;
        }
        return count;
    }

    private static LinkedList<Document> createDocuments(int[] priorities) {
        return IntStream.range(0, priorities.length)
                        .mapToObj(idx -> new Document(priorities[idx], idx))
                        .collect(Collectors.toCollection(LinkedList::new));
    }

    private static int findIndexOfHighPriority(LinkedList<Document> documents) {
        Iterator<Document> documentIterator = documents.iterator();
        int highPriorityIndex = -1;
        int maxPriority = -1;
        int index = 0;
        while (documentIterator.hasNext()) {
            Document document = documentIterator.next();
            if (document.isHighPriorityThan(maxPriority)) {
                maxPriority = document.priority;
                highPriorityIndex = index;
            }
            index++;
        }
        return highPriorityIndex;
    }

    private static void locateDocumentAtFirst(int index, LinkedList<Document> documents) {
        while (index-- > 0) {
            Document document = documents.pollFirst();
            documents.addLast(document);
        }
    }

    private static class Document {
        private final int priority;
        private final int index;

        public Document(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }

        public boolean isHighPriorityThan(int priority) {
            return this.priority > priority;
        }

        public boolean isEqualIndex(int index){
            return this.index == index;
        }
    }
}
