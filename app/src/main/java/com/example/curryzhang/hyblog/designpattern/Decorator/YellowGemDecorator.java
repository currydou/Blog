package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class YellowGemDecorator implements IEquipDecorator {

    private IEquip equip;

    public YellowGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 10 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + " + 黄宝石";
    }
}
