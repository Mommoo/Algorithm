package com.mommoo.programmers.dev._2021;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> nodes = new HashMap<>();
        for (String enrollName: enroll) {
            nodes.put(enrollName, new Node());
        }

        for (int i = 0 ; i < enroll.length; ++i) {
            String name = enroll[i];
            String referralName = referral[i];

            if (referralName.equals("-")) {
                continue;
            }

            Node selfNode = nodes.get(name);
            Node referralNode = nodes.get(referralName);

            selfNode.setReferral(referralNode);
        }

        for (int i = 0; i < seller.length; ++i) {
            String sellerName = seller[i];
            int sellerAmount = amount[i] * 100;

            Node sellerNode = nodes.get(sellerName);
            sellerNode.addAmount(sellerAmount);
        }

        int[] answer = new int[enroll.length];
        int index = -1;
        for (String enrollName: enroll) {
            Node node = nodes.get(enrollName);
            answer[++index] = node.amount;
        }

        return answer;
    }

    private static class Node {
        private static final Node CENTER = new Node();
        private Node referral = CENTER;
        private int amount;

        public void setReferral(Node referral) {
            this.referral = referral;
        }

        public void addAmount(int amount) {
            if (this == CENTER) {
                this.amount += amount;
                return;
            }

            int referralAmount = amount / 10;
            if (referralAmount == 0) {
                this.amount += amount;
                return;
            }

            this.amount += amount - referralAmount;
            referral.addAmount(referralAmount);
        }
    }
}
