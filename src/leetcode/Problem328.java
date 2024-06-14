package leetcode;

public class Problem328 {
    static public class ListNode {
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
        Problem328 solution = new Problem328();

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = solution.oddEvenList(head);

        ListNode current = head;
        print(current);
        // Output: 1 3 5 2 4
    }

    private static void print(ListNode current) {
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        // Iterate through the list, separating odd and even nodes
        while (even != null && even.next != null) {
            odd.next = even.next; // Link odd nodes together
            odd = odd.next; // Move odd pointer forward
            even.next = odd.next; // Link even nodes together
            even = even.next; // Move even pointer forward
        }

        odd.next = evenHead;
        return head;
    }


}
