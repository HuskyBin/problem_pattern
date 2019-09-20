// 先借用leetcode 743 的答案
// 但是包含了所有的精髓
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null) {
            return 0;
        }
        int[][] graph = new int[N + 1][N + 1];
        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }
        for (int i = 0; i < times.length; i++) {
            graph[times[i][0]][times[i][1]] = times[i][2];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, K});
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        while (!heap.isEmpty()) {
            int[] pNodePair = heap.poll();
            int curCost = pNodePair[0];
            int node = pNodePair[1];
            for (int i = 1; i <= N; i++) {
                if (graph[node][i] >= 0) {
                    if (curCost + graph[node][i] < dist[i]) {
                        dist[i] = curCost + graph[node][i];
                        heap.add(new int[]{curCost + graph[node][i], i});
                    }
                }
            }
        }
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, dist[i]);
        }
        return result;
    }

    
}

//其实用Queue也是可以实现Dijstra算法的不过复杂度是V的平方，用PriorityQueue的复杂度是(E + V)LogV
// Adding these running times together, we have O(|E|log|V|) for all priority value updates and O(|V|log|V|) for
//     removing all vertices (there are also some other O(|E|) and O(|V|) additive terms, but they are dominated by these two terms). 
//     This means the running time for Dijkstra's algorithm using a binary min-heap as a priority queue is O((|E|+|V|)log|V|).
class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int N, int K) {
        int res = 0;
        vector<vector<int>> edges(101, vector<int>(101, -1));
        queue<int> q{{K}};
        vector<int> dist(N + 1, INT_MAX);
        dist[K] = 0;
        for (auto e : times) edges[e[0]][e[1]] = e[2];
        while (!q.empty()) {
            int u = q.front(); q.pop();
            for (int v = 1; v <= 100; ++v) {
                if (edges[u][v] != -1 && dist[u] + edges[u][v] < dist[v]) {
                    q.push(v);
                    dist[v] = dist[u] + edges[u][v];
                }
            }
        }
        for (int i = 1; i <= N; ++i) {
            res = max(res, dist[i]);
        }
        return res == INT_MAX ? -1 : res;
    }
};


// visited pattern
// 886. Reachable Nodes In Subdivided Graph
class Solution {
    public int reachableNodes(int[][] edges, int M, int N) {
        if (edges == null || edges.length == 0) {
            return 0;
        }
        int[][] graph = new int[N][N];
        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        heap.add(new int[]{0, M});
        Set<Integer> visited = new HashSet<>();
        int result = 0;
        while (!heap.isEmpty()) {
            int[] curNode = heap.poll();
            int node = curNode[0];
            int moves = curNode[1];
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            result++;
            for (int i = 0; i < N; i++) {
                if (graph[node][i] >= 0) {
                    if (moves - graph[node][i] - 1 >= 0 && !visited.contains(i)) {
                        heap.offer(new int[]{i, moves - graph[node][i] - 1});
                    }
                    result += Math.min(moves, graph[node][i]);
                    graph[i][node] -= Math.min(moves, graph[node][i]);
                }
            }
        }
        return result;
    }
}
