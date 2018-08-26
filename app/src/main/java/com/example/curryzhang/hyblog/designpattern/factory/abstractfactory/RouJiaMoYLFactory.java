package com.example.curryzhang.hyblog.designpattern.factory.abstractfactory;

/**
 * 提供肉夹馍的原料
 * Created by curry.zhang on 3/22/2017.
 */

public interface RouJiaMoYLFactory {
    /**
     * 生产肉
     */
    Meat createMeat();

    /**
     * 生产调料等等
     *
     * @return
     */
    YuanLiao createYuanLiao();
}
