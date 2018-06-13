有向图（可以判断有环，并且输出顺序）：
1: BFS 的拓扑排序
2: DFS 用三种颜色，最后加入的时候要倒过来  
（参考挑战程序设计 p284）
实现 Leetcode Course Schedule I 和 II

无向图（只能判断是否有环，并不能输出顺序）：
1: BFS 只需两种颜色
2: DFS 只需两种颜色
3: 并查集 
参考 Leetcode Graph Valid Tree 图验证树： http://www.cnblogs.com/grandyang/p/5257919.html


拓扑排序 BFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();
        for (int[] rule : prerequisites) {
            int number = degree.getOrDefault(rule[0], 0);
            degree.put(rule[0], number + 1);
            graph.computeIfAbsent(rule[1], k -> new ArrayList<>()).add(rule[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!degree.containsKey(i)) {
                queue.offer(i);
            }
        }
        int number = 0;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            number++;
            List<Integer> neighbors = graph.getOrDefault(curNode, new ArrayList<>());
            for (int node : neighbors) {
                degree.put(node, degree.get(node) - 1);
                if (degree.get(node) == 0) {
                    queue.offer(node);
                }
            }
        }
        return numCourses == number;
    }
}

// DFS
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] rule : prerequisites) {
            graph.computeIfAbsent(rule[1], k -> new ArrayList<>()).add(rule[0]);
        }
        int[] visited = new int[numCourses];
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(graph, visited, i, results)) {
                return new int[0];
            }
        }
        Collections.reverse(results);
        return results.stream().mapToInt(i -> i).toArray();
    }
    
    private boolean canFinishDFS(Map<Integer, List<Integer>> graph, int[] visited, int node, List<Integer> results) {
        if (visited[node] == -1) {
            return false;
        }    
        if (visited[node] == 1) {
            return true;
        }
        visited[node] = -1;
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!canFinishDFS(graph, visited, neighbor, results)) {
                return false;
            }
        }
        results.add(node);
        visited[node] = 1;
        return true;
    }
}
