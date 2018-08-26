package com.example.curryzhang.hyblog.designpattern.factory.factory;

import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.LaRouJiaMo;
import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.RoujiaMo;
import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.SuanRouJiaMo;
import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.TianRouJiaMo;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public class XianRouJiaMoStore extends RoujiaMoStore {
    @Override
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
