package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 装备接口
 * Created by curry.zhang on 3/22/2017.
 */
public interface IEquip {
    /**
     * 计算攻击力
     * @return
     */
    int calculateAttack();

    /**
     * 装备的描述
     * @return
     */
    String description();
}
