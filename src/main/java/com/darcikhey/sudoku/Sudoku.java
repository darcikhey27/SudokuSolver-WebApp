package com.darcikhey.sudoku;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.*;

@Component
public class Sudoku {

    private static class Cell {
        int value;
        int y;
        int x;
        List<Integer> neighboors;

        public Cell(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.neighboors = new LinkedList<>();
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

    public String buildFromJsonString(String jsonString) {
        System.out.println("from repository " + jsonString);

        buildGrid(jsonString);
        return "{status : \"ok from repo\"}";
    }

    private void buildGrid(String string) {
        //Cell[][] grid = new Cell[9][9];
        //JsonObject jsonObject = getJsonObj(string);

        //String json = "{\"0\":[\"3\",\"0\",\"6\",\"5\",\"0\",\"8\",\"4\",\"0\",\"0\"],\"1\":[\"5\",\"2\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\"],\"2\":[\"0\",\"8\",\"7\",\"0\",\"0\",\"0\",\"0\",\"3\",\"1\"],\"3\":[\"0\",\"0\",\"3\",\"0\",\"1\",\"0\",\"0\",\"8\",\"0\"],\"4\":[\"9\",\"0\",\"0\",\"8\",\"6\",\"3\",\"0\",\"0\",\"5\"],\"5\":[\"0\",\"5\",\"0\",\"0\",\"9\",\"0\",\"6\",\"0\",\"0\"],\"6\":[\"1\",\"3\",\"0\",\"0\",\"0\",\"0\",\"2\",\"5\",\"0\"],\"7\":[\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"7\",\"4\"],\"8\":[\"0\",\"0\",\"5\",\"2\",\"0\",\"6\",\"3\",\"0\",\"0\"]}";
        JsonObject jsonObject = getJsonObj(string);


        for(int i = 0; i < 9; i++) {
            JsonArray array = jsonObject.getJsonArray("" + i);
            for(int k = 0; k < 9; k++) {
                int value = Integer.parseInt(String.valueOf(array.getJsonString(k)));
                this.grid[i][k] = new Cell(value, i, k);
                //System.out.println(jsonObject.getS);
                //System.out.print(array.getJsonString(k));
            }
            // System.out.println();
        }
    }

    private JsonObject getJsonObj(String s) {
        JsonReader jsonReader = Json.createReader(new StringReader(s));
        return jsonReader.readObject();
    }

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

    public static void main(String[] args) {

        Scanner fin = null;
        try {
            fin = new Scanner(new File("sudoku.txt"));

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Cell[][] grid = new Cell[9][9];
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9; k++) {
                grid[i][k] = new Cell(fin.nextInt(), i, k);
            }
        }

        Sudoku sudoku = new Sudoku();
        boolean wasSolved = sudoku.solve(grid);
        System.out.println(wasSolved ? "Success" : "Fail");
        print(grid);


    }

    private static void print(Cell[][] grid) {
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9; k++) {
                System.out.print(grid[i][k].value);
            }
            System.out.println();
        }
    }
}
