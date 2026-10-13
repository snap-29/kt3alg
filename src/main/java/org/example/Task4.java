package org.example;

public class Task4 {

    public static int largestRectangle(int[] heights, int[] stack) {
        int size = 0;
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            int currentHeight =
                    i == heights.length ? 0 : heights[i];

            while (size > 0
                    && heights[stack[size - 1]] > currentHeight) {

                int height = heights[stack[--size]];

                int leftBoundary =
                        size == 0 ? -1 : stack[size - 1];

                int width = i - leftBoundary - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            if (i < heights.length) {
                stack[size++] = i;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] heights = new int[m];
        int[] stack = new int[m];

        int answer = 0;

        for (int row = 0; row < n; row++) {
            String line = scanner.next();

            for (int column = 0; column < m; column++) {
                if (line.charAt(column) == '1') {
                    heights[column]++;
                } else {
                    heights[column] = 0;
                }
            }

            answer = Math.max(
                    answer,
                    largestRectangle(heights, stack)
            );
        }

        System.out.println(answer);
    }
}