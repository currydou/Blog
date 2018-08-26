package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 护腕
 * Created by curry.zhang on 3/22/2017.
 */

public class WristEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战护腕";
    }
}
