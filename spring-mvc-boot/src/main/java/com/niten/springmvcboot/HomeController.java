package com.niten.springmvcboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        System.out.println("home controller...");
        return "index";
    }

    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int i, @RequestParam("num2") int j) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");

        int num3 = i + j;
        System.out.println(num3);

        modelAndView.addObject("num3", num3);
        return modelAndView;
    }


}
