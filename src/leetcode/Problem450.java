package leetcode;

public class Problem450 {

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
//                        50
//                     /      \
//                   30        70
//                 /   \       /   \
//               20    40     60    80
//              / \    / \    / \   / \
//            10  25  35 45  55 65 75 85

        TreeNode root = new TreeNode(50);

        root.left = new TreeNode(30);
        root.right = new TreeNode(70);

        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);

        root.left.left.left = new TreeNode(10);
        root.left.left.right = new TreeNode(25);
        root.left.right.left = new TreeNode(35);
        root.left.right.right = new TreeNode(45);

        root.right.left.left = new TreeNode(55);
        root.right.left.right = new TreeNode(65);
        root.right.right.left = new TreeNode(75);
        root.right.right.right = new TreeNode(85);

        TreeNode updatedRoot = deleteNode(root, 30);

        // Print inorder traversal of an updated tree
        System.out.print("Inorder after deletion: ");
        inorderPrint(updatedRoot);
        System.out.println();
    }

    public static void inorderPrint(TreeNode node) {
        if (node == null)
            return;

        inorderPrint(node.left);
        System.out.print(node.val + " ");
        inorderPrint(node.right);
    }

    static public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        }

        return root;
    }

    static private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
