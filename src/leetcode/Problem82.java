package leetcode;

public class Problem82 {
    static class ListNode {
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
        // Create a sample linked list: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        ListNode result = deleteDuplicates(head);

        // Output the modified list after removing duplicates: 1 -> 2 -> 5
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }

            if (prev.next == curr) {
                prev = prev.next;
            } else {
                prev.next = curr.next;
            }

            curr = curr.next;
        }

        return dummy.next;
    }
}
