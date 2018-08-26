package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 戒指
 * Created by curry.zhang on 3/22/2017.
 */

public class RingEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战戒指";
    }
}
