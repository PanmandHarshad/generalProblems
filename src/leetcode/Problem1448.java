package leetcode;

public class Problem1448 {

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
        // Test Case 1: Basic Tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);
        System.out.println("Test Case 1: " + goodNodes(root1)); // Expected: 4

        // Test Case 2: All Nodes are Good
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        System.out.println("Test Case 2: " + goodNodes(root2)); // Expected: 7

        // Test Case 3: No Good Nodes Except Root
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(0);
        root3.left.right = new TreeNode(1);
        root3.right.left = new TreeNode(0);
        root3.right.right = new TreeNode(1);
        System.out.println("Test Case 3: " + goodNodes(root3)); // Expected: 1

        // Test Case 4: Only Left Subtree
        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(8);
        root4.left.left = new TreeNode(7);
        root4.left.left.left = new TreeNode(6);
        System.out.println("Test Case 4: " + goodNodes(root4)); // Expected: 1

        // Test Case 5: Only Right Subtree
        TreeNode root5 = new TreeNode(5);
        root5.right = new TreeNode(10);
        root5.right.right = new TreeNode(15);
        root5.right.right.right = new TreeNode(20);
        System.out.println("Test Case 5: " + goodNodes(root5)); // Expected: 4

        // Test Case 6: Random Values
        TreeNode root6 = new TreeNode(9);
        root6.left = new TreeNode(3);
        root6.right = new TreeNode(8);
        root6.left.left = new TreeNode(4);
        root6.left.right = new TreeNode(2);
        root6.right.left = new TreeNode(10);
        root6.right.right = new TreeNode(7);
        System.out.println("Test Case 6: " + goodNodes(root6)); // Expected: 4

        // Test Case 7: Single Node Tree
        TreeNode root7 = new TreeNode(7);
        System.out.println("Test Case 7: " + goodNodes(root7)); // Expected: 1

        // Test Case 8: Null Tree
        System.out.println("Test Case 8: " + goodNodes(null)); // Expected: 0
    }

    static public int goodNodes(TreeNode root) {
        return getGoodNodes(root, Integer.MIN_VALUE);
    }

    static private int getGoodNodes(TreeNode root, int maxSoFar) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val >= maxSoFar) {
            maxSoFar = root.val;
            count++;
        }

        return count + getGoodNodes(root.left, maxSoFar) + getGoodNodes(root.right, maxSoFar);
    }
}
