package com.example.curryzhang.hyblog.designpattern.command;

/**
 * 空对象，好处，不用进行null判断
 * Created by curry.zhang on 3/22/2017.
 */

public class NoCommand implements Command {

    @Override
    public void execute() {

    }
}
