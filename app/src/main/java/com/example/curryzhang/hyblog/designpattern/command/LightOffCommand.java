package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
