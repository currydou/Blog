package com.example.curryzhang.hyblog.designpattern.observer.first;

/**
 *定义了对系那个之间的一对多的依赖，当一个对象改变时，它的所有依赖者都会收到通知并自动更新。
 *
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        //模拟一个3D服务号
        ObjectFor3D objectFor3D = new ObjectFor3D();
        //客户1
        Observer observer1 = new Observer1(objectFor3D);
        Observer observer2 = new Observer2(objectFor3D);

        objectFor3D.setMsg("1111");
        objectFor3D.setMsg("2222");

    }
}
