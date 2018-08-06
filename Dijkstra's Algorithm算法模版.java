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
