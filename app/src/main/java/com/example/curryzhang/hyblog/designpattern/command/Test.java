package com.example.curryzhang.hyblog.designpattern.command;

/**
 * 将“请求”封装成对象，以便使用不同的请求，队列或者日志来参数化其他对象。
 * 命令模式也支持可撤销的操作
 * 将动作请求者与动作执行者完全解耦
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        /**
         * 三个家电
         */
        Light light = new Light();
        Door door = new Door();
        Computer computer = new Computer();
        /**
         * 一个控制器，假设是我们的app主界面
         */
        ControlPannel controlPannel = new ControlPannel();
        //为每个按钮设置功能
        controlPannel.setCommand(0, new LightOnCommand(light));
        controlPannel.setCommand(1, new LightOffCommand(light));
        controlPannel.setCommand(2, new ComputerOnCommand(computer));
        controlPannel.setCommand(3, new ComputerOffCommand(computer));
        controlPannel.setCommand(4, new DoorOnCommand(door));
        controlPannel.setCommand(5, new DoorOffCommand(door));

        //模拟点击
        controlPannel.keyPressed(0);
        controlPannel.keyPressed(2);
        controlPannel.keyPressed(3);
        controlPannel.keyPressed(4);
        controlPannel.keyPressed(5);
        controlPannel.keyPressed(8);

        QuickCommand quickCommand = new QuickCommand(new Command[]{new DoorOffCommand(door),
                new LightOffCommand(light), new ComputerOffCommand(computer)});
        System.out.println("一键搞定按钮");
        controlPannel.setCommand(8,quickCommand);
        controlPannel.keyPressed(8);

    }
}
