package com.example.curryzhang.hyblog.CommonAdapter.encapsulation1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by curry.zhang on 4/27/2017.
 */
public class MyAdapter<T> extends CommonAdapter<T> {

    public MyAdapter(Context context, List<T> mDatas) {
        super(context, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                android.R.layout.activity_list_item, position);
        TextView mTitle = viewHolder.getView(android.R.id.text1);
        mTitle.setText((String) mDatas.get(position));
        return viewHolder.getConvertView();
    }

}