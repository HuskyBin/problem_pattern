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


//1197. Minimum Knight Moves
class Solution {
    class Triplet{//stores node information, g_cost(depth), and f_cost(heuristic_cost+g_cost)
        int[] node; 
        int g_cost, f_cost;
        Triplet(int[] node, int g, int f){
            this.node=node;
            this.g_cost=g;
            this.f_cost=f; 
        }
    }
    private static final int[][] dir = {{-1,-2},{1,-2},{-1,2},{1,2},{-2,-1},{2,-1},{-2,1},{2,1}};
    public int minKnightMoves(int x, int y) {
        //reduce search space
        x = Math.abs(x); y = Math.abs(y);
        //store seen cells
        Set<String> seen = new HashSet();
    	seen.add(0+","+0);
        //use a priorityqueue and sort by f(n)
    	PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>((Triplet a1, Triplet a2)->a1.f_cost-a2.f_cost);
    	pq.add(new Triplet(new int[] {0,0}, 0, heursitic_cost(x, y, 0, 0)));
    	while(!pq.isEmpty()) {
    		Triplet cur = pq.poll();
    		int[] node = cur.node;
    		int depth = cur.g_cost;
    		if(node[0]==x && node[1]==y) {//if arrive at destination, return g(n)
    			return depth;
    		}
    		int next_depth = depth+1;
    		for(int j=0; j<dir.length; ++j){//check neighbors
    			int nxt_x = node[0]+dir[j][0];
                int nxt_y = node[1]+dir[j][1];
                String nxt = nxt_x+","+nxt_y;
                if(!seen.contains(str) && nxt_x>=-2 && nxt_y>=-2){//here we can add a judging statement if there's obstacle
                	int h_cost = heursitic_cost(x, y, nxt_x, nxt_y);
                	pq.add(new Triplet(new int[] {nxt_x, nxt_y}, next_depth, next_depth+h_cost));
                	seen.add(nxt);
                }
    		}
    	}
    	return 0;
    }
    
    private int heursitic_cost(int x, int y, int x1, int y1) {//admissible, never overestimate the cost
    	int max=Math.max(Math.abs(x-x1), Math.abs(y-y1));
    	int min=Math.min(Math.abs(x-x1), Math.abs(y-y1));
    	int short_cost = Math.max(0, (min*2-max)/3);
    	int long_cost = (max-short_cost)/3;
 
    	return long_cost+short_cost;
    }
}
