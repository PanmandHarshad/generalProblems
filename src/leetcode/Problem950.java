package leetcode;

import java.util.*;

public class Problem950 {
    public static void main(String[] args) {
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        int[] ints = deckRevealedIncreasing(deck);
        for (int num : ints) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static public int[] deckRevealedIncreasing(int[] deck) {
        int[] result = new int[deck.length];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < deck.length; i++) {
            deque.add(i);
        }

        //Arrays.sort(deck);
        mergeSort(deck);

        for (int num : deck) {
            if (!deque.isEmpty()) {
                result[deque.removeFirst()] = num;
                if (!deque.isEmpty())
                    deque.addLast(deque.removeFirst());
            }
        }
        return result;
    }

    static private void mergeSort(int[] arr) {
        divide(arr, 0, arr.length - 1);
    }

    static private void divide(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;

        divide(arr, start, mid);
        divide(arr, mid + 1, end);
        conquer(arr, start, mid, end);
    }

    static private void conquer(int[] arr, int start, int mid, int end) {
        int[] merged = new int[end - start + 1];
        int index1 = start;
        int index2 = mid + 1;
        int x = 0;

        while (index1 <= mid && index2 <= end) {
            if (arr[index1] <= arr[index2]) {
                merged[x++] = arr[index1++];
            } else {
                merged[x++] = arr[index2++];
            }
        }


        while (index1 <= mid) {
            merged[x++] = arr[index1++];
        }

        while (index2 <= end) {
            merged[x++] = arr[index2++];
        }

        for (int i = 0, j = start; i < merged.length; i++, j++) {
            arr[j] = merged[i];
        }
    }
}
