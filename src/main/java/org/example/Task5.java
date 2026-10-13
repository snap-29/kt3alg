package org.example;

import java.util.Arrays;

public class Task5 {
    private static final int MASK_COUNT = 1 << 10;

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static int shortestPath(
            char[][] map,
            int start,
            int target
    ) {
        int n = map.length;
        int m = map[0].length;

        int stateCount = n * m * MASK_COUNT;

        int[] distance = new int[stateCount];
        Arrays.fill(distance, -1);

        int[] queue = new int[stateCount];
        int head = 0;
        int tail = 0;

        int initialState = start * MASK_COUNT;

        distance[initialState] = 0;
        queue[tail++] = initialState;

        while (head < tail) {
            int state = queue[head++];

            int cell = state / MASK_COUNT;
            int mask = state % MASK_COUNT;

            if (cell == target) {
                return distance[state];
            }

            int row = cell / m;
            int column = cell % m;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DR[direction];
                int nextColumn = column + DC[direction];

                if (nextRow < 0 || nextRow >= n
                        || nextColumn < 0 || nextColumn >= m) {
                    continue;
                }

                char symbol = map[nextRow][nextColumn];

                if (symbol == '#') {
                    continue;
                }

                int nextMask = mask;

                if (symbol >= 'A' && symbol <= 'J') {
                    int door = symbol - 'A';

                    if ((mask & (1 << door)) == 0) {
                        continue;
                    }
                }

                if (symbol >= 'a' && symbol <= 'j') {
                    int key = symbol - 'a';
                    nextMask |= 1 << key;
                }

                int nextCell = nextRow * m + nextColumn;
                int nextState = nextCell * MASK_COUNT + nextMask;

                if (distance[nextState] != -1) {
                    continue;
                }

                distance[nextState] = distance[state] + 1;
                queue[tail++] = nextState;
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