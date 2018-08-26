package com.example.curryzhang.hyblog.designpattern.adapter;

/**
 * 定义：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。
 * (把一个接口转成另一个接口)
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        V5PowerAdapter v5PowerAdapter = new V5PowerAdapter(new V220Power());
        mobile.inputPower(v5PowerAdapter);
    }
}
