package com.example.curryzhang.hyblog.fulldisplayandcyclicdrag.cyclicdrag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.curryzhang.hyblog.R;

import java.util.Random;

public class ScrollViewAddContentActivity extends AppCompatActivity {

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_add_content);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        final LinearLayout llTest = (LinearLayout) findViewById(R.id.llTest);
        random = new Random();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = createView();
                llTest.addView(view, 0);
            }
        });

    }

    private View createView() {
        View view = new View(ScrollViewAddContentActivity.this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        view.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        view.setLayoutParams(layoutParams);
        return view;
    }

}
