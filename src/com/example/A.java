package com.example;

public class A {
    private int x;

    public A(){
        System.out.println("A's private constructor is running");
    }

    public A(int x) {
        this.x = x;
    }

    public int fun() {
        return 2 * x;
    }

    protected void gun(){
        System.out.println("Gun is running..");
    }
}
