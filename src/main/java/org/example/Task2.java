package org.example;

import java.util.Arrays;

public class Task2 {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static int shortestPath(
            char[][] map,
            int start,
            int target
    ) {
        int n = map.length;
        int m = map[0].length;
        int cells = n * m;

        int[] distance = new int[cells];
        Arrays.fill(distance, -1);

        int[] queue = new int[cells];
        int head = 0;
        int tail = 0;

        queue[tail++] = start;
        distance[start] = 0;

        while (head < tail) {
            int current = queue[head++];

            if (current == target) {
                return distance[current];
            }

            int row = current / m;
            int column = current % m;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DR[direction];
                int nextColumn = column + DC[direction];

                if (nextRow < 0 || nextRow >= n
                        || nextColumn < 0 || nextColumn >= m) {
                    continue;
                }

                if (map[nextRow][nextColumn] == '#') {
                    continue;
                }

                int next = nextRow * m + nextColumn;

                if (distance[next] != -1) {
                    continue;
                }

                distance[next] = distance[current] + 1;
                queue[tail++] = next;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        char[][] map = new char[n][];
        int start = -1;
        int target = -1;

        for (int row = 0; row < n; row++) {
            map[row] = scanner.next().toCharArray();

            for (int column = 0; column < m; column++) {
                if (map[row][column] == 'S') {
                    start = row * m + column;
                } else if (map[row][column] == 'T') {
                    target = row * m + column;
                }
            }
        }

        System.out.println(shortestPath(map, start, target));
    }
}