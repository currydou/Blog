package com.example.curryzhang.hyblog.designpattern.strategy;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class AttackJY implements IAttackBehavior {
    @Override
    public void attack() {
        System.out.println("攻击");
    }
}
