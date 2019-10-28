// level traverse

// leetcode 515. Find Largest Value in Each Tree Row
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode topNode = queue.poll();
                if (max < topNode.val) {
                    max = topNode.val;
                } 
                if (topNode.left != null) {
                    queue.add(topNode.left);
                }
                if (topNode.right != null) {
                    queue.add(topNode.right);
                }
            }
            resultList.add(max);
        }
        return resultList;
    }
}
