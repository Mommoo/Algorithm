package com.mommoo.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class MinimumSpanningTree {
    public static void main(String[] args) {
        Graph graph = createGraph();
//        new KruskalAlgorithm(graph);
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

        public Set<String> toNodes() {
            Set<String> nodes = new HashSet<>();

            for (GraphEntity graphEntity : entities) {
                nodes.add(graphEntity.sourceNode);
                nodes.add(graphEntity.endNode);
            }

            return nodes;
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

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof GraphEntity))
                return false;
            GraphEntity that = (GraphEntity) o;
            return edgeWeight == that.edgeWeight &&
                   Objects.equals(sourceNode, that.sourceNode) &&
                   Objects.equals(endNode, that.endNode);
        }

        public boolean isContainNode(String node) {
            return sourceNode.equals(node) || endNode.equals(node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceNode, endNode, edgeWeight);
        }
    }

    private static class NodeGroup {
        private Set<String> nodes = new HashSet<>();
        private Set<GraphEntity> graphEntities = new HashSet<>();

        public boolean canAttach(GraphEntity graphEntity) {
            return nodes.contains(graphEntity.sourceNode) || nodes.contains(graphEntity.endNode);
        }

        public boolean isCircularConnect(GraphEntity graphEntity) {
            return nodes.contains(graphEntity.sourceNode) && nodes.contains(graphEntity.endNode);
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
            System.out.println(graphEntities.stream().sorted(Comparator.comparingInt(g -> g.edgeWeight)).collect(Collectors.toList()));
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

    private static class KruskalAlgorithm {
        private final List<NodeGroup> groups = new LinkedList<>();

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

            groups.get(0).printEntities();
        }

        public boolean isCircularConnection(GraphEntity graphEntity) {
            NodeGroup group = findGroup(graphEntity);

            if (group == null) {
                return false;
            }

            return group.isCircularConnect(graphEntity);
        }

        private NodeGroup findGroup(GraphEntity graphEntity) {
            return groups.stream()
                         .filter(group -> group.canAttach(graphEntity))
                         .findFirst()
                         .orElse(null);
        }

        public void updateGroup(GraphEntity graphEntity) {
            List<NodeGroup> findGroups = groups.stream()
                                               .filter(group -> group.canAttach(graphEntity))
                                               .collect(Collectors.toList());

            if (findGroups.isEmpty()) {
                NodeGroup newNodeGroup = new NodeGroup();
                newNodeGroup.attach(graphEntity);
                groups.add(newNodeGroup);
            } else if (findGroups.size() == 1) {
                NodeGroup nodeGroup = findGroups.get(0);
                nodeGroup.attach(graphEntity);
            } else {
                NodeGroup newNodeGroup = new NodeGroup();

                for (NodeGroup nodeGroup : findGroups) {
                    groups.remove(nodeGroup);
                    newNodeGroup.addGroup(nodeGroup);
                }

                groups.add(newNodeGroup);
            }

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
                System.out.println(entities);
                System.out.println(findNodes + " , " + minimumEntity);
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
