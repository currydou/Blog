package com.example.curryzhang.hyblog.designpattern.strategy;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class RunJCTQ implements IRunBehavior {
    @Override
    public void run() {
        System.out.println("逃跑");
    }
}
