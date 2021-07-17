package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ5567 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int knownCount = Integer.parseInt(reader.readLine());
        int friendListSize = Integer.parseInt(reader.readLine());
        FriendRelation friendRelation = new FriendRelation();
        while (friendListSize-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String first = tokenizer.nextToken();
            String second = tokenizer.nextToken();

            friendRelation.addRelations(first, second);
        }
        List<String> oneLevelFriends = friendRelation.getFriends("1");
        Set<String> inviters = new HashSet<>(oneLevelFriends);
        oneLevelFriends.stream()
                       .map(friendRelation::getFriends)
                       .forEach(inviters::addAll);
        System.out.println(inviters.size() - 1);
    }

    private static class FriendRelation {
        private final Map<String, List<String>> friends = new HashMap<>();

        public void addRelations(String friend1, String friend2) {
            addFriend(friend1, friend2);
            addFriend(friend2, friend1);
        }

        private void addFriend(String first, String second) {
            friends.computeIfAbsent(first, (key) -> new LinkedList<>());
            friends.get(first).add(second);
        }

        public List<String> getFriends(String friend) {
            return Collections.unmodifiableList(friends.getOrDefault(friend, Collections.emptyList()));
        }
    }
}
