package com.example.curryzhang.hyblog.CommonAdapter.encapsulation3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by curry.zhang on 4/27/2017.
 */


public abstract class CommonAdapter3<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter3(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder3 viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder3 helper, T item);

    private ViewHolder3 getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder3.get(mContext, convertView, parent, mItemLayoutId, position);
    }

}
