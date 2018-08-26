package com.example.curryzhang.hyblog.designpattern.status;

/**
 * 状态模式定义：允许对象在内部状态改变时，同时改变它的行为，对象看起来好像修改了它的类。
 * (当对象的内部状态改变时，它的行为跟随状态的改变而改变了，看起来好像重新初始化了一个类似的)
 * Created by curry.zhang on 3/22/2017.
 */

public class TestTra {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(10);
        machine.insertMoney();
        machine.backMoney();

        System.out.println("------------------");

        machine.insertMoney();
        machine.turnCrank();

        System.out.println("--------压力测试-----------");
        machine.insertMoney();
        machine.insertMoney();
        machine.turnCrank();
        machine.turnCrank();
        machine.backMoney();
        machine.turnCrank();
    }
}
