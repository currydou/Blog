package com.example.curryzhang.hyblog.gesturelock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.curryzhang.hyblog.R;

/**
 * ---------需要注意的点---------
 * ===总体的宫格的位置:
 * 1:viewgroup和view，viewgroup内容的方向设为垂直居中，然后计算每个view的宽度和间距，用除了第一个，
 *    其他的根据第一个的位置用addrule添加。
 * 2:第一次的锁屏是计算每个的绝对位置来布局的
 * ===圆形笔触。绘制三角形。旋转。宽高的获取方式
 */
public class GestureLockActivity extends AppCompatActivity {

    private GestureLockViewGroup mGestureLockViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);

        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        mGestureLockViewGroup.setAnswer(new int[]{1, 2, 3, 4, 5});
        mGestureLockViewGroup
                .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {

                    @Override
                    public void onUnmatchedExceedBoundary() {
                        Toast.makeText(GestureLockActivity.this, "错误5次...",
                                Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched) {
                        Toast.makeText(GestureLockActivity.this, matched + "",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBlockSelected(int cId) {
                    }
                });
    }

}
