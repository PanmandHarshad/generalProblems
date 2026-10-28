package leetcode;

import java.util.*;

public class Problem71 {
    static public String simplifyPath(String path) {
        String[] splittedString = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        for (String str : splittedString) {
            if (str.isEmpty() || str.equals(".")) {
                continue;
            }

            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(str);
            }
        }

        return "/" + String.join("/", stack);
    }

    public static void main(String[] args) {
        String[] testCases = {
                "/home/",
                "/home//foo/",
                "/home/user/../docs",
                "/a/./b/../../c/",
                "/../",
                "/..."
        };

        for (String path : testCases) {
            System.out.println("Input:    " + path
                    + "\nOutput:   " + simplifyPath(path)
                    + "\n"
            );
        }
    }
}
