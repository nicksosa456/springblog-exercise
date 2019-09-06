package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String rolledDice(@PathVariable int n, Model viewModel) {
        int dice = (int) Math.floor(Math.random()*6)+1;
        if (n == dice) {
            return "The number is "+dice+". You guessed "+n+". You guessed correctly~";
        } else {
            return "The number is "+dice+". You guessed "+n+". You guessed incorrectly.";
        }
    }
}
