package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 武器
 * Created by curry.zhang on 3/22/2017.
 */

public class ArmEquip implements IEquip {

    @Override
    public int calculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }
}
