package com.example.curryzhang.hyblog.designpattern.status.restructure;

/**
 * 状态的接口
 * Created by curry.zhang on 3/22/2017.
 */

public interface State {
    /**
     * 放钱
     */
    void insertMoney();

    /**
     * 退钱
     */
    void backMoney();

    /**
     *转动曲柄
     */
    void turnCrank();

    /**
     *出商品
     */
    void dispense();
}
