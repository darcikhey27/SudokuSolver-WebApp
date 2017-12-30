package com.darcikhey.sudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;

/**
 * Created by DARCI on 12/29/2017.
 * Mind wide open
 */
@Service
public class SudokuService {


    private final Sudoku sudoku;

    @Autowired
    public SudokuService(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public String buildFromJson(String jsonString) {
        return this.sudoku.buildFromJsonString(jsonString);
    }
}
