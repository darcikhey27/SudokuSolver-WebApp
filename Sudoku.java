import java.util.LinkedList;
import java.util.List;

public class Sudoku {

    private static class Cell {
        int value;
        int y;
        int x;
        List<Integer> candidates;

        public Cell(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.candidates = new LinkedList<>();
        }
        @Override
        public String toString() {
            return this.value+" ("+this.x+","+this.y+")";
        }
    }

    public void solve(Cell[][] grid) {
        int i = 0;
        int j = 0;
        boolean foundVacant = false;

        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(grid[x][y].value == 0) {
                    i = x;
                    j = y;
                    foundVacant = true;
                    break;
                }
            }
            if(foundVacant) {
                break;
            }
        }
        List<Integer> candidates = getCandidates(grid, i, j);


    }

    private List<Integer> getCandidates(Cell[][] grid, int i, int j) {

        List<Integer> rowValues = new LinkedList<>();
        for(int y = 0; y < 4; y++) {
            if(grid[i][y].value != 0) {
                rowValues.add(grid[i][y].value);
            }
        }
        List<Integer> colValues = new LinkedList<>();
        for(int x = 0; x < 4; x++) {
            if(grid[x][j].value != 0) {
                colValues.add(grid[x][j].value);
            }
        }

        rowValues.addAll(colValues);

        int k = 0;
        int l = 0;
        if(i >= 0 && i <= 2) {
            k = 0;
        }
        else if(i >= 3 && i <=5) {
            k = 3;
        }
        else {
            k = 6;
        }

        if(l >= 0 && l <= 2) {
            l = 0;
        }
        else if(l >= 3 && l <=5) {
            l = 3;
        }
        else {
            l = 6;
            System.out.println("i");
        }

        // work on the mini grid section

        return rowValues;
    }

    public static void main(String[] args) {
        Cell[][] board = new Cell[4][4];
        board[0][0] = new Cell(0,0,0);
        board[0][1] = new Cell(4,0,1);
        board[0][2] = new Cell(1,0,2);
        board[0][3] = new Cell(0,0,3);

        board[1][0] = new Cell(1,1,0);
        board[1][1] = new Cell(0,1,1);
        board[1][2] = new Cell(0,1,2);
        board[1][3] = new Cell(0,1,3);

        board[2][0] = new Cell(0,2,0);
        board[2][1] = new Cell(0,2,1);
        board[2][2] = new Cell(0,2,2);
        board[2][3] = new Cell(2,2,3);

        board[3][0] = new Cell(0,3,0);
        board[3][1] = new Cell(2,3,1);
        board[3][2] = new Cell(3,3,2);
        board[3][3] = new Cell(0,3,3);

        Sudoku sudoku = new Sudoku();
        sudoku.solve(board);



    }
}
