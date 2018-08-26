package com.example.curryzhang.hyblog.designpattern.observer.first;

/**
 * Created by curry.zhang on 3/22/2017.
 */

/**
 * 所有观察者需要实现此接口
 */
public interface Observer {
    void update(String msg);
}
