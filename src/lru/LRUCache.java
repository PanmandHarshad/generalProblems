package lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Node head;
    private final Node tail;
    private final Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = this.head;
        cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1;
        }
    }

    private void insert(Node node) {
        cache.put(node.key, node);

        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;
    }

    private void remove(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        if (cache.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public void printCacheContents() {
        Node current = head.next;
        System.out.print("LRU Cache Contents: ");
        while (current != tail) {
            System.out.print("(" + current.key + ":" + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.printCacheContents(); // Output: LRU Cache Contents: (2:2) (1:1)

        System.out.println(cache.get(1)); // Output: 1
        cache.printCacheContents(); // Output: LRU Cache Contents: (1:1) (2:2)

        cache.put(3, 3);
        cache.printCacheContents(); // Output: LRU Cache Contents: (3:3) (1:1)

        System.out.println(cache.get(2)); // Output: -1 (key not found)
        cache.printCacheContents(); // Output: LRU Cache Contents: (3:3) (1:1)
    }

}

