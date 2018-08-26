package com.example.curryzhang.hyblog.designpattern.status;

/**
 * 自动售货机
 * Created by curry.zhang on 3/22/2017.
 */

public class VendingMachine {
    /**
     * 已投币
     */
    private final static int HAS_MONEY = 0;
    /**
     * 未投币
     */
    private final static int NO_MONEY = 1;
    /**
     * 售出商品
     */
    private final static int SOLD = 2;
    /**
     * 商品售馨
     */
    private final static int SOLD_OUT = 3;
    private int currentStatus = NO_MONEY;
    /**
     * 商品数量
     */
    private int count = 0;

    public VendingMachine(int count) {
        this.count = count;
        if (count > 0) {
            currentStatus = NO_MONEY;
        }
    }

    /**
     * 投入硬币，任何状态用户都可能投币
     */
    public void insertMoney() {
        switch (currentStatus) {
            case NO_MONEY:
                currentStatus = HAS_MONEY;
                System.out.println("成功投入硬币");
                break;
            case HAS_MONEY:
                System.out.println("已有硬币，无须投币");
                break;
            case SOLD:
                System.out.println("请稍等。。。");
                break;
            case SOLD_OUT:
                System.out.println("商品已售馨，请勿投币");
                break;
        }
    }

    /**
     * 退币，任何状态都能退币
     */
    public void backMoney() {
        switch (currentStatus) {
            case NO_MONEY:

                System.out.println("您未投入硬币");
                break;
            case HAS_MONEY:
                currentStatus = NO_MONEY;
                System.out.println("退币成功");
                break;
            case SOLD:
                System.out.println("您已经买了糖果");
                break;
            case SOLD_OUT:
                System.out.println("您未投币");
                break;
        }
    }

    /**
     * 转动手柄购买，任何状态用户都可能转动手柄
     */
    public void turnCrank() {
        switch (currentStatus) {
            case NO_MONEY:
                System.out.println("先投入硬币");
                break;
            case HAS_MONEY:

                System.out.println("正在出商品");
                currentStatus = SOLD;
                dispense();
                break;
            case SOLD:
                System.out.println("连续转动也没用");
                break;
            case SOLD_OUT:
                System.out.println("商品已经售馨");
                break;
        }
    }

    /**
     * 发放商品
     */
    private void dispense() {
        switch (currentStatus) {
            case NO_MONEY:
            case HAS_MONEY:
            case SOLD_OUT:
                throw new IllegalStateException();
            case SOLD:
                count--;
                System.out.println("发放商品。。。");
                if (count == 0) {
                    System.out.println("商品售馨");
                    currentStatus = SOLD_OUT;
                } else {
                    currentStatus = NO_MONEY;
                }
                break;
        }
    }
}
