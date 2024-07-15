package leetcode;

public class Problem19 {

    static public class ListNode {
        int val;
        ListNode next;


        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = createList(values);
        int n = 2;

        System.out.print("Original list: ");
        printList(head);

        ListNode newHead = removeNthFromEnd(head, n);

        System.out.print("Modified list: ");
        printList(newHead);
    }

    static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    static public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return null;
        }

        int totalNodes = linkedListLength(head);
        int traversalNodes = totalNodes - n;

        if (traversalNodes < 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        // 1 -> 2 -> 3 -> 4 -> 5
        while (traversalNodes != 0) {
            current = current.next;
            traversalNodes--;
        }

        current.next = current.next.next;

        return dummy.next;
    }

    static private int linkedListLength(ListNode head) {
        ListNode current = head;
        int count = 0;

        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
