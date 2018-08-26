package com.example.curryzhang.hyblog.designpattern.singleton;

/**
 * 恶汉模式
 * Created by curry.zhang on 3/22/2017.
 */

public class Singleton {
    private static Singleton instace = new Singleton();

    public static Singleton getInstace() {
        return instace;
    }
}
