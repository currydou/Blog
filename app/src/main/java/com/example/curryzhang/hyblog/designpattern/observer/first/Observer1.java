package com.example.curryzhang.hyblog.designpattern.observer.first;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Observer1 implements Observer {

    private Subject subject;

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("observer1 得到 3D 号码 -->" + msg + ", 记下来");
    }
}
