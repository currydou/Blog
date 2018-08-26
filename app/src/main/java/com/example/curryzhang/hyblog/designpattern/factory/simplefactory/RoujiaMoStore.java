package com.example.curryzhang.hyblog.designpattern.factory.simplefactory;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class RoujiaMoStore {
  /*  *//**
     * 根据传入类型卖不同的肉夹馍
     *//*
    public RoujiaMo sellRoujiaMo(String type) {
        RoujiaMo roujiaMo = null;
        if (type.equals("Suan")) {
            roujiaMo = new SuanRouJiaMo();
        } else if (type.equals("Tian")) {
            roujiaMo = new TianRouJiaMo();
        } else if (type.equals("La")) {
            roujiaMo = new LaRouJiaMo();
        }

        roujiaMo.prepare();
        roujiaMo.fire();
        roujiaMo.pack();
        return roujiaMo;
    }*/

    private SimpleRouJiaMoFactory factory;

    public RoujiaMoStore(SimpleRouJiaMoFactory factory) {
        this.factory = factory;
    }

    public RoujiaMo sellRouJiaoMo(String type) {
        RoujiaMo rouJiaoMo = factory.createRouJiaoMo(type);
        rouJiaoMo.prepare();
        rouJiaoMo.fire();
        rouJiaoMo.pack();
        return rouJiaoMo;
    }
}
