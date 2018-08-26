package com.example.curryzhang.hyblog.designpattern.adapter;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Mobile {
    /**
     * 充电
     */
    public void inputPower(V5Power power) {
        int provideV5Power = power.provideV5Power();
        System.out.println("手机(客户端):我需要5v电压充电，现在是-->" + provideV5Power + "V");
    }
}
