package leetcode;

public class Problem430 {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    /**
     * <pre>
     * Example structure of a multilevel doubly linked list:
     *
     * Level 1: 1 - 2 - 3 - 4 - 5 - 6 - NULL
     *                  |
     * Level 2:         7 - 8 - 9 - 10 - NULL
     *                      |
     * Level 3:             11 - 12 - NULL
     *
     * The list above can be visualized as:
     *
     *  1 - 2 - 3 - 4 - 5 - 6 - NULL
     *          |
     *          7 - 8 - 9 - 10 - NULL
     *              |
     *              11 - 12 - NULL
     * </pre>
     */
    static public Node flatten(Node head) {
        if (head == null)
            return null;

        Node current = head;

        while (current != null) {
            if (current.child != null) {
                Node child = current.child;
                Node next = current.next;

                current.next = child;
                child.prev = current;
                current.child = null;

                while (child.next != null) {
                    child = child.next;
                }

                child.next = next;

                if (next != null) {
                    next.prev = child;
                }
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {

    }


}
