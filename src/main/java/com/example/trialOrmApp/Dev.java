package com.example.trialOrmApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dev {

    @Autowired
    private Laptop laptop ;

    public void bild(){
        laptop.compile();
        System.out.println("Working on a project");
    }



}
