package com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.fulldisplay;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.curryzhang.hyblog.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FullDisplayActivity extends Activity {

    private MyListView mListView;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_full_display);

        mListView = (MyListView) findViewById(R.id.id_lv);
        initDatas();
        initViews();

    }

    private void initViews() {
        mListView = (MyListView) findViewById(R.id.id_lv);
        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                View convertView = helper.getConvertView();
                convertView.setBackgroundColor(Color.rgb(
                        new Random().nextInt(255), new Random().nextInt(255),
                        new Random().nextInt(255)));
                ViewGroup.LayoutParams lp = convertView.getLayoutParams();
                lp.height = mListView.getItemHeight();
                convertView.setLayoutParams(lp);
                helper.setText(R.id.id_title, item);
            }
        };

        mListView.setAdapter(mAdapter);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add(String.valueOf((char) +i));
        }
    }
}
