package com.example.curryzhang.hyblog.CommonAdapter.encapsulation3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.curryzhang.hyblog.R;

import java.util.ArrayList;

/**
 * 是activity;只是演示用法;用MyAdapter2为了名字保持和之前一致，方便理清楚类的用途
 * Created by curry.zhang on 4/27/2017.
 */
public class MyAdapter3 extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new CommonAdapter3<Bean>(this, new ArrayList<Bean>(), R.layout.item_horizontal_scroll) {
            @Override
            public void convert(ViewHolder3 helper, Bean bean) {
                helper.setText(R.id.id_index_gallery_item_text, "前面是一个TextView，这里是设置的文本"+bean.getName());
            }
        };


    }

    class Bean {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
