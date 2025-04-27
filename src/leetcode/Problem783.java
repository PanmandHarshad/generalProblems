package leetcode;

public class Problem783 {

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
        // Build the tree:
        //      4
        //     / \
        //    2   6
        //   / \
        //  1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        int result = minDiffInBST(root);

        System.out.println("Minimum difference in BST: " + result);
    }

    static Integer res = Integer.MAX_VALUE;
    static Integer prev = null;

    static public int minDiffInBST(TreeNode root) {
        if (root.left != null)
            minDiffInBST(root.left);

        if (prev != null)
            res = Math.min(res, root.val - prev);

        prev = root.val;

        if (root.right != null)
            minDiffInBST(root.right);
        return res;
    }
}
