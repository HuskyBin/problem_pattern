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
