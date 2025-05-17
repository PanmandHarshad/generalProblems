package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1161 {
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
        // Construct the binary tree:
        //         1
        //       /   \
        //      7     0
        //     / \
        //    7  -8

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);

        int result = maxLevelSum(root);
        System.out.println("Maximum level sum is at level: " + result);
    }

    static public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int maxLevel = 1;
        int maxSum = root.val;
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            int currentSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                currentSum += currentNode.val;

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;
    }
}
