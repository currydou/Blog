package com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.curryzhang.hyblog.R;
import com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag.SingleItemScrollView.Adapter;
import com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag.SingleItemScrollView.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CyclicDragActivity extends Activity {
    private SingleItemScrollView mScrollView;
    private Adapter mAdapter;
    private LayoutInflater mInflater;
    private List<Bean> mDatas = new ArrayList<>(Arrays.asList(//
            new Bean(R.drawable.icon_about, Color.rgb(123, 34, 45)),//
            new Bean(R.drawable.icon_map, Color.rgb(12, 34, 145)),//
            new Bean(R.drawable.icon_message, Color.rgb(177, 234, 145)),//
            new Bean(R.drawable.icon_oil, Color.rgb(88, 134, 145))//
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cyclic_drag);

        mInflater = LayoutInflater.from(this);

        mScrollView = (SingleItemScrollView) findViewById(R.id.id_scrollview);
        mAdapter = new Adapter() {
            @Override
            public View getView(SingleItemScrollView parent, int pos) {
                View view = mInflater.inflate(R.layout.item_cyclic_drag, null);
                ImageView iv = (ImageView) view.findViewById(R.id.id_title);
                iv.setImageResource(mDatas.get(pos).getResId());
                view.setBackgroundColor(mDatas.get(pos).getColor());
                return view;
            }

            @Override
            public int getCount() {
                return 4;
            }

        };

        mScrollView.setAdapter(mAdapter);

        mScrollView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                Toast.makeText(getApplicationContext(), pos + "",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
