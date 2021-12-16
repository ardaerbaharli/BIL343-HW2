package com.company;

class ThreadChild extends Thread {
    public void run() {
        Utils.cleanDirectory("Invoice");
        Utils.cleanDirectory("Report");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started.");
        Runtime current = Runtime.getRuntime();
        current.addShutdownHook(new ThreadChild());
        new Gate();
    }
}
