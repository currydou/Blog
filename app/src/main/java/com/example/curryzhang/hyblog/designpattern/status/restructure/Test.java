package com.example.curryzhang.hyblog.designpattern.status.restructure;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(10);
        machine.insertMoney();
        machine.backMoney();

        System.out.println("---我要中奖---");

        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();

        System.out.println("---压力测试---");

        machine.insertMoney();
        machine.backMoney();
        machine.backMoney();
        machine.turnCrank();
        machine.turnCrank();
        machine.backMoney();
    }
}
