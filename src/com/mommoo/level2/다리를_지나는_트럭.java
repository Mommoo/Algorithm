package com.mommoo.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class 다리를_지나는_트럭 {
    public static void main(String[] args) {
        System.out.println(new 다리를_지나는_트럭().solution(2, 10, new int[]{7}));
        System.out.println(new 다리를_지나는_트럭().solution(2, 10, new int[]{7, 4}));
        System.out.println(new 다리를_지나는_트럭().solution(2, 10, new int[]{7, 4, 5}));
        System.out.println(new 다리를_지나는_트럭().solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(new 다리를_지나는_트럭().solution(100, 100, new int[]{10}));
        System.out.println(new 다리를_지나는_트럭().solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> truckQueue = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            truckQueue.offer(new Truck(truckWeight));
        }

        Bridge bridge = new Bridge(weight, bridge_length);

        while (!truckQueue.isEmpty()) {
            Truck truck = truckQueue.peek();
            if (bridge.isMoreTruck(truck)) {
                bridge.putTruck(truckQueue.poll());
                continue;
            }

            bridge.getOutFirstTruck();
        }

        bridge.getOutRemainTruck();
        return bridge.totalTime;
    }

    public static class Bridge {
        private static final int NO_MORE_TRUCK = 0;
        private static final int NORMAL_MORE_TRUCK = 1;
        private static final int MORE_TRUCK_IF_FIRST_CAR_OUT = 2;
        private final int bridgeWeight;
        private final int length;
        private List<Truck> trucks = new ArrayList<>();
        private int totalTime = 0;

        public Bridge(int bridgeWeight, int length) {
            this.bridgeWeight = bridgeWeight;
            this.length = length;
        }

        public boolean isMoreTruck(Truck truck) {
            return computeMoreTruckCondition(truck) != NO_MORE_TRUCK;
        }

        public void getOutFirstTruck() {
            if (trucks.isEmpty()) {
                return;
            }

            int distance = trucks.get(0).getDistance(length);
            totalTime += distance;
            trucks.remove(0);
            trucks.forEach(t -> t.move(distance));
        }

        public void getOutRemainTruck() {
            if (trucks.isEmpty()) {
                return;
            }

            Truck lastTruck = trucks.get(trucks.size() - 1);
            int distance = lastTruck.getDistance(length);
            totalTime += distance;
            trucks.clear();
        }

        public void putTruck(Truck truck) {
            int moreTruckCondition = computeMoreTruckCondition(truck);

            if (moreTruckCondition == NO_MORE_TRUCK) {
                return;
            }

            if (moreTruckCondition == NORMAL_MORE_TRUCK) {
                this.trucks = trucks.stream()
                                    .filter(t -> !t.isAt(length - 1))
                                    .peek(t -> t.move(1))
                                    .collect(Collectors.toList());
                totalTime += 1;
            }

            if (moreTruckCondition == MORE_TRUCK_IF_FIRST_CAR_OUT) {
                getOutFirstTruck();
            }

            truck.move(1);
            trucks.add(truck);
        }

        private int computeMoreTruckCondition(Truck truck) {
            int totalTruckWeightInBridge = trucks.stream()
                                                 .mapToInt(Truck::getWeight)
                                                 .sum();

            if (totalTruckWeightInBridge + truck.getWeight() <= bridgeWeight) {
                return NORMAL_MORE_TRUCK;
            }

            int totalTruckWeightInBridgeWithOutFirst = totalTruckWeightInBridge - trucks.get(0).getWeight();

            if (totalTruckWeightInBridgeWithOutFirst + truck.getWeight() <= bridgeWeight) {
                return MORE_TRUCK_IF_FIRST_CAR_OUT;
            }

            return NO_MORE_TRUCK;
        }


    }

    public static class Truck {
        private final int weight;
        private int index = -1;

        public Truck(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public void move(int distance) {
            index += distance;
        }

        public int getDistance(int toIndex) {
            return toIndex - this.index;
        }

        public boolean isAt(int index) {
            return this.index == index;
        }
    }
}
