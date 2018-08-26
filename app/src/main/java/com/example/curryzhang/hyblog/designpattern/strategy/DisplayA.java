package com.example.curryzhang.hyblog.designpattern.strategy;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class DisplayA implements IDisplayBehavior {

    @Override
    public void display() {
        System.out.println("展示");
    }
}
