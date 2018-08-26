package com.example.curryzhang.hyblog.jsoup;

import java.util.List;

/**
 * Created by curry.zhang on 3/20/2017.
 */

public class Test
{

    public static void main(String[] args) {
//      new Test().getDatasByClass();
      new Test().getDatasByCssQuery();
    }

//    @org.junit.Test
    public void getDatasByClass()
    {
        Rule rule = new Rule(
                "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",
                new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "兴网","" },
                "cont_right", Rule.CLASS, Rule.POST);
        List<LinkTypeData> extracts = ExtractService.extract(rule);
        printf(extracts);
    }

//    @org.junit.Test
    public void getDatasByCssQuery()
    {
        Rule rule = new Rule("http://www.11315.com/search",
                new String[] { "name" }, new String[] { "兴网" },
                "合作", Rule.SELECTION, Rule.GET);
        List<LinkTypeData> extracts = ExtractService.extract(rule);
        printf(extracts);
    }

    public void printf(List<LinkTypeData> datas)
    {
        for (LinkTypeData data : datas)
        {
            System.out.println(data.getLinkText());
            System.out.println(data.getLinkHref());
            System.out.println("***********************************");
        }

    }
}