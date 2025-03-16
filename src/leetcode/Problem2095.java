package leetcode;

public class Problem2095 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Test cases with multiple inputs
        int[][] testCases = {
                {1, 3, 4, 7, 1, 2, 6},  // Odd length
                {1, 2, 3, 4},           // Even length
                {1},                    // Single element (edge case)
                {1, 2},                 // Two elements
                {2, 4, 6, 8, 10, 12}    // Another even length case
        };

        for (int[] testCase : testCases) {
            ListNode head = createList(testCase);
            System.out.print("Original List: ");
            printList(head);

            head = deleteMiddle(head);
            System.out.print("After Deletion: ");
            printList(head);

            System.out.println();
        }
    }

    static public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;

        return head;
    }
}
