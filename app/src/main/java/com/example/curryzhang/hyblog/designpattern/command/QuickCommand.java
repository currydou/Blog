package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class QuickCommand implements Command {

    private Command[] commands;

    public QuickCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }
}
