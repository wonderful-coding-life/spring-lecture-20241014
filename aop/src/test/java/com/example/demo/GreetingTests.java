package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GreetingTests {
    @Autowired
    private Greeting greeting;

    @Test
    public void sayHello() {
        greeting.sayHello();
    }

    @Test
    public void sayGoodbye() {
        greeting.sayGoodbye();
    }
}
