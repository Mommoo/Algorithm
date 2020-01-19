package com.mommoo.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class MinimumSpanningTree {
    public static void main(String[] args) {
        Graph graph = createGraph();
        new KruskalAlgorithm(graph);
    }

    // https://t1.daumcdn.net/cfile/tistory/256619495806628508
    private static Graph createGraph() {
        Graph graph = new Graph();
        graph.add("a", "b", 4);
        graph.add("a", "h", 8);
        graph.add("b", "h", 11);
        graph.add("b", "c", 8);
        graph.add("c", "i", 2);
        graph.add("c", "f", 4);
        graph.add("c", "d", 7);
        graph.add("d", "e", 9);
        graph.add("d", "f", 14);
        graph.add("e", "f", 10);
        graph.add("f", "g", 2);
        graph.add("g", "i", 6);
        graph.add("g", "h", 1);
        graph.add("h", "i", 7);
        return graph;
    }

    private static class Graph {
        private final List<GraphEntity> entities = new LinkedList<>();

        public void add(String sourceNode, String endNode, int edgeWeight) {
            entities.add(new GraphEntity(sourceNode, endNode, edgeWeight));
        }

        //        public Map<String, List<GraphEntity>> get() {
        //            Map<String, List<GraphEntity>> graphEntities = new HashMap<>();
        //
        //            for (GraphEntity graphEntity : entities) {
        //                graphEntities
        //                        .computeIfAbsent(graphEntity.sourceNode, key -> new LinkedList<>())
        //                        .add(graphEntity);
        //                graphEntities
        //                        .computeIfAbsent(graphEntity.endNode, key -> new LinkedList<>())
        //                        .add(graphEntity);
        //            }
        //            System.out.println(graphEntities);
        //            return graphEntities;
        //        }

        public List<GraphEntity> toEntities() {
            return new ArrayList<>(entities);
        }
    }

    private static class GraphEntity {
        private final String sourceNode;
        private final String endNode;
        private final int edgeWeight;

        public GraphEntity(String sourceNode, String endNode, int edgeWeight) {
            this.sourceNode = sourceNode;
            this.endNode = endNode;
            this.edgeWeight = edgeWeight;
        }

        @Override
        public String toString() {
            return "GraphEntity{" +
                   "sourceNode='" + sourceNode + '\'' +
                   ", endNode='" + endNode + '\'' +
                   ", edgeWeight=" + edgeWeight +
                   '}';
        }
    }

    private static class Node {
        private final String name;
        private final int edgeWeight;
        private final List<Node> nextNodes = new LinkedList<>();

        public Node(String name, int edgeWeight) {
            this.name = name;
            this.edgeWeight = edgeWeight;
        }

        public void addNode(String name, int edgeWeight) {
            Node nextNode = new Node(name, edgeWeight);
            nextNodes.add(nextNode);
            nextNode.addNode(this.name, this.edgeWeight);
        }
    }

    private static class KruskalAlgorithm {
        private final List<Set<String>> groups = new LinkedList<>();

        public KruskalAlgorithm(Graph graph) {
            // 가중치 값으로 오름차순 정렬
            Queue<GraphEntity> graphEntities = graph.toEntities()
                                                    .stream()
                                                    .sorted(Comparator.comparingInt(e -> e.edgeWeight))
                                                    .collect(Collectors.toCollection(LinkedList::new));
            System.out.println(graphEntities);

            while (!graphEntities.isEmpty()) {
                GraphEntity graphEntity = graphEntities.poll();

                if (isCircularConnection(graphEntity)) {
                    continue;
                }

                updateGroup(graphEntity);

                System.out.println(groups);
            }
        }

        public boolean isCircularConnection(GraphEntity graphEntity) {
            Set<String> group = findGroup(graphEntity);

            if (group == null) {
                return false;
            }

            return group.contains(graphEntity.sourceNode) && group.contains(graphEntity.endNode);
        }

        private Set<String> findGroup(GraphEntity graphEntity) {
            return groups.stream()
                         .filter(group -> group.contains(graphEntity.sourceNode) || group.contains(graphEntity.endNode))
                         .findFirst()
                         .orElse(null);
        }

        public void updateGroup(GraphEntity graphEntity) {
            List<Set<String>> findGroups = groups.stream()
                                                 .filter(group -> group.contains(graphEntity.sourceNode) || group.contains(graphEntity.endNode))
                                                 .collect(Collectors.toList());

            if (findGroups.isEmpty()) {
                Set<String> newGroup = new HashSet<>();
                newGroup.add(graphEntity.sourceNode);
                newGroup.add(graphEntity.endNode);
                groups.add(newGroup);
            } else if (findGroups.size() == 1) {
                Set<String> group = findGroups.get(0);
                group.add(graphEntity.sourceNode);
                group.add(graphEntity.endNode);
            } else {
                Set<String> newGroup = new HashSet<>();

                for (Set<String> findGroup : findGroups) {
                    groups.remove(findGroup);
                    newGroup.addAll(findGroup);
                }

                newGroup.add(graphEntity.sourceNode);
                newGroup.add(graphEntity.endNode);
                groups.add(newGroup);
            }

        }
    }
}
