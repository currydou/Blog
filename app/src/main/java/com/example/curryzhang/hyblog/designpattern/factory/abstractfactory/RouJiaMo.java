package com.example.curryzhang.hyblog.designpattern.factory.abstractfactory;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public abstract class RouJiaMo {
    protected String name;

    /**
     * 准备工作
     */
    public final void prepare(RouJiaMoYLFactory ylFactory) {
        Meat meat = ylFactory.createMeat();
        YuanLiao yuanLiao = ylFactory.createYuanLiao();
        System.out.println("使用官方的原料" + meat + " , " + yuanLiao + "作为原材料制作肉夹馍");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public final void pack() {
        System.out.println("肉夹馍-专用袋-包装");
    }

    /**
     * 密制设备-烘烤2分钟
     */
    public final void fire() {
        System.out.println("肉夹馍-专用设备-烘烤");
    }

}
