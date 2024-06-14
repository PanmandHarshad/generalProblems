package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem36 {

    public static void main(String[] args) {

//        System.out.println(isValidSudoku(new char[][]
//                {
//                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//                }));

        System.out.println(isValidSudoku(new char[][]
                {
                        {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                }));

        System.out.println(isValidSudoku(new char[][]
                {
                        {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                        {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                        {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                        {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                        {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                        {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                        {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
                }));
    }

    static public boolean isValidSudoku(char[][] board) {
//        if (board == null || board.length < 8 || board[0].length < 8) {
//            return false;
//        }
//
//        for (int n = 0; n < 9; n++) {
//            Set<Character> rowSet = new HashSet<>();
//            for (int m = 0; m < 9; m++) {
//                char value = board[n][m];
//                if (value != '.' && !rowSet.add(value)) {
//                    System.out.println("Row evaluation");
//                    System.out.println("board[" + n + "][" + m + "]");
//                    return false;
//                }
//            }
//
//            Set<Character> columnSet = new HashSet<>();
//            for (int p = 0; p < 9; p++) {
//                char value = board[p][n];
//                if (value != '.' && !columnSet.add(value)) {
//                    System.out.println("Column evaluation");
//                    System.out.println("board[" + p + "][" + n + "]");
//                    return false;
//                }
//            }
//
//            int startIndex = n / 3 * 3;
//            Set<Character> gridSet = new HashSet<>();
//            for (int i = startIndex; i < startIndex + 3; i++) {
//                for (int j = startIndex; j < startIndex + 3; j++) {
//                    char value = board[i][j];
//                    if (value != '.' && !gridSet.add(value)) {
//                        System.out.println("Grid evaluation");
//                        System.out.println("board[" + i + "][" + j + "]");
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;

        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] squares = new HashSet[9];

        // Initialize sets
        for (int i = 0; i < 9; i++) {
            cols[i] = new HashSet<>();
            rows[i] = new HashSet<>();
            squares[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char value = board[r][c];
                if (value == '.') {
                    continue;
                }

                int bigMatrix = (r / 3) * 3 + c / 3;
                if (rows[r].contains(value) || cols[c].contains(value) || squares[bigMatrix].contains(value)) {
                    return false;
                }

                rows[r].add(value);
                cols[c].add(value);
                squares[bigMatrix].add(value);
            }
        }

        return true;
    }


}
