package com.projects.pizzaservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Maria on 10.11.2016.
 */
@Controller
public class AppController {

    @RequestMapping(value = {"/", "/helloworld**"}, method = {RequestMethod.GET})
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Tutorial");
        model.addObject("message", "Welcome Page !");
        model.setViewName("helloworld");
        return model;
    }
}