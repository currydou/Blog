package com.example.curryzhang.hyblog.xiaomisms;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 什么时候(哪个方法里)用getMeasuredHeight，
 * Created by curry.zhang on 4/10/2017.
 */

public class BounceScrollView extends ScrollView {

    private boolean called;
    private Callback mCallback;
    /**
     * 包含的View
     */
    private View mView;

    /**
     * 存储正常的位置
     */
    private Rect mRect = new Rect();

    /**
     * y坐标
     */
    private int lastY;
    private boolean first = true;

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 根据XML生成视图工作完成，该函数在生成视图的最后调用，在所有子视图添加完成之后，即使子类
     * 覆盖了onFinishInflate方法，也应该调用父类的方法，使该方法得以执行
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            mView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mView != null) {
            commonOnTouch(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void commonOnTouch(MotionEvent ev) {
        int action = ev.getAction();
        int currentY = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            /**
             * 跟随手指移动
             */
            case MotionEvent.ACTION_MOVE:

                int diffY = currentY - lastY;
                if (first) {
                    diffY = 0;
                    first = false;
                }
                lastY = currentY;
                //是否需要移动
                if (isNeedMove()) {
                    if (mRect.isEmpty()) {
                        //记录移动前的位置
                        mRect.set(mView.getLeft(), mView.getTop(), mView.getRight(), mView.getBottom());
                    }
                    //根据手指滑动对view重新布局
                    mView.layout(mView.getLeft(), mView.getTop() + diffY, mView.getRight(),
                            mView.getBottom() + diffY);
                    //当超过一半后设置回调
                    if (shouldCallBack(diffY)) {
                        if (mCallback != null) {
                            if (!called) {
                                called = true;
                                //当达到滑到一半的条件时，恢复
                                resetPosition();
                                mCallback.callback();
                            }
                        }
                    }
                }

                break;
            /**
             * 反弹回去
             */
            case MotionEvent.ACTION_UP:
                if (!mRect.isEmpty()) {
                    resetPosition();
                }
                break;
        }
    }

    /**
     * 当从上往下，移动距离达到一半时，回调接口
     *
     * @return
     */
    private boolean shouldCallBack(int diffY) {
        if (diffY > 0 && mView.getTop() > getHeight() / 2) {
            return true;
        }
        return false;
    }

    /**
     * 重置位置
     */
    private void resetPosition() {
        //添加一个动画；；动画也可以用scroller
        Animation animation = new TranslateAnimation(0, 0, mView.getTop(), mRect.top);
        animation.setDuration(200);
        animation.setFillAfter(true);
        mView.startAnimation(animation);
        mView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        mRect.setEmpty();
        first = true;
        called = false;
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     * <p>
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isNeedMove() {
        //mView.getMeasuredHeight(); 内容可以比父容器高度高。所以当他们的差等于scrolly时，就到了底部
        //getHeight();
        int offset = mView.getMeasuredHeight() - getHeight();//？？？？？？？？？？？？？？？？？？？？？/
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    public void setCallBack(Callback callback) {
        mCallback = callback;
    }

    interface Callback {
        void callback();
    }

}
