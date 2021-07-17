package com.mommoo.practice.minimum_spanning_tree;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<String, String> mapper = new HashMap<>();

    private final Map<String, String> elements = new HashMap<>();

    public String find(String element) {
        if (!elements.containsKey(element)) {
            elements.put(element, element);
        }

        String rootElement = elements.get(element);

        if (rootElement.equals(element)) {
            return rootElement;
        }

        return find(rootElement);
    }

    public void union(String element1, String element2) {
        String rootElement1 = find(element1);
        String rootElement2 = find(element2);

        if (rootElement1.compareTo(rootElement2) < 0) {
            elements.put(rootElement2, rootElement1);
            elements.put(element2, rootElement1);
        } else {
            elements.put(rootElement1, rootElement2);
            elements.put(element1, rootElement2);
        }
    }

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet();
//        disjointSet.union("1", "2");
        disjointSet.union("2", "3");
        disjointSet.union("3", "4");
        disjointSet.union("5", "6");
        disjointSet.union("1", "2");
        disjointSet.union("6", "7");
        disjointSet.union("7", "8");
        disjointSet.union("9", "10");
        System.out.println(disjointSet.elements);
        System.out.println(disjointSet.find("2") + " , " + disjointSet.find("3"));
    }
}
