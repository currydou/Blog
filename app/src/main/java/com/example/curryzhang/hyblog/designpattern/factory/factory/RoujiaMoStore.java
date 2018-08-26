package com.example.curryzhang.hyblog.designpattern.factory.factory;

import com.example.curryzhang.hyblog.designpattern.factory.simplefactory.RoujiaMo;

/**
 * Created by curry.zhang on 3/22/2017.
 */

public abstract class RoujiaMoStore {

    public abstract RoujiaMo createRouJiaoMo(String type);

    public RoujiaMo sellRouJiaoMo(String type) {
        RoujiaMo rouJiaoMo = createRouJiaoMo(type);
        rouJiaoMo.prepare();
        rouJiaoMo.fire();
        rouJiaoMo.pack();
        return rouJiaoMo;
    }
}
