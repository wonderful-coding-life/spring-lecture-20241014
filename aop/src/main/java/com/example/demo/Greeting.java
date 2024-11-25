package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Greeting {
    @PrintExecutionTime
    public void sayHello() {
        try {
            Thread.sleep((int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello ?");
    }

    @PrintExecutionTime
    public void sayGoodbye() {
        System.out.println("Hello ?");
    }
}
