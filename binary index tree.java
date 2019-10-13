// 参考http://www.cnblogs.com/grandyang/p/4985506.html and http://www.cnblogs.com/grandyang/p/5300458.html
class NumMatrix {
    
    private int[][] nums;
    private int[][] tree;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        tree = new int[matrix.length + 1][matrix[0].length + 1];
        nums = new int[matrix.length + 1][matrix[0].length + 1];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                update(i + 1, j + 1, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        for (int i = row; i < tree.length; i += i&-i) {
            for (int j = col; j < tree[0].length; j += j&-j) {
                tree[i][j] += diff;
            }
        }
        nums[row][col] = val;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2 + 1, col2 + 1) - getSum(row1, col2 + 1) - getSum(row2 + 1, col1) + getSum(row1, col1);
    }
    
    private int getSum(int row, int column) {
        int result = 0;
        for (int i = row; i > 0; i -= i&-i) {
            for (int j = column; j > 0; j -= j&-j) {
                result += tree[i][j];
            }
        }
        return result;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
