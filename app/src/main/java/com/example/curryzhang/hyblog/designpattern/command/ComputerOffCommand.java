package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class ComputerOffCommand implements Command {

    private Computer computer;

    public ComputerOffCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.off();
    }
}
