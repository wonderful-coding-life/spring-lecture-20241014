package org.example;

public class WatchTester {
    private Watch watch;

    public WatchTester(Watch watch) {
        this.watch = watch;
    }

    public void run() {
        System.out.println("current date is " + watch.getDate());
        System.out.println("current time is " + watch.getTime());
    }
}
