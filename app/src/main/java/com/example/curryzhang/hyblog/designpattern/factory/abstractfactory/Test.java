package com.example.curryzhang.hyblog.designpattern.factory.abstractfactory;

import com.example.curryzhang.hyblog.designpattern.factory.factory.XianRouJiaMoStore;
import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.RoujiaMo;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class Test {
    public static void main(String[] args) {
        XianRouJiaMoStore rouJiaMoStore = new XianRouJiaMoStore();
        RoujiaMo suanRoujiaMo = rouJiaMoStore.sellRouJiaoMo("Suan");
//        System.out.println(suanRoujiaMo.na);
    }
}
