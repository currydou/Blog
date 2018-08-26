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
 *  可以自定义的除了scaleCount还有缩放常量mMinScale
 * Created by curry.zhang on 4/20/2017.
 */

public class MyImageView extends ImageView {

    private static final String TAG = "curry";

    private static final int SCALE_REDUCE_INIT = 0;
    //缩放中
    private static final int SCALING = 1;
    private static final int SCALE_ADD_INIT = 6;

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
    private boolean finished = true;
    private int scaleCount;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyImageView, defStyle, 0);
        scaleCount = typedArray.getInt(R.styleable.MyImageView_count, 5);
        typedArray.recycle();
    }

    /**
     * 必要的初始化
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            //控件的宽
            int mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            //控件的高
            int mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

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
                mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                break;
            case MotionEvent.ACTION_UP:
                mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 控制缩放的Handler
     */
    private Handler mScaleHandler = new Handler() {
        private Matrix matrix = new Matrix();
        //用来控制缩放几次
        private int count = 0;
        private float smallScale = (float) Math.sqrt(Math.sqrt(mMinScale));
        private float primaryScale = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
        private float currentScale = smallScale;
        /**
         * 是否已经调用了点击事件
         */
        private boolean clicked;

        public void handleMessage(Message msg) {
            matrix.set(getImageMatrix());
            switch (msg.what) {
                case SCALE_REDUCE_INIT:
                    if (finished) { //此时缩放结束
                        //设置为缩放没有结束的标志
                        finished = false;
                        count = 0;
                        //指定每次缩小的比例
                        currentScale = smallScale;
                        //发送缩小消息
                        mScaleHandler.sendEmptyMessage(SCALING);
                    } else {
                        //缩放没有结束，保留缩放消息
                        mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                    }
                    break;
                case SCALING:
                    beginScale(matrix, currentScale);
                    if (count < scaleCount) {
                        //每次缩放之后都不会主动回来，所以通过几次的缩放之后，形成动画效果
                        mScaleHandler.sendEmptyMessage(SCALING);
                    } else {
                        //达到缩放次数，设置点击事件
                        finished = true;
                        if (mOnViewClickListener != null && !clicked) {
                            //没有点击过的情况
                            clicked = true;
                            mOnViewClickListener.onViewClick(MyImageView.this);
                        } else {
                            clicked = false;
                        }
                    }
                    count++;

                    break;
                case SCALE_ADD_INIT://按下的时候恢复状态。恢复时也是通过几次恢复，形成动画效果
                    if (finished) {
                        //此时缩放结束
                        finished = false;
                        count = 0;
                        currentScale = primaryScale;
                        mScaleHandler.sendEmptyMessage(SCALING);
                    } else {
                        //同上
                        mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
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
        //（x缩放的比例，y缩放的比例，绘制的x的原点，绘制的y的原点）  (原点在新图片的左上角)
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
        void onViewClick(MyImageView view);
    }

}