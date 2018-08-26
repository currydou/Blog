package com.example.curryzhang.hyblog.horizontalscrollview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.curryzhang.hyblog.R;

import java.util.List;

/**
 * Created by curry.zhang on 4/25/2017.
 */

public class HorizontalScrollViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.item_horizontal_scroll, parent, false);
            viewHolder.mImg = (ImageView) convertView
                    .findViewById(R.id.id_index_gallery_item_image);
            viewHolder.mText = (TextView) convertView
                    .findViewById(R.id.id_index_gallery_item_text);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mImg.setImageResource(mDatas.get(position));
        if (position == 1) {
            viewHolder.mImg.setBackgroundColor(Color.GREEN);
        }
        viewHolder.mText.setText("some info ");

        return convertView;
    }

    private class ViewHolder {
        ImageView mImg;
        TextView mText;
    }

}
