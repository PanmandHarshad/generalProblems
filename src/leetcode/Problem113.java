package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem113 {

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

        // Test Case 1: Normal binary tree with multiple valid paths
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);

        int targetSum1 = 22;
        System.out.println("Test Case 1:");
        System.out.println(Problem113.pathSum(root1, targetSum1));  // Expected: [[5,4,11,2],[5,8,4,5]]

        // Test Case 2: Tree with single node (matching sum)
        TreeNode root2 = new TreeNode(5);
        int targetSum2 = 5;
        System.out.println("Test Case 2:");
        System.out.println(Problem113.pathSum(root2, targetSum2));  // Expected: [[5]]

        // Test Case 3: Tree with single node (not matching sum)
        TreeNode root3 = new TreeNode(1);
        int targetSum3 = 5;
        System.out.println("Test Case 3:");
        System.out.println(Problem113.pathSum(root3, targetSum3));  // Expected: []

        // Test Case 4: Null tree
        TreeNode root4 = null;
        int targetSum4 = 0;
        System.out.println("Test Case 4:");
        System.out.println(Problem113.pathSum(root4, targetSum4));  // Expected: []

        // Test Case 5: Tree with negative numbers
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(-2);
        root5.right = new TreeNode(-3);
        root5.left.left = new TreeNode(1);
        root5.left.right = new TreeNode(3);
        root5.right.left = new TreeNode(-2);
        root5.left.left.left = new TreeNode(-1);

        int targetSum5 = -1;
        System.out.println("Test Case 5:");
        System.out.println(Problem113.pathSum(root5, targetSum5));  // Expected: [[1,-2,1,-1]]
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPaths(root, targetSum, new Stack<>(), result);
        return result;
    }

    private static void findPaths(TreeNode root, int targetSum, Stack<Integer> stack, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        stack.add(root.val);

        if (root.left == null && root.right == null && root.val == targetSum) {
            result.add(new ArrayList<>(stack));
        } else {
            findPaths(root.left, targetSum - root.val, stack, result);
            findPaths(root.right, targetSum - root.val, stack, result);
        }
        stack.pop(); // Backtrack
    }
}
