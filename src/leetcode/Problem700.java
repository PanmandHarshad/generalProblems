package leetcode;

public class Problem700 {

    public static class TreeNode {
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
        // Construct the BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Value to search for
        int target = 2;

        // Create a Solution object and call the searchBST method
        Problem700 solution = new Problem700();
        TreeNode result = solution.searchBST(root, target);

        // Print the result
        if (result != null) {
            System.out.println("Found node with value: " + result.val);
        } else {
            System.out.println("Value not found in the BST.");
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST1(root.left, val);
        } else {
            return searchBST1(root.right, val);
        }
    }


}
