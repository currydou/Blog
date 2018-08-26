package com.example.curryzhang.hyblog.designpattern.facade;

import com.example.curryzhang.hyblog.designpattern.command.Computer;
import com.example.curryzhang.hyblog.designpattern.command.Light;

/**
 * 外观模式定义：提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用。
 * (其实就是为了方便客户的使用，把一群操作，封装成一个方法。)
 * Created by curry.zhang on 3/22/2017.
 */

public class HomeThreaterFacade {
    private Computer computer;
    private Player player;
    private Light light;
    private Projector projector;
    private PopcornPopper popper;

    public HomeThreaterFacade(Computer computer, Player player, Light light, Projector projector, PopcornPopper popper) {
        this.computer = computer;
        this.player = player;
        this.light = light;
        this.projector = projector;
        this.popper = popper;
    }

    public void watchMovie() {
        /**
         * 1.打开爆米花机
         * 2.制作爆米花
         * 3.将灯光调暗
         * 4.打开投影仪
         * 5.放下投影仪投影区
         * 6.打开电脑
         * 7.打开播放器
         * 8.将播放器音调设为环绕立体声
         */
        popper.on();
        popper.makePopcorn();
        light.off();
        projector.on();
        projector.open();
        computer.on();
        player.on();
        player.make3DListener();
    }

    public void stopMovie() {
        popper.off();
        popper.stopPopcorn();
        /*
        * ....
        * */
    }
}

class Player {
    void off() {

    }

    void on() {

    }

     void make3DListener() {

    }
}

class Projector {
    void close() {

    }

    void off() {

    }

    void on() {

    }

    void open() {

    }

}

class PopcornPopper {
    void on() {

    }

    void makePopcorn() {

    }
    void stopPopcorn() {

    }

    void off() {

    }
}
