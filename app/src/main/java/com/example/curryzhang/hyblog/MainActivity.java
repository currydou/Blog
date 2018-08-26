package com.example.curryzhang.hyblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.curryzhang.hyblog.customviewgroup.CustomViewGroupActivity;
import com.example.curryzhang.hyblog.flowlayout.FlowLayoutActivity;
import com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag.CyclicDragActivity;
import com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag.ScrollViewAddContentActivity;
import com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.fulldisplay.FullDisplayActivity;
import com.example.curryzhang.hyblog.gesturelock.GestureLockActivity;
import com.example.curryzhang.hyblog.horizontalscrollview.HorizontalScrollviewActivity;
import com.example.curryzhang.hyblog.imagematrix.ImageMatrixActivity;
import com.example.curryzhang.hyblog.motionevent.MoveEventActivity;
import com.example.curryzhang.hyblog.verticalViewGroup.VerticalViewGroupActivity;
import com.example.curryzhang.hyblog.win8clickeffect.Win8Activity;
import com.example.curryzhang.hyblog.xiaomisms.XiaoMiSMSActivity;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, VerticalViewGroupActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, XiaoMiSMSActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, GestureLockActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(MainActivity.this, Win8Activity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(MainActivity.this, HorizontalScrollviewActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(MainActivity.this, CustomViewGroupActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(MainActivity.this, FlowLayoutActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(MainActivity.this, FullDisplayActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(MainActivity.this, CyclicDragActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(MainActivity.this, ScrollViewAddContentActivity.class));
                break;
            case R.id.btn11:
                startActivity(new Intent(MainActivity.this, ImageMatrixActivity.class));
                break;
            case R.id.btn12:
                startActivity(new Intent(MainActivity.this, MoveEventActivity.class));
                break;
        }
    }

}
