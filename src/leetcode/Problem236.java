package leetcode;

public class Problem236 {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Build the binary tree:
        /*
                  3
                /   \
               5     1
              / \   / \
             6  2  0  8
               / \
              7   4
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Test 1: LCA of 5 and 1 should be 3
        TreeNode lca1 = lowestCommonAncestor(root, root.left, root.right);
        System.out.println("LCA of 5 and 1: " + (lca1 != null ? lca1.val : "null"));  // Output: 3

        // Test 2: LCA of 5 and 4 should be 5
        TreeNode lca2 = lowestCommonAncestor(root, root.left, root.left.right.right);
        System.out.println("LCA of 5 and 4: " + (lca2 != null ? lca2.val : "null"));  // Output: 5

        // Test 3: LCA of 7 and 8 should be 3
        TreeNode lca3 = lowestCommonAncestor(root, root.left.right.left, root.right.right);
        System.out.println("LCA of 7 and 8: " + (lca3 != null ? lca3.val : "null"));  // Output: 3
    }


    /**
     * 1. If root is null, return null.
     * 2. If root is p or q, return root.
     * 3. Recursively search in left and right subtrees.
     * 4. If both left and right are non-null → that means p and q are found on different sides → so root is the LCA.
     * 5. Otherwise, return whichever side is non-null.
     */
    // Most efficient solution
    static public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return (left != null) ? left : right;
    }
}
