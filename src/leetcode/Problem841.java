package leetcode;

import java.util.*;

public class Problem841 {

    public static void main(String[] args) {

        // Test Case 1
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(3));
        rooms1.add(new ArrayList<>());
        System.out.println("Test Case 1: " + canVisitAllRooms(rooms1));  // Expected: true

        // Test Case 2
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));
        System.out.println("Test Case 2: " + canVisitAllRooms(rooms2));  // Expected: false

        // Test Case 3
        List<List<Integer>> rooms3 = new ArrayList<>();
        rooms3.add(Arrays.asList(1, 2, 3));
        rooms3.add(new ArrayList<>());
        rooms3.add(new ArrayList<>());
        rooms3.add(new ArrayList<>());
        System.out.println("Test Case 3: " + canVisitAllRooms(rooms3));  // Expected: true

        // Test Case 4
        List<List<Integer>> rooms4 = new ArrayList<>();
        rooms4.add(new ArrayList<>());
        System.out.println("Test Case 4: " + canVisitAllRooms(rooms4));  // Expected: true

        // Test Case 5
        List<List<Integer>> rooms5 = new ArrayList<>();
        rooms5.add(Arrays.asList(1));
        rooms5.add(Arrays.asList(2));
        rooms5.add(Arrays.asList(0));
        System.out.println("Test Case 5: " + canVisitAllRooms(rooms5));  // Expected: true

        // Test case 6
        List<List<Integer>> rooms6 = new ArrayList<>();
        rooms6.add(Arrays.asList(2));
        rooms6.add(new ArrayList<>());
        rooms6.add(Arrays.asList(1));
        System.out.println("Test Case 6: " + canVisitAllRooms(rooms6));  // Expected: true

    }

    static public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(0, rooms, visited);
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }
        return true;
    }

    // Most efficient solution
    static private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true;
        for (int key : rooms.get(room)) {
            if (!visited[key]) {
                dfs(key, rooms, visited);
            }
        }
    }

    static private boolean bfs(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[rooms.size()];
        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int room = queue.poll();
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }


}
