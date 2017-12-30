package com.darcikhey.sudoku;

import org.springframework.stereotype.Component;

import javax.json.*;

import java.io.StringReader;
import java.util.*;

@Component
public class Sudoku {

    private static class Cell {
        int value;
        int y;
        int x;

        public Cell(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.value + " (" + this.x + "," + this.y + ")";
        }
    }

    private Cell[][] grid;

    public Sudoku() {
        this.grid = new Cell[9][9];
    }

    private void print() {
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9; k++) {
                System.out.print(this.grid[i][k].value);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        // build the JSON object
        for(int i = 0; i < 9; i++) {
            // create an array that will be in the final json object
            JsonArrayBuilder itemArray = Json.createArrayBuilder();
            for(int k = 0; k < 9; k++) {
                // add the cell x,y value to the array
                itemArray.add(this.grid[i][k].value);
            }
            // add the array to the index of i
            json.add("" + i, itemArray);
        }

        return json.build().toString();
    }

    /* will take in a json string passed form the frontend
       will pass it to the helper method to build the grid, then solve it
     */
    public String buildFromJsonString(String jsonString) {
        String result = buildGrid(jsonString);
        return result;
    }

    private String buildGrid(String string) {
        JsonObject jsonObject = getJsonObj(string);

        // build the grid board from the json object
        for(int i = 0; i < 9; i++) {
            JsonArray array = jsonObject.getJsonArray("" + i);
            for(int k = 0; k < 9; k++) {
                String s = array.getString(k);
                int value = Integer.parseInt(s);
                this.grid[i][k] = new Cell(value, i, k);
            }
        }
        // solve the grid puzzle here
        boolean wasSolved = this.solve(this.grid);
        // if not solved return bad status
        if(!wasSolved) {
            return "{status: \"fail\"}";
        }
        print();
        return this.toString();
    }

    // helper to build a JSON object from a string
    private JsonObject getJsonObj(String s) {
        JsonReader jsonReader = Json.createReader(new StringReader(s));
        return jsonReader.readObject();
    }

    // main algorithm to solve the puzzle
    public boolean solve(Cell[][] grid) {
        int i = 0;
        int j = 0;
        boolean foundVacant = false;
        boolean wasSolved = false;
        Map<Integer, Integer> candidates;

        if(isFull(grid)) {
            System.out.println("finished sudoku successfuly");
            return true;
        }
        else {
            for(int x = 0; x < 9; x++) {
                for(int y = 0; y < 9; y++) {
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
            candidates = getCandidates(grid, i, j);

            for(int x = 1; x < 10; x++) {
                if(candidates.get(x) != null) {
                    grid[i][j].value = candidates.get(x);

                    wasSolved = solve(grid);
                    if(wasSolved) {
                        break;
                    }
                }
            }
            // backtrack
            if(!wasSolved) {
                grid[i][j].value = 0;
            }
        }
        return wasSolved;
    }

    private boolean isFull(Cell[][] grid) {
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                if(grid[x][y].value == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private Map<Integer, Integer> getCandidates(Cell[][] grid, int i, int j) {

        List<Integer> rowValues = new LinkedList<>();
        for(int y = 0; y < 9; y++) {
            if(grid[i][y].value != 0) {
                rowValues.add(grid[i][y].value);
            }
        }
        List<Integer> colValues = new LinkedList<>();
        for(int x = 0; x < 9; x++) {
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
        else if(i >= 3 && i <= 5) {
            k = 3;
        }
        else {
            k = 6;
        }

        if(j >= 0 && j <= 2) {
            l = 0;
        }
        else if(j >= 3 && j <= 5) {
            l = 3;
        }
        else {
            l = 6;
        }

        // work on the mini grid section
        List<Integer> squareValues = new LinkedList<>();
        for(int x = k; x < k + 3; x++) {
            for(int y = l; y < l + 3; y++) {
                if(grid[x][y].value != 0) {
                    squareValues.add(grid[x][y].value);
                }
            }
        }

        squareValues.addAll(rowValues);
        Map<Integer, Integer> candidates = new Hashtable<Integer, Integer>();
        addToFinalList(candidates, squareValues);

        return candidates;
    }

    private void addToFinalList(Map<Integer, Integer> map, List<Integer> list) {
        int place = 1;
        for(int i = 1; i < 10; i++) {
            if(!list.contains(i)) {
                map.put(place, i);
                place++;
            }
        }
    }

}
