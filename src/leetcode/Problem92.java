package leetcode;

public class Problem92 {

    static private class ListNode {
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

    public static void main(String[] args) {
        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Specify m and n
        int m = 2;
        int n = 4;

        // Call your solution method (replace 'reverseBetween' with your actual method name)
        ListNode newHead = reverseBetween(head, m, n);

        // Print the modified list
        printList(newHead);
    }

    static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    static public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode reverseStart = prev.next;
        ListNode reverseEnd = reverseStart;
        for (int i = left; i < right; i++) {
            reverseEnd = reverseEnd.next;
        }

        ListNode nextNode = reverseEnd.next;
        reverseEnd.next = null;

        ListNode newHead = reverseList(reverseStart);

        prev.next = newHead;

        while (newHead.next != null) {
            newHead = newHead.next;
        }
        newHead.next = nextNode;

        return dummy.next;
    }

    static private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
