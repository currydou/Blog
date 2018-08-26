package com.example.curryzhang.hyblog.designpattern.strategy;

/**
 * 策略模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
 * 需要设计一个接口，为一系列实现类提供统一的方法，多个实现类实现该接口，设计一个抽象类。
 *
 * OO原则
 * 1.封装变化(把可能变化的代码封装起来)
 * 2.多用组合，少用继承(使用组合的方式，为客户设置了算法)
 * 3.针对接口编程，不针对实现(对于Role类的设计完全的针对角色，和技能的实现没有关系)
 *
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        Role roleA = new RoleA("A");

        roleA.setAttackBehavior(new AttackJY())
                .setDefendBehavior(new DefendTBS())
                .setDisplayBehavior(new DisplayA())
                .setRunBehavior(new RunJCTQ());

        roleA.run();
        roleA.attack();
        roleA.defend();
        roleA.display();


    }
}
