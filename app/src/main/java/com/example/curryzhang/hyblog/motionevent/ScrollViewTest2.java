package com.example.curryzhang.hyblog.motionevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by curry on 2018/8/26.
 * https://blog.csdn.net/yanzhenjie1003/article/details/53046027
 */

public class ScrollViewTest2 extends LinearLayout {

    private Scroller scroller;

    public ScrollViewTest2(Context context) {
        this(context,null);
    }

    public ScrollViewTest2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollViewTest2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
           measureChild(child,widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int measuredWidth = child.getMeasuredWidth();
                child.layout(measuredWidth * i, 0, measuredWidth * (1 + i), child.getMeasuredHeight());
            }
        }
    }

    float mLastX;
    int currentPage;
    // 手指按下时View的相对坐标。
    private int mDownViewX;


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float x = event.getRawX();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) { // 如果上次的调用没有执行完就取消。
                    scroller.abortAnimation();
                }
                mLastX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dxMove = (int) (mLastX - x);
                scrollBy(dxMove, 0);
                mLastX = x;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                int sonIndex = (getScrollX() + getWidth() / 2) / getWidth();

                // 如果滑动页面超过当前页面数，那么把屏index定为最大页面数的index。
                int childCount = getChildCount();
                if (sonIndex >= childCount)
                    sonIndex = childCount - 1;

                // 现在滑动的相对距离。
                int dx = sonIndex * getWidth() - getScrollX();
                // Y方向不变，X方向到目的地。
                scroller.startScroll(getScrollX(), 0, dx, 0, 500);
                invalidate();

                invalidate();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
