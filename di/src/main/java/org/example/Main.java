package org.example;

public class Main {
    public static void main(String[] args) {
        WatchTester watchTester = new WatchTester(new AppleWatch());
        watchTester.run();
    }
}