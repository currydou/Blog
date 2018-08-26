package com.example.curryzhang.hyblog.designpattern.singleton;

/**
 * 懒汉模式
 * Created by curry.zhang on 3/22/2017.
 */

public class Singleton02 {
    private static Singleton02 instance;

    public static Singleton02 getInstance() {
        if (instance == null) {
            synchronized (Singleton02.class) {
                if (instance == null) {
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }
}
