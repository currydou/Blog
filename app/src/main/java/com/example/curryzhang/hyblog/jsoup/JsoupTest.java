package com.example.curryzhang.hyblog.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *
 *
 * --------------------使用DOM方法来遍历一个文档---------------------------------------------------
 * class="form-group" 获取 getElementsByClass("form-group")
 * <div class="form-group">   获取 getElementsByTag("div")
 * <form id="form-user-login" name="userLogin" novalidate=""> 获取  getElementById("form-user-login")
 * <a href="http://baidu.com" target="_blank">如何设置？</a> 获取 link.attr("href")  返回链接字符串
 *
 * --------------------你想使用类似于CSS或jQuery的语法来查找和操作元素。----------------------------------------------------
 * Element.select(String selector) 和 Elements.select(String selector)
 *
 *
 *
 * 某些网站禁止爬虫，不能抓取或者抓取一定数量后封IP。这时候我们需要伪装成浏览器进行抓取，这可以通过修改http包中的header来实现
 */

public class JsoupTest {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://xjh.haitou.cc/wh/uni-1/after/hold/page-1/").get();

//        Elements elements = document.getElementsByTag("ul");
        Elements elements = document.getElementsByClass("xjh-live-cell living old");
//        Element element = document.getElementById("navbarloginBtn");
        for (Element element : elements) {
            Elements elements1 = element.getElementsByTag("a");
            String content = elements1.attr("href");
            System.out.println(content);
        }
//        System.out.println(element.text());
    }

}
