package com.example.curryzhang.hyblog.xiaomisms;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by curry.zhang on 4/10/2017.
 */

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 解决ScrollView与ListView的嵌套问题
         */
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
