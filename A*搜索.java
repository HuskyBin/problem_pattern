// 启发函数的性质
// 那么能不能综合上面Dijkstra算法得到最优解和贪心算法速度快的特点，有更好的办法呢？ 
// 【注意一下这个地方，Dijkstra算法是适用于任何图算法找最短距离均可的，但是用到启发式算法的话，大部分情况下会是一个方格图，因为只有方格图才能比较好的估算从当前点到终点的距离】 
// 那么我们可以先定义一个估算函数 
// f(n)=g(n)+h(n)
// f(n)=g(n)+h(n)

// 其中g(n)g(n)表示起点到当前点nn实际走的代价，h(n)h(n)表示当前点nn到终点的估算代价。 
// 所以上面两个合起来就是其走当前点到终点的总代价函数f(n)f(n) 
// 而h(n)h(n)这个估计函数不同的估计情况，结果也不会相同。
// 先考虑极端情况， 如果h(n)=0h(n)=0的情况下，只有g(n)g(n)起作用，那么A*算法就是Dijkstra算法。
// 如果h(n)h(n)始终小于等于实际nn点到终点的距离，那么必然能够保证A*算法找的解就是最优解。而且h(n)h(n)越小，则A*扩展的节点也就越多，A*算法运行的也就越慢。
// 如果h(n)h(n)始终都等于实际nn点到终点的距离，那么A*算法只会严格走从起点到终点的最短路径。虽然这种情况一般不可能发生，当然一些特殊情况下会发生【比如没有障碍物的情况】。
// 如果h(n)h(n)有时候大于实际nn点到终点的距离，那么不能保证A*算法能够找到最短路径
// 另外一种极端情况，就是如果只有h(n)h(n)发挥作用，则A*算法就相当于贪心算法。
// ————————————————
// 版权声明：本文为CSDN博主「哈乐笑」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
// 原文链接：https://blog.csdn.net/haolexiao/article/details/70302848


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

//773 Sliding Puzzle
    
    class Solution {
    
    // A*
       public int slidingPuzzle(int[][] a) {
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<State> pq = new PriorityQueue<>();
        Set<State> been = new HashSet<>();
        int zi = 0, zj = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 0) {
                    zi = i;
                    zj = j;
                    break;
                }
            }
        }
        State start = new State(a, 0, zi, zj);
        pq.add(start);
        been.add(start);
        while (!pq.isEmpty()) {
            State pop = pq.remove();
            if (pop.isGoal()) return pop.taken;
            for (int[] move : moves) {
                int nzi = pop.zi + move[0];
                int nzj = pop.zj + move[1];
                State newState = pop.swap(nzi, nzj);
                if (newState == null || been.contains(newState)) continue;
                been.add(newState);
                pq.add(newState);
            }
        }
        return -1;
    }

    private static class State implements Comparable<State> {
        int[][] a;
        int taken;
        int zi, zj;
        public State(int[][] a, int taken, int zi, int  zj) {
            this.a = new int[2][3];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) this.a[i][j] = a[i][j];
            }
            this.taken = taken;
            this.zi = zi;
            this.zj = zj;
        }

        public State swap(int i, int j) {
            if (i < 0 || i >= 2 || j < 0 || j >= 3) return null;
            State res = new State(this.a, this.taken + 1, i, j);
            int temp = res.a[i][j];
            res.a[i][j] = res.a[zi][zj];
            res.a[zi][zj] = temp;
            return res;
        }

        public int distance() {
            int res = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (a[i][j] == 0) continue;
                    int val = a[i][j] - 1;
                    int si = val / 3;
                    int sj = val % 3;
                    res += Math.abs(si - i) + Math.abs(sj - j);
                }
            }
            return res;
        }

        public boolean isGoal() {
            return distance() == 0;
        }

        @Override
        public boolean equals(Object obj) {
            State that = (State) obj;
            return Arrays.deepEquals(this.a, that.a);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(a);
        }

        @Override
        public int compareTo(State that) {
            return this.distance() + taken - that.distance() - that.taken;
        }
    }
   ｝
