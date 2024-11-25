package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WatchTester {
    @Autowired
    private List<Watch> watches;

    @Value("${tester.name}")
    private String testerName;

    @PostConstruct
    public void run() {
        System.out.println("Tester: " + testerName);
        for (Watch watch : watches) {
            System.out.println("current date is " + watch.getDate());
            System.out.println("current time is " + watch.getTime());
        }
    }
}
