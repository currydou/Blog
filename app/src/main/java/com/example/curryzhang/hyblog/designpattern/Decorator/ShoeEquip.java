package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 鞋子
 * 攻击力 5
 * Created by curry.zhang on 3/22/2017.
 */

public class ShoeEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战靴子";
    }
}
