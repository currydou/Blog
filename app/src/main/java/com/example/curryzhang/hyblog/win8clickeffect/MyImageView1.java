package com.example.curryzhang.hyblog.win8clickeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.curryzhang.hyblog.R;

/**
 * 1.如果抬起快了的话，就有可能在缩小还没有完成的时候，发送抬起的消息，如果不对这个消息保留处理，抬起效果就没了。
 * 2.自定义点击事件是因为ontouevent返回了true，true是因为我们的imagview要使用按下，抬起事件。
 * <p>
 * 可能会是个问题：
 * 1.会有惯性动画效果
 * <p>
 * 可以自定义的除了scaleCount还有缩放常量mMinScale
 * Created by curry.zhang on 4/20/2017.
 */

public class MyImageView1 extends ImageView {

    private static final String TAG = "MyImageView";

    private static final int SCALE_REDUCE_INIT = 0;
    private static final int SCALING = 1;
    private static final int SCALE_ADD_INIT = 6;

    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeight;
    /**
     * 控件的宽1/2
     */
    private int mCenterWidth;
    /**
     * 控件的高 1/2
     */
    private int mCenterHeight;
    /**
     * 设置一个缩放的常量
     */
    private float mMinScale = 0.85f;
    /**
     * 缩放是否结束
     */
    private boolean isFinish = true;

    public MyImageView1(Context context) {
        this(context, null);
    }

    public MyImageView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 必要的初始化
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

            mCenterWidth = mWidth / 2;
            mCenterHeight = mHeight / 2;

            Drawable drawable = getDrawable();
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bd.setAntiAlias(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float X = event.getX();
                float Y = event.getY();
                mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                break;
            case MotionEvent.ACTION_UP:
                mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                break;
        }
        return true;
    }

    /**
     * 控制缩放的Handler
     */
    private Handler mScaleHandler = new Handler() {
        private Matrix matrix = new Matrix();
        private int count = 0;
        private float s;
        /**
         * 是否已经调用了点击事件
         */
        private boolean isClicked;

        public void handleMessage(android.os.Message msg) {
            matrix.set(getImageMatrix());
            switch (msg.what) {
                case SCALE_REDUCE_INIT:
                    if (!isFinish) {
                        mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                    } else {
                        isFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(mMinScale));
                        beginScale(matrix, s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    break;
                case SCALING:
                    beginScale(matrix, s);
                    if (count < 4) {
                        mScaleHandler.sendEmptyMessage(SCALING);
                    } else {
                        isFinish = true;
                        if (MyImageView1.this.mOnViewClickListener != null && !isClicked) {
                            isClicked = true;
                            MyImageView1.this.mOnViewClickListener.onViewClick(MyImageView1.this);
                        } else {
                            isClicked = false;
                        }
                    }
                    count++;

                    break;
                case 6:
                    if (!isFinish) {
                        mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                    } else {
                        isFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
                        beginScale(matrix, s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    break;
            }
        }
    };

    protected void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缩放
     *
     * @param matrix
     * @param scale
     */
    private synchronized void beginScale(Matrix matrix, float scale) {
        matrix.postScale(scale, scale, mCenterWidth, mCenterHeight);
        setImageMatrix(matrix);
    }

    /**
     * 回调接口
     */
    private OnViewClickListener mOnViewClickListener;

    public void setOnClickIntent(OnViewClickListener onViewClickListener) {
        this.mOnViewClickListener = onViewClickListener;
    }

    public interface OnViewClickListener {
        void onViewClick(MyImageView1 view);
    }

}