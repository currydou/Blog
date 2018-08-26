package com.example.curryzhang.hyblog.designpattern.factory.simplefactory;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public abstract class RoujiaMo {
    protected String name;

    /**
     * 准备工作
     */
    public void prepare() {
        System.out.println("揉面-剁肉-完成准备工作");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public void pack() {
        System.out.println("肉夹馍-专用袋-包装");
    }

    /**
     * 密制设备-烘烤2分钟
     */
    public void fire() {
        System.out.println("肉夹馍-专用设备-烘烤");
    }

}
