package com.mommoo.level2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 주식가격 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 주식가격().solution(new int[]{1, 2, 3, 2, 3})));
        System.out.println(Arrays.toString(new 주식가격().solution(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(new 주식가격().solution(new int[]{5, 5, 4, 7, 1})));
    }

    public int[] solution(int[] p) {
        int[] answers = new int[p.length];
        List<StockPrice> prices = new LinkedList<>();
        int index = 0;
        for (int value : p) {

            Iterator<StockPrice> priceIterator = prices.iterator();
            while (priceIterator.hasNext()) {
                StockPrice price = priceIterator.next();
                boolean isProgress = price.progress(value);
                if (!isProgress) {
                    answers[price.index] = price.time;
                    priceIterator.remove();
                }
            }

            prices.add(new StockPrice(index++, value));
        }

        prices.forEach(price -> answers[price.index] = price.time);

        return answers;
    }

    private static class StockPrice {
        private final int index;
        private final int value;
        private int time = 0;

        public StockPrice(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public boolean progress(int value) {
            time++;
            return this.value <= value;
        }
    }
}
