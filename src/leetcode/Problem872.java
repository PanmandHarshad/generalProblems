package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem872 {
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
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(9);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(7);
        root2.left.right.right = new TreeNode(4);

        System.out.println("Test Case 1: " + leafSimilar(root1, root2)); // Expected: true

        // ✅ Test Case 2: Different leaf sequences
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(2);

        System.out.println("Test Case 2: " + leafSimilar(root3, root4)); // Expected: false

        // ✅ Test Case 3: One tree is empty
        TreeNode root5 = null; // Empty tree
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);

        System.out.println("Test Case 3: " + leafSimilar(root5, root6)); // Expected: false

        // ✅ Test Case 4: Trees with only one node each (same value)
        TreeNode root7 = new TreeNode(7);
        TreeNode root8 = new TreeNode(7);

        System.out.println("Test Case 4: " + leafSimilar(root7, root8)); // Expected: true

        // ✅ Test Case 5: Trees with different structures but same leaf sequence
        TreeNode root9 = new TreeNode(4);
        root9.left = new TreeNode(3);
        root9.right = new TreeNode(7);
        root9.right.left = new TreeNode(6);
        root9.right.right = new TreeNode(2);

        TreeNode root10 = new TreeNode(5);
        root10.left = new TreeNode(6);
        root10.right = new TreeNode(2);

        System.out.println("Test Case 5: " + leafSimilar(root9, root10)); // Expected: true
    }

    static public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        getLeafNodes(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        getLeafNodes(root2, list2);
        return list1.equals(list2);
    }

    static private void getLeafNodes(TreeNode root, List<Integer> leafValues) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                leafValues.add(root.val);
            }
            getLeafNodes(root.left, leafValues);
            getLeafNodes(root.right, leafValues);
        }
    }
}
