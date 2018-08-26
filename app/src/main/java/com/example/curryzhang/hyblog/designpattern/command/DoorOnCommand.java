package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class DoorOnCommand implements Command {

    private Door door;

    public DoorOnCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }
}
