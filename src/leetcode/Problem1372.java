package leetcode;

public class Problem1372 {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
         /*
            Tree structure:
                1
               / \
              2   3
               \   \
                4   5
               /
              6

         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        int result = longestZigZag(root);
        System.out.println("Longest ZigZag Path Length: " + result);  // Output: 3
    }

    private static int maxZigZag = 0;

    static public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);
        return maxZigZag;
    }

    // Most efficient solution
    static private void dfs(TreeNode node, boolean isLeft, int length) {
        if (node == null)
            return;

        maxZigZag = Math.max(maxZigZag, length);

        if (isLeft) {
            dfs(node.right, false, length + 1); // ZigZag continues
            dfs(node.left, true, 1);            // Restart from left
        } else {
            dfs(node.left, true, length + 1);   // ZigZag continues
            dfs(node.right, false, 1);          // Restart from right
        }
    }

}