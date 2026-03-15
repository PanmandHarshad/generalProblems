package leetcode;

import java.util.*;

public class Problem380 {

    static class RandomizedSet {

        private final List<Integer> values;

        private final Map<Integer, Integer> valToIndex;

        private final Random rand;

        public RandomizedSet() {
            values = new ArrayList<>();
            valToIndex = new HashMap<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (valToIndex.get(val) != null) {
                return false;
            }

            values.add(val);
            valToIndex.put(val, values.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            Integer index = valToIndex.get(val);
            if (index == null) {
                return false;
            }

            Integer lastElement = values.get(values.size() - 1);
            values.set(index, lastElement);
            valToIndex.put(lastElement, index);
            values.remove(values.size() - 1);
            valToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            return values.get(rand.nextInt(values.size()));
        }
    }
}
