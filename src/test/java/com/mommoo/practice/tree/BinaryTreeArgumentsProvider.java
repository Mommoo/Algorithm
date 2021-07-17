package com.mommoo.practice.tree;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

public class BinaryTreeArgumentsProvider {
    private static final int[] RANDOM_NODES = {10, 5, 3, 1, 12, 14, 4, 7, 2, 13, 6, 11, 8, 9, 15};
    private static final int[] SORTED_NODES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public static Stream<Arguments> provideGetHeight() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 4),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 5),
                Arguments.of(RANDOM_NODES, 4)
        );
    }

    public static Stream<Arguments> provideGetHeightAsArgument() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 2, 2),
                Arguments.of(SORTED_NODES, 10, 4),
                Arguments.of(SORTED_NODES, 6, 3),
                Arguments.of(RANDOM_NODES, 5, 3),
                Arguments.of(RANDOM_NODES, 1, 2),
                Arguments.of(RANDOM_NODES, 4, 3),
                Arguments.of(RANDOM_NODES, 13, 4)
        );
    }

    public static Stream<Arguments> provideGetNodes() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 2, Arrays.asList(4, 5, 6, 7)),
                Arguments.of(SORTED_NODES, 0, Arrays.asList(1)),
                Arguments.of(SORTED_NODES, 3, Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15)),
                Arguments.of(RANDOM_NODES, 0, Arrays.asList(10)),
                Arguments.of(RANDOM_NODES, 1, Arrays.asList(5, 3)),
                Arguments.of(RANDOM_NODES, 2, Arrays.asList(1, 12, 14, 4)),
                Arguments.of(RANDOM_NODES, 3, Arrays.asList(7, 2, 13, 6, 11, 8, 9, 15))
        );
    }

    public static Stream<Arguments> provideGetLeftNode() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 2, 6),
                Arguments.of(SORTED_NODES,12, null),
                Arguments.of(SORTED_NODES, 6, 14),
                Arguments.of(RANDOM_NODES,1, 1),
                Arguments.of(RANDOM_NODES, 2, 14),
                Arguments.of(RANDOM_NODES,5, 11),
                Arguments.of(RANDOM_NODES,11, null),
                Arguments.of(RANDOM_NODES,14, null)
        );
    }

    public static Stream<Arguments> provideGetRightNode() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 2, 7),
                Arguments.of(SORTED_NODES,9, null),
                Arguments.of(SORTED_NODES, 6, 15),
                Arguments.of(RANDOM_NODES, 1, 12),
                Arguments.of(RANDOM_NODES,2, 4),
                Arguments.of(RANDOM_NODES,5, 8),
                Arguments.of(RANDOM_NODES,11, null),
                Arguments.of(RANDOM_NODES,14, null)
        );
    }

    public static Stream<Arguments> provideGetSibling() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 2, 2),
                Arguments.of(SORTED_NODES, 9, 11),
                Arguments.of(SORTED_NODES, 5, 7),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, 13, null),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, 14, null),
                Arguments.of(RANDOM_NODES, 0, null),
                Arguments.of(RANDOM_NODES, 1, 3),
                Arguments.of(RANDOM_NODES, 5, 4),
                Arguments.of(RANDOM_NODES, 11, 8),
                Arguments.of(RANDOM_NODES, 14, 9)

        );
    }

    public static Stream<Arguments> provideBfs() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 3, Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)),
                Arguments.of(SORTED_NODES, 9, Arrays.asList(10, 11, 12, 13, 14, 15)),
                Arguments.of(SORTED_NODES, 6, Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15)),
                Arguments.of(RANDOM_NODES, 3, Arrays.asList(1, 12, 14, 4, 7, 2, 13, 6, 11, 8, 9, 15)),
                Arguments.of(RANDOM_NODES, 6, Arrays.asList(4, 7, 2, 13, 6, 11, 8, 9, 15))
        );
    }

    public static Stream<Arguments> providePreOrderTraversal() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 1, Arrays.asList(2, 4, 8, 9, 5, 10, 11)),
                Arguments.of(SORTED_NODES, 4, Arrays.asList(5, 10, 11)),
                Arguments.of(SORTED_NODES, 5, Arrays.asList(6, 12, 13)),
                Arguments.of(RANDOM_NODES, 0, Arrays.asList(10, 5, 1, 7, 2, 12, 13, 6, 3, 14, 11, 8, 4, 9, 15)),
                Arguments.of(RANDOM_NODES, 2, Arrays.asList(3, 14, 11, 8, 4, 9, 15))
        );
    }

    public static Stream<Arguments> provideInOrderTraversal() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 7, Arrays.asList(8)),
                Arguments.of(SORTED_NODES, 1, Arrays.asList(8, 4, 9, 2, 10, 5, 11)),
                Arguments.of(SORTED_NODES, 0, Arrays.asList(8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15)),
                Arguments.of(RANDOM_NODES, 0, Arrays.asList(7, 1, 2, 5, 13, 12, 6, 10, 11, 14, 8, 3, 9, 4, 15)),
                Arguments.of(RANDOM_NODES, 2, Arrays.asList(11, 14, 8, 3, 9, 4, 15)),
                Arguments.of(RANDOM_NODES, 14, Arrays.asList(15))
        );
    }

    public static Stream<Arguments> providePostOrderTraversal() {
        return Stream.of(
                Arguments.of(SORTED_NODES, 11, Arrays.asList(12)),
                Arguments.of(SORTED_NODES, 5, Arrays.asList(12, 13, 6)),
                Arguments.of(SORTED_NODES, 0, Arrays.asList(8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1)),
                Arguments.of(RANDOM_NODES, 0, Arrays.asList(7, 2, 1, 13, 6, 12, 5, 11, 8, 14, 9, 15, 4, 3, 10)),
                Arguments.of(RANDOM_NODES, 2, Arrays.asList(11, 8, 14, 9, 15, 4, 3))
        );
    }
}
