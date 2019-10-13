class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
	    List<Integer>[] graph = new ArrayList[n];
	    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
	    for (int i = 0; i < connections.size(); i++) {
		    List<Integer> edge = connections.get(i);
		    graph[edge.get(0)].add(edge.get(1));
		    graph[edge.get(1)].add(edge.get(0));
	    }
	    boolean[] visited = new boolean[n];
	    int[] order = new int[n];
	    List<List<Integer>> results = new ArrayList<>();
	    int[] uuid = new int[1]; // a global unique counter that assigns traversal order to each node
	    dfs(graph, -1, 0, visited, order, uuid, results);
	    return results;
    }

public void dfs(List<Integer>[] graph, int pre, int src, boolean[] visited, int[] order, int[] uuid, List<List<Integer>> results) {
	  visited[src] = true;
	  order[src] = uuid[0]++;
	  int orig_order = order[src];
	  for (Integer neighbour : graph[src]) {
		  if (neighbour == pre) continue; // pre: guarantee no backward traversal
		  if (!visited[neighbour]) dfs(graph, src, neighbour, visited, order, uuid, results);
		  // order[src] keeps the earliest point that src can reach without passing src->pre
		  order[src] = Math.min(order[src], order[neighbour]); // used by its predecessor to tell if pre->src is critical.
		  if (orig_order < order[neighbour]) results.add(Arrays.asList(src, neighbour));
	  }
  }
}
