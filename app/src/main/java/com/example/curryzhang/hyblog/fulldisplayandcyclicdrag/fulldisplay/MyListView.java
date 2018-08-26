package com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.fulldisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.example.curryzhang.hyblog.R;

/**
 * =======功能==========
 * 1.完整显示item
 * 2.可以屏蔽自动滚动
 * =======问题记录======
 * 发现滑动后，完全隐藏的item的颜色会变->也就是getview被执行隐藏item个数的次数-->网上查了下，setSelection会造成触发getView。
 * 链接如下。但是可能只是有关系，没有实际看过。先记一下。
 * http://blog.csdn.net/chenxin_003/article/details/49006301
 */
public class MyListView extends ListView implements OnScrollListener {
    private static final String TAG = MyListView.class.getSimpleName();

    /**
     * 每个屏幕显示多少个Item
     */
    private int mItemCountInOneScreen;
    /**
     * 每个Item的高度
     */
    private int mItemHeight;
    /**
     * 记录第一个显示的Item
     */
    private int mFirstVisibleItem;

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法中拿到自定义属性ItemCount，计算每个Item高度
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        // 获取MyListView_itemCount
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyListView, defStyle, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyListView_itemCount:
                    mItemCountInOneScreen = a.getInt(attr, 6);
                    break;
            }
        }
        a.recycle();

        // 计算每个Item高度。屏幕的密度包括状态栏！！
        mItemHeight = (outMetrics.heightPixels - getStatusHeight(context)) / mItemCountInOneScreen;//还要减去状态栏的高度？？？

        // 设置一个滚动监听
        setOnScrollListener(this);

        Log.e(TAG, mItemCountInOneScreen + "");
    }

    /**
     * 这个里面的操作时屏蔽自动滚动！！！(可以不要)
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP) {
            checkForReset();
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 滚动结束
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            checkForReset();
        }
    }

    private void checkForReset() {
        //注意：在ListView中，使用getChildAt(index)的取值，只能是当前可见区域（列表可滚动）的子项！
        //猜想1：可能是由于ListView内部维持了一个容器，保存当前屏幕上的ItemView，取出的时候就从容器中取，所以get.(0)第一个就是可见的第一个。
                            //但是如果这样的话，不会有下标越界吗，不记得随便取过，只用过get.(0)
                            //测试后，确实如上面猜想那样，但是不会越界，超过维持的索引就会返回null，看源码也是这样
        // 获取第一个Item的top
        int top = getChildAt(0).getTop();
        if (top == 0)
            return;
        // 绝对值不为0时，如果绝对值大于mItemHeight的一半，则收缩，即显示下一个Item
        if (Math.abs(top) > mItemHeight / 2) {
            this.setSelection(mFirstVisibleItem + 1);

            // this.scrollTo(x, y)
            // smoothScrollToPosition(mFirstVisibleItem - 1);
            // scrollBy(0, mItemHeight- Math.abs(top));
            // smoothScrollBy(mItemHeight- Math.abs(top), 200);

        } else {// 绝对值不为0时，如果绝对值小于于mItemHeight的一半，则展开，显示当前完整的Item
            this.setSelection(mFirstVisibleItem);

            // this.scrollTo(x, y)
            // smoothScrollBy( -Math.abs(top), 200);
            // smoothScrollByOffset(offset);
            // scrollBy(0, -Math.abs(top));
            // smoothScrollToPosition(mFirstVisibleItem);
        }
        // smoothScrollToPosition(mFirstVisibleItem);
    }

    /**
     * 滚动过程中不断记录当前显示的第一个Item
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        // Log.e(TAG, mFirstVisibleItem + "");
    }

    /**
     * 对外公布每个Item的高度
     *
     * @return
     */
    public int getItemHeight() {
        return mItemHeight;
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
            int height = Integer.parseInt(clazz.getField("status_bar_height") .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
