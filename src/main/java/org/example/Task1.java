package org.example;

public class Task1 {

    public static int[] calculateWaitingDays(int[] prices) {
        int n = prices.length;

        int[] answer = new int[n];
        int[] stack = new int[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            while (size > 0
                    && prices[i] > prices[stack[size - 1]]) {

                int previousDay = stack[--size];
                answer[previousDay] = i - previousDay;
            }

            stack[size++] = i;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int[] prices = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        int[] answer = calculateWaitingDays(prices);
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                output.append(' ');
            }

            output.append(answer[i]);
        }

        System.out.println(output);
    }
}