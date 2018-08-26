package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 蓝宝石装饰品
 * Created by curry.zhang on 3/22/2017.
 */

public class BlueGemDecorator implements IEquipDecorator {

    /**
     * 每个装饰品维护一个装备
     */
    private IEquip equip;

    public BlueGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 5 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + " + 蓝宝石";
    }
}
