package com.example.curryzhang.hyblog.designpattern.status.restructure;


import java.util.Random;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class HasMoneyState implements State {

    private VendingMachine machine;
    private Random random = new Random();

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("您已投过币，无需再投");
    }

    @Override
    public void backMoney() {
        System.out.println("退币成功");
        machine.setState(machine.getNoMoneyState());
    }

    @Override
    public void turnCrank() {
        System.out.println("您转动了手柄");
        int winner = random.nextInt(10);
        if (winner == 0 && machine.getCount() > 1) {
            machine.setState(machine.getWinnerState());
        } else {
            machine.setState(machine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态");
    }
}
