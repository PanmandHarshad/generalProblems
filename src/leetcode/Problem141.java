package leetcode;

public class Problem141 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // Test 1: Empty list
        ListNode head1 = null;
        System.out.println("Test 1: " + hasCycle(head1));
        // Expected: false


        // Test 2: Single node, no cycle
        ListNode head2 = new ListNode(1);
        System.out.println("Test 2: " + hasCycle(head2));
        // Expected: false


        // Test 3: Single node, self-cycle
        ListNode head3 = new ListNode(1);
        head3.next = head3;

        System.out.println("Test 3: " + hasCycle(head3));
        // Expected: true


        // Test 4: Two nodes, no cycle
        ListNode head4 = new ListNode(1);
        head4.next = new ListNode(2);

        System.out.println("Test 4: " + hasCycle(head4));
        // Expected: false


        // Test 5: Two nodes, cycle
        ListNode head5 = new ListNode(1);
        ListNode node5_2 = new ListNode(2);

        head5.next = node5_2;
        node5_2.next = head5;

        System.out.println("Test 5: " + hasCycle(head5));
        // Expected: true


        // Test 6: Normal list, no cycle
        ListNode head6 = new ListNode(1);
        head6.next = new ListNode(2);
        head6.next.next = new ListNode(3);
        head6.next.next.next = new ListNode(4);

        System.out.println("Test 6: " + hasCycle(head6));
        // Expected: false


        // Test 7: Cycle starts in the middle
        ListNode head7 = new ListNode(1);
        ListNode node7_2 = new ListNode(2);
        ListNode node7_3 = new ListNode(3);
        ListNode node7_4 = new ListNode(4);

        head7.next = node7_2;
        node7_2.next = node7_3;
        node7_3.next = node7_4;
        node7_4.next = node7_2;

        System.out.println("Test 7: " + hasCycle(head7));
        // Expected: true
    }
}