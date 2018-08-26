package com.example.curryzhang.hyblog.motionevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by curry on 2018/8/26.
 * https://blog.csdn.net/yanzhenjie1003/article/details/53046027
 */

public class ScrollViewTest1 extends LinearLayout {

    private Scroller scroller;

    public ScrollViewTest1(Context context) {
        this(context,null);
    }

    public ScrollViewTest1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollViewTest1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);

    }


    int lastX;
    int lastY;
    // 手指按下时View的相对坐标。
    private int mDownViewX;
    private int mDownViewY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }

                mDownViewX = getScrollX();
                mDownViewY = getScrollY();

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                int currentX = (int) event.getRawX();
                int currentY = (int) event.getRawY();

                int dx = currentX - lastX;
                int dy = currentY - lastY;

                scrollBy(-dx, -dy);


                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                return true;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
//                scrollTo(mDownViewX, mDownViewY);
                scroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY());
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
