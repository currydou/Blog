package com.example.curryzhang.hyblog.designpattern.status.restructure;


/**
 * Created by curry.zhang on 3/22/2017.
 */

public class WinnerState implements State {

    private VendingMachine machine;

    public WinnerState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        throw new IllegalStateException("非法状态");
    }

    @Override
    public void backMoney() {
        throw new IllegalStateException("非法状态");
    }

    @Override
    public void turnCrank() {
        throw new IllegalStateException("非法状态");
    }

    @Override
    public void dispense() {
        System.out.println("您中奖了，恭喜您，将得到2件商品");
        machine.dispense();
        if (machine.getCount() == 0) {
            System.out.println("商品已经售馨");
            machine.setState(machine.getSoldOutState());
        } else {
            machine.dispense();
            if (machine.getCount() > 0) {
                System.out.println("商品已经售馨");
                machine.setState(machine.getSoldOutState());
            }
        }
    }
}
