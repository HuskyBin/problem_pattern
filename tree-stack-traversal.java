// pre-order
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
       List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (!stack.isEmpty() || pNode != null) {
            if (pNode != null) {
                resultList.add(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode topNode = stack.pop();
                pNode = topNode.right;
            }
        }
        return resultList; 
    }
}

// inOrder
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (!stack.isEmpty() || pNode != null) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode topNode = stack.pop();
                resultList.add(topNode.val);
                pNode = topNode.right;
            }
        }
        return resultList;
    }
}

//postorderTraversal
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (!stack.isEmpty() || pNode != null) {
            if (pNode != null) {
                resultList.add(0, pNode.val);
                stack.push(pNode);
                pNode = pNode.right;
            } else {
                TreeNode topNode = stack.pop();
                pNode = topNode.left;
            }
        }
        return resultList; 
    }
}


