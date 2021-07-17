package com.mommoo.practice.minimum_spanning_tree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Graph graph = createGraph();
        new KruskalAlgorithm(graph);
        new PrimsAlgorithm(graph);
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

    // https://www.zerocho.com/category/Algorithm/post/584bcd42580277001862f1a7
    private static Graph createGraph2() {
        Graph graph = new Graph();
        graph.add("a", "b", 6);
        graph.add("a", "c", 3);
        graph.add("b", "c", 2);
        graph.add("b", "d", 5);
        graph.add("c", "d", 3);
        graph.add("d", "e", 2);
        graph.add("d", "f", 3);
        graph.add("f", "e", 5);
        return graph;
    }

    private static class Graph {
        private final List<GraphEntity> entities = new LinkedList<>();

        public void add(String sourceNode, String endNode, int edgeWeight) {
            entities.add(new GraphEntity(sourceNode, endNode, edgeWeight));
        }

        public List<GraphEntity> toEntities() {
            return new ArrayList<>(entities);
        }

        public Set<String> toNodes() {
            Set<String> nodes = new HashSet<>();

            for (GraphEntity graphEntity : entities) {
                nodes.add(graphEntity.sourceNode);
                nodes.add(graphEntity.endNode);
            }

            return nodes;
        }
    }

    private static class GraphEntity implements Comparable<GraphEntity> {
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

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof GraphEntity))
                return false;
            GraphEntity that = (GraphEntity) o;
            return edgeWeight == that.edgeWeight &&
                   ((Objects.equals(sourceNode, that.sourceNode) && Objects.equals(endNode, that.endNode)) ||
                   (Objects.equals(sourceNode, that.endNode) && Objects.equals(endNode, that.sourceNode)));
        }

        public boolean isContainNode(String node) {
            return sourceNode.equals(node) || endNode.equals(node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceNode, endNode, edgeWeight);
        }

        @Override
        public int compareTo(GraphEntity o) {
            if (edgeWeight == o.edgeWeight) {
                return sourceNode.compareTo(o.sourceNode);
            }
            return edgeWeight - o.edgeWeight;
        }
    }

    private static class NodeGroup {
        private Set<String> nodes = new HashSet<>();
        private Set<GraphEntity> graphEntities = new HashSet<>();

        public boolean canAttach(GraphEntity graphEntity) {
            return nodes.contains(graphEntity.sourceNode) || nodes.contains(graphEntity.endNode);
        }

        public boolean isContainNode(String nodeName) {
            return nodes.contains(nodeName);
        }

        public void attach(GraphEntity graphEntity) {
            nodes.add(graphEntity.sourceNode);
            nodes.add(graphEntity.endNode);
            graphEntities.add(graphEntity);
        }

        public void addGroup(NodeGroup nodeGroup) {
            nodes.addAll(nodeGroup.nodes);
            graphEntities.addAll(nodeGroup.graphEntities);
        }

        public void printEntities() {
            System.out.println(graphEntities.stream().sorted().collect(Collectors.toList()));
        }

        @Override
        public String toString() {
            return "NodeGroup{" +
                   "nodes=" + nodes +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof NodeGroup))
                return false;
            NodeGroup nodeGroup = (NodeGroup) o;
            return Objects.equals(nodes, nodeGroup.nodes) &&
                   Objects.equals(graphEntities, nodeGroup.graphEntities);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodes, graphEntities);
        }

        public int size() {
            return nodes.size();
        }
    }

    private static class DisjointSet {
        private Map<String, String> elements = new HashMap<>();

        public String findRootElement(String element) {
            elements.putIfAbsent(element, element);
            String findElement = elements.get(element);

            if (Objects.equals(findElement, element)) {
                return findElement;
            }

            // find next root element
            return findRootElement(findElement);
        }

        public void union(String element1, String element2) {
            String rootElement1 = findRootElement(element1);
            String rootElement2 = findRootElement(element2);
            if (rootElement1.compareTo(rootElement2) < 0) {
                elements.put(element2, rootElement1);
                elements.put(rootElement2, rootElement1);
            } else {
                elements.put(element1, rootElement2);
                elements.put(rootElement1, rootElement2);
            }

            System.out.println(elements);
        }

        public boolean isSameSet(String element1, String element2) {
            System.out.println(element1 + " , " + element2);
            System.out.println(findRootElement(element1) + " , " + findRootElement(element2));
            return Objects.equals(findRootElement(element1), findRootElement(element2));
        }
    }

    private static class KruskalAlgorithm {
        private final DisjointSet disjointSet = new DisjointSet();
        private final NodeGroup nodeGroup = new NodeGroup();

        public KruskalAlgorithm(Graph graph) {
            // 가중치 값으로 오름차순 정렬
            PriorityQueue<GraphEntity> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(g -> g.edgeWeight));
            priorityQueue.addAll(graph.toEntities());

            while (!priorityQueue.isEmpty()) {
                GraphEntity graphEntity = priorityQueue.poll();

                if (disjointSet.isSameSet(graphEntity.sourceNode, graphEntity.endNode)) {
                    continue;
                }

                disjointSet.union(graphEntity.sourceNode, graphEntity.endNode);
                nodeGroup.attach(graphEntity);
            }

            nodeGroup.printEntities();
        }
    }

    private static class PrimsAlgorithm {
        private Set<String> findNodes = new HashSet<>();
        private NodeGroup findNodeGroups = new NodeGroup();

        public PrimsAlgorithm(Graph graph) {
            int size = graph.toNodes().size();
            List<GraphEntity> entities = graph.toEntities();
            String node = graph.toNodes().iterator().next();
            findNodes.add(node);
            while (findNodeGroups.size() != size) {
                GraphEntity minimumEntity = findMinimumEdgeEntity(entities);
                entities.remove(minimumEntity);
                addFoundNode(minimumEntity);
                findNodeGroups.attach(minimumEntity);
            }
            findNodeGroups.printEntities();
        }

        private void addFoundNode(GraphEntity graphEntity) {
            this.findNodes.add(graphEntity.sourceNode);
            this.findNodes.add(graphEntity.endNode);
        }

        private GraphEntity findMinimumEdgeEntity(List<GraphEntity> entities) {
            return entities.stream()
                           .filter(this::isContainNodes)
                           .min(Comparator.comparingInt(g -> g.edgeWeight))
                           .orElse(null);
        }

        private boolean isContainNodes(GraphEntity entity) {
            if (findNodes.contains(entity.sourceNode) && findNodes.contains(entity.endNode)) {
                return false;
            }

            return findNodes.stream().anyMatch(entity::isContainNode);
        }
    }
}
