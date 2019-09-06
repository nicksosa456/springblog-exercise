package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number}/and/{numberTwo}")
    @ResponseBody
    public String add(@PathVariable int number, @PathVariable int numberTwo) {
        return number + " + " +numberTwo + " = "+ (number+numberTwo);
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public String subtract(@PathVariable int a, @PathVariable int b) {
        return b + " - " + a + " = " +(b-a);
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String multiply(@PathVariable int a, @PathVariable int b) {
        return a+" * "+b+" = "+(a*b);
    }

    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public String divide(@PathVariable double a, @PathVariable double b) {
        return a+" / "+b+" = "+(a/b);
    }
}
