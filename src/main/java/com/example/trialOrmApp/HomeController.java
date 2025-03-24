package com.example.trialOrmApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet(){
        return "Welcome to Soke Trial";
    }

    @RequestMapping("/about")
    public String abouts(){
        return "We dont teach we eductae";
    }
}
