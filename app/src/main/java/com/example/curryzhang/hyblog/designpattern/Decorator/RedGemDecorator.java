package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class RedGemDecorator implements IEquipDecorator {

    private IEquip equip;

    public RedGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 15 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + " + 红宝石";
    }

}
