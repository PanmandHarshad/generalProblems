package leetcode;

public class Problem112 {
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
        // Test Case 1: Path sum exists
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        System.out.println("Test Case 1: " + hasPathSum(root1, 22)); // Expected: true

        // Test Case 2: Path sum does not exist
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println("Test Case 2: " + hasPathSum(root2, 5)); // Expected: false

        // Test Case 3: Single node equals target sum
        TreeNode root3 = new TreeNode(7);
        System.out.println("Test Case 3: " + hasPathSum(root3, 7)); // Expected: true

        // Test Case 4: Tree with negative values
        TreeNode root4 = new TreeNode(-2);
        root4.right = new TreeNode(-3);
        System.out.println("Test Case 4: " + hasPathSum(root4, -5)); // Expected: true

        // Test Case 5: Empty tree
        TreeNode root5 = null;
        System.out.println("Test Case 5: " + hasPathSum(root5, 0)); // Expected: false

        // Test Case 6: Multiple valid paths but only one matches
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.left.left = new TreeNode(1);
        root6.right.right = new TreeNode(1);
        System.out.println("Test Case 6: " + hasPathSum(root6, 4)); // Expected: true
    }

    // Simple solution
    static public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return (root.val - targetSum) == 0;
        } else {
            if (hasPathSum(root.left, targetSum - root.val)) {
                return true;
            } else if (hasPathSum(root.right, targetSum - root.val)) {
                return true;
            } else {
                return false;
            }
        }
    }

    // Well refactored solution
    static public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

}
