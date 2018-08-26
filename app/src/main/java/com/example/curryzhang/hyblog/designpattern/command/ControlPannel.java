package com.example.curryzhang.hyblog.designpattern.command;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class ControlPannel {
    private static final int CONTROL_SIZE = 9;
    private Command[] commands;

    public ControlPannel() {
        commands = new Command[CONTROL_SIZE];
        /**
         * 初始化所有按钮指向空对象
         */
        for (int i = 0;i<CONTROL_SIZE;i++){
            commands[i] = new NoCommand();
        }
    }

    /**
     * 设置每个按钮对应的命令
     */
    public void setCommand(int index, Command command) {
        commands[index] = command;
    }

    /**
     * 模拟点击按钮
     */
    public void keyPressed(int index) {
        commands[index].execute();
    }
}
