package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
