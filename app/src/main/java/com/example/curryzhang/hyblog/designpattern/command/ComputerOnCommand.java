package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class ComputerOnCommand implements Command {

    private Computer computer;

    public ComputerOnCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.on();
    }
}
