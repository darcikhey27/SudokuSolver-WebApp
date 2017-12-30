package com.darcikhey.sudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DARCI on 12/27/2017.
 * Mind wide open
 */
@Controller
public class SudokuController {



    private final SudokuService sudokuService;

    @Autowired
    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }


    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    @ResponseBody
    public String solve(@RequestBody String jsonString) {
       // System.out.println(jsonString);

        return this.sudokuService.buildFromJson(jsonString);
    }

}