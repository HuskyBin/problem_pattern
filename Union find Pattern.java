private class UnionFind {
        private int[] parent;
        //树的高度
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        //递归path compresion 更好
        public int find(int p) {
           if (parent[p] == p) {
               return p;
           } else {
               parent[p] = find(parent[p]);
               return parent[p];
           }
        }
        
        public int find(int i) { // path compression
            if (parent[i] != i) {
                parent[i] = find(parent[i])
            };  
            return parent[i];
        }
        
        // 迭代的path compression
        public int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        
        public void uion(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp == rootq) {
                return;
            }
            if (rank[rootp] > rank[rootq]) {
                parent[rootq] = rootp;
            } else {
                parent[rootp] = parent[rootq];
                // 只有当相等时候才高度加1
                if (rank[rootp] == rank[rootq]) {
                    rank[rootp]++;
                }
            }
        }
    }


