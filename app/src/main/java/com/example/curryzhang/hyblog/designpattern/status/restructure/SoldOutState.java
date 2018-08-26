package com.example.curryzhang.hyblog.designpattern.status.restructure;


import java.util.Random;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class SoldOutState implements State {

    private VendingMachine machine;
    private Random random = new Random();

    public SoldOutState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("投币失败，商品已售馨");
    }

    @Override
    public void backMoney() {
        System.out.println("您未投币，想退钱？");
    }

    @Override
    public void turnCrank() {
        System.out.println("商品售馨，转动手柄也没有用");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态");
    }
}
