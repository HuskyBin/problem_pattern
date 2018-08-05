/*aproach #2: A* Search [Accepted]
Intuition and Algorithm

The A star algorithm is another path-finding algorithm. For every node at position (r, c), we have some estimated cost node.f = node.g + node.h, where node.g is the actual distance from (sr, sc) to (r, c), and node.h is our heuristic* (guess) of the distance from (r, c) to (tr, tc). In this case, our guess will be the taxicab distance, node.h = abs(r-tr) + abs(c-tc).

We keep a priority queue to decide what node to search in (expand) next. We can prove that if we find the target node, we must have travelled the lowest possible distance node.g. By considering the last time where two backwards paths are the same, without loss of generality we could suppose the penultimate square of the two paths are different, and then in this case node.f = node.g + 1, showing the path with less actual distance travelled is expanded first as desired.

It might be useful for solvers familiar with Dijkstra's Algorithm to know that A* Search is a special case of Dijkstra's with node.h = 0 always.
*/
public int cutOffTree(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
    int R = forest.size(), C = forest.get(0).size();
    PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
        (a, b) -> Integer.compare(a[0], b[0]));
    heap.offer(new int[]{0, 0, sr, sc});

    HashMap<Integer, Integer> cost = new HashMap();
    cost.put(sr * C + sc, 0);

    while (!heap.isEmpty()) {
        int[] cur = heap.poll();
        int g = cur[1], r = cur[2], c = cur[3];
        if (r == tr && c == tc) return g;
        for (int di = 0; di < 4; ++di) {
            int nr = r + dr[di], nc = c + dc[di];
            if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                int ncost = g + 1 + Math.abs(nr-tr) + Math.abs(nc-tr);
                if (ncost < cost.getOrDefault(nr * C + nc, 9999)) {
                    cost.put(nr * C + nc, ncost);
                    heap.offer(new int[]{ncost, g+1, nr, nc});
                }
            }
        }
    }
    return -1;
}
