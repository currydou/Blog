package com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * ====内部ItemView的维持和自定义Adapter的关系=====
 * 1.自定义的Adapter只是为了外部向内部传递数据，作为一个临时的中间过渡桥梁，
 * 进来后把数据给ViewGroup的内部的数组，ItemView的增删都是用的这个数组。
 * =====界面上显示的ItemView的数量和维持的数量的问题=====
 * 1.起因：是ItemView在计算的时候会被舍去小数点，所以假如定义了四个ItemView，外加自己最后加上的一个。
 * 本来只有在滑动的时候才能显示五个ItemView，现在不滑动也会显示五个，第五个只会露出一点。
 * 问题：根据代码逻辑，刚打开界面，滑动，scrollY为0时，会在顶部添加一个ItemView，删除掉最后一个(也就是只露出一点的那个)。
 * 按理说这时候，ItemView的数量仍然会是五个。
 * 为0的时候，也就是还没有滑动，也就是下面的那一点ItemView还能看见，
 * 那么这时候到底是它被移除了还是数量变为六个了。
 * 测试；推论：测试的数量一直是五个，也就是那个ItemView确实在数组里面被移除了，但为什么仍能看到？
 * 有可能只是视图的缓存，是虚的，实际没有了，也就是滑动到不可见再滑到可见是恢复不了的。
 * 于是将到达屏幕底部时执行的addChildToLast()方法注释掉，
 * 由可见一点到不可见，再想让它可见，已经滑不到了，验证猜想。
 */
public class SingleItemScrollView extends ScrollView implements OnClickListener {

    /**
     * Item点击的回调
     */
    private OnItemClickListener mListener;

    private Adapter mAdapter;
    /**
     * 屏幕的高度
     */
    private int mScreenHeight;
    /**
     * 每个条目的高度
     */
    private int mItemHeight;
    private ViewGroup mContainer;

    /**
     * 条目总数
     */
    private int mItemCount;

    private boolean flag;

    /**
     * 适配器
     *
     * @author zhy
     */
    public static abstract class Adapter {
        public abstract View getView(SingleItemScrollView parent, int pos);

        public abstract int getCount();
    }

    /**
     * 点击的回调
     */
    public interface OnItemClickListener {
        void onItemClick(int pos, View view);
    }

    public SingleItemScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 计算屏幕的高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScreenHeight -= getStatusHeight(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //防止多次调用
        if (!flag) {
            //get(0)得到最外层的LinearLyout
            mContainer = (ViewGroup) getChildAt(0);
            //根据Adapter的方法，为容器添加Item
            if (mAdapter != null) {
                mItemCount = mAdapter.getCount();
                mItemHeight = mScreenHeight / mItemCount;
                mContainer.removeAllViews();
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    addChildView(i);
                }
            }
            addChildView(0);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在容器末尾添加一个Item
     *
     * @param i
     */
    private void addChildView(int i) {
        View item = mAdapter.getView(this, i);
        //设置参数
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        //设置Tag
        item.setTag(i);
        //添加事件
        item.setOnClickListener(this);
        mContainer.addView(item);
    }

    //getchildat()具体使用，删除方法后的操作。。。
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        flag = true;
        int action = ev.getAction();
        int scrollY = getScrollY();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "scrollY = " + scrollY);
                // 表示此时ScrollView的顶部已经达到屏幕顶部
                if (scrollY == 0) {
                    addChildToFirst();
                }
                // ScrollView的底部已经到达屏幕底部
                //这样的判断，主要是因为，担心用户设置的个数拿屏幕的高度来除并不是整除的，整数除整数难免有个四舍五入什么的，所以我设置了一个小的范围；
                if (Math.abs(scrollY - mItemHeight) <= mItemCount) {
                    addChildToLast();
                }

                break;
            case MotionEvent.ACTION_UP:
                checkForReset();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 在顶部添加一个View，并移除最后一个View
     */
    protected void addChildToFirst() {
        Log.e("TAG", "addChildToFirst");
        //1.getChildAt(mItemCount - 1)就是需要的那个View
        //2.是不是可见的ItemView有个容器，按照那个下标来取的？是的。详见MyListView的这个方法测试结论
        int pos = (Integer) mContainer.getChildAt(mItemCount - 1).getTag();
        addChildView(pos, 0);
        mContainer.removeViewAt(mContainer.getChildCount() - 1);
        //这里测试得出一个结论，ScrollView内容的滑动如果超过了内容，只会滑动到底部。
        //即如果最大的scrollY是100，scrollTo的滑动参数设置大于100的，都会跟100是一样的效果
        //当注释掉下面这行，当scrollY==0，就会发生一直闪烁的情况。因为每添加一个新的view时，scrollY都是0，所以会一直触发增加新ItemView
        scrollTo(0, mItemHeight );
    }

    /**
     * 在底部添加一个View，并移除第一个View
     */
    private void addChildToLast() {
        Log.e("TAG", "addChildToLast");
        int pos = (Integer) mContainer.getChildAt(1).getTag();
        addChildView(pos);
        mContainer.removeViewAt(0);
        scrollTo(0, 0);

    }

    /**
     * 在容器指定位置添加一个Item
     *
     * @param i
     */
    private void addChildView(int i, int index) {
        View item = mAdapter.getView(this, i);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        item.setTag(i);
        item.setOnClickListener(this);
        mContainer.addView(item, index);
    }

    /**
     * 检查当前getScrollY,显示完成Item，或者收缩此Item
     */
    private void checkForReset() {
        int val = getScrollY() % mItemHeight;
        if (val >= mItemHeight / 2) {
            smoothScrollTo(0, mItemHeight);
        } else {
            smoothScrollTo(0, 0);
        }

    }


    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public void setAdapter(Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        int pos = (Integer) v.getTag();
        if (mListener != null) {
            mListener.onItemClick(pos, v);
        }
    }

}
