package com.minsk24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

}
