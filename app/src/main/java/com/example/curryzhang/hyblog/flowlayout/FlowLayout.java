package com.example.curryzhang.hyblog.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        Log.e(TAG, sizeWidth + "," + sizeHeight);

        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        int cCount = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);


            /**
             * 下面主要是计算wrap_content时的默认值
             */
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth <= sizeWidth) {
                // 没超过。累加值lineWidth,lineHeight取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            } else {
                //如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
                // 重新开启新行，开始记录
                lineWidth = childWidth;
                // 叠加当前高度，
                lineHeight = childHeight + lineHeight;
            }

        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : lineWidth,
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : lineHeight);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();

        //已经添加的view的总宽度
        int lineWidth = 0;
        int currentLineHeight = 0;
        int totalLineHeight = 0;
        int cCount = getChildCount();
        // 遍历所有的孩子
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果已经需要换行。
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width) {
                // 重置行宽
                lineWidth = 0;
                //每一行布局完后，将高度累加
                totalLineHeight += currentLineHeight;
            }

            //计算childView的left,totalLineHeight,right,bottom
            int childLeft = lineWidth + lp.leftMargin;
            int childTop = totalLineHeight + lp.topMargin;
            int childRight = childLeft + child.getMeasuredWidth();
            int childBottom = childTop + child.getMeasuredHeight();

            child.layout(childLeft, childTop, childRight, childBottom);

            // 如果不需要换行，则累加childView的宽度
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            //取一行中最高的childiew的高度
            currentLineHeight = Math.max(currentLineHeight, childHeight + lp.topMargin + lp.bottomMargin);
        }

    }
}
