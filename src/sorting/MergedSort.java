package sorting;

public class MergedSort {
    public static void main(String[] args) {
//        int[] arr = {6, 3, 9, 5, 2, 8};
        int[] arr = {5, 3, 7, 10, 6};
        divide(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void divide(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int mid = start + (end - start) / 2;
        divide(arr, start, mid);
        divide(arr, mid + 1, end);
        conquer(arr, start, mid, end);
    }

    private static void conquer(int[] arr, int start, int mid, int end) {

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

        for (int j : merged) {
            arr[start++] = j;
        }
    }
}
