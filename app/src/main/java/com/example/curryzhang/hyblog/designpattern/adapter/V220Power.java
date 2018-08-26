package com.example.curryzhang.hyblog.designpattern.adapter;

/**
 * 家用220v交流电
 * Created by curry.zhang on 3/22/2017.
 */

public class V220Power {
    /**
     * 提供220v电压
     */
    public int provideV220Power() {
        System.out.println("我提供220v交流电压");
        return 220;
    }
}
