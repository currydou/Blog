package com.example.curryzhang.hyblog.xiaomisms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.curryzhang.hyblog.R;

import java.util.ArrayList;
import java.util.Arrays;

public class XiaoMiSMSActivity extends AppCompatActivity {

    private MyListView mListView;
    private BounceScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_mi_sms);
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollView);
        mScrollView.setCallBack(new BounceScrollView.Callback() {

            @Override
            public void callback() {
                Toast.makeText(XiaoMiSMSActivity.this, "you can do something!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mListView = (MyListView) findViewById(R.id.id_listView);
        mListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList("Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java", "Android", "Lucene", "C++",
                        "C#", "HTML"))));
    }

}
