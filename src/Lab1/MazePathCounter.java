package Lab1;

import java.util.Arrays;

public class MazePathCounter {

    final int INF = 100;

    private final int n; // размер графа

    private final int[][] m;

    private final int s; // фейковый старт

    private final int t; // фейковый финиш

    private final int[][] capacity; // пропускные способности ребер

    private final int[][] flow; // потоки

    private boolean[] visited;

    MazePathCounter(int[][] m, int[] in, int[] out) {

        this.n = m.length + 2;
        this.m = new int[n][n];
        capacity = new int[n][n];
        flow = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < m.length; i++) {
            if (m[i].length >= 0) {
                System.arraycopy(m[i], 0, this.m[i], 0, m[i].length);
                System.arraycopy(m[i], 0, capacity[i], 0, m[i].length);
            }
        }

        s = n - 2;
        t = n - 1;
        for (int v : in) {
            this.m[s][v] = 1;
            this.m[v][s] = 1;
            capacity[s][v] = INF;
        }
        for (int u : out) {
            this.m[t][u] = 1;
            this.m[u][t] = 1;
            capacity[u][t] = INF;
        }
    }

    int calcPeople() {
        for (int i = 0; i < n; i++) {
            flow[i] = new int[n];
        }

        int nPeople = 0;
        while (true) {
            Arrays.fill(visited, false);
            int add = dfs(s, INF);
            if (add == 0) {
                return nPeople;
            }
            nPeople += add;
        }
    }

    private int dfs(int u, int Cmin) { // Cmin — пропускная способность в текущем подпотоке
        if (u == t) {
            return Cmin;
        }
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (m[u][v] == 0 || visited[v] || flow[u][v] >= capacity[u][v]) {
                continue;
            }

            int delta = dfs(v, Math.min(Cmin, capacity[u][v] - flow[u][v]));
            if (delta > 0) {
                flow[u][v] += delta;
                flow[v][u] -= delta;
                return delta;
            }
        }
        return 0;
    }

}
