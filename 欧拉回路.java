/*
有两种欧拉路。第一种叫做 Eulerian path(trail)，沿着这条路径走能够走遍图中每一条边；第二种叫做 Eularian cycle，沿着这条路径走，不仅能走遍图中每一条边，而且起点和终点都是同一个顶点。注意：欧拉路要求每条边只能走一次，但是对顶点经过的次数没有限制。

满足什么性质的图才能有欧拉路？根据 wikipedia 对欧拉路的介绍：

在无向图中，所有顶点的度数均为偶，则存在 Eularian cycle；若有且仅有两个顶点的度数为奇，其余的都为偶，则存在 Eularian path；
在有向图中，所有顶点的入度数等于出度数，则存在 Eularian cycle；若有且仅有两个顶点：其中一个入度数比出度数大 1，另一个入度数比出度数小 1，其余的顶点入度数等于出度数，则存在 Eularian path.
*/

// Eulerian path, using Hierholzer’s Algorithm
// 332. Reconstruct Itinerary
public class Solution {

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
}
