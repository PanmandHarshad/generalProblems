package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem437 {

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
        // Test Case 1: Example from Leetcode
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(11);
        root1.left.left.left = new TreeNode(3);
        root1.left.left.right = new TreeNode(-2);
        root1.left.right.right = new TreeNode(1);

        System.out.println("Test 1: " + pathSum(root1, 8)); // Expected: 3

        // Test Case 2: Tree with negative values
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(-2);
        root2.right = new TreeNode(-3);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(-2);
        root2.left.left.left = new TreeNode(-1);

        System.out.println("Test 2: " + pathSum(root2, -1)); // Expected: 4

        // Test Case 3: Tree with only one node
        TreeNode root3 = new TreeNode(5);
        System.out.println("Test 3: " + pathSum(root3, 5)); // Expected: 1

        // Test Case 4: Tree with no path equal to target sum
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        System.out.println("Test 4: " + pathSum(root4, 5)); // Expected: 0

        // Test Case 5: Empty tree
        TreeNode root5 = null;
        System.out.println("Test 5: " + pathSum(root5, 0)); // Expected: 0
    }

    // Space complexity : O(n2)
    static public int pathSum(TreeNode root, int targetSum) {
        return pathSumHelper(root, (long) targetSum);
    }

    static private int pathSumHelper(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        return findPathCount(root, targetSum)
                + pathSumHelper(root.left, targetSum)
                + pathSumHelper(root.right, targetSum);
    }

    static private int findPathCount(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        if (root.val == targetSum) {
            count = 1;
        }

        count += findPathCount(root.left, targetSum - root.val);
        count += findPathCount(root.right, targetSum - root.val);
        return count;
    }

    // Most efficient solution O(n), but difficult to understand
    static public int pathSum1(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1); // Base case: a sum of 0 has occurred once
        return dfs(root, 0L, targetSum, prefixSumMap);
    }

    static private int dfs(TreeNode node, long currSum, int targetSum, Map<Long, Integer> prefixSumMap) {
        if (node == null) {
            return 0;
        }

        currSum += node.val;
        int count = prefixSumMap.getOrDefault(currSum - targetSum, 0);

        // Update prefixSumMap with the current sum
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        // Recurse left and right
        count += dfs(node.left, currSum, targetSum, prefixSumMap);
        count += dfs(node.right, currSum, targetSum, prefixSumMap);

        // Backtrack: remove the current sum from the map after visiting children
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return count;
    }
}
