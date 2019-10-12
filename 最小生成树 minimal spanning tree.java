// Kruskal
class Solution {
    public int minimumCost(int N, int[][] connections) {
        UnionFind unionFind = new UnionFind(N + 1);
        
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        
        int sum = 0;
        int count = 1;
        for (int[] connection : connections) {
            int first = unionFind.find(connection[0]);
            int second = unionFind.find(connection[1]);
            if (first == second) {
                continue;
            }
            sum += connection[2];
            unionFind.union(first, second);
            count++;
            if (count == N) {
                return sum;
            }
        }
        return -1;
    }
    
    private class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        private int find(int p) {
            if (parent[p] == p) {
                return p;
            } else {
                parent[p] = find(parent[p]);
                return parent[p];
            }
        }
        
        private void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            
            if (rootq == rootp) {
                return;
            }
            if (rank[rootp] > rank[rootq]) {
                parent[rootq] = rootp; 
            } else {
                parent[rootp] = parent[rootq];
                if (rank[rootp] == rank[rootq]) {
                    rank[rootp]++;
                }
            }
        }
    }
}



// Prim
