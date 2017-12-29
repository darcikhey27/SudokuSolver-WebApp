package com.darcikhey.sudoku;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DARCI on 12/27/2017.
 * Mind wide open
 */
@Controller
public class SudokuController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}