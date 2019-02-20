//DFS:
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

// BFS
class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();
        for (int[] rule : prerequisites) {
            int number = degree.getOrDefault(rule[0], 0);
            degree.put(rule[0], number + 1);
            graph.computeIfAbsent(rule[1], k -> new ArrayList<>()).add(rule[0]);
        }
        List<Integer> results = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!degree.containsKey(i)) {
                queue.offer(i);
            }
        }
        int number = 0;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            results.add(curNode);
            number++;
            List<Integer> neighbors = graph.getOrDefault(curNode, new ArrayList<>());
            for (int node : neighbors) {
                degree.put(node, degree.get(node) - 1);
                if (degree.get(node) == 0) {
                    queue.offer(node);
                }
            }
        }
        if (number != numCourses) {
            return new int[0];
        }
        return results.stream().mapToInt(i -> i).toArray();  
        
    }
}
