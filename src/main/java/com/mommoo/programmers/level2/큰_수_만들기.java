package com.mommoo.programmers.level2;

public class 큰_수_만들기 {
    public static void main(String[] args) {
        System.out.println(new 큰_수_만들기().solution("31", 1));
        System.out.println(new 큰_수_만들기().solution("1924", 2));
        System.out.println(new 큰_수_만들기().solution("1924", 3));
        System.out.println(new 큰_수_만들기().solution("1231234", 3));
        System.out.println(new 큰_수_만들기().solution("4177252841", 4));
    }

    public String solution(String number, int k) {
        StringBuilder builder = new StringBuilder();

        while (k != 0) {
            int maxNumberIndex = findMaxNumberIndexInRanges(number, k);
            builder.append(number.charAt(maxNumberIndex));

            number = number.substring(maxNumberIndex + 1);
            k -= maxNumberIndex;

            if (k == number.length()) {
                number = "";
                break;
            }
        }

        return builder.toString() + number;
    }

    private static int findMaxNumberIndexInRanges(String number, int endIndex) {
        endIndex = Math.min(endIndex, number.length() - 1);

        int maxNumberIndex = - 1;
        char maxNumber = '0' - 1;

        for (int i = 0 ; i < endIndex + 1; i++) {
            char cNumber = number.charAt(i);
            if (maxNumber < cNumber) {
                maxNumberIndex = i;
                maxNumber = cNumber;
            }
        }

        return maxNumberIndex;
    }
}
