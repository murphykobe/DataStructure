/**
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        helper2(res, board, 0);       
        
        return res;
    }
    
	/*Helper method with boolean return*/
    // check each col 
    private boolean helper(List<List<String>> res, char[][] board, int col) {
         if (col == board.length) {
             List<String> list = new ArrayList();
             for (int j = 0; j < board.length; j++) {
                 list.add(new String(board[j]));
			 }
             res.add(list);
             return true;
         }
        
         boolean flag = false;
         // for each col, check every possible row
         for (int i = 0; i < board.length; i++) {
             if (isSafe(board, i, col)) {
                 board[i][col] = 'Q';
                
                 flag = helper(res, board, col + 1) || flag;
                
                 board[i][col] = '.';   // back tracking
             }
         }
        
         return flag;
     }
    
	/*Helper method without return*/
    private void helper2(List<List<String>> res, char[][] board, int col) {
        if (col == board.length) {
            List<String> list = new ArrayList();
            for (int j = 0; j < board.length; j++) {
                list.add(new String(board[j]));
            }
            res.add(list);
            return;
        }
        
        // for each col, check every possible row
        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 'Q';
                
                helper2(res, board, col + 1);
                
                board[i][col] = '.';   // back tracking
            }
        }
        
    }
    
    private boolean isSafe(char[][] board, int row, int col) {
        int i, j;
        
        // only check its left side
        
        // check left row
        for (i = 0; i < col; i++) {
            if (board[row][i] == 'Q') return false;
        }
        
        // upper diagonal
        for (i = row, j = col; i >= 0 && j >= 0; j--, i--) {
            if (board[i][j] == 'Q') return false;
        }
        
        // lower diagonal
        for (i = row, j = col; i < board.length && j >= 0; j--, i++) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
}