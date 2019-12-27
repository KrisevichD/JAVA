package Lab1;

import java.util.Arrays;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class MazePathCounter {

    final int INF = 10000;

    private final int n; // размер графа

    private final int[][] m;

    private final int s; // фейковый старт

    private final int t; // фейковый финиш

    private final int[][] capacity; // пропускные способности ребер

    private final int[][] flow; // потоки

    private boolean[] visited;

    MazePathCounter(int[][] m, int[] in, int[] out, boolean allowManyPeopleInNode) {

        n = allowManyPeopleInNode ? m.length + 2 : m.length * m.length + 2;

        this.m = new int[n][n];
        capacity = new int[n][n];
        flow = new int[n][n];
        visited = new boolean[n];
        s = n - 2;
        t = n - 1;

        if (allowManyPeopleInNode) {
            initAllowManyPeopleInNode(m, in, out);
        } else {
            init(m, in, out);
        }
    }

    private void initAllowManyPeopleInNode(int[][] m, int[] in, int[] out) {
        for (int i = 0; i < m.length; i++) {
            if (m[i].length >= 0) {
                System.arraycopy(m[i], 0, this.m[i], 0, m[i].length);
                System.arraycopy(m[i], 0, capacity[i], 0, m[i].length);
            }
        }

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

    private void init(int[][] m, int[] in, int[] out) {

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i][j] == 1) {
                    for (int k = 0; k < m[i].length; k++) {
                        if (k == j) {
                            continue;
                        }
                        this.m[i * m.length + j][i * m.length + k] = 1;
                        this.m[i * m.length + k][i * m.length + j] = 1;
                        capacity[i * m.length + j][i * m.length + k] = 1;
                        capacity[i * m.length + k][i * m.length + j] = 1;
                    }
                }
            }
        }


        for (int v : in) {
            for (int i = 0; i < m[v].length; i++) {
                if (m[v][i] == 1) {
                    int a = min(i, v);
                    int b = max(i, v);
                    this.m[s][a * 10 + b] = 1;
                    this.m[a * 10 + b][s] = 1;
                    capacity[s][v * 10 + i] = INF;
                }
            }
        }


        for (int u : out) {
            for (int i = 0; i < m[u].length; i++) {
                if (m[u][i] == 1) {
                    int a = min(i, u);
                    int b = max(i, u);
                    this.m[t][a * 10 + b] = 1;
                    this.m[a * 10 + b][t] = 1;
                    capacity[t][u * 10 + i] = INF;
                }
            }
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

            int delta = dfs(v, min(Cmin, capacity[u][v] - flow[u][v]));
            if (delta > 0) {
                flow[u][v] += delta;
                flow[v][u] -= delta;
                return delta;
            }
        }
        return 0;
    }

}
