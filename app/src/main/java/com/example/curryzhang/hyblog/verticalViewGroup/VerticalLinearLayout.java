package com.example.curryzhang.hyblog.verticalViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * 本身就有一些问题，以后再看
 * <p>
 * 要看的东西
 * 1.本身要实现的效果的逻辑
 * 2.自定义ViewGroup需要注意的点，跟之前比较
 * ViewGroup 是一个抽象类，onMeasure方法需要各个子类自己实现。
 * android开发探索艺术上面关于直接继承自ViewGroup的有两个例子说明，
 * 一个是LinearLayout的，一个是横向滑动的ListView的(有不足之处)
 * <p>
 * 跟书上的不太一样，如果按照书上的，在onMeasure里还要处理一些
 * <p>
 * 疑问？？？？？？？？？？：
 * invalidate()和postInvalidate();
 * <p>
 * Created by curry.zhang on 3/20/2017.
 */

public class VerticalLinearLayout extends ViewGroup {

    private static final String TAG = "VerticalLinearLayout";
    //屏幕高度
    private int mScreenHeight;
    //手指按下时的getScrollY
    private int mScrollStart;
    //手指抬起时的getScrollY
    private int mScrollEnd;
    //记录移动的Y
    private int mLastY;
    //滚动辅助类
    private Scroller mScroller;
    //是否正在滚动
    private boolean scrolling;
    //加速度检测
    private VelocityTracker mVelocityTracker;
    //记录当前页
    private int currentPage = 0;
    //是否是处于边界(用来防止速率快了的时候，在边界翻页)
    private boolean border = false;
    //
    private OnPageChangeListener mOnPageChangeListener;

    public VerticalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        //初始化
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {         //和书上不一样，不需要处理wrap_content???？？？
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            //这一行书上的横向ListView没有，onLayout基本差不多
            measureChild(childView, widthMeasureSpec, mScreenHeight);//最后一个参数。是每一个view的高度。这里每个view的高度都是一样的，如果不一样呢
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {//和书上差不多，就是指定子view的布局参数
        if (changed) {
            int childCount = getChildCount();
            //设置主布局的高度
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            lp.height = mScreenHeight * childCount;
            setLayoutParams(lp);
            //设置子布局的高度
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE) {
                    child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);//排列每个子布局
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果当前正在滚动，调用父类的onTouchEvent。自己不拦截事件
        if (scrolling) {
            return super.onTouchEvent(event);
        }

        int currentY = (int) event.getY();
        //计算速率
        obtainVelocity(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mScrollStart = getScrollY();
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                //在移动就停止正在进行的动画？？？？？？？？？？？？？？？？？？？？？？？？？？？这里好像没什么用
//                if (!mScroller.isFinished()) {
//                    mScroller.abortAnimation();
//                }
                //旧的坐标减去新的
                int diffY = mLastY - currentY;

                //边界值检查
                int scrollY = getScrollY();
                //已经到达顶端，下拉多少，就往上滚动多少
                if (diffY < 0 && scrollY <= 0) {            //边界检查好像没有用？？？？？？？按照我的想法改过了。。。。。。
                    //移动滑动距离的三分之一，所以不会超过屏幕一半，所以不会到-1页
                    diffY = (int) (diffY * 0.3f);
                    border = true;
                }
                //已经到达底部，上拉多少就往下滚多少
                if (diffY > 0 && getHeight() - getScrollY() <= mScreenHeight) {
                    diffY = (int) (diffY * 0.3f);
                    border = true;
                }

                //只要移动就有滑动效果
                scrollBy(0, diffY);
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                mScrollEnd = getScrollY();
                int dScrollY = mScrollEnd - mScrollStart;//这个应该可以用getX,getY代替的。。。。。。。。看完再看看是不是。。。。。。。。。。。。。。。。。
                if (wantScrollToNext()) {//往上滑动
                    if (shouldScrollToNext() && !border) {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }

                if (wantScrollToPre()) {//往下滑动
                    if (shouldScrollToPre() && !border) {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }

                scrolling = true;
                border = false;
                postInvalidate();
                recycleVelocity();
                break;
        }

        return true;        //..................
    }

    /**
     * 释放资源
     */
    private void recycleVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到上一页
     *
     * @return
     */
    private boolean shouldScrollToPre() {
        return -mScrollEnd + mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    private boolean wantScrollToPre() {
        return mScrollEnd < mScrollStart;
    }

    /**
     * 根据滑动距离判断是否能够滚动到下一页
     * 滑动的距离是否大于（屏幕的二分之一  或者  速率是不是达到界限）
     *
     * @return
     */
    private boolean shouldScrollToNext() {
        return mScrollEnd - mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 获取y方向的加速度
     *
     * @return
     */
    private int getVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到下一页
     *
     * @return
     */
    private boolean wantScrollToNext() {
        return mScrollEnd > mScrollStart;
    }

    /**
     * 初始化加速度检测器
     *
     * @param event
     */
    private void obtainVelocity(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    //用于弹性滑动
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //这里用于滑动的代码是固定的，只需要下面两行就行了。
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        } else {
            //这里的和滑动没关系，为了对外提供一个接口
            int position = getScrollY() / mScreenHeight;
            Log.e(TAG, "computeScroll: " + position + "," + currentPage);
            if (position != currentPage) {
                if (mOnPageChangeListener != null) {
                    currentPage = position;
                    mOnPageChangeListener.onPageChange(currentPage);
                }
            }

            scrolling = false;
        }
    }

    public interface OnPageChangeListener {
        void onPageChange(int currentPage);
    }

    /**
     * 设置回调接口
     */
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }
}
