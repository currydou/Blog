package com.example.curryzhang.hyblog.designpattern.strategy;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class DefendTBS implements IDefendBehavior {
    @Override
    public void defend() {
        System.out.println("防御");
    }
}
