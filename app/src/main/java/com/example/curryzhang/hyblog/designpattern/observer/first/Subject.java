package com.example.curryzhang.hyblog.designpattern.observer.first;

/**
 * Created by curry.zhang on 3/22/2017.
 */


/**
 * 主题接口
 */
public interface Subject {
    /**
     * 注册一个观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有的观察者
     */
    void notifyObservers();
}
