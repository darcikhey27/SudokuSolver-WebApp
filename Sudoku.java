package sudoku;

import java.util.Stack;

public class Sudoku {
	private int[][] board;
	private int size;
	
	public Sudoku(int[][] board) {
		this.board = board;
		
	}
	
	public boolean isFull(int board[][]) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public Stack<Integer> candidates(int[][] board, int i, int j) {
		Stack<Integer> candidates = new Stack<Integer>();
		
		
		return null;
	}
	public void print() {
		for(int i =0; i < this.board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				System.out.print(this.board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int b[][] = new int[4][4];
		b[0][0] = 1;
		b[0][1] = 0;
		b[0][2] = 3;
		b[0][3] = 0;
		
		b[1][0] = 1;
		b[1][1] = 0;
		b[1][2] = 3;
		b[1][3] = 0;
		
		b[2][0] = 1;
		b[2][1] = 0;
		b[2][2] = 3;
		b[2][3] = 0;
		
		b[3][0] = 1;
		b[3][1] = 0;
		b[3][2] = 3;
		b[3][3] = 0;
		
		Sudoku sudoku = new Sudoku(b);

		sudoku.print();
		
	}

}
