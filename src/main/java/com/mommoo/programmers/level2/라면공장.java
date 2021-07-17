package com.mommoo.programmers.level2;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 라면공장 {
    public static void main(String[] args) {
        System.out.println(new 라면공장().solution(4, new int[]{4, 10, 15}, new int[]{20, 5, 10}, 30));
        System.out.println(new 라면공장().solution(20, new int[]{4, 5, 6, 70}, new int[]{20, 20, 20, 50}, 100));
    }

    public int solution(int stock, int[] dates, int[] supplies, int k) {
        Stock myStock = new Stock(stock);
        Stock goalStock = new Stock(k);

        StockProvider stockProvider = StockProvider.from(dates, supplies);

        while (myStock.isLessThan(goalStock)) {
            Stock nextStock = stockProvider.provideStock(myStock);
            myStock = myStock.add(nextStock);
        }

        return stockProvider.provideCount;
    }

    private static class StockProvider {
        private final List<Supply> supplies;
        private final PriorityQueue<Supply> priorityQueue = maxPriorityQueue();
        private int provideCount = 0;

        private StockProvider(List<Supply> supplies) {
            this.supplies = supplies;
        }

        private static PriorityQueue<Supply> maxPriorityQueue() {
            Comparator<Supply> descendingSort = (s1, s2) -> s2.stock - s1.stock;
            return new PriorityQueue<>(descendingSort);
        }

        private static StockProvider from(int[] dates, int[] supplies) {
            return new StockProvider(IntStream.range(0, dates.length)
                                              .mapToObj(idx -> new Supply(dates[idx], supplies[idx]))
                                              .collect(Collectors.toCollection(LinkedList::new)));
        }

        public Stock provideStock(Stock ownedStock) {
            putCandidateSupplies(ownedStock);
            provideCount++;

            return Optional.ofNullable(priorityQueue.poll())
                           .map(Supply::toStock)
                           .orElseThrow(() -> new IllegalStateException("doesn't exist providable stock"));
        }

        private void putCandidateSupplies(Stock ownedStock) {
            Iterator<Supply> iterator = supplies.iterator();
            while (iterator.hasNext()) {
                Supply supply = iterator.next();
                if (!supply.isCanSupply(ownedStock)) {
                    break;
                }

                priorityQueue.offer(supply);
                iterator.remove();
            }
        }
    }

    private static class Stock {
        private int value;

        public Stock(int value) {
            this.value = value;
        }

        public Stock add(Stock stock) {
            return new Stock(this.value + stock.value);
        }

        public boolean isLessThan(Stock stock) {
            return this.value < stock.value;
        }
    }

    private static class Supply {
        private final int date;
        private final int stock;

        public Supply(int date, int stock) {
            this.date = date;
            this.stock = stock;
        }

        public boolean isCanSupply(Stock stock) {
            return stock.value >= date;
        }

        public Stock toStock() {
            return new Stock(stock);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Supply))
                return false;
            Supply supply = (Supply) o;
            return date == supply.date &&
                   stock == supply.stock;
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, stock);
        }
    }
}
