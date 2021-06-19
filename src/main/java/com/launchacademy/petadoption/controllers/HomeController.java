package com.launchacademy.petadoption.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/pets", "/pets/{type}", "/pets/{type}/{id}", "/adoptions", "/adoptions/new"})
    public String forward() {
        return "forward:/index.html";
    }
}

//need all the routes for it to work