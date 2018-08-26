package com.example.curryzhang.hyblog.CommonAdapter.encapsulation2;

/**
 * Created by curry.zhang on 4/27/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.curryzhang.hyblog.CommonAdapter.encapsulation1.ViewHolder;
import com.example.curryzhang.hyblog.R;

import java.util.ArrayList;

/**
 * 是activity;只是演示用法;用MyAdapter2为了名字保持和之前一致，方便理清楚类的用途
 * Created by curry.zhang on 4/27/2017.
 */
public class MyAdapter2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListView listView = new ListView(this);
        listView.setAdapter(new CommonAdapter2<String>(this, new ArrayList<String>(), R.layout.item_horizontal_scroll) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {
                TextView text = viewHolder.getView(R.id.id_txt);
                text.setText("文本");
            }
        });

    }

}
