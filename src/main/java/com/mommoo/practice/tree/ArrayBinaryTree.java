package com.mommoo.practice.tree;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayBinaryTree implements Tree {
    private final int[] elements;

    public ArrayBinaryTree(int... elements) {
        this.elements = elements;
    }

    @Override
    public Integer getHeight() {
        return getHeight(elements.length - 1);
    }

    @Override
    public Integer getHeight(int index) {
        if (index < 0 || elements.length <= index) {
            return null;
        }

        int len = index + 1;

        int n = 0;
        while (len >= Math.pow(2, n)) {
            n++;
        }

        return n;
    }

    @Override
    public List<Integer> getNodes(int level) {
        if (level < 0) {
            return Collections.emptyList();
        }

        int nodeCount = (int) Math.pow(2, level);
        int beginIndex = nodeCount - 1;
        int nodeLen = Math.min(nodeCount, elements.length - beginIndex);
        return Collections.unmodifiableList(Arrays.stream(elements)
                                                  .skip(beginIndex)
                                                  .limit(nodeLen)
                                                  .boxed()
                                                  .collect(Collectors.toList()));
    }

    @Override
    public Integer getLeftNode(int index) {
        int leftNodeIndex = index * 2 + 1;
        if (leftNodeIndex >= elements.length) {
            return null;
        }

        return elements[leftNodeIndex];
    }

    @Override
    public Integer getRightNode(int index) {
        int rightNodeIndex = index * 2 + 2;
        if (rightNodeIndex >= elements.length) {
            return null;
        }

        return elements[rightNodeIndex];
    }

    @Override
    public Integer getSibling(int index) {
        if (index < 0) {
            return null;
        }

        if (index >= elements.length) {
            return null;
        }

        boolean isLeftNode = index % 2 != 0;
        int siblingNodeIndex = isLeftNode ? index + 1 : index - 1;

        if (0 <= siblingNodeIndex && siblingNodeIndex < elements.length) {
            return elements[siblingNodeIndex];
        }

        return null;
    }

    @Override
    public List<Integer> bfs(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.stream(elements)
                                                  .skip(beginIndex)
                                                  .boxed()
                                                  .collect(Collectors.toList()));
    }

    @Override
    public List<Integer> preOrderTraversalByRecursive(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }

        List<Integer> nodes = new ArrayList<>();
        preOrderTraversalByRecursive(nodes, beginIndex);
        return nodes;
    }

    private void preOrderTraversalByRecursive(List<Integer> visits, int index) {
        if (index >= elements.length) {
            return;
        }

        visits.add(elements[index]);
        preOrderTraversalByRecursive(visits, 2 * index + 1);
        preOrderTraversalByRecursive(visits, 2 * index + 2);
    }

    @Override
    public List<Integer> inOrderTraversalByRecursive(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }

        List<Integer> nodes = new ArrayList<>();
        inOrderTraversalByRecursive(nodes, beginIndex);
        return nodes;
    }

    private void inOrderTraversalByRecursive(List<Integer> visits, int index) {
        if (index >= elements.length) {
            return;
        }

        inOrderTraversalByRecursive(visits, 2 * index + 1);
        visits.add(elements[index]);
        inOrderTraversalByRecursive(visits, 2 * index + 2);
    }

    @Override
    public List<Integer> postOrderTraversalByRecursive(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }

        List<Integer> nodes = new ArrayList<>();
        postOrderTraversalByRecursive(nodes, beginIndex);
        return nodes;
    }

    private void postOrderTraversalByRecursive(List<Integer> visits, int index) {
        if (index >= elements.length) {
            return;
        }

        postOrderTraversalByRecursive(visits, 2 * index + 1);
        postOrderTraversalByRecursive(visits, 2 * index + 2);
        visits.add(elements[index]);
    }

    @Override
    public List<Integer> preOrderTraversalByIteration(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }
        List<Integer> nodes = new ArrayList<>();
        Deque<Integer> nextIndexStack = new LinkedList<>();
        nextIndexStack.push(beginIndex);

        while (!nextIndexStack.isEmpty()) {
            int index = nextIndexStack.pop();
            nodes.add(elements[index]);
            int leftNodeIndex = 2 * index + 1;
            int rightNodeIndex = 2 * index + 2;

            if (rightNodeIndex < elements.length) {
                nextIndexStack.push(rightNodeIndex);
            }

            if (leftNodeIndex < elements.length) {
                nextIndexStack.push(leftNodeIndex);
            }
        }

        return nodes;
    }

    @Override
    public List<Integer> inOrderTraversalByIteration(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }

        List<Integer> nodes = new ArrayList<>();
        Deque<Integer> nextIndexStack = new LinkedList<>();
        nextIndexStack.push(beginIndex);

        while (!nextIndexStack.isEmpty()) {
            int currentIndex = nextIndexStack.peek();
            int leftIndex = currentIndex * 2 + 1;
            while (leftIndex < elements.length) {
                nextIndexStack.push(leftIndex);
                leftIndex = leftIndex * 2 + 1;
            }

            int lastLeftIndex = nextIndexStack.pop();
            nodes.add(elements[lastLeftIndex]);

            if (nextIndexStack.isEmpty()) {
                break;
            }

            int parentIndex = nextIndexStack.pop();
            nodes.add(elements[parentIndex]);

            int rightIndex = parentIndex * 2 + 2;
            if (rightIndex < elements.length) {
               nextIndexStack.push(rightIndex);
            }
        }
        return nodes;
    }

    @Override
    public List<Integer> postOrderTraversalByIteration(int beginIndex) {
        if (beginIndex < 0) {
            return Collections.emptyList();
        }

        Deque<Integer> nextIndexStack = new LinkedList<>();
        nextIndexStack.push(beginIndex);

        Deque<Integer> output = new LinkedList<>();

        while (!nextIndexStack.isEmpty()) {
            int currentIndex = nextIndexStack.pop();
            output.push(currentIndex);

            int leftIndex = currentIndex * 2 + 1;
            int rightIndex = currentIndex * 2 + 2;

            if (leftIndex < elements.length) {
                nextIndexStack.push(leftIndex);
            }

            if (rightIndex < elements.length) {
                nextIndexStack.push(rightIndex);
            }
        }

        List<Integer> nodes = new ArrayList<>();
        while (!output.isEmpty()) {
            nodes.add(elements[output.pop()]);
        }
        return nodes;
    }
}
