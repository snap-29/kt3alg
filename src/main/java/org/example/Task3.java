package org.example;

public class Task3 {

    public static boolean hasCycle(int[] next, int start) {
        int slow = start;
        int fast = start;

        while (fast != -1) {
            int firstStep = next[fast];

            if (firstStep == -1) {
                return false;
            }

            slow = next[slow];
            fast = next[firstStep];

            if (fast != -1 && slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int start = scanner.nextInt();

        int[] next = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            next[i] = scanner.nextInt();
        }

        System.out.println(hasCycle(next, start) ? "YES" : "NO");
    }
}