package com.example.curryzhang.hyblog.designpattern.factory.simplefactory;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class SimpleRouJiaMoFactory {
    public RoujiaMo createRouJiaoMo(String type) {
        RoujiaMo roujiaMo = null;
        if (type.equals("Suan")) {
            roujiaMo = new SuanRouJiaMo();
        } else if (type.equals("Tian")) {
            roujiaMo = new TianRouJiaMo();
        } else if (type.equals("La")) {
            roujiaMo = new LaRouJiaMo();
        }
        return roujiaMo;
    }
}
