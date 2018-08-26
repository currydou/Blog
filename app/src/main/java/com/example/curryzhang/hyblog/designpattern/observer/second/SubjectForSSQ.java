package com.example.curryzhang.hyblog.designpattern.observer.second;

import java.util.Observable;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class SubjectForSSQ extends Observable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}
