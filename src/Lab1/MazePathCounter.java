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

        this.n = m.length;
        this.m = new int[n + 2][n + 2];
        capacity = new int[n + 2][n + 2];
        flow = new int[n + 2][n + 2];

        for (int i = 0; i < m.length; i++) {
            if (m[i].length >= 0) {
                System.arraycopy(m[i], 0, this.m[i], 0, m[i].length);
                System.arraycopy(m[i], 0, capacity[i], 0, m[i].length);
            }
        }

        s = n;
        t = n + 1;

    }

    int calcPeople() {
        Arrays.fill(visited, false);
        Arrays.fill(flow, new int[n]);
        dfs(s, INF);
        int nPeople = 0;
        for (int v = 0; v < n; v++) {
            nPeople += flow[s][v];
        }
        return nPeople;
    }

    private int dfs(int u, int Cmin) { // Cmin — пропускная способность в текущем подпотоке
        if (u == t) {
            return Cmin;
        }
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (m[u][v] == 0) {
                continue;
            }
            if (!visited[v] && flow[u][v] < capacity[u][v]) {
                int delta = dfs(v, Math.min(Cmin, capacity[u][v] - flow[u][v]));
                if (delta > 0) {
                    flow[u][v] += delta;
                    flow[v][u] -= delta;
                    return delta;
                }
            }
        }
        return 0;
    }

}
