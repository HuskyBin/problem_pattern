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


//872. Leaf-Similar Trees
class Solution {
     public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); s2.push(root2);
        while (!s1.empty() && !s2.empty())
            if (dfs(s1) != dfs(s2)) return false;
        return s1.empty() && s2.empty();
    }

    public int dfs(Stack<TreeNode> s) {
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
            if (node.left == null && node.right == null) return node.val;
        }
        return -1;
    }
}
