import java.util.*;

/*
Problem Statement

Bob loves to play with the pipelines, which are closed from one end. Bob gives you the pipeline with some queries Q. Each query contains:

• Two integers, X and N

• If X = 1 insert number N into the pipeline from the top

• If X = 2 remove an integer that is occurring the most (i.e., has the largest frequency in the pipeline) and remove its occurrence closest to the open end of the pipeline. If the frequency of more than one number is the same and largest, you should remove the number closest to the open end of the pipeline. In this case, N = - 1

Task
Return the list of elements removed during the X = 2 query.

Input Format
• The first line of the input format contains an integer Q denoting the number of queries.
• The following line contains an integer 2, denoting that each query will have 2 values.
• The following Q lines contain two space-separated integers, X and N.


Constraints
• 1 <= Q <= 10 ^ 4
• 1 <= X <= 2
• 0 <= N <= 10 ^ 9 if X = 1
N = - 1 if X = 2

Output Format

• Print the list of elements removed during the x = 2 query.

Evaluation Parameters
• Sample Input
6
2
1 2
1 4
1 3
1 2
2 -1
2 -1

• Sample Output
2
3

• Explanation

• After the first operation, the pipeline will be 2.

• After the second operation, the pipeline will be 2, 4.

• After the third operation, the pipeline will be 2, 4, and 3.

• After the fourth operation, the pipeline will be 2, 4, 3, and 2.

• After the fifth operation, 2 will be removed as 2 occurs most times, and the pipeline will be 2, 4, and 3.

• After the sixth operation, 3 will be removed as 2, 4, and 3 occur most times, but 3 is more close to the open side of the pipeline. The pipeline will be: 2, 4.

• Therefore, you print [2, 3] elements removed during the X = 2 query.


Solve this problem using Java 8.

Method template is as below:
public static List<Integer> elements (List<List<Integer>> queries){
// Write your code here
}
 */
public class Pipeline {
    public static List<Integer> elements(List<List<Integer>> queries) {
        // TreeMap to maintain frequency of each element
        TreeMap<Integer, Integer> frequencyMap = new TreeMap<>();
        // Deque to represent the pipeline
        Deque<Integer> pipeline = new LinkedList<>();
        // List to store removed elements during X=2 queries
        List<Integer> removedElements = new ArrayList<>();

        // Process each query
        for (List<Integer> query : queries) {
            int X = query.get(0);
            int N = query.get(1);

            if (X == 1) {
                // Insert operation
                pipeline.addFirst(N);
                frequencyMap.put(N, frequencyMap.getOrDefault(N, 0) + 1);
            } else if (X == 2) {
                // Remove operation
                if (!pipeline.isEmpty()) {
                    int removed = removeElement(frequencyMap, pipeline);
                    removedElements.add(removed);
                }
            }
        }

        return removedElements;
    }

    private static int removeElement(TreeMap<Integer, Integer> frequencyMap, Deque<Integer> pipeline) {
        // Find the maximum frequency
        int maxFrequency = Collections.max(frequencyMap.values());
        int removed = -1;
        Iterator<Integer> iterator = pipeline.iterator();

        // Iterate through the pipeline to find the element with the maximum frequency
        while (iterator.hasNext()) {
            int num = iterator.next();
            if (frequencyMap.get(num) == maxFrequency) {
                removed = num;
                iterator.remove(); // Remove the element closest to the open end
                break;
            }
        }

        // Update the frequency map
        int frequency = frequencyMap.get(removed);
        if (frequency == 1) {
            frequencyMap.remove(removed);
        } else {
            frequencyMap.put(removed, frequency - 1);
        }

        return removed;
    }

    public static void main(String[] args) {
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(1, 2));
        queries.add(Arrays.asList(1, 4));
        queries.add(Arrays.asList(1, 3));
        queries.add(Arrays.asList(1, 2));
        queries.add(Arrays.asList(2, -1));
        queries.add(Arrays.asList(2, -1));

        List<Integer> removedElements = elements(queries);
        System.out.println(removedElements); // Output: [2, 3]
    }
}
