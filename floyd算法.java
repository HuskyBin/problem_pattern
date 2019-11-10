// https://zhuanlan.zhihu.com/p/33162490

// https://www.jianshu.com/p/160b4bea182c


// //解决任意两点最短距离的算法

// Floyd算法只能在不存在负权环的情况下使用，因为其并不能判断负权环，上面也说过，如果有负权环，那么最短路将无意义，因为我们可以不断走负权环，这样最短路径值便成为了负无穷。但是其可以处理带负权边但是无负权环的情况。以上就是求多源最短路的Floyd算法，基于动态规划，十分优雅。但是其复杂度确实是不敢恭维，因为要维护一个路径矩阵，因此空间复杂度达到了O(n^2)，时间复杂度达到了O(n^3)，只有在数据规模很小的时候，才适合使用这个算法，但是在实际的应用中，求单源最短路是最常见的，比如在路由算法中，某个节点仅需要知道从这个节点出发到达其他节点的最短路径，而不需要知道其他点的情况，因此在路由算法中最适合的应该是单源最短路算法，Dijkstra算法。
private void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                System.out.println(i + " " + j + ":" + a[i][j]);
            }
        }
    }





#pragma mark - - Bellman-Ford 算法
-(void)test3 {
    int k[9][3] = {{0,0,0},{1,2,2},{1,3,6},{1,4,4},{2,3,3},{3,1,7},{3,4,1},{4,1,5},{4,3,12}};
    int inf =9999; // 表示无穷大
    int n=4; // 顶点数
    int m=8; // 边数
    
    // u[i] v[i] 分别表示编号为i的边的顶点
    // v[i] 表示编号为i的边的权值
    int u[10];
    int v[10];
    int w[10];
    
    for (int i=1; i<=m; i++) {
        u[i] = k[i][0];
        v[i] = k[i][1];
        w[i] = k[i][2];
    }
    
    //dis[i] 表示顶点i到源点的最短距离
    int dis[10];
    
    for (int i=0; i<10; i++) {
        dis[i] =inf;
    }
    
    // 设置源点1
    dis[1] =0 ;
    
    // 核心代码
    for (int i=1; i<=n-1; i++) {
        int check=0;
        for (int j=1; j<=m; j++) {
            if (dis[v[j]] > dis[u[j]] + w[j]) {
                dis[v[j]] = dis[u[j]] + w[j];
                check =1;
            }
        }
        
        /*
         check=0 表示当前这轮对边的松弛操作 不能使dis[]发生变化,相等于没有扩充新的边，
         那么即使再次循环，也不会松弛成功。故可以提前退出循环
         */
        if (check==0) {
            break ;
        }
    }
    
    // 检查 是否有负权回路
    int flag=0;
    for (int i=1; i<=m; i++) {
        if (dis[v[i]] > dis[u[i]] + w[i]) {
            flag =1;
        }
    }
    
    if (flag==1) {
        printf("存在负权回路 \n");
    }else {
        
        // 打印 数据
        for (int i=1; i<=n; i++) {
            printf("%4d",dis[I]);
        }
        printf("\n");
    }
    
    
}
