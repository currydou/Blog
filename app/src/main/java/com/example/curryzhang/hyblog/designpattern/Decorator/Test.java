package com.example.curryzhang.hyblog.designpattern.Decorator;

/**
 * 如要扩展功能，装饰者提供了比集成更有弹性的替代方案，动态地将责任附加到对象上。
 * (添加一些辅助辅助功能，并不希望改变这个类的代码)
 *
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        //一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        System.out.println("一个镶嵌2颗红宝石，1颗蓝宝石的靴子");
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        System.out.println("攻击力：" + equip.calculateAttack());
        System.out.println("描述：" + equip.description());
        System.out.println("--------------");
        //一个镶嵌一颗红宝石，一颗蓝宝石,一颗黄宝石的武器
        System.out.println("一个镶嵌一颗红宝石，一颗蓝宝石,一颗黄宝石的武器");
        equip = new RedGemDecorator(new BlueGemDecorator(new YellowGemDecorator(new ArmEquip())));
        System.out.println("攻击力：" + equip.calculateAttack());
        System.out.println("描述：" + equip.description());
        System.out.println("--------------");
    }
}
