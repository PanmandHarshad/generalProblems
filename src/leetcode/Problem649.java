package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem649 {
    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RD"));
        System.out.println(predictPartyVictory("RDD"));
    }

    static public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        int length = senate.length();

        for (int i = 0; i < length; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();

            if (rIndex < dIndex) {
                radiant.offer(rIndex + length);
            } else {
                dire.offer(dIndex + length);
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
