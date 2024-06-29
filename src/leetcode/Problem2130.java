package leetcode;

public class Problem2130 {
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
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        System.out.println(pairSum(head));
    }

    static public int pairSum(ListNode head) {

        ListNode cur = head;
        ListNode secondPart = getSecondPart(head);
        ListNode reversedSecondPart = reverseList(secondPart);

        int maxValue = Integer.MIN_VALUE;

        while (reversedSecondPart != null) {
            int twinSum = cur.val + reversedSecondPart.val;
            if (twinSum > maxValue) {
                maxValue = twinSum;
            }
            cur = cur.next;
            reversedSecondPart = reversedSecondPart.next;
        }
        return maxValue;
    }

    static private ListNode getSecondPart(ListNode head) {
        ListNode currentNode = head;
        ListNode nextNode = head;

        while (nextNode != null && nextNode.next != null) {
            currentNode = currentNode.next;
            nextNode = nextNode.next.next;
        }
        return currentNode;
    }

    static private ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }

        return prev;
    }
}
