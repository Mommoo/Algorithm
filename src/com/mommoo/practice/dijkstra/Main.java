package com.mommoo.practice.dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    private static final Map<Stuff, Boolean> VISITS = new HashMap<>();
    private static final Map<Stuff, Map<Stuff, Integer>> PATH_COSTS = new HashMap<>();
    private static final Map<Stuff, Stuff> PARENTS = new HashMap<>();
    private static final Map<Stuff, Integer> COSTS = new HashMap<>();

    private enum Stuff {
        SHEET, LP, POSTER, DRUM, GUITAR, PIANO
    }

    public static void main(String[] args) {
        PATH_COSTS.put(Stuff.SHEET, new HashMap<>());
        PATH_COSTS.get(Stuff.SHEET).put(Stuff.LP, 5);
        PATH_COSTS.get(Stuff.SHEET).put(Stuff.POSTER, 0);

        PATH_COSTS.put(Stuff.POSTER, new HashMap<>());
        PATH_COSTS.get(Stuff.POSTER).put(Stuff.GUITAR, 30);
        PATH_COSTS.get(Stuff.POSTER).put(Stuff.DRUM, 35);

        PATH_COSTS.put(Stuff.LP, new HashMap<>());
        PATH_COSTS.get(Stuff.LP).put(Stuff.GUITAR, 15);
        PATH_COSTS.get(Stuff.LP).put(Stuff.DRUM, 20);

        PATH_COSTS.put(Stuff.GUITAR, new HashMap<>());
        PATH_COSTS.get(Stuff.GUITAR).put(Stuff.PIANO, 20);

        PATH_COSTS.put(Stuff.DRUM, new HashMap<>());
        PATH_COSTS.get(Stuff.DRUM).put(Stuff.PIANO, 10);

        PARENTS.put(Stuff.LP, Stuff.SHEET);
        PARENTS.put(Stuff.POSTER, Stuff.SHEET);

        for (Stuff stuff: Stuff.values()) {
            VISITS.put(stuff, false);
        }
        VISITS.put(Stuff.SHEET, true);

        COSTS.put(Stuff.LP, 5);
        COSTS.put(Stuff.POSTER, 0);
        COSTS.put(Stuff.DRUM, Integer.MAX_VALUE);
        COSTS.put(Stuff.GUITAR, Integer.MAX_VALUE);
        COSTS.put(Stuff.PIANO, Integer.MAX_VALUE);

        while (remainNeedToVisit()) {
            Stuff findMinPathCostStuff = findMinimumPathCostStuff();
            VISITS.put(findMinPathCostStuff, Boolean.TRUE);

            if (findMinPathCostStuff == Stuff.PIANO) {
                continue;
            }

            int cost = COSTS.get(findMinPathCostStuff);
            Map<Stuff, Integer> nextPathCosts = PATH_COSTS.get(findMinPathCostStuff);
            for (Stuff stuff : nextPathCosts.keySet()) {
                if (Boolean.TRUE.equals(VISITS.get(stuff))) {
                    continue;
                }

                int computedNextPathCost = cost + nextPathCosts.get(stuff);
                if (COSTS.get(stuff) > computedNextPathCost) {
                    COSTS.put(stuff, computedNextPathCost);
                    PARENTS.put(stuff, findMinPathCostStuff);
                }
            }
        }

        System.out.println(COSTS.get(Stuff.PIANO));
        Stack<Stuff> stack = new Stack<>();
        Stuff stuff = Stuff.PIANO;
        while (true) {
            stack.push(stuff);
            if (stuff == Stuff.SHEET) {
                break;
            }

            stuff = PARENTS.get(stuff);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(stack.pop().name());
        while (!stack.isEmpty()) {
            builder.append("->").append(stack.pop().name());
        }
        System.out.println(builder);
    }

    private static boolean remainNeedToVisit() {
        for (Stuff stuff: Stuff.values()) {
            if (Boolean.FALSE.equals(VISITS.get(stuff))) {
                return true;
            }
        }

        return false;
    }

    private static Stuff findMinimumPathCostStuff() {
        int minValue = Integer.MAX_VALUE;
        Stuff findStuff = null;

        for (Stuff stuff: COSTS.keySet()) {
            int cost = COSTS.get(stuff);
            if (Boolean.FALSE.equals(VISITS.get(stuff)) && cost < minValue) {
                findStuff = stuff;
                minValue = cost;
            }
        }

        return findStuff;
    }
}
