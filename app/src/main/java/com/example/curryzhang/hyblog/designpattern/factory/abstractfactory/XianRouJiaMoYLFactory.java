package com.example.curryzhang.hyblog.designpattern.factory.abstractfactory;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class XianRouJiaMoYLFactory implements RouJiaMoYLFactory {
    @Override
    public Meat createMeat() {
        return new FreshMeat();
    }

    @Override
    public YuanLiao createYuanLiao() {
        return new XianTeSeYuanliao();
    }
}
